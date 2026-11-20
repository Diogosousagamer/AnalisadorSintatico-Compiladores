/* ***************************************************************
* Autores............: Davi Gabrielli Santos
                       Diogo Oliveira de Sousa
                       Gustavo Henrique Oliveira Fernandes
* Matricula..........: 202410855 / 202411226 / 202410104
* Inicio.............: 01/11/2026
* Ultima alteracao...: 20/11/2026
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
	// Variaveis e instancias
	private TelaPrincipalController controller;
	private Gramatica gramatica;
	private AnalisadorLexico analisadorLexico;
	private TabelaSLR tabelaSLR;

	/*
     * ***************************************************************
     * Metodo: AnalisadorSintatico
     * Funcao: cria uma nova instancia da classe AnalisadorSintatico
     * Parametros: TelaPrincipalController controller - instancia do controller da TelaPrincipal
     * Retorno: nenhum
     ****************************************************************/

	public AnalisadorSintatico(TelaPrincipalController controller) {
		// Inicializa o controller
		this.controller = controller;

		// Inicializa a gramatica, o analisador lexico e a tabela SLR
		gramatica = new Gramatica();
		analisadorLexico = new AnalisadorLexico(controller);
		tabelaSLR = new TabelaSLR();
	}

	/*
     * ***************************************************************
     * Metodo: analisarCodigo
     * Funcao: analisa sintaticamente um determinado codigo
     * Parametros: File codigo - codigo a ser analisado
     * Retorno: void
     ****************************************************************/

	public void analisarCodigo(File codigo) {
		// Chama o analisador lexico para fazer a analise lexica
		ArrayList<TuplaLexema> tuplas = analisadorLexico.analisarCodigo(codigo);

		// Exibe os resultados da analise lexica
		Platform.runLater(() -> controller.realizarAnaliseLexica(tuplas));

		// Inicializa a tabela de simbolos e adiciona um lexema $ para marcar o fim da cadeia
		ArrayList<EntradaTabelaSimbolos> tabelaSimbolos = analisadorLexico.getTabelaSimbolos();
		tabelaSimbolos.add(new EntradaTabelaSimbolos("$", null, -1));

		// Log que registra o progresso da analise sintatica
		StringBuilder log = new StringBuilder();

		// Pilha que armazena os lexemas a serem computados pela analise 
		Deque<String> pilha = new ArrayDeque<>();

		// Joga o estado inicial (0) no topo da pilha
		pilha.push("0");

		// Booleana que sinaliza presenca (ou nao) de erros sintaticos no codigo
		boolean temErro = false;

		// Proxima entrada da tabela de simbolos
		EntradaTabelaSimbolos proximaEntrada = null;

		// Booleana que sinaliza se o comando computado eh (ou nao) uma declaracao
		boolean ehDeclaracao = false;
		
		// Analisa cada entrada presente na tabela de simbolos
		for (int i = 0; i < tabelaSimbolos.size(); i++) {
			// Obtem a entrada contida na posicao atual da tabela de simbolos
			EntradaTabelaSimbolos e = tabelaSimbolos.get(i);

			// Obtem a entrada sucessora, desde que nao ultrapasse os limites da tabela de simbolos
			proximaEntrada = (i + 1 < tabelaSimbolos.size()) ? tabelaSimbolos.get(i + 1) : null;

			// Obtem o lexema da entrada atual
			String lex = e.getLexema();

			// Se o lexema nao for nulo
			if (lex != null) {
				// Sinaliza se eh uma declaracao ou nao
				if (lex.equals("var") || lex.equals("procedure") || lex.equals("program")) {
					ehDeclaracao = true;
				}
				else if (lex.equals(":") || lex.equals("begin")) {
					ehDeclaracao = false;
				}
			}

			// Identifica o lexema a ser submetido para analise
			String lexema = identificarLexema(e, proximaEntrada, ehDeclaracao);

			// Obtem o estado atual acessando o topo da pilha
			int estadoAtual = Integer.parseInt(pilha.peek());

			// Pesquisa a acao com base no estado e lexema identificados
			String acao = tabelaSLR.pesquisarAcao(estadoAtual, lexema);

			// Registra o lexema, o estado atual e a acao obtidos
			log.append("Lexema: ").append(lexema)
		   	   .append(" Estado atual: ").append(estadoAtual)
		       .append(" Acao: ").append(acao).append("\n");

		    // Se a acao nao for nula
			if (acao != null) {
				// Interrompe o laco se a cadeia for reconhecida
				if (acao.equals("OK")) {
					break;
				}
				else if (acao.startsWith("e")) { // Se tratar de um empilhamento
					// Extrai o estado a ser empilhado
					String novoEstado = acao.substring(1);

					// Empilha o lexema e o novo estado
					pilha.push(lexema);
					pilha.push(novoEstado);
				}
				else if (acao.startsWith("r")) { // Se a acao for uma reducao
					// Obtem o numero da regra e reduz a pilha
					int numeroRegra = Integer.parseInt(acao.substring(1));
					estadoAtual = reduzir(pilha, numeroRegra);

					// Obtem a regra e o seu lado esquerdo
					RegraProducao regra = gramatica.obterRegraProducao(numeroRegra);
					String naoTerminal = regra.getNaoTerminal();

					// Decrementa o contador para que o elemento atual seja analisado
					i--;

					// Se o estado atual existir
					if (estadoAtual != -1) {
						// Obtem o novo estado a partir da transicao na tabela SLR
						String novoEstado = Integer.toString(tabelaSLR.pesquisarTransicao(estadoAtual, naoTerminal));

						// Se o novo estado existir
						if (!novoEstado.equals("-1")) {
							// Empilha o nao terminal e o novo estado
							pilha.push(naoTerminal);
							pilha.push(novoEstado);
						}
						else {
							// Se o novo estado nao existir, registra o erro
							temErro = true;
							controller.registrarErro(new RegistroErro(e.getLinha(), "Transicao incompleta! " + lexema));
						}
					}
					else {
						// Se o estado atual nao existir, registra o erro
						temErro = true;
						controller.registrarErro(new RegistroErro(e.getLinha(), "Reducao incompleta! " + lexema));
					}
				}
			}
			else {
				// Se a acao nao existir, registra o erro
				temErro = true;
				controller.registrarErro(new RegistroErro(e.getLinha(), "Acao nao encontrada! " + lexema));
			}
		}

		// Retorna se a cadeia foi analisada (ou nao) com sucesso
		String erro = (temErro) ? "ERRO na cadeia sintatica!\n\n" : "Cadeia reconhecida!\n\n";

		// Converte o log para uma String
		final String resultado = log.toString();

		// Exibe os resultados da analise sintatica na interface
		Platform.runLater(() -> controller.imprimirResultadoSintatico(erro + resultado));

		// Exibe os erros identificados pela analise
		controller.exibirErros();
	}

	/*
     * ***************************************************************
     * Metodo: reduzir
     * Funcao: aplica a acao de reducao dentro da pilha SLR(1) e retorna
     		   um novo estado obtido
     * Parametros: Deque<String> pilha - pilha do analisador
     			   int numeroRegra - numero da regra a ser reduzida
     * Retorno: int
     ****************************************************************/

	private int reduzir(Deque<String> pilha, int numeroRegra) {
		// Obtem a regra de producao
		RegraProducao regra = gramatica.obterRegraProducao(numeroRegra);
		
		// Variavel que armazena o estado atual
		int estadoAtual = -1;

		// Se a regra de producao nao for nula
		if (regra != null) {
			// Separa os elementos gerados pela regra de producao em um vetor
			String[] producao = regra.getProducao().split(" ");

			// Obtem o tamanho da producao para saber quantos elementos deverao ser desempilhados
			int tamanho = (!regra.getProducao().equals("e")) ? producao.length * 2 : 0;

			// Desempilha os elementos ate atingir o tamanho
			for (int i = 0; i < tamanho; i++) {
				pilha.pop();
			}

			// Obtem o estado atual no topo da pilha
			estadoAtual = Integer.parseInt(pilha.peek());
		}

		// Retorna o estado atual
		return estadoAtual;
	}

	/*
     * ***************************************************************
     * Metodo: identificarLexema
     * Funcao: identifica o tipo de lexema a ser computado pela analise sintatica
     * Parametros: EntradaTabelaSimbolos entrada - entrada a ser computada
     			   EntradaTabelaSimbolos proximaEntrada - proxima entrada a ser computada
     			   boolean ehDeclaracao - sinaliza se eh uma declaracao de variavel
     * Retorno: void
     ****************************************************************/

	private String identificarLexema(EntradaTabelaSimbolos entrada, EntradaTabelaSimbolos proximaEntrada, boolean ehDeclaracao) {
		// Terminal a ser retornado para a analise
		String terminal = "";

		// Token da entrada da tabela de simbolos
		Token token = entrada.getToken();

		// Lexema da entrada da tabela de simbolos
		String lexema = entrada.getLexema();

		// Retorna o lexema se ele nao for nulo e indicar o fim da cadeia ($)
		if (lexema != null && lexema.equals("$")) {
			return lexema;
		}

		// Se o token e o lexema nao forem nulos
		if (token != null && lexema != null) {
			// O terminal eh retornado com base no token da entrada
			switch (token) {
				case IDENTIFICADOR:
					// Se for uma declaracao
					if (ehDeclaracao) {
						// Retorna "ident"
						terminal = "ident";
					}
					else if (proximaEntrada != null && proximaEntrada.getLexema().equals("(")) {
						// Retorna "proc" se o identificador for sucedido por um abre parenteses,
						// sinalizando declaracao de metodo
						terminal = "proc";
					}
					else {
						// Para os demais casos, retorna "variavel"
						terminal = "variavel";
					}

					break;

				case PALAVRA_RESERVADA:
					// Se for uma declaracao de tipo (inteiro/real/char/string), retorna "tipo"		
					if (lexema.equals("integer") || lexema.equals("real") || lexema.equals("char") || lexema.equals("string")) {
						terminal = "tipo";
					}
					else {
						// Para os demais casos, retorna o proprio lexema
						terminal = lexema;
					}

					break;

				case CONSTANTE_NUMERICA:
					// Retorna "numero"
					terminal = "numero";
					break;

				case STRING:
					// Retorna "variavel"
					terminal = "variavel";
					break;

				case SIMBOLO_ESPECIAL:
				case OPERADOR_ARITMETICO:
				case OPERADOR_LOGICO:
				case OPERADOR_RELACIONAL:
				case OPERADOR_ATRIBUICAO:
					// Para os demais tipos de tokens, retorna o proprio lexema
					terminal = lexema;
					break;

			}
		}

		// Retorna o terminal obtido
		return terminal;
	}
}