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
		StringBuilder log = new StringBuilder();

		Deque<String> pilha = new ArrayDeque<>();
		pilha.push("0");

		boolean temErro = false;
		EntradaTabelaSimbolos proximaEntrada = null;

		boolean ehDeclaracao = false;
		
		for (int i = 0; i < tabelaSimbolos.size(); i++) {
			EntradaTabelaSimbolos e = tabelaSimbolos.get(i);
			proximaEntrada = (i + 1 < tabelaSimbolos.size()) ? tabelaSimbolos.get(i + 1) : null;

			String lex = e.getLexema();

			if (lex != null) {
				if (lex.equals("var") || lex.equals("procedure") || lex.equals("program")) {
					ehDeclaracao = true;
				}
				else if (lex.equals(":") || lex.equals("begin")) {
					ehDeclaracao = false;
				}
			}

			String lexema = identificarLexema(e, proximaEntrada, ehDeclaracao);

			int estadoAtual = Integer.parseInt(pilha.peek());
			String acao = tabelaSLR.pesquisarAcao(estadoAtual, lexema);
			final int estado = estadoAtual;

			log.append("Lexema: ").append(lexema)
		   	   .append(" Estado atual: ").append(estadoAtual)
		       .append(" Acao: ").append(acao).append("\n");

			if (acao != null) {
				if (acao.equals("OK")) {
					if (!temErro) log.append("\nCadeia reconhecida!");
					break;
				}
				else if (acao.startsWith("e")) {
					pilha.push(lexema);
					String novoEstado = acao.substring(1);
					pilha.push(novoEstado);
				}
				else if (acao.startsWith("r")) {
					int numeroRegra = Integer.parseInt(acao.substring(1));
					estadoAtual = reduzir(pilha, numeroRegra);
					RegraProducao regra = gramatica.obterRegraProducao(numeroRegra);
					String naoTerminal = regra.getNaoTerminal();
					i--;

					if (estadoAtual != -1) {
						String novoEstado = Integer.toString(tabelaSLR.pesquisarTransicao(estadoAtual, naoTerminal));

						if (!novoEstado.equals("-1")) {
							pilha.push(naoTerminal);
							pilha.push(novoEstado);
						}
						else {
							temErro = true;
							controller.registrarErro(new RegistroErro(e.getLinha(), "Transicao incompleta! " + lexema));
						}
					}
					else {
						temErro = true;
						controller.registrarErro(new RegistroErro(e.getLinha(), "Reducao incompleta! " + lexema));
					}
				}
			}
			else {
				temErro = true;
				controller.registrarErro(new RegistroErro(e.getLinha(), "Acao nao encontrada! " + lexema));
			}
		}

		if (temErro) log.append("\nERRO na cadeia sintatica!");
		final String resultado = log.toString();
		Platform.runLater(() -> controller.imprimirResultadoSintatico(resultado));
		controller.exibirErros();
	}

	private int reduzir(Deque<String> pilha, int numeroRegra) {
		RegraProducao regra = gramatica.obterRegraProducao(numeroRegra);
		int estadoAtual = -1;

		if (regra != null) {
			String[] producao = regra.getProducao().split(" ");
			int tamanho = (!regra.getProducao().equals("e")) ? producao.length * 2 : 0;

			for (int i = 0; i < tamanho; i++) {
				pilha.pop();
			}

			estadoAtual = Integer.parseInt(pilha.peek());
		}

		return estadoAtual;
	}

	private String identificarLexema(EntradaTabelaSimbolos entrada, EntradaTabelaSimbolos proximaEntrada, boolean ehDeclaracao) {
		String terminal = "";
		Token token = entrada.getToken();
		String lexema = entrada.getLexema();

		if (lexema != null && lexema.equals("$")) {
			return lexema;
		}

		if (token != null && lexema != null) {
			switch (token) {
				case IDENTIFICADOR:
					if (ehDeclaracao) {
						terminal = "ident";
					}
					else if (proximaEntrada != null && proximaEntrada.getLexema().equals("(")) {
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
		}

		return terminal;
	}
}