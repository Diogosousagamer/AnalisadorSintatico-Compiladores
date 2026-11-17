/* ***************************************************************
* Autores............: Davi Gabrielli Santos
                       Diogo Oliveira de Sousa
                       Gustavo Henrique Oliveira Fernandes
* Matricula..........: 202410855 / 202411226 / 202410104
* Inicio.............: 11/11/2026
* Ultima alteracao...: 11/11/2026
* Nome...............: RegistroErro
* Funcao.............: Classe que representa um erro registrado durante 
					   a analise de um codigo.
                     
*************************************************************** */

package model;

public class RegistroErro {
	private int linha;
	private String erro;

	public RegistroErro(int linha, String erro) {
		this.linha = linha;
		this.erro = erro;
	}

	public int getLinha() {
		return linha;
	}

	public String getErro() {
		return erro;
	}
}