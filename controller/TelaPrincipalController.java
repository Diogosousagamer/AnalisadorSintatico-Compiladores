/* ***************************************************************
* Autores............: Davi Gabrielli Santos
                       Diogo Oliveira de Sousa
                       Gustavo Henrique Oliveira Fernandes
* Matricula..........: 202410855 / 202411226 / 202410104
* Inicio.............: 21/10/2026
* Ultima alteracao...: 20/11/2026
* Nome...............: TelaPrincipalController
* Funcao.............: Classe que gerencia as operacoes da TelaPrincipal.
                     
*************************************************************** */

package controller;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.AnalisadorSintatico;
import model.RegistroErro;
import model.Token;
import model.TuplaLexema;

public class TelaPrincipalController {
	// Componentes da interface
	@FXML private Button btnAbrirArquivo;
	@FXML private Button btnExcluirArquivo;
	@FXML private Button btnVoltar;
	@FXML private TextArea txtCodigo;
	@FXML private TextArea txtErros;
	@FXML private TextArea txtLexico;
	@FXML private TextArea txtSintatico;

	// Variaveis e instancias
	private AnalisadorSintatico analisadorSintatico;
	private File codigo;
	private File arquivoSaida;
	private ArrayList<RegistroErro> erros = new ArrayList<>();

	/*
     * ***************************************************************
     * Metodo: voltar
     * Funcao: volta para a tela inicial (TelaMenu)
     * Parametros: ActionEvent event - evento gerado ao clicar no botao
     * Retorno: void
     ****************************************************************/

	@FXML
	private void voltar(ActionEvent event) throws IOException {
		// Carrega o arquivo FXML da TelaPrincipal
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaMenu.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);

		// Obtem a janela e troca a cena (tela)
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);

		// Mantem a tela maximizada
		stage.setMaximized(false);
		stage.setMaximized(true);
	}

	/*
     * ***************************************************************
     * Metodo: abrirArquivo
     * Funcao: abre o arquivo contendo os lexemas e tokens do programa
     * Parametros: ActionEvent event - evento gerado ao clicar no botao
     * Retorno: void
     ****************************************************************/

	@FXML
	private void abrirArquivo(ActionEvent event) {
		// Se o arquivo de saida nao for nulo e existir
		if (arquivoSaida != null && arquivoSaida.exists()) {
			// O metodo tenta executar o seguinte bloco de codigo
			try {
				// Abre o arquivo se o Desktop suporta-lo
				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().open(arquivoSaida);
				}
			}
			catch (IOException e) {
				// Em caso de excecao, ela eh rastreada na pilha de execucao
				e.printStackTrace();
			}	
		}
	}

	/*
     * ***************************************************************
     * Metodo: excluirArquivo
     * Funcao: exclui o arquivo contendo os lexemas e tokens do programa
     * Parametros: ActionEvent event - evento gerado ao clicar no botao
     * Retorno: void
     ****************************************************************/

	@FXML
	private void excluirArquivo(ActionEvent event) {
		// O metodo tenta executar o seguinte bloco de codigo
		try {
			// Volta pra tela inicial se o arquivo nao for nulo, existir e for deletado
			if (arquivoSaida != null && arquivoSaida.exists() && arquivoSaida.delete()) {
				voltar(event);
			}
		}
		catch (IOException e) {
			// Em caso de excecao, ela eh rastreada na pilha de execucao
			e.printStackTrace();
		}
	}

	/*
     * ***************************************************************
     * Metodo: registrarErro
     * Funcao: registra um erro obtido durante a analise do codigo
     * Parametros: RegistroErro erro - erro a ser registrado
     * Retorno: void
     ****************************************************************/

	public void registrarErro(RegistroErro erro) {
		// Adiciona o erro na lista de erros
		erros.add(erro);

		// Ordena a lista de erros pela linha obtida
		erros.sort(Comparator.comparingInt(RegistroErro::getLinha));
	}

	/*
     * ***************************************************************
     * Metodo: exibirErros
     * Funcao: exibe os erros registrados durante a analise na caixa de erros
     * Parametros: nenhum parametro foi definido para esta funcao
     * Retorno: void
     ****************************************************************/

	public void exibirErros() {
		// Se a lista de erros nao estiver vazia
		if (!erros.isEmpty()) {
			// Imprime cada erro registrado na caixa de erros
			for (RegistroErro e : erros) {
				txtErros.appendText("Linha (" + Integer.toString(e.getLinha()) + "): " + e.getErro() + "\n");
			}
		}
	}

	/*
     * ***************************************************************
     * Metodo: carregarArquivo
     * Funcao: carrega um arquivo para ser compilado
     * Parametros: File codigo - codigo a ser compilado
     * Retorno: void
     ****************************************************************/

	public void carregarArquivo(File codigo) {
		// Carrega o arquivo de codigo
		this.codigo = codigo;

		// Inicializa o analisador sintatico
		analisadorSintatico = new AnalisadorSintatico(this);

		Platform.runLater(() -> {
			// Escreve o codigo fonte dentro da interface
			escreverCodigo();
		});

		// Realiza a analise do codigo
		analisadorSintatico.analisarCodigo(codigo);
	}

	/*
     * ***************************************************************
     * Metodo: escreverCodigo
     * Funcao: insere o codigo contido no arquivo dentro do txtCodigo
     * Parametros: nenhum parametro foi definido para esta funcao
     * Retorno: void
     ****************************************************************/

	private void escreverCodigo() {
		// O metodo tenta executar as seguintes operacoes de leitura com o BufferedReader
		try (BufferedReader br = new BufferedReader(new FileReader(codigo))) {
			// Variavel usada para guardar a linha
			String linha = "";

			// Enquanto ainda houverem linhas a serem lidas
			while ((linha = br.readLine()) != null) {
				// Insere as linhas dentro do txtCodigo mais uma quebra-linha
				txtCodigo.appendText(linha + "\n");
			}
		}
		catch (IOException e) {
			// Em caso de excecao, ela eh rastreada na pilha de execucao
			e.printStackTrace();
		}
	}

	/*
     * ***************************************************************
     * Metodo: realizarAnaliseLexica
     * Funcao: retorna os resultados da analise lexica do codigo
     * Parametros: ArrayList<TuplaLexema> tuplas - lista de tuplas 
     * Retorno: void
     ****************************************************************/

	public void realizarAnaliseLexica(ArrayList<TuplaLexema> tuplas) {
		// Se tivermos obtido tuplas
		if (!tuplas.isEmpty()) {			
			for (TuplaLexema t : tuplas) {
				// Formata a tupla contendo o lexema e o token, concatenando-a dentro do txtResultados
				String tupla = "<" + t.getLexema() + ", " + obterSimboloToken(t.getToken()) + ">";
				txtLexico.appendText(tupla + "\n");
			}

			// Usa as informacoes obtidas para gerar o arquivo de saida
			escreverArquivoSaida();
		}
	}

	public void imprimirResultadoSintatico(String res) {
		txtSintatico.appendText(res + "\n");
	}

	/*
     * ***************************************************************
     * Metodo: escreverArquivoSaida
     * Funcao: gera um arquivo contendo a analise completa do codigo
     * Parametros: nenhum parametro foi definido para esta funcao
     * Retorno: void
     ****************************************************************/

	private void escreverArquivoSaida() {
		// Obtem as tuplas listadas no resultado da analise lexica
		String tuplas = txtLexico.getText();

		// Se a lista de tuplas nao estiver vazia e o arquivo do codigo fonte nao for nulo
		if (!tuplas.isEmpty() && codigo != null) {
			// Cria-se um novo arquivo a ser salvo na mesma pasta do arquivo fonte
			// e com um nome pre-definido
			String saida = "saida_" + codigo.getName();
			arquivoSaida = new File(codigo.getParent(), saida);

			// Se o arquivo de saida nao for nulo
			if (arquivoSaida != null) {
				// O metodo tenta executar as seguintes operacoes de escrita com um BufferedWriter
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoSaida))) {
					// Escreve as tuplas dentro do arquivo
					bw.write(tuplas);

					// Ativa os botoes para abrir/excluir o arquivo de saida
					btnAbrirArquivo.setDisable(false);
					btnExcluirArquivo.setDisable(false);
				}
				catch (IOException e) {
					// Em caso de excecao, ela eh rastreada na pilha de execucao
					e.printStackTrace();
				}
			}
		}
	}

	/*
     * ***************************************************************
     * Metodo: obterSimboloToken
     * Funcao: retorna uma abreviacao correspondente ao token
     * Parametros: Token token - token 
     * Retorno: String
     ****************************************************************/

	private String obterSimboloToken(Token token) {
		// O switch/case retorna o simbolo correspondente ao token
		// passado como parametro
		switch (token) {
			case IDENTIFICADOR:
				return "id";

			case PALAVRA_RESERVADA:
				return "palavra_reservada";

			case CONSTANTE_NUMERICA:
				return "num";

			case CARACTERE:
				return "char";

			case STRING:
				return "string";

			case OPERADOR_ARITMETICO:
			case OPERADOR_RELACIONAL:
			case OPERADOR_LOGICO:
			case OPERADOR_ATRIBUICAO:
				return "op";

			case SIMBOLO_ESPECIAL:
				return "simbolo_especial";
		}

		// Retorna uma string vazia caso nenhuma correspondencia
		// tiver sido encontrada
		return "";
	}
}