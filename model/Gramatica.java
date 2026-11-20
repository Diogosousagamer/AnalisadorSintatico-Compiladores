/* ***************************************************************
* Autores............: Davi Gabrielli Santos
                       Diogo Oliveira de Sousa
                       Gustavo Henrique Oliveira Fernandes
* Matricula..........: 202410855 / 202411226 / 202410104
* Inicio.............: 01/11/2026
* Ultima alteracao...: 20/11/2026
* Nome...............: Gramatica
* Funcao.............: Classe que representa a gramatica da linguagem Mini Pascal.
                     
*************************************************************** */

package model;

import java.util.Hashtable;

public class Gramatica {
	// Variaveis e instancias
	private Hashtable<Integer, RegraProducao> regrasProducao;

	/*
     * ***************************************************************
     * Metodo: Gramatica
     * Funcao: inicializa uma nova instancia da classe Gramatica
     * Parametros: nenhum parametro foi definido para esta funcao
     * Retorno: nenhum
     ****************************************************************/

	public Gramatica() {
		// Inicializa a gramatica
		this.inicializarGramatica();
	}

	/*
     * ***************************************************************
     * Metodo: inicializarGramatica
     * Funcao: inicializa a gramatica da linguagem
     * Parametros: nenhum parametro foi definido para esta funcao
     * Retorno: void
     ****************************************************************/

	private void inicializarGramatica() {
		// Inicializa a tabela de regras de producao
		regrasProducao = new Hashtable<>();

		// Inicializa as regras de producao da gramatica aumentada (Simbolo P' extra)
		regrasProducao.putIfAbsent(0, new RegraProducao("P'", "P"));
		regrasProducao.putIfAbsent(1, new RegraProducao("P", "program ident ; B ."));
		regrasProducao.putIfAbsent(2, new RegraProducao("B", "VAR_LIST PROC_LIST C_SEC"));
		regrasProducao.putIfAbsent(3, new RegraProducao("VAR_LIST", "var VAR_DECL_LIST"));
		regrasProducao.putIfAbsent(4, new RegraProducao("VAR_LIST", "e"));
		regrasProducao.putIfAbsent(5, new RegraProducao("VAR_DECL_LIST", "VAR_DECL ; VAR_DECL_LIST"));
		regrasProducao.putIfAbsent(6, new RegraProducao("VAR_DECL_LIST", "VAR_DECL ;"));
		regrasProducao.putIfAbsent(7, new RegraProducao("VAR_DECL", "ID_LIST : tipo"));
		regrasProducao.putIfAbsent(8, new RegraProducao("ID_LIST", "ident , ID_LIST"));
		regrasProducao.putIfAbsent(9, new RegraProducao("ID_LIST", "ident"));
		regrasProducao.putIfAbsent(10, new RegraProducao("PROC_LIST", "PROC PROC_LIST"));
		regrasProducao.putIfAbsent(11, new RegraProducao("PROC_LIST", "e"));
		regrasProducao.putIfAbsent(12, new RegraProducao("PROC", "procedure ident PA_LIST ; B ;"));
		regrasProducao.putIfAbsent(13, new RegraProducao("PA_LIST", "( PA_DECL_LIST )"));
		regrasProducao.putIfAbsent(14, new RegraProducao("PA_LIST", "e"));
		regrasProducao.putIfAbsent(15, new RegraProducao("PA_DECL_LIST", "PA_DECL ; PA_DECL_LIST"));
		regrasProducao.putIfAbsent(16, new RegraProducao("PA_DECL_LIST", "PA_DECL"));
		regrasProducao.putIfAbsent(17, new RegraProducao("PA_DECL", "VAR_OPT ID_LIST : tipo"));
		regrasProducao.putIfAbsent(18, new RegraProducao("VAR_OPT", "var"));
		regrasProducao.putIfAbsent(19, new RegraProducao("VAR_OPT", "e"));
		regrasProducao.putIfAbsent(20, new RegraProducao("C_SEC", "begin C_LIST end"));
		regrasProducao.putIfAbsent(21, new RegraProducao("C_LIST", "C ; C_LIST"));
		regrasProducao.putIfAbsent(22, new RegraProducao("C_LIST", "e"));
		regrasProducao.putIfAbsent(23, new RegraProducao("C", "C_FECHADO"));
		regrasProducao.putIfAbsent(24, new RegraProducao("C", "C_ABERTO"));
		regrasProducao.putIfAbsent(25, new RegraProducao("C_FECHADO", "VAR_ATR"));
		regrasProducao.putIfAbsent(26, new RegraProducao("C_FECHADO", "PROC_CA"));
		regrasProducao.putIfAbsent(27, new RegraProducao("C_FECHADO", "FOR_FECHADO"));
		regrasProducao.putIfAbsent(28, new RegraProducao("C_FECHADO", "W_FECHADO"));
		regrasProducao.putIfAbsent(29, new RegraProducao("C_FECHADO", "C_SEC"));
		regrasProducao.putIfAbsent(30, new RegraProducao("C_FECHADO", "if E then CORPO_FECHADO else CORPO_FECHADO"));
		regrasProducao.putIfAbsent(31, new RegraProducao("C_FECHADO", "if E then CORPO_FECHADO ; else CORPO_FECHADO"));
		regrasProducao.putIfAbsent(32, new RegraProducao("C_ABERTO", "if E then C"));
		regrasProducao.putIfAbsent(33, new RegraProducao("C_ABERTO", "if E then C_FECHADO else C_ABERTO"));
		regrasProducao.putIfAbsent(34, new RegraProducao("C_ABERTO", "FOR_ABERTO"));
		regrasProducao.putIfAbsent(35, new RegraProducao("C_ABERTO", "W_ABERTO"));
		regrasProducao.putIfAbsent(36, new RegraProducao("CORPO_FECHADO", "C_FECHADO"));
		regrasProducao.putIfAbsent(37, new RegraProducao("CORPO_FECHADO", "e"));
		regrasProducao.putIfAbsent(38, new RegraProducao("VAR_ATR", "variavel := EXPR"));
		regrasProducao.putIfAbsent(39, new RegraProducao("PROC_CA", "proc PROC_ARGS"));
		regrasProducao.putIfAbsent(40, new RegraProducao("PROC_ARGS", "( E_LIST )"));
		regrasProducao.putIfAbsent(41, new RegraProducao("PROC_ARGS", "e"));
		regrasProducao.putIfAbsent(42, new RegraProducao("E_LIST", "EXPR , E_LIST"));
		regrasProducao.putIfAbsent(43, new RegraProducao("E_LIST", "EXPR"));
		regrasProducao.putIfAbsent(44, new RegraProducao("FOR_FECHADO", "for VAR_ATR to F do C_FECHADO"));
		regrasProducao.putIfAbsent(45, new RegraProducao("FOR_ABERTO", "for VAR_ATR to F do C_ABERTO"));
		regrasProducao.putIfAbsent(46, new RegraProducao("W_FECHADO", "while E do C_FECHADO"));
		regrasProducao.putIfAbsent(47, new RegraProducao("W_ABERTO", "while E do C_ABERTO"));
		regrasProducao.putIfAbsent(48, new RegraProducao("EXPR", "EXPR LOG_OP_OR_XOR LOG_TERM"));
		regrasProducao.putIfAbsent(49, new RegraProducao("EXPR", "LOG_TERM"));
		regrasProducao.putIfAbsent(50, new RegraProducao("LOG_TERM", "LOG_TERM and E"));
		regrasProducao.putIfAbsent(51, new RegraProducao("LOG_TERM", "E"));
		regrasProducao.putIfAbsent(52, new RegraProducao("E", "SI_E REL_OP SI_E"));
		regrasProducao.putIfAbsent(53, new RegraProducao("E", "SI_E"));
		regrasProducao.putIfAbsent(54, new RegraProducao("SI_E", "SIGN_OPT SI_E_TAIL"));
		regrasProducao.putIfAbsent(55, new RegraProducao("SI_E_TAIL", "SI_E_TAIL ADD_OP TERM"));
		regrasProducao.putIfAbsent(56, new RegraProducao("SI_E_TAIL", "TERM"));
		regrasProducao.putIfAbsent(57, new RegraProducao("TERM", "TERM MULT_OP F"));
		regrasProducao.putIfAbsent(58, new RegraProducao("TERM", "F"));
		regrasProducao.putIfAbsent(59, new RegraProducao("F", "numero"));
		regrasProducao.putIfAbsent(60, new RegraProducao("F", "variavel"));
		regrasProducao.putIfAbsent(61, new RegraProducao("F", "( EXPR )"));
		regrasProducao.putIfAbsent(62, new RegraProducao("F", "not F"));
		regrasProducao.putIfAbsent(63, new RegraProducao("SIGN_OPT", "+"));
		regrasProducao.putIfAbsent(64, new RegraProducao("SIGN_OPT", "-"));
		regrasProducao.putIfAbsent(65, new RegraProducao("SIGN_OPT", "e"));
		regrasProducao.putIfAbsent(66, new RegraProducao("ADD_OP", "+"));
		regrasProducao.putIfAbsent(67, new RegraProducao("ADD_OP", "-"));
		regrasProducao.putIfAbsent(68, new RegraProducao("MULT_OP", "*"));
		regrasProducao.putIfAbsent(69, new RegraProducao("MULT_OP", "/"));
		regrasProducao.putIfAbsent(70, new RegraProducao("MULT_OP", "%"));
		regrasProducao.putIfAbsent(71, new RegraProducao("REL_OP", "="));
		regrasProducao.putIfAbsent(72, new RegraProducao("REL_OP", "<"));
		regrasProducao.putIfAbsent(73, new RegraProducao("REL_OP", ">"));
		regrasProducao.putIfAbsent(74, new RegraProducao("REL_OP", "<="));
		regrasProducao.putIfAbsent(75, new RegraProducao("REL_OP", ">="));
		regrasProducao.putIfAbsent(76, new RegraProducao("REL_OP", "<>"));
		regrasProducao.putIfAbsent(77, new RegraProducao("LOG_OP_OR_XOR", "or"));
		regrasProducao.putIfAbsent(78, new RegraProducao("LOG_OP_OR_XOR", "xor"));
	}

	/*
     * ***************************************************************
     * Metodo: obterRegraProducao
     * Funcao: obtem uma regra de producao da gramatica a partir do numero desta
     * Parametros: int numeroRegra - numero da regra a ser selecionada
     * Retorno: RegraProducao
     ****************************************************************/

	public RegraProducao obterRegraProducao(int numeroRegra) {
		return regrasProducao.get(numeroRegra);
	}
}