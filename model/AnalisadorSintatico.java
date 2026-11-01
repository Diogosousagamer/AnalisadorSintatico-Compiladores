/* ***************************************************************
* Autores............: Davi Gabrielli Santos
                       Diogo Oliveira de Sousa
                       Gustavo Henrique Oliveira Fernandes
* Matricula..........: 202410855 / 202411226 / 202410104
* Inicio.............: 21/10/2026
* Ultima alteracao...: 21/10/2026
* Nome...............: AnalisadorSintatico
* Funcao.............: Classe que designa as operacoes do analisador sintatico de um compilador.
                     
*************************************************************** */

package model;

public class AnalisadorSintatico {
	private Gramatica gramatica;
	private AnalisadorLexico analisadorLexico;
	private TabelaSLR tabelaSLR;

	public AnalisadorSintatico() {
		gramatica = new Gramatica();
		analisadorLexico = new AnalisadorLexico();
		tabelaSLR = new TabelaSLR();
	}
}