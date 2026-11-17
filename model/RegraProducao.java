/* ***************************************************************
* Autores............: Davi Gabrielli Santos
                       Diogo Oliveira de Sousa
                       Gustavo Henrique Oliveira Fernandes
* Matricula..........: 202410855 / 202411226 / 202410104
* Inicio.............: 11/11/2026
* Ultima alteracao...: 11/11/2026
* Nome...............: RegraProducao
* Funcao.............: Classe que representa uma regra de producao da
					   gramatica do Mini Pascal.
                     
*************************************************************** */

package model;

public class RegraProducao {
	// Variaveis e instancias
	private String naoTerminal;
	private String producao;

	public RegraProducao(String naoTerminal, String producao) {
		this.naoTerminal = naoTerminal;
		this.producao = producao;
	}

	public String getNaoTerminal() {
		return naoTerminal;
	}

	public String getProducao() {
		return producao;
	} 
}