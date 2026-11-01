/* ***************************************************************
* Autores............: Davi Gabrielli Santos
                       Diogo Oliveira de Sousa
                       Gustavo Henrique Oliveira Fernandes
* Matricula..........: 202410855 / 202411226 / 202410104
* Inicio.............: 21/10/2026
* Ultima alteracao...: 21/10/2026
* Nome...............: Gramatica
* Funcao.............: Classe que representa a gramatica da linguagem Mini Pascal.
                     
*************************************************************** */

package model;

import java.util.Hashtable;

public class Gramatica {
	private Hashtable<Integer, RegraProducao> regrasProducao;

	public Gramatica() {
		this.inicializarGramatica();
	}

	private void inicializarGramatica() {
		regrasProducao = new RegraProducao<>();

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
	    regrasProducao.putIfAbsent(13, new RegraProducao("PA_LIST", "(PA_DECL_LIST)"));
	    regrasProducao.putIfAbsent(14, new RegraProducao("PA_LIST", "e"));
	    regrasProducao.putIfAbsent(15, new RegraProducao("PA_DECL_LIST", "PA_DECL ; PA_DECL_LIST"));
	    regrasProducao.putIfAbsent(16, new RegraProducao("PA_DECL_LIST", "PA_DECL"));
	    regrasProducao.putIfAbsent(17, new RegraProducao("PA_DECL", "VAR_OPT ID_LIST : tipo"));
	    regrasProducao.putIfAbsent(18, new RegraProducao("VAR_OPT", "var"));
	    regrasProducao.putIfAbsent(19, new RegraProducao("VAR_OPT", "e"));
	    regrasProducao.putIfAbsent(20, new RegraProducao("C_SEC", "begin C_LIST end"));
	    regrasProducao.putIfAbsent(21, new RegraProducao("C_LIST", "C ; C_LIST"));
	    regrasProducao.putIfAbsent(22, new RegraProducao("C_LIST", "e"));
	    regrasProducao.putIfAbsent(23, new RegraProducao("C", "VAR_ATR"));
	    regrasProducao.putIfAbsent(24, new RegraProducao("C", "PROC_CA"));
	    regrasProducao.putIfAbsent(25, new RegraProducao("C", "I"));
	    regrasProducao.putIfAbsent(26, new RegraProducao("C", "FOR"));
	    regrasProducao.putIfAbsent(27, new RegraProducao("C", "W"));
	    regrasProducao.putIfAbsent(28, new RegraProducao("C", "C_SEC"));
	    regrasProducao.putIfAbsent(29, new RegraProducao("VAR_ATR", "variavel := EXPR"));
	    regrasProducao.putIfAbsent(30, new RegraProducao("PROC_CA", "proc PROC_ARGS"));
	    regrasProducao.putIfAbsent(31, new RegraProducao("PROC_ARGS", "(E_LIST)"));
	    regrasProducao.putIfAbsent(32, new RegraProducao("PROC_ARGS", "e"));
	    regrasProducao.putIfAbsent(33, new RegraProducao("E_LIST", "EXPR , E_LIST"));
	    regrasProducao.putIfAbsent(34, new RegraProducao("E_LIST", "EXPR"));
	    regrasProducao.putIfAbsent(35, new RegraProducao("I", "if E then C ELSE_OPT"));
	    regrasProducao.putIfAbsent(36, new RegraProducao("ELSE_OPT", "else C"));
	    regrasProducao.putIfAbsent(37, new RegraProducao("ELSE_OPT", "e"));
	    regrasProducao.putIfAbsent(38, new RegraProducao("FOR", "for VAR_ATR to F do C"));
	    regrasProducao.putIfAbsent(39, new RegraProducao("W", "while E do C"));
	    regrasProducao.putIfAbsent(40, new RegraProducao("EXPR", "EXPR LOG_OP_OR_XOR LOG_TERM"));
	    regrasProducao.putIfAbsent(41, new RegraProducao("EXPR", "LOG_TERM"));
	    regrasProducao.putIfAbsent(42, new RegraProducao("LOG_TERM", "LOG_TERM and E"));
	    regrasProducao.putIfAbsent(43, new RegraProducao("LOG_TERM", "E"));
	    regrasProducao.putIfAbsent(44, new RegraProducao("E", "SI_E REL_OP SI_E"));
	    regrasProducao.putIfAbsent(45, new RegraProducao("E", "SI_E"));
	    regrasProducao.putIfAbsent(46, new RegraProducao("SI_E", "SIGN_OPT SI_E_TAIL"));
	    regrasProducao.putIfAbsent(47, new RegraProducao("SI_E_TAIL", "SI_E_TAIL ADD_OP TERM"));
	    regrasProducao.putIfAbsent(48, new RegraProducao("SI_E_TAIL", "TERM"));
	    regrasProducao.putIfAbsent(49, new RegraProducao("TERM", "TERM MULT_OP F"));
	    regrasProducao.putIfAbsent(50, new RegraProducao("TERM", "F"));
	    regrasProducao.putIfAbsent(51, new RegraProducao("F", "numero"));
	    regrasProducao.putIfAbsent(52, new RegraProducao("F", "variavel"));
	    regrasProducao.putIfAbsent(53, new RegraProducao("F", "(EXPR)"));
	    regrasProducao.putIfAbsent(54, new RegraProducao("F", "not F"));
	    regrasProducao.putIfAbsent(55, new RegraProducao("SIGN_OPT", "+"));
	    regrasProducao.putIfAbsent(56, new RegraProducao("SIGN_OPT", "-"));
	    regrasProducao.putIfAbsent(57, new RegraProducao("SIGN_OPT", "e"));
	    regrasProducao.putIfAbsent(58, new RegraProducao("ADD_OP", "+"));
	    regrasProducao.putIfAbsent(59, new RegraProducao("ADD_OP", "-"));
	    regrasProducao.putIfAbsent(60, new RegraProducao("MULT_OP", "*"));
	    regrasProducao.putIfAbsent(61, new RegraProducao("MULT_OP", "/"));
	    regrasProducao.putIfAbsent(62, new RegraProducao("MULT_OP", "%"));
	    regrasProducao.putIfAbsent(63, new RegraProducao("REL_OP", "="));
	    regrasProducao.putIfAbsent(64, new RegraProducao("REL_OP", "<"));
	    regrasProducao.putIfAbsent(65, new RegraProducao("REL_OP", ">"));
	    regrasProducao.putIfAbsent(66, new RegraProducao("REL_OP", "<="));
	    regrasProducao.putIfAbsent(67, new RegraProducao("REL_OP", ">="));
	    regrasProducao.putIfAbsent(68, new RegraProducao("REL_OP", "<>"));
	    regrasProducao.putIfAbsent(69, new RegraProducao("LOG_OP_OR_XOR", "or"));
	    regrasProducao.putIfAbsent(70, new RegraProducao("LOG_OP_OR_XOR", "xor"));
	}

	class RegraProducao {
		private String naoTerminal;
		private String producao;

		public RegraProducao(String naoTerminal, String producao) {
			this.naoTerminal = naoTerminal;
			this.producao = producao;
		}
	}
}