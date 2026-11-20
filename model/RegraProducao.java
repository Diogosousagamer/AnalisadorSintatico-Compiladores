/* ***************************************************************
* Autores............: Davi Gabrielli Santos
                       Diogo Oliveira de Sousa
                       Gustavo Henrique Oliveira Fernandes
* Matricula..........: 202410855 / 202411226 / 202410104
* Inicio.............: 11/11/2026
* Ultima alteracao...: 20/11/2026
* Nome...............: RegraProducao
* Funcao.............: Classe que representa uma regra de producao da
					   gramatica do Mini Pascal.
                     
*************************************************************** */

package model;

public class RegraProducao {
	// Variaveis e instancias
	private String naoTerminal;
	private String producao;

	/*
     * ***************************************************************
     * Metodo: RegraProducao
     * Funcao: cria uma nova instancia da classe RegraProducao
     * Parametros: String naoTerminal - lado esquerdo da regra de producao
     			   String producao - lado direito da regra de producao
     * Retorno: nenhum
     ****************************************************************/

	public RegraProducao(String naoTerminal, String producao) {
		this.naoTerminal = naoTerminal;
		this.producao = producao;
	}

	/*
     * ***************************************************************
     * Metodo: getNaoTerminal
     * Funcao: retorna o lado esquerdo (nao terminal) da regra de producao
     * Parametros: nenhum parametro foi definido para esta funcao
     * Retorno: String
     ****************************************************************/

	public String getNaoTerminal() {
		return naoTerminal;
	}

	/*
     * ***************************************************************
     * Metodo: getProducao
     * Funcao: retorna o lado direito (itens gerados) da regra de producao
     * Parametros: nenhum parametro foi definido para esta funcao
     * Retorno: String
     ****************************************************************/

	public String getProducao() {
		return producao;
	} 
}