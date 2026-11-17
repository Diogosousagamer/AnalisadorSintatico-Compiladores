/* ***************************************************************
* Autores............: Davi Gabrielli Santos
                       Diogo Oliveira de Sousa
                       Gustavo Henrique Oliveira Fernandes
* Matricula..........: 202410855 / 202411226 / 202410104
* Inicio.............: 01/11/2026
* Ultima alteracao...: 17/11/2026
* Nome...............: AnalisadorSintatico
* Funcao.............: Classe que designa as operacoes do analisador sintatico de um compilador.
                     
*************************************************************** */

package model;

import controller.TelaPrincipalController;
import java.io.File;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Deque;
import javafx.application.Platform;

public class AnalisadorSintatico {
	private TelaPrincipalController controller;
	private Gramatica gramatica;
	private AnalisadorLexico analisadorLexico;
	private TabelaSLR tabelaSLR;

	public AnalisadorSintatico(TelaPrincipalController controller) {
		this.controller = controller;

		gramatica = new Gramatica();
		analisadorLexico = new AnalisadorLexico(controller);
		tabelaSLR = new TabelaSLR();
	}

	public void analisarCodigo(File codigo) {
		ArrayList<TuplaLexema> tuplas = analisadorLexico.analisarCodigo(codigo);
		Platform.runLater(() -> controller.realizarAnaliseLexica(tuplas));

		ArrayList<EntradaTabelaSimbolos> tabelaSimbolos = analisadorLexico.getTabelaSimbolos();
		tabelaSimbolos.add(new EntradaTabelaSimbolos("$", null, -1));

		Deque<String> pilha = new ArrayDeque<>();
		pilha.push("0");

		boolean temErro = false;
		EntradaTabelaSimbolos ultimaEntrada = null;
		EntradaTabelaSimbolos proximaEntrada = null;
		
		for (int i = 0; i < tabelaSimbolos.size(); i++) {
			EntradaTabelaSimbolos e = tabelaSimbolos.get(i);
			proximaEntrada = tabelaSimbolos.get(i + 1);
			String lexema = identificarLexema(e, ultimaEntrada, proximaEntrada);
			int estadoAtual = Integer.parseInt(pilha.peek());
			String acao = tabelaSLR.pesquisarAcao(estadoAtual, lexema);

			if (acao != null) {
				if (acao.equals("OK")) {
					if (!temErro) Platform.runLater(() -> controller.imprimirResultadoSintatico("Cadeia reconhecida!"));
					break;
				}
				else if (acao.startsWith("e")) {
					pilha.push(lexema);
					String novoEstado = acao.substring(1);
					pilha.push(novoEstado);
					ultimaEntrada = e;
				}
				else if (acao.startsWith("r")) {
					int numeroRegra = Integer.parseInt(acao.substring(1));
					estadoAtual = reduzir(pilha, numeroRegra);
					String naoTerminal = pilha.peek();
					i--;

					if (estadoAtual != -1) {
						String novoEstado = Integer.toString(tabelaSLR.pesquisarTransicao(estadoAtual, naoTerminal));

						if (novoEstado != null) {
							pilha.push(novoEstado);
							ultimaEntrada = e;
						}
						else {
							temErro = true;
							controller.registrarErro(new RegistroErro(e.getLinha(), "Transição incompleta! " + lexema));
						}
					}
					else {
						temErro = true;
						controller.registrarErro(new RegistroErro(e.getLinha(), "Redução incompleta! " + lexema));
					}
				}
			}
			else {
				temErro = true;
				controller.registrarErro(new RegistroErro(e.getLinha(), "Ação não encontrada! " + lexema));
			}
		}

		if (temErro) Platform.runLater(() -> controller.imprimirResultadoSintatico("ERRO na cadeia sintática!"));
		controller.exibirErros();
	}

	private int reduzir(Deque<String> pilha, int numeroRegra) {
		RegraProducao regra = gramatica.obterRegraProducao(numeroRegra);
		int estadoAtual = -1;

		if (regra != null) {
			String[] producao = regra.getProducao().split(" ");
			int tamanho = producao.length * 2;

			for (int i = 0; i < tamanho; i++) {
				pilha.pop();
			}

			estadoAtual = Integer.parseInt(pilha.peek());
			pilha.push(regra.getNaoTerminal());
		}

		return estadoAtual;
	}

	private String identificarLexema(EntradaTabelaSimbolos entrada, EntradaTabelaSimbolos ultimaEntrada, EntradaTabelaSimbolos proximaEntrada) {
		String terminal = "";
		Token token = entrada.getToken();
		String lexema = entrada.getLexema();

		switch (token) {
			case IDENTIFICADOR:
				if (ultimaEntrada != null && ultimaEntrada.getToken() == Token.PALAVRA_RESERVADA && 
					(ultimaEntrada.getLexema().equals("var") 
					|| ultimaEntrada.getLexema().equals("procedure")
				    || ultimaEntrada.getLexema().equals("program"))) {
					terminal = "ident";
				}
				else if (proximaEntrada != null && (proximaEntrada.getLexema().equals("(") 
					|| proximaEntrada.getLexema().equals(""))) {
					terminal = "proc";
				}
				else {
					terminal = "variavel";
				}

				break;

			case PALAVRA_RESERVADA:
				if (lexema.equals("integer") || lexema.equals("real")) {
					terminal = "tipo";
				}
				else {
					terminal = lexema;
				}

				break;

			case CONSTANTE_NUMERICA:
				terminal = "numero";
				break;

			case SIMBOLO_ESPECIAL:
			case OPERADOR_ARITMETICO:
			case OPERADOR_LOGICO:
			case OPERADOR_RELACIONAL:
			case OPERADOR_ATRIBUICAO:
				terminal = lexema;
				break;

		}

		return terminal;
	}
}