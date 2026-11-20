/* ***************************************************************
* Autores............: Davi Gabrielli Santos
                       Diogo Oliveira de Sousa
                       Gustavo Henrique Oliveira Fernandes
* Matricula..........: 202410855 / 202411226 / 202410104
* Inicio.............: 01/11/2026
* Ultima alteracao...: 20/11/2026
* Nome...............: TabelaSLR
* Funcao.............: Classe que designa as operacoes da tabela SLR da gramatica do Analisador Sintatico do Mini Pascal.
                     
*************************************************************** */

package model;

import java.util.ArrayList;
import java.util.Hashtable;

public class TabelaSLR {
    // Variaveis e instancias
    private Hashtable<Integer, Hashtable<String, String>> entradasTerminais;
    private Hashtable<Integer, Hashtable<String, Integer>> entradasNaoTerminais;

    /*
     * ***************************************************************
     * Metodo: TabelaSLR
     * Funcao: cria uma nova instancia da classe TabelaSLR
     * Parametros: nenhum parametro foi definido para esta funcao
     * Retorno: nenhum
     ****************************************************************/

    public TabelaSLR() {
        // Inicializa a tabela SLR
        this.iniciarTabela();
    }   

    /*
     * ***************************************************************
     * Metodo: iniciarTabela
     * Funcao: inicializa as entradas da tabela SLR
     * Parametros: nenhum parametro foi definido para esta funcao
     * Retorno: void
     ****************************************************************/

    private void iniciarTabela() {
        // Inicializa as tabelas de acoes e transicoes
        entradasTerminais = new Hashtable<>();
        entradasNaoTerminais = new Hashtable<>();

        // Inicializa as entradas para cada estado da tabela
        for (int i = 0; i < 154; i++) {
           entradasTerminais.putIfAbsent(i, new Hashtable<>());
           entradasNaoTerminais.putIfAbsent(i, new Hashtable<>());
        }

        // Preenche as acoes e transicoes da tabela SLR
        preencherTerminais();
        preencherNaoTerminais();
    }

    /*
     * ***************************************************************
     * Metodo: pesquisarAcao
     * Funcao: pesquisa a acao (empilhamento ou reducao) a ser realizada 
               durante a analise sintatica SLR
     * Parametros: Integer estado - estado
                   String terminal - terminal que serve como gatilho para a acao
     * Retorno: String
     ****************************************************************/

    public String pesquisarAcao(Integer estado, String terminal) {
        // Acao a ser obtida
        String acao = null;

        // Se o estado nao for nulo
        if (estado != null) {
            // Busca a tabela de acoes do estado
            Hashtable<String, String> acoes = entradasTerminais.get(estado);

            // Se a tabela e o terminal nao forem nulos
            if (acoes != null && terminal != null) {
                // Obtem a acao a ser realizada a partir do terminal
                acao = acoes.get(terminal);
            }
        }

        // Retorna a acao
        return acao;
    }

    /*
     * ***************************************************************
     * Metodo: pesquisarTransicao
     * Funcao: pesquisa a transicao a ser realizada durante a analise sintatica SLR
     * Parametros: Integer estado - estado atual registrado na pilha
                   String naoTerminal - lado esquerdo da producao
     * Retorno: int
     ****************************************************************/

    public int pesquisarTransicao(Integer estado, String naoTerminal) {
        // Novo estado a ser obtido a partir da transicao
        Integer novoEstado = null;

        // Se o estado nao for nulo
        if (estado != null) {
            // Obtem a tabela de transicoes para este estado
            Hashtable<String, Integer> entradas = entradasNaoTerminais.get(estado);

            // Se a tabela e o nao terminal nao forem nulos
            if (entradas != null && naoTerminal != null) {
                // Obtem o novo estado
                novoEstado = entradas.get(naoTerminal);

                // Se o novo estado permanecer nulo, atribui o valor -1
                if (novoEstado == null) novoEstado = -1;
            }
        }

        // Retorna o novo estado
        return novoEstado;
    }
   
    /*
     * ***************************************************************
     * Metodo: preencherNaoTerminais
     * Funcao: preenche as transicoes da tabela SLR
     * Parametros: nenhum parametro foi definido para esta funcao
     * Retorno: void
     ****************************************************************/

    private void preencherNaoTerminais() {
        // Estado 0
        entradasNaoTerminais.get(0).putIfAbsent("P", 1);
        
        // Estado 4
        entradasNaoTerminais.get(4).putIfAbsent("B", 5);
        entradasNaoTerminais.get(4).putIfAbsent("VAR_LIST", 6);
     
        // Estado 6
        entradasNaoTerminais.get(6).putIfAbsent("PROC_LIST", 9);
        entradasNaoTerminais.get(6).putIfAbsent("PROC", 10);
      
        // Estado 7
        entradasNaoTerminais.get(7).putIfAbsent("VAR_DECL_LIST", 12);
        entradasNaoTerminais.get(7).putIfAbsent("VAR_DECL", 13);
        entradasNaoTerminais.get(7).putIfAbsent("ID_LIST", 14);
        
        // Estado 9
        entradasNaoTerminais.get(9).putIfAbsent("C_SEC", 16);
        
        // Estado 10
        entradasNaoTerminais.get(10).putIfAbsent("PROC_LIST", 18);
        entradasNaoTerminais.get(10).putIfAbsent("PROC", 10);
        
        // Estado 17
        entradasNaoTerminais.get(17).putIfAbsent("C_SEC", 31);
        entradasNaoTerminais.get(17).putIfAbsent("C_LIST", 23);
        entradasNaoTerminais.get(17).putIfAbsent("C", 24);
        entradasNaoTerminais.get(17).putIfAbsent("C_FECHADO", 25);
        entradasNaoTerminais.get(17).putIfAbsent("C_ABERTO", 26);
        entradasNaoTerminais.get(17).putIfAbsent("VAR_ATR", 27);
        entradasNaoTerminais.get(17).putIfAbsent("PROC_CA", 28);
        entradasNaoTerminais.get(17).putIfAbsent("FOR_FECHADO", 29);
        entradasNaoTerminais.get(17).putIfAbsent("FOR_ABERTO", 33);
        entradasNaoTerminais.get(17).putIfAbsent("W_FECHADO", 30);
        entradasNaoTerminais.get(17).putIfAbsent("W_ABERTO", 34);
        
        // Estado 19
        entradasNaoTerminais.get(19).putIfAbsent("PA_LIST", 39);
      
        // Estado 20
        entradasNaoTerminais.get(20).putIfAbsent("VAR_DECL_LIST", 41);
        entradasNaoTerminais.get(20).putIfAbsent("VAR_DECL", 13);
        entradasNaoTerminais.get(20).putIfAbsent("ID_LIST", 14);
       
        // Estado 22
        entradasNaoTerminais.get(22).putIfAbsent("ID_LIST", 43);
      
        // Estado 32
        entradasNaoTerminais.get(32).putIfAbsent("E", 46);
        entradasNaoTerminais.get(32).putIfAbsent("SI_E", 47);
        entradasNaoTerminais.get(32).putIfAbsent("SIGN_OPT", 48);
        
        // Estado 36
        entradasNaoTerminais.get(36).putIfAbsent("PROC_ARGS", 52);
        
        // Estado 37
        entradasNaoTerminais.get(37).putIfAbsent("VAR_ATR", 54);
        
        // Estado 38
        entradasNaoTerminais.get(38).putIfAbsent("E", 55);
        entradasNaoTerminais.get(38).putIfAbsent("SI_E", 47);
        entradasNaoTerminais.get(38).putIfAbsent("SIGN_OPT", 48);
      
        // Estado 40
        entradasNaoTerminais.get(40).putIfAbsent("PA_DECL_LIST", 57);
        entradasNaoTerminais.get(40).putIfAbsent("PA_DECL", 58);
        entradasNaoTerminais.get(40).putIfAbsent("VAR_OPT", 59);
     
        // Estado 45
        entradasNaoTerminais.get(45).putIfAbsent("C_SEC", 31);
        entradasNaoTerminais.get(45).putIfAbsent("C_LIST", 61);
        entradasNaoTerminais.get(45).putIfAbsent("C", 24);
        entradasNaoTerminais.get(45).putIfAbsent("C_FECHADO", 25);
        entradasNaoTerminais.get(45).putIfAbsent("C_ABERTO", 26);
        entradasNaoTerminais.get(45).putIfAbsent("VAR_ATR", 27);
        entradasNaoTerminais.get(45).putIfAbsent("PROC_CA", 28);
        entradasNaoTerminais.get(45).putIfAbsent("FOR_FECHADO", 29);
        entradasNaoTerminais.get(45).putIfAbsent("FOR_ABERTO", 33);
        entradasNaoTerminais.get(45).putIfAbsent("W_FECHADO", 30);
        entradasNaoTerminais.get(45).putIfAbsent("W_ABERTO", 34);
        
        // Estado 47
        entradasNaoTerminais.get(47).putIfAbsent("REL_OP", 63);
       
        // Estado 48
        entradasNaoTerminais.get(48).putIfAbsent("SI_E_TAIL", 70);
        entradasNaoTerminais.get(48).putIfAbsent("TERM", 71);
        entradasNaoTerminais.get(48).putIfAbsent("F", 72);
      
        // Estado 51
        entradasNaoTerminais.get(51).putIfAbsent("EXPR", 77);
        entradasNaoTerminais.get(51).putIfAbsent("LOG_TERM", 78);
        entradasNaoTerminais.get(51).putIfAbsent("E", 79);
        entradasNaoTerminais.get(51).putIfAbsent("SI_E", 47);
        entradasNaoTerminais.get(51).putIfAbsent("SIGN_OPT", 48);
      
        // Estado 53
        entradasNaoTerminais.get(53).putIfAbsent("E_LIST", 80);
        entradasNaoTerminais.get(53).putIfAbsent("EXPR", 81);
        entradasNaoTerminais.get(53).putIfAbsent("LOG_TERM", 78);
        entradasNaoTerminais.get(53).putIfAbsent("E", 79);
        entradasNaoTerminais.get(53).putIfAbsent("SI_E", 47);
        entradasNaoTerminais.get(53).putIfAbsent("SIGN_OPT", 48);
      
        // Estado 56
        entradasNaoTerminais.get(56).putIfAbsent("B", 84);
        entradasNaoTerminais.get(56).putIfAbsent("VAR_LIST", 6);
      
        // Estado 59
        entradasNaoTerminais.get(59).putIfAbsent("ID_LIST", 87);
      
        // Estado 62
        entradasNaoTerminais.get(62).putIfAbsent("C_SEC", 31);
        entradasNaoTerminais.get(62).putIfAbsent("C", 89);
        entradasNaoTerminais.get(62).putIfAbsent("C_FECHADO", 90);
        entradasNaoTerminais.get(62).putIfAbsent("C_ABERTO", 26);
        entradasNaoTerminais.get(62).putIfAbsent("CORPO_FECHADO", 88);
        entradasNaoTerminais.get(62).putIfAbsent("VAR_ATR", 27);
        entradasNaoTerminais.get(62).putIfAbsent("PROC_CA", 28);
        entradasNaoTerminais.get(62).putIfAbsent("FOR_FECHADO", 29);
        entradasNaoTerminais.get(62).putIfAbsent("FOR_ABERTO", 33);
        entradasNaoTerminais.get(62).putIfAbsent("W_FECHADO", 30);
        entradasNaoTerminais.get(62).putIfAbsent("W_ABERTO", 34);
      
        // Estado 63
        entradasNaoTerminais.get(63).putIfAbsent("SI_E", 91);
        entradasNaoTerminais.get(63).putIfAbsent("SIGN_OPT", 48);
      
        // Estado 70
        entradasNaoTerminais.get(70).putIfAbsent("ADD_OP", 92);
      
        // Estado 71
        entradasNaoTerminais.get(71).putIfAbsent("MULT_OP", 95);
      
        // Estado 75
        entradasNaoTerminais.get(75).putIfAbsent("EXPR", 99);
        entradasNaoTerminais.get(75).putIfAbsent("LOG_TERM", 78);
        entradasNaoTerminais.get(75).putIfAbsent("E", 79);
        entradasNaoTerminais.get(75).putIfAbsent("SI_E", 47);
        entradasNaoTerminais.get(75).putIfAbsent("SIGN_OPT", 48);
      
        // Estado 76
        entradasNaoTerminais.get(76).putIfAbsent("F", 100);
      
        // Estado 77
        entradasNaoTerminais.get(77).putIfAbsent("LOG_OP_OR_XOR", 101);
       
        // Estado 81
        entradasNaoTerminais.get(81).putIfAbsent("LOG_OP_OR_XOR", 101);
      
        // Estado 82
        entradasNaoTerminais.get(82).putIfAbsent("F", 107);
      
        // Estado 83
        entradasNaoTerminais.get(83).putIfAbsent("C_SEC", 31);
        entradasNaoTerminais.get(83).putIfAbsent("C_FECHADO", 108);
        entradasNaoTerminais.get(83).putIfAbsent("C_ABERTO", 109);
        entradasNaoTerminais.get(83).putIfAbsent("VAR_ATR", 27);
        entradasNaoTerminais.get(83).putIfAbsent("PROC_CA", 28);
        entradasNaoTerminais.get(83).putIfAbsent("FOR_FECHADO", 29);
        entradasNaoTerminais.get(83).putIfAbsent("FOR_ABERTO", 33);
        entradasNaoTerminais.get(83).putIfAbsent("W_FECHADO", 30);
        entradasNaoTerminais.get(83).putIfAbsent("W_ABERTO", 34);
      
        // Estado 86
        entradasNaoTerminais.get(86).putIfAbsent("PA_DECL_LIST", 111);
        entradasNaoTerminais.get(86).putIfAbsent("PA_DECL", 58);
        entradasNaoTerminais.get(86).putIfAbsent("VAR_OPT", 59);
      
        // Estado 92
        entradasNaoTerminais.get(92).putIfAbsent("TERM", 116);
        entradasNaoTerminais.get(92).putIfAbsent("F", 72);
       
        // Estado 95
        entradasNaoTerminais.get(95).putIfAbsent("F", 117);
      
        // Estado 99
        entradasNaoTerminais.get(99).putIfAbsent("LOG_OP_OR_XOR", 101);
      
        // Estado 101
        entradasNaoTerminais.get(101).putIfAbsent("LOG_TERM", 119);
        entradasNaoTerminais.get(101).putIfAbsent("E", 79);
        entradasNaoTerminais.get(101).putIfAbsent("SI_E", 47);
        entradasNaoTerminais.get(101).putIfAbsent("SIGN_OPT", 48);
      
        // Estado 104
        entradasNaoTerminais.get(104).putIfAbsent("E", 120);
        entradasNaoTerminais.get(104).putIfAbsent("SI_E", 47);
        entradasNaoTerminais.get(104).putIfAbsent("SIGN_OPT", 48);
      
        // Estado 106
        entradasNaoTerminais.get(106).putIfAbsent("E_LIST", 121);
        entradasNaoTerminais.get(106).putIfAbsent("EXPR", 81);
        entradasNaoTerminais.get(106).putIfAbsent("LOG_TERM", 78);
        entradasNaoTerminais.get(106).putIfAbsent("E", 79);
        entradasNaoTerminais.get(106).putIfAbsent("SI_E", 47);
        entradasNaoTerminais.get(106).putIfAbsent("SIGN_OPT", 48);
      
        // Estado 113
        entradasNaoTerminais.get(113).putIfAbsent("C_SEC", 31);
        entradasNaoTerminais.get(113).putIfAbsent("C_FECHADO", 125);
        entradasNaoTerminais.get(113).putIfAbsent("CORPO_FECHADO", 124);
        entradasNaoTerminais.get(113).putIfAbsent("VAR_ATR", 27);
        entradasNaoTerminais.get(113).putIfAbsent("PROC_CA", 28);
        entradasNaoTerminais.get(113).putIfAbsent("FOR_FECHADO", 29);
        entradasNaoTerminais.get(113).putIfAbsent("W_FECHADO", 30);
      
        // Estado 115
        entradasNaoTerminais.get(115).putIfAbsent("C_ABERTO", 130);
        entradasNaoTerminais.get(115).putIfAbsent("FOR_ABERTO", 33);
        entradasNaoTerminais.get(115).putIfAbsent("W_ABERTO", 34);
      
        // Estado 116
        entradasNaoTerminais.get(116).putIfAbsent("MULT_OP", 95);
      
        // Estado 122
        entradasNaoTerminais.get(122).putIfAbsent("C_SEC", 31);
        entradasNaoTerminais.get(122).putIfAbsent("C_FECHADO", 134);
        entradasNaoTerminais.get(122).putIfAbsent("C_ABERTO", 135);
        entradasNaoTerminais.get(122).putIfAbsent("VAR_ATR", 27);
        entradasNaoTerminais.get(122).putIfAbsent("PROC_CA", 28);
        entradasNaoTerminais.get(122).putIfAbsent("FOR_FECHADO", 29);
        entradasNaoTerminais.get(122).putIfAbsent("FOR_ABERTO", 33);
        entradasNaoTerminais.get(122).putIfAbsent("W_FECHADO", 30);
        entradasNaoTerminais.get(122).putIfAbsent("W_ABERTO", 34);
      
        // Estado 126
        entradasNaoTerminais.get(126).putIfAbsent("E", 136);
        entradasNaoTerminais.get(126).putIfAbsent("SI_E", 47);
        entradasNaoTerminais.get(126).putIfAbsent("SIGN_OPT", 48);
      
        // Estado 127
        entradasNaoTerminais.get(127).putIfAbsent("VAR_ATR", 137);
      
        // Estado 128
        entradasNaoTerminais.get(128).putIfAbsent("E", 138);
        entradasNaoTerminais.get(128).putIfAbsent("SI_E", 47);
        entradasNaoTerminais.get(128).putIfAbsent("SIGN_OPT", 48);
      
        // Estado 129
        entradasNaoTerminais.get(129).putIfAbsent("C_SEC", 31);
        entradasNaoTerminais.get(129).putIfAbsent("C_FECHADO", 125);
        entradasNaoTerminais.get(129).putIfAbsent("CORPO_FECHADO", 139);
        entradasNaoTerminais.get(129).putIfAbsent("VAR_ATR", 27);
        entradasNaoTerminais.get(129).putIfAbsent("PROC_CA", 28);
        entradasNaoTerminais.get(129).putIfAbsent("FOR_FECHADO", 29);
        entradasNaoTerminais.get(129).putIfAbsent("W_FECHADO", 30);
      
        // Estado 131
        entradasNaoTerminais.get(131).putIfAbsent("E", 140);
        entradasNaoTerminais.get(131).putIfAbsent("SI_E", 47);
        entradasNaoTerminais.get(131).putIfAbsent("SIGN_OPT", 48);
      
        // Estado 132
        entradasNaoTerminais.get(132).putIfAbsent("VAR_ATR", 141);
      
        // Estado 133
        entradasNaoTerminais.get(133).putIfAbsent("E", 142);
        entradasNaoTerminais.get(133).putIfAbsent("SI_E", 47);
        entradasNaoTerminais.get(133).putIfAbsent("SIGN_OPT", 48);
      
        // Estado 143
        entradasNaoTerminais.get(143).putIfAbsent("C_SEC", 31);
        entradasNaoTerminais.get(143).putIfAbsent("C_FECHADO", 125);
        entradasNaoTerminais.get(143).putIfAbsent("CORPO_FECHADO", 88);
        entradasNaoTerminais.get(143).putIfAbsent("VAR_ATR", 27);
        entradasNaoTerminais.get(143).putIfAbsent("PROC_CA", 28);
        entradasNaoTerminais.get(143).putIfAbsent("FOR_FECHADO", 29);
        entradasNaoTerminais.get(143).putIfAbsent("W_FECHADO", 30);
      
        // Estado 144
        entradasNaoTerminais.get(144).putIfAbsent("F", 149);
      
        // Estado 145
        entradasNaoTerminais.get(145).putIfAbsent("C_SEC", 31);
        entradasNaoTerminais.get(145).putIfAbsent("C_FECHADO", 108);
        entradasNaoTerminais.get(145).putIfAbsent("VAR_ATR", 27);
        entradasNaoTerminais.get(145).putIfAbsent("PROC_CA", 28);
        entradasNaoTerminais.get(145).putIfAbsent("FOR_FECHADO", 29);
        entradasNaoTerminais.get(145).putIfAbsent("W_FECHADO", 30);
      
        // Estado 146
        entradasNaoTerminais.get(146).putIfAbsent("C_SEC", 31);
        entradasNaoTerminais.get(146).putIfAbsent("C", 89);
        entradasNaoTerminais.get(146).putIfAbsent("C_FECHADO", 150);
        entradasNaoTerminais.get(146).putIfAbsent("C_ABERTO", 26);
        entradasNaoTerminais.get(146).putIfAbsent("VAR_ATR", 27);
        entradasNaoTerminais.get(146).putIfAbsent("PROC_CA", 28);
        entradasNaoTerminais.get(146).putIfAbsent("FOR_FECHADO", 29);
        entradasNaoTerminais.get(146).putIfAbsent("FOR_ABERTO", 33);
        entradasNaoTerminais.get(146).putIfAbsent("W_FECHADO", 30);
        entradasNaoTerminais.get(146).putIfAbsent("W_ABERTO", 34);
      
        // Estado 147
        entradasNaoTerminais.get(147).putIfAbsent("F", 151);
      
        // Estado 148
        entradasNaoTerminais.get(148).putIfAbsent("C_ABERTO", 109);
        entradasNaoTerminais.get(148).putIfAbsent("FOR_ABERTO", 33);
        entradasNaoTerminais.get(148).putIfAbsent("W_ABERTO", 34);
      
        // Estado 152
        entradasNaoTerminais.get(152).putIfAbsent("C_SEC", 31);
        entradasNaoTerminais.get(152).putIfAbsent("C_FECHADO", 134);
        entradasNaoTerminais.get(152).putIfAbsent("VAR_ATR", 27);
        entradasNaoTerminais.get(152).putIfAbsent("PROC_CA", 28);
        entradasNaoTerminais.get(152).putIfAbsent("FOR_FECHADO", 29);
        entradasNaoTerminais.get(152).putIfAbsent("W_FECHADO", 30);
       
        // Estado 153
        entradasNaoTerminais.get(153).putIfAbsent("C_ABERTO", 135);
        entradasNaoTerminais.get(153).putIfAbsent("FOR_ABERTO", 33);
        entradasNaoTerminais.get(153).putIfAbsent("W_ABERTO", 34);
    }

    /*
     * ***************************************************************
     * Metodo: preencherTerminais
     * Funcao: preenche as acoes da tabela SLR (empilhamento/reducao)
     * Parametros: nenhum parametro foi definido para esta funcao
     * Retorno: void
     ****************************************************************/

   private void preencherTerminais() {
        // Estado 0
        entradasTerminais.get(0).putIfAbsent("program", "e2");
        
        // Estado 1
        entradasTerminais.get(1).putIfAbsent("$", "OK");
        
        // Estado 2
        entradasTerminais.get(2).putIfAbsent("ident", "e3");
      
        // Estado 3
        entradasTerminais.get(3).putIfAbsent(";", "e4");
        
        // Estado 4
        entradasTerminais.get(4).putIfAbsent(";", "r4");
        entradasTerminais.get(4).putIfAbsent(".", "r4");
        entradasTerminais.get(4).putIfAbsent("var", "e7");
        entradasTerminais.get(4).putIfAbsent("procedure", "r4");
        entradasTerminais.get(4).putIfAbsent("begin", "r4");
        
        // Estado 5
        entradasTerminais.get(5).putIfAbsent(".", "e8");
        
        // Estado 6
        entradasTerminais.get(6).putIfAbsent("procedure", "e11");
        entradasTerminais.get(6).putIfAbsent("begin", "r11");
      
        // Estado 7
        entradasTerminais.get(7).putIfAbsent("ident", "e15");
      
        // Estado 8
        entradasTerminais.get(8).putIfAbsent("$", "r1");
        
        // Estado 9
        entradasTerminais.get(9).putIfAbsent("begin", "e17");
        
        // Estado 10
        entradasTerminais.get(10).putIfAbsent("procedure", "e11");
        entradasTerminais.get(10).putIfAbsent("begin", "r11");
      
        // Estado 11
        entradasTerminais.get(11).putIfAbsent("ident", "e19");
        
        // Estado 12
        entradasTerminais.get(12).putIfAbsent(";", "r3");
        entradasTerminais.get(12).putIfAbsent(".", "r3");
        entradasTerminais.get(12).putIfAbsent("procedure", "r3");
        entradasTerminais.get(12).putIfAbsent("begin", "r3");
      
        // Estado 13
        entradasTerminais.get(13).putIfAbsent(";", "e20");
      
        // Estado 14
        entradasTerminais.get(14).putIfAbsent(":", "e21");
        
        // Estado 15
        entradasTerminais.get(15).putIfAbsent(":", "r9");
        entradasTerminais.get(15).putIfAbsent(",", "e22");
      
        // Estado 16
        entradasTerminais.get(16).putIfAbsent(";", "r2");
        entradasTerminais.get(16).putIfAbsent(".", "r2");
        
        // Estado 17
        entradasTerminais.get(17).putIfAbsent("begin", "e17");
        entradasTerminais.get(17).putIfAbsent("end", "r22");
        entradasTerminais.get(17).putIfAbsent("if", "e32");
        entradasTerminais.get(17).putIfAbsent("variavel", "e35");
        entradasTerminais.get(17).putIfAbsent("proc", "e36");
        entradasTerminais.get(17).putIfAbsent("for", "e37");
        entradasTerminais.get(17).putIfAbsent("while", "e38");
        
        // Estado 18
        entradasTerminais.get(18).putIfAbsent("begin", "r10");
        
        // Estado 19
        entradasTerminais.get(19).putIfAbsent(";", "r14");
        entradasTerminais.get(19).putIfAbsent("(", "e40");
        
        // Estado 20
        entradasTerminais.get(20).putIfAbsent("ident", "e15");
        entradasTerminais.get(20).putIfAbsent(";", "r6");
        entradasTerminais.get(20).putIfAbsent(".", "r6");
        entradasTerminais.get(20).putIfAbsent("procedure", "r6");
        entradasTerminais.get(20).putIfAbsent("begin", "r6");
      
        // Estado 21
        entradasTerminais.get(21).putIfAbsent("tipo", "e42");
      
        // Estado 22
        entradasTerminais.get(22).putIfAbsent("ident", "e15");
        
        // Estado 23
        entradasTerminais.get(23).putIfAbsent("end", "e44");
        
        // Estado 24
        entradasTerminais.get(24).putIfAbsent(";", "e45");
      
        // Estado 25
        entradasTerminais.get(25).putIfAbsent(";", "r23");
        
        // Estado 26
        entradasTerminais.get(26).putIfAbsent(";", "r24");
        
        // Estado 27
        entradasTerminais.get(27).putIfAbsent(";", "r25");
        entradasTerminais.get(27).putIfAbsent("else", "r25");
      
        // Estado 28
        entradasTerminais.get(28).putIfAbsent(";", "r26");
        entradasTerminais.get(28).putIfAbsent("else", "r26");
        
        // Estado 29
        entradasTerminais.get(29).putIfAbsent(";", "r27");
        entradasTerminais.get(29).putIfAbsent("else", "r27");
      
        // Estado 30
        entradasTerminais.get(30).putIfAbsent(";", "r28");
        entradasTerminais.get(30).putIfAbsent("else", "r28");
      
        // Estado 31
        entradasTerminais.get(31).putIfAbsent(";", "r29");
        entradasTerminais.get(31).putIfAbsent("else", "r29");
        
        // Estado 32
        entradasTerminais.get(32).putIfAbsent("(", "r65");
        entradasTerminais.get(32).putIfAbsent("variavel", "r65");
        entradasTerminais.get(32).putIfAbsent("numero", "r65");
        entradasTerminais.get(32).putIfAbsent("not", "r65");
        entradasTerminais.get(32).putIfAbsent("+", "e49");
        entradasTerminais.get(32).putIfAbsent("-", "e50");
      
        // Estado 33
        entradasTerminais.get(33).putIfAbsent(";", "r34");
      
        // Estado 34
        entradasTerminais.get(34).putIfAbsent(";", "r35");
        
        // Estado 35
        entradasTerminais.get(35).putIfAbsent(":=", "e51");
        
        // Estado 36
        entradasTerminais.get(36).putIfAbsent(";", "r41");
        entradasTerminais.get(36).putIfAbsent("(", "e53");
        entradasTerminais.get(36).putIfAbsent("else", "r41");
        
        // Estado 37
        entradasTerminais.get(37).putIfAbsent("variavel", "e35");
        
        // Estado 38
        entradasTerminais.get(38).putIfAbsent("(", "r65");
        entradasTerminais.get(38).putIfAbsent("variavel", "r65");
        entradasTerminais.get(38).putIfAbsent("numero", "r65");
        entradasTerminais.get(38).putIfAbsent("not", "r65");
        entradasTerminais.get(38).putIfAbsent("+", "e49");
        entradasTerminais.get(38).putIfAbsent("-", "e50");
        
        // Estado 39
        entradasTerminais.get(39).putIfAbsent(";", "e56");
        
        // Estado 40
        entradasTerminais.get(40).putIfAbsent("ident", "r19");
        entradasTerminais.get(40).putIfAbsent("var", "e60");
        
        // Estado 41
        entradasTerminais.get(41).putIfAbsent(";", "r5");
        entradasTerminais.get(41).putIfAbsent(".", "r5");
        entradasTerminais.get(41).putIfAbsent("procedure", "r5");
        entradasTerminais.get(41).putIfAbsent("begin", "r5");
        
        // Estado 42
        entradasTerminais.get(42).putIfAbsent(";", "r7");
        
        // Estado 43
        entradasTerminais.get(43).putIfAbsent(":", "r8");
        
        // Estado 44
        entradasTerminais.get(44).putIfAbsent(";", "r20");
        entradasTerminais.get(44).putIfAbsent(".", "r20");
        entradasTerminais.get(44).putIfAbsent("else", "r20");
        
        // Estado 45
        entradasTerminais.get(45).putIfAbsent("begin", "e17");
        entradasTerminais.get(45).putIfAbsent("end", "r22");
        entradasTerminais.get(45).putIfAbsent("if", "e32");
        entradasTerminais.get(45).putIfAbsent("variavel", "e35");
        entradasTerminais.get(45).putIfAbsent("proc", "e36");
        entradasTerminais.get(45).putIfAbsent("for", "e37");
        entradasTerminais.get(45).putIfAbsent("while", "e38");
        
        // Estado 46
        entradasTerminais.get(46).putIfAbsent("then", "e62");
        
        // Estado 47
        entradasTerminais.get(47).putIfAbsent(";", "r53");
        entradasTerminais.get(47).putIfAbsent(",", "r53");
        entradasTerminais.get(47).putIfAbsent(")", "r53");
        entradasTerminais.get(47).putIfAbsent("then", "r53");
        entradasTerminais.get(47).putIfAbsent("else", "r53");
        entradasTerminais.get(47).putIfAbsent("to", "r53");
        entradasTerminais.get(47).putIfAbsent("do", "r53");
        entradasTerminais.get(47).putIfAbsent("and", "r53");
        entradasTerminais.get(47).putIfAbsent("=", "e64");
        entradasTerminais.get(47).putIfAbsent("<", "e65");
        entradasTerminais.get(47).putIfAbsent(">", "e66");
        entradasTerminais.get(47).putIfAbsent("<=", "e67");
        entradasTerminais.get(47).putIfAbsent(">=", "e68");
        entradasTerminais.get(47).putIfAbsent("<>", "e69");
        entradasTerminais.get(47).putIfAbsent("or", "r53");
        entradasTerminais.get(47).putIfAbsent("xor", "r53");
        
        // Estado 48
        entradasTerminais.get(48).putIfAbsent("(", "e75");
        entradasTerminais.get(48).putIfAbsent("variavel", "e74");
        entradasTerminais.get(48).putIfAbsent("numero", "e73");
        entradasTerminais.get(48).putIfAbsent("not", "e76");
        
        // Estado 49
        entradasTerminais.get(49).putIfAbsent("(", "r63");
        entradasTerminais.get(49).putIfAbsent("variavel", "r63");
        entradasTerminais.get(49).putIfAbsent("numero", "r63");
        entradasTerminais.get(49).putIfAbsent("not", "r63");
        
        // Estado 50
        entradasTerminais.get(50).putIfAbsent("(", "r64");
        entradasTerminais.get(50).putIfAbsent("variavel", "r64");
        entradasTerminais.get(50).putIfAbsent("numero", "r64");
        entradasTerminais.get(50).putIfAbsent("not", "r64");
        
        // Estado 51
        entradasTerminais.get(51).putIfAbsent("(", "r65");
        entradasTerminais.get(51).putIfAbsent("variavel", "r65");
        entradasTerminais.get(51).putIfAbsent("numero", "r65");
        entradasTerminais.get(51).putIfAbsent("not", "r65");
        entradasTerminais.get(51).putIfAbsent("+", "e49");
        entradasTerminais.get(51).putIfAbsent("-", "e50");
        
        // Estado 52
        entradasTerminais.get(52).putIfAbsent(";", "r39");
        entradasTerminais.get(52).putIfAbsent("else", "r39");
        
        // Estado 53
        entradasTerminais.get(53).putIfAbsent("(", "r65");
        entradasTerminais.get(53).putIfAbsent("variavel", "r65");
        entradasTerminais.get(53).putIfAbsent("numero", "r65");
        entradasTerminais.get(53).putIfAbsent("not", "r65");
        entradasTerminais.get(53).putIfAbsent("+", "e49");
        entradasTerminais.get(53).putIfAbsent("-", "e50");
        
        // Estado 54
        entradasTerminais.get(54).putIfAbsent("to", "e82");
        
        // Estado 55
        entradasTerminais.get(55).putIfAbsent("do", "e83");
        
        // Estado 56
        entradasTerminais.get(56).putIfAbsent(";", "r4");
        entradasTerminais.get(56).putIfAbsent(".", "r4");
        entradasTerminais.get(56).putIfAbsent("var", "e7");
        entradasTerminais.get(56).putIfAbsent("procedure", "r4");
        entradasTerminais.get(56).putIfAbsent("begin", "r4");
        
        // Estado 57
        entradasTerminais.get(57).putIfAbsent(")", "e85");
        
        // Estado 58
        entradasTerminais.get(58).putIfAbsent(";", "e86");
        entradasTerminais.get(58).putIfAbsent(")", "r16");
        
        // Estado 59
        entradasTerminais.get(59).putIfAbsent("ident", "e15");
        
        // Estado 60
        entradasTerminais.get(60).putIfAbsent("ident", "r18");
        
        // Estado 61
        entradasTerminais.get(61).putIfAbsent("end", "r21");
        
        // Estado 62
        entradasTerminais.get(62).putIfAbsent(";", "r37");
        entradasTerminais.get(62).putIfAbsent("begin", "e17");
        entradasTerminais.get(62).putIfAbsent("if", "e32");
        entradasTerminais.get(62).putIfAbsent("else", "r37");
        entradasTerminais.get(62).putIfAbsent("variavel", "e35");
        entradasTerminais.get(62).putIfAbsent("proc", "e36");
        entradasTerminais.get(62).putIfAbsent("for", "e37");
        entradasTerminais.get(62).putIfAbsent("while", "e38");
        
        // Estado 63
        entradasTerminais.get(63).putIfAbsent("(", "r65");
        entradasTerminais.get(63).putIfAbsent("variavel", "r65");
        entradasTerminais.get(63).putIfAbsent("numero", "r65");
        entradasTerminais.get(63).putIfAbsent("not", "r65");
        entradasTerminais.get(63).putIfAbsent("+", "e49");
        entradasTerminais.get(63).putIfAbsent("-", "e50");
        
        // Estado 64
        entradasTerminais.get(64).putIfAbsent(";", "r71");
        entradasTerminais.get(64).putIfAbsent(",", "r71");
        entradasTerminais.get(64).putIfAbsent("(", "r71");
        entradasTerminais.get(64).putIfAbsent(")", "r71");
        entradasTerminais.get(64).putIfAbsent("then", "r71");
        entradasTerminais.get(64).putIfAbsent("else", "r71");
        entradasTerminais.get(64).putIfAbsent("variavel", "r71");
        entradasTerminais.get(64).putIfAbsent("to", "r71");
        entradasTerminais.get(64).putIfAbsent("do", "r71");
        entradasTerminais.get(64).putIfAbsent("and", "r71");
        entradasTerminais.get(64).putIfAbsent("numero", "r71");
        entradasTerminais.get(64).putIfAbsent("not", "r71");
        entradasTerminais.get(64).putIfAbsent("+", "r71");
        entradasTerminais.get(64).putIfAbsent("-", "r71");
        entradasTerminais.get(64).putIfAbsent("or", "r71");
        entradasTerminais.get(64).putIfAbsent("xor", "r71");
      
        // Estado 65
        entradasTerminais.get(65).putIfAbsent(";", "r72");
        entradasTerminais.get(65).putIfAbsent(",", "r72");
        entradasTerminais.get(65).putIfAbsent("(", "r72");
        entradasTerminais.get(65).putIfAbsent(")", "r72");
        entradasTerminais.get(65).putIfAbsent("then", "r72");
        entradasTerminais.get(65).putIfAbsent("else", "r72");
        entradasTerminais.get(65).putIfAbsent("variavel", "r72");
        entradasTerminais.get(65).putIfAbsent("to", "r72");
        entradasTerminais.get(65).putIfAbsent("do", "r72");
        entradasTerminais.get(65).putIfAbsent("and", "r72");
        entradasTerminais.get(65).putIfAbsent("numero", "r72");
        entradasTerminais.get(65).putIfAbsent("not", "r72");
        entradasTerminais.get(65).putIfAbsent("+", "r72");
        entradasTerminais.get(65).putIfAbsent("-", "r72");
        entradasTerminais.get(65).putIfAbsent("or", "r72");
        entradasTerminais.get(65).putIfAbsent("xor", "r72");
      
        // Estado 66
        entradasTerminais.get(66).putIfAbsent(";", "r73");
        entradasTerminais.get(66).putIfAbsent(",", "r73");
        entradasTerminais.get(66).putIfAbsent("(", "r73");
        entradasTerminais.get(66).putIfAbsent(")", "r73");
        entradasTerminais.get(66).putIfAbsent("then", "r73");
        entradasTerminais.get(66).putIfAbsent("else", "r73");
        entradasTerminais.get(66).putIfAbsent("variavel", "r73");
        entradasTerminais.get(66).putIfAbsent("to", "r73");
        entradasTerminais.get(66).putIfAbsent("do", "r73");
        entradasTerminais.get(66).putIfAbsent("and", "r73");
        entradasTerminais.get(66).putIfAbsent("numero", "r73");
        entradasTerminais.get(66).putIfAbsent("not", "r73");
        entradasTerminais.get(66).putIfAbsent("+", "r73");
        entradasTerminais.get(66).putIfAbsent("-", "r73");
        entradasTerminais.get(66).putIfAbsent("or", "r73");
        entradasTerminais.get(66).putIfAbsent("xor", "r73");
      
        // Estado 67
        entradasTerminais.get(67).putIfAbsent(";", "r74");
        entradasTerminais.get(67).putIfAbsent(",", "r74");
        entradasTerminais.get(67).putIfAbsent("(", "r74");
        entradasTerminais.get(67).putIfAbsent(")", "r74");
        entradasTerminais.get(67).putIfAbsent("then", "r74");
        entradasTerminais.get(67).putIfAbsent("else", "r74");
        entradasTerminais.get(67).putIfAbsent("variavel", "r74");
        entradasTerminais.get(67).putIfAbsent("to", "r74");
        entradasTerminais.get(67).putIfAbsent("do", "r74");
        entradasTerminais.get(67).putIfAbsent("and", "r74");
        entradasTerminais.get(67).putIfAbsent("numero", "r74");
        entradasTerminais.get(67).putIfAbsent("not", "r74");
        entradasTerminais.get(67).putIfAbsent("+", "r74");
        entradasTerminais.get(67).putIfAbsent("-", "r74");
        entradasTerminais.get(67).putIfAbsent("or", "r74");
        entradasTerminais.get(67).putIfAbsent("xor", "r74");
        
        // Estado 68
        entradasTerminais.get(68).putIfAbsent(";", "r75");
        entradasTerminais.get(68).putIfAbsent(",", "r75");
        entradasTerminais.get(68).putIfAbsent("(", "r75");
        entradasTerminais.get(68).putIfAbsent(")", "r75");
        entradasTerminais.get(68).putIfAbsent("then", "r75");
        entradasTerminais.get(68).putIfAbsent("else", "r75");
        entradasTerminais.get(68).putIfAbsent("variavel", "r75");
        entradasTerminais.get(68).putIfAbsent("to", "r75");
        entradasTerminais.get(68).putIfAbsent("do", "r75");
        entradasTerminais.get(68).putIfAbsent("and", "r75");
        entradasTerminais.get(68).putIfAbsent("numero", "r75");
        entradasTerminais.get(68).putIfAbsent("not", "r75");
        entradasTerminais.get(68).putIfAbsent("+", "r75");
        entradasTerminais.get(68).putIfAbsent("-", "r75");
        entradasTerminais.get(68).putIfAbsent("or", "r75");
        entradasTerminais.get(68).putIfAbsent("xor", "r75");
        
        // Estado 69
        entradasTerminais.get(69).putIfAbsent(";", "r76");
        entradasTerminais.get(69).putIfAbsent(",", "r76");
        entradasTerminais.get(69).putIfAbsent("(", "r76");
        entradasTerminais.get(69).putIfAbsent(")", "r76");
        entradasTerminais.get(69).putIfAbsent("then", "r76");
        entradasTerminais.get(69).putIfAbsent("else", "r76");
        entradasTerminais.get(69).putIfAbsent("variavel", "r76");
        entradasTerminais.get(69).putIfAbsent("to", "r76");
        entradasTerminais.get(69).putIfAbsent("do", "r76");
        entradasTerminais.get(69).putIfAbsent("and", "r76");
        entradasTerminais.get(69).putIfAbsent("numero", "r76");
        entradasTerminais.get(69).putIfAbsent("not", "r76");
        entradasTerminais.get(69).putIfAbsent("+", "r76");
        entradasTerminais.get(69).putIfAbsent("-", "r76");
        entradasTerminais.get(69).putIfAbsent("or", "r76");
        entradasTerminais.get(69).putIfAbsent("xor", "r76");
        
        // Estado 70
        entradasTerminais.get(70).putIfAbsent(";", "r54");
        entradasTerminais.get(70).putIfAbsent(",", "r54");
        entradasTerminais.get(70).putIfAbsent(")", "r54");
        entradasTerminais.get(70).putIfAbsent("then", "r54");
        entradasTerminais.get(70).putIfAbsent("else", "r54");
        entradasTerminais.get(70).putIfAbsent("to", "r54");
        entradasTerminais.get(70).putIfAbsent("do", "r54");
        entradasTerminais.get(70).putIfAbsent("and", "r54");
        entradasTerminais.get(70).putIfAbsent("+", "e93");
        entradasTerminais.get(70).putIfAbsent("-", "e94");
        entradasTerminais.get(70).putIfAbsent("=", "r54");
        entradasTerminais.get(70).putIfAbsent("<", "r54");
        entradasTerminais.get(70).putIfAbsent(">", "r54");
        entradasTerminais.get(70).putIfAbsent("<=", "r54");
        entradasTerminais.get(70).putIfAbsent(">=", "r54");
        entradasTerminais.get(70).putIfAbsent("<>", "r54");
        entradasTerminais.get(70).putIfAbsent("or", "r54");
        entradasTerminais.get(70).putIfAbsent("xor", "r54");
        
        // Estado 71
        entradasTerminais.get(71).putIfAbsent(";", "r56");
        entradasTerminais.get(71).putIfAbsent(",", "r56");
        entradasTerminais.get(71).putIfAbsent(")", "r56");
        entradasTerminais.get(71).putIfAbsent("then", "r56");
        entradasTerminais.get(71).putIfAbsent("else", "r56");
        entradasTerminais.get(71).putIfAbsent("to", "r56");
        entradasTerminais.get(71).putIfAbsent("do", "r56");
        entradasTerminais.get(71).putIfAbsent("and", "r56");
        entradasTerminais.get(71).putIfAbsent("+", "r56");
        entradasTerminais.get(71).putIfAbsent("-", "r56");
        entradasTerminais.get(71).putIfAbsent("*", "e96");
        entradasTerminais.get(71).putIfAbsent("/", "e97");
        entradasTerminais.get(71).putIfAbsent("%", "e98");
        entradasTerminais.get(71).putIfAbsent("=", "r56");
        entradasTerminais.get(71).putIfAbsent("<", "r56");
        entradasTerminais.get(71).putIfAbsent(">", "r56");
        entradasTerminais.get(71).putIfAbsent("<=", "r56");
        entradasTerminais.get(71).putIfAbsent(">=", "r56");
        entradasTerminais.get(71).putIfAbsent("<>", "r56");
        entradasTerminais.get(71).putIfAbsent("or", "r56");
        entradasTerminais.get(71).putIfAbsent("xor", "r56");
      
        // Estado 72
        entradasTerminais.get(72).putIfAbsent(";", "r58");
        entradasTerminais.get(72).putIfAbsent(",", "r58");
        entradasTerminais.get(72).putIfAbsent(")", "r58");
        entradasTerminais.get(72).putIfAbsent("then", "r58");
        entradasTerminais.get(72).putIfAbsent("else", "r58");
        entradasTerminais.get(72).putIfAbsent("to", "r58");
        entradasTerminais.get(72).putIfAbsent("do", "r58");
        entradasTerminais.get(72).putIfAbsent("and", "r58");
        entradasTerminais.get(72).putIfAbsent("+", "r58");
        entradasTerminais.get(72).putIfAbsent("-", "r58");
        entradasTerminais.get(72).putIfAbsent("*", "r58");
        entradasTerminais.get(72).putIfAbsent("/", "r58");
        entradasTerminais.get(72).putIfAbsent("%", "r58");
        entradasTerminais.get(72).putIfAbsent("=", "r58");
        entradasTerminais.get(72).putIfAbsent("<", "r58");
        entradasTerminais.get(72).putIfAbsent(">", "r58");
        entradasTerminais.get(72).putIfAbsent("<=", "r58");
        entradasTerminais.get(72).putIfAbsent(">=", "r58");
        entradasTerminais.get(72).putIfAbsent("<>", "r58");
        entradasTerminais.get(72).putIfAbsent("or", "r58");
        entradasTerminais.get(72).putIfAbsent("xor", "r58");
        
        // Estado 73
        entradasTerminais.get(73).putIfAbsent(";", "r59");
        entradasTerminais.get(73).putIfAbsent(",", "r59");
        entradasTerminais.get(73).putIfAbsent(")", "r59");
        entradasTerminais.get(73).putIfAbsent("then", "r59");
        entradasTerminais.get(73).putIfAbsent("else", "r59");
        entradasTerminais.get(73).putIfAbsent("to", "r59");
        entradasTerminais.get(73).putIfAbsent("do", "r59");
        entradasTerminais.get(73).putIfAbsent("and", "r59");
        entradasTerminais.get(73).putIfAbsent("+", "r59");
        entradasTerminais.get(73).putIfAbsent("-", "r59");
        entradasTerminais.get(73).putIfAbsent("*", "r59");
        entradasTerminais.get(73).putIfAbsent("/", "r59");
        entradasTerminais.get(73).putIfAbsent("%", "r59");
        entradasTerminais.get(73).putIfAbsent("=", "r59");
        entradasTerminais.get(73).putIfAbsent("<", "r59");
        entradasTerminais.get(73).putIfAbsent(">", "r59");
        entradasTerminais.get(73).putIfAbsent("<=", "r59");
        entradasTerminais.get(73).putIfAbsent(">=", "r59");
        entradasTerminais.get(73).putIfAbsent("<>", "r59");
        entradasTerminais.get(73).putIfAbsent("or", "r59");
        entradasTerminais.get(73).putIfAbsent("xor", "r59");
        
        // Estado 74
        entradasTerminais.get(74).putIfAbsent(";", "r60");
        entradasTerminais.get(74).putIfAbsent(",", "r60");
        entradasTerminais.get(74).putIfAbsent(")", "r60");
        entradasTerminais.get(74).putIfAbsent("then", "r60");
        entradasTerminais.get(74).putIfAbsent("else", "r60");
        entradasTerminais.get(74).putIfAbsent("to", "r60");
        entradasTerminais.get(74).putIfAbsent("do", "r60");
        entradasTerminais.get(74).putIfAbsent("and", "r60");
        entradasTerminais.get(74).putIfAbsent("+", "r60");
        entradasTerminais.get(74).putIfAbsent("-", "r60");
        entradasTerminais.get(74).putIfAbsent("*", "r60");
        entradasTerminais.get(74).putIfAbsent("/", "r60");
        entradasTerminais.get(74).putIfAbsent("%", "r60");
        entradasTerminais.get(74).putIfAbsent("=", "r60");
        entradasTerminais.get(74).putIfAbsent("<", "r60");
        entradasTerminais.get(74).putIfAbsent(">", "r60");
        entradasTerminais.get(74).putIfAbsent("<=", "r60");
        entradasTerminais.get(74).putIfAbsent(">=", "r60");
        entradasTerminais.get(74).putIfAbsent("<>", "r60");
        entradasTerminais.get(74).putIfAbsent("or", "r60");
        entradasTerminais.get(74).putIfAbsent("xor", "r60");
        
        // Estado 75
        entradasTerminais.get(75).putIfAbsent("(", "r65");
        entradasTerminais.get(75).putIfAbsent("variavel", "r65");
        entradasTerminais.get(75).putIfAbsent("numero", "r65");
        entradasTerminais.get(75).putIfAbsent("not", "r65");
        entradasTerminais.get(75).putIfAbsent("+", "e49");
        entradasTerminais.get(75).putIfAbsent("-", "e50");
        
        // Estado 76
        entradasTerminais.get(76).putIfAbsent("(", "e75");
        entradasTerminais.get(76).putIfAbsent("variavel", "e74");
        entradasTerminais.get(76).putIfAbsent("numero", "e73");
        entradasTerminais.get(76).putIfAbsent("not", "e76");
        
        // Estado 77
        entradasTerminais.get(77).putIfAbsent(";", "r38");
        entradasTerminais.get(77).putIfAbsent("else", "r38");
        entradasTerminais.get(77).putIfAbsent("to", "r38");
        entradasTerminais.get(77).putIfAbsent("or", "e102");
        entradasTerminais.get(77).putIfAbsent("xor", "e103");
        
        // Estado 78
        entradasTerminais.get(78).putIfAbsent(";", "r49");
        entradasTerminais.get(78).putIfAbsent(",", "r49");
        entradasTerminais.get(78).putIfAbsent(")", "r49");
        entradasTerminais.get(78).putIfAbsent("else", "r49");
        entradasTerminais.get(78).putIfAbsent("to", "r49");
        entradasTerminais.get(78).putIfAbsent("and", "e104");
        entradasTerminais.get(78).putIfAbsent("or", "r49");
        entradasTerminais.get(78).putIfAbsent("xor", "r49");
      
        // Estado 79
        entradasTerminais.get(79).putIfAbsent(";", "r51");
        entradasTerminais.get(79).putIfAbsent(",", "r51");
        entradasTerminais.get(79).putIfAbsent(")", "r51");
        entradasTerminais.get(79).putIfAbsent("else", "r51");
        entradasTerminais.get(79).putIfAbsent("to", "r51");
        entradasTerminais.get(79).putIfAbsent("and", "r51");
        entradasTerminais.get(79).putIfAbsent("or", "r51");
        entradasTerminais.get(79).putIfAbsent("xor", "r51");
        
        // Estado 80
        entradasTerminais.get(80).putIfAbsent(")", "e105");
        
        // Estado 81
        entradasTerminais.get(81).putIfAbsent(",", "e106");
        entradasTerminais.get(81).putIfAbsent(")", "r43");
        entradasTerminais.get(81).putIfAbsent("or", "e102");
        entradasTerminais.get(81).putIfAbsent("xor", "e103");
        
        // Estado 82
        entradasTerminais.get(82).putIfAbsent("(", "e75");
        entradasTerminais.get(82).putIfAbsent("variavel", "e74");
        entradasTerminais.get(82).putIfAbsent("numero", "e73");
        entradasTerminais.get(82).putIfAbsent("not", "e76");
        
        // Estado 83
        entradasTerminais.get(83).putIfAbsent("begin", "e17");
        entradasTerminais.get(83).putIfAbsent("if", "e32");
        entradasTerminais.get(83).putIfAbsent("variavel", "e35");
        entradasTerminais.get(83).putIfAbsent("proc", "e36");
        entradasTerminais.get(83).putIfAbsent("for", "e37");
        entradasTerminais.get(83).putIfAbsent("while", "e38");
        
        // Estado 84
        entradasTerminais.get(84).putIfAbsent(";", "e110");
        
        // Estado 85
        entradasTerminais.get(85).putIfAbsent(";", "r13");
        
        // Estado 86
        entradasTerminais.get(86).putIfAbsent("ident", "r19");
        entradasTerminais.get(86).putIfAbsent("var", "e60");
        
        // Estado 87
        entradasTerminais.get(87).putIfAbsent(":", "e112");
        
        // Estado 88
        entradasTerminais.get(88).putIfAbsent(";", "e114");
        entradasTerminais.get(88).putIfAbsent("else", "e113");
        
        // Estado 89
        entradasTerminais.get(89).putIfAbsent(";", "r32");
        
        // Estado 90
        entradasTerminais.get(90).putIfAbsent(";", "r36");
        entradasTerminais.get(90).putIfAbsent("else", "e115");
      
        // Estado 91
        entradasTerminais.get(91).putIfAbsent(";", "r52");
        entradasTerminais.get(91).putIfAbsent(",", "r52");
        entradasTerminais.get(91).putIfAbsent(")", "r52");
        entradasTerminais.get(91).putIfAbsent("then", "r52");
        entradasTerminais.get(91).putIfAbsent("else", "r52");
        entradasTerminais.get(91).putIfAbsent("to", "r52");
        entradasTerminais.get(91).putIfAbsent("do", "r52");
        entradasTerminais.get(91).putIfAbsent("and", "r52");
        entradasTerminais.get(91).putIfAbsent("or", "r52");
        entradasTerminais.get(91).putIfAbsent("xor", "r52");
        
        // Estado 92
        entradasTerminais.get(92).putIfAbsent("(", "e75");
        entradasTerminais.get(92).putIfAbsent("variavel", "e74");
        entradasTerminais.get(92).putIfAbsent("numero", "e73");
        entradasTerminais.get(92).putIfAbsent("not", "e76");
      
        // Estado 93
        entradasTerminais.get(93).putIfAbsent("(", "r66");
        entradasTerminais.get(93).putIfAbsent("variavel", "r66");
        entradasTerminais.get(93).putIfAbsent("numero", "r66");
        entradasTerminais.get(93).putIfAbsent("not", "r66");
      // Estado 94
        entradasTerminais.get(94).putIfAbsent("(", "r67");
        entradasTerminais.get(94).putIfAbsent("variavel", "r67");
        entradasTerminais.get(94).putIfAbsent("numero", "r67");
        entradasTerminais.get(94).putIfAbsent("not", "r67");
      // Estado 95
        entradasTerminais.get(95).putIfAbsent("(", "e75");
        entradasTerminais.get(95).putIfAbsent("variavel", "e74");
        entradasTerminais.get(95).putIfAbsent("numero", "e73");
        entradasTerminais.get(95).putIfAbsent("not", "e76");
      // Estado 96
        entradasTerminais.get(96).putIfAbsent("(", "r68");
        entradasTerminais.get(96).putIfAbsent("variavel", "r68");
        entradasTerminais.get(96).putIfAbsent("numero", "r68");
        entradasTerminais.get(96).putIfAbsent("not", "r68");
      // Estado 97
        entradasTerminais.get(97).putIfAbsent("(", "r69");
        entradasTerminais.get(97).putIfAbsent("variavel", "r69");
        entradasTerminais.get(97).putIfAbsent("numero", "r69");
        entradasTerminais.get(97).putIfAbsent("not", "r69");
      // Estado 98
        entradasTerminais.get(98).putIfAbsent("(", "r70");
        entradasTerminais.get(98).putIfAbsent("variavel", "r70");
        entradasTerminais.get(98).putIfAbsent("numero", "r70");
        entradasTerminais.get(98).putIfAbsent("not", "r70");
      // Estado 99
        entradasTerminais.get(99).putIfAbsent(")", "e118");
        entradasTerminais.get(99).putIfAbsent("or", "e102");
        entradasTerminais.get(99).putIfAbsent("xor", "e103");
      // Estado 100
        entradasTerminais.get(100).putIfAbsent(";", "r62");
        entradasTerminais.get(100).putIfAbsent(",", "r62");
        entradasTerminais.get(100).putIfAbsent(")", "r62");
        entradasTerminais.get(100).putIfAbsent("then", "r62");
        entradasTerminais.get(100).putIfAbsent("else", "r62");
        entradasTerminais.get(100).putIfAbsent("to", "r62");
        entradasTerminais.get(100).putIfAbsent("do", "r62");
        entradasTerminais.get(100).putIfAbsent("and", "r62");
        entradasTerminais.get(100).putIfAbsent("+", "r62");
        entradasTerminais.get(100).putIfAbsent("-", "r62");
        entradasTerminais.get(100).putIfAbsent("*", "r62");
        entradasTerminais.get(100).putIfAbsent("/", "r62");
        entradasTerminais.get(100).putIfAbsent("%", "r62");
        entradasTerminais.get(100).putIfAbsent("=", "r62");
        entradasTerminais.get(100).putIfAbsent("<", "r62");
        entradasTerminais.get(100).putIfAbsent(">", "r62");
        entradasTerminais.get(100).putIfAbsent("<=", "r62");
        entradasTerminais.get(100).putIfAbsent(">=", "r62");
        entradasTerminais.get(100).putIfAbsent("<>", "r62");
        entradasTerminais.get(100).putIfAbsent("or", "r62");
        entradasTerminais.get(100).putIfAbsent("xor", "r62");
      // Estado 101
        entradasTerminais.get(101).putIfAbsent("(", "r65");
        entradasTerminais.get(101).putIfAbsent("variavel", "r65");
        entradasTerminais.get(101).putIfAbsent("numero", "r65");
        entradasTerminais.get(101).putIfAbsent("not", "r65");
        entradasTerminais.get(101).putIfAbsent("+", "e49");
        entradasTerminais.get(101).putIfAbsent("-", "e50");
      // Estado 102
        entradasTerminais.get(102).putIfAbsent(";", "r77");
        entradasTerminais.get(102).putIfAbsent(",", "r77");
        entradasTerminais.get(102).putIfAbsent("(", "r77");
        entradasTerminais.get(102).putIfAbsent(")", "r77");
        entradasTerminais.get(102).putIfAbsent("else", "r77");
        entradasTerminais.get(102).putIfAbsent("variavel", "r77");
        entradasTerminais.get(102).putIfAbsent("to", "r77");
        entradasTerminais.get(102).putIfAbsent("and", "r77");
        entradasTerminais.get(102).putIfAbsent("numero", "r77");
        entradasTerminais.get(102).putIfAbsent("not", "r77");
        entradasTerminais.get(102).putIfAbsent("+", "r77");
        entradasTerminais.get(102).putIfAbsent("-", "r77");
        entradasTerminais.get(102).putIfAbsent("=", "r77");
        entradasTerminais.get(102).putIfAbsent("<", "r77");
        entradasTerminais.get(102).putIfAbsent(">", "r77");
        entradasTerminais.get(102).putIfAbsent("<=", "r77");
        entradasTerminais.get(102).putIfAbsent(">=", "r77");
        entradasTerminais.get(102).putIfAbsent("<>", "r77");
        entradasTerminais.get(102).putIfAbsent("or", "r77");
        entradasTerminais.get(102).putIfAbsent("xor", "r77");
      
        // Estado 103
        entradasTerminais.get(103).putIfAbsent(";", "r78");
        entradasTerminais.get(103).putIfAbsent(",", "r78");
        entradasTerminais.get(103).putIfAbsent("(", "r78");
        entradasTerminais.get(103).putIfAbsent(")", "r78");
        entradasTerminais.get(103).putIfAbsent("else", "r78");
        entradasTerminais.get(103).putIfAbsent("variavel", "r78");
        entradasTerminais.get(103).putIfAbsent("to", "r78");
        entradasTerminais.get(103).putIfAbsent("and", "r78");
        entradasTerminais.get(103).putIfAbsent("numero", "r78");
        entradasTerminais.get(103).putIfAbsent("not", "r78");
        entradasTerminais.get(103).putIfAbsent("+", "r78");
        entradasTerminais.get(103).putIfAbsent("-", "r78");
        entradasTerminais.get(103).putIfAbsent("=", "r78");
        entradasTerminais.get(103).putIfAbsent("<", "r78");
        entradasTerminais.get(103).putIfAbsent(">", "r78");
        entradasTerminais.get(103).putIfAbsent("<=", "r78");
        entradasTerminais.get(103).putIfAbsent(">=", "r78");
        entradasTerminais.get(103).putIfAbsent("<>", "r78");
        entradasTerminais.get(103).putIfAbsent("or", "r78");
        entradasTerminais.get(103).putIfAbsent("xor", "r78");
      
        // Estado 104
        entradasTerminais.get(104).putIfAbsent("(", "r65");
        entradasTerminais.get(104).putIfAbsent("variavel", "r65");
        entradasTerminais.get(104).putIfAbsent("numero", "r65");
        entradasTerminais.get(104).putIfAbsent("not", "r65");
        entradasTerminais.get(104).putIfAbsent("+", "e49");
        entradasTerminais.get(104).putIfAbsent("-", "e50");
      
        // Estado 105
        entradasTerminais.get(105).putIfAbsent(";", "r40");
        entradasTerminais.get(105).putIfAbsent("else", "r40");
      
        // Estado 106
        entradasTerminais.get(106).putIfAbsent("(", "r65");
        entradasTerminais.get(106).putIfAbsent("variavel", "r65");
        entradasTerminais.get(106).putIfAbsent("numero", "r65");
        entradasTerminais.get(106).putIfAbsent("not", "r65");
        entradasTerminais.get(106).putIfAbsent("+", "e49");
        entradasTerminais.get(106).putIfAbsent("-", "e50");
        
        // Estado 107
        entradasTerminais.get(107).putIfAbsent("do", "e122");
        
        // Estado 108
        entradasTerminais.get(108).putIfAbsent(";", "r46");
        entradasTerminais.get(108).putIfAbsent("else", "r46");
      
        // Estado 109
        entradasTerminais.get(109).putIfAbsent(";", "r47");
      
        // Estado 110
        entradasTerminais.get(110).putIfAbsent("procedure", "r12");
        entradasTerminais.get(110).putIfAbsent("begin", "r12");
        
        // Estado 111
        entradasTerminais.get(111).putIfAbsent(")", "r15");
        
        // Estado 112
        entradasTerminais.get(112).putIfAbsent("tipo", "e123");
      
        // Estado 113
        entradasTerminais.get(113).putIfAbsent(";", "r37");
        entradasTerminais.get(113).putIfAbsent("begin", "e17");
        entradasTerminais.get(113).putIfAbsent("if", "e126");
        entradasTerminais.get(113).putIfAbsent("else", "r37");
        entradasTerminais.get(113).putIfAbsent("variavel", "e35");
        entradasTerminais.get(113).putIfAbsent("proc", "e36");
        entradasTerminais.get(113).putIfAbsent("for", "e127");
        entradasTerminais.get(113).putIfAbsent("while", "e128");
        
        // Estado 114
        entradasTerminais.get(114).putIfAbsent("else", "e129");
        
        // Estado 115
        entradasTerminais.get(115).putIfAbsent("if", "e131");
        entradasTerminais.get(115).putIfAbsent("for", "e132");
        entradasTerminais.get(115).putIfAbsent("while", "e133");
        
        // Estado 116
        entradasTerminais.get(116).putIfAbsent(";", "r55");
        entradasTerminais.get(116).putIfAbsent(",", "r55");
        entradasTerminais.get(116).putIfAbsent(")", "r55");
        entradasTerminais.get(116).putIfAbsent("then", "r55");
        entradasTerminais.get(116).putIfAbsent("else", "r55");
        entradasTerminais.get(116).putIfAbsent("to", "r55");
        entradasTerminais.get(116).putIfAbsent("do", "r55");
        entradasTerminais.get(116).putIfAbsent("and", "r55");
        entradasTerminais.get(116).putIfAbsent("+", "r55");
        entradasTerminais.get(116).putIfAbsent("-", "r55");
        entradasTerminais.get(116).putIfAbsent("*", "e96");
        entradasTerminais.get(116).putIfAbsent("/", "e97");
        entradasTerminais.get(116).putIfAbsent("%", "e98");
        entradasTerminais.get(116).putIfAbsent("=", "r55");
        entradasTerminais.get(116).putIfAbsent("<", "r55");
        entradasTerminais.get(116).putIfAbsent(">", "r55");
        entradasTerminais.get(116).putIfAbsent("<=", "r55");
        entradasTerminais.get(116).putIfAbsent(">=", "r55");
        entradasTerminais.get(116).putIfAbsent("<>", "r55");
        entradasTerminais.get(116).putIfAbsent("or", "r55");
        entradasTerminais.get(116).putIfAbsent("xor", "r55");
        
        // Estado 117
        entradasTerminais.get(117).putIfAbsent(";", "r57");
        entradasTerminais.get(117).putIfAbsent(",", "r57");
        entradasTerminais.get(117).putIfAbsent(")", "r57");
        entradasTerminais.get(117).putIfAbsent("then", "r57");
        entradasTerminais.get(117).putIfAbsent("else", "r57");
        entradasTerminais.get(117).putIfAbsent("to", "r57");
        entradasTerminais.get(117).putIfAbsent("do", "r57");
        entradasTerminais.get(117).putIfAbsent("and", "r57");
        entradasTerminais.get(117).putIfAbsent("+", "r57");
        entradasTerminais.get(117).putIfAbsent("-", "r57");
        entradasTerminais.get(117).putIfAbsent("*", "r57");
        entradasTerminais.get(117).putIfAbsent("/", "r57");
        entradasTerminais.get(117).putIfAbsent("%", "r57");
        entradasTerminais.get(117).putIfAbsent("=", "r57");
        entradasTerminais.get(117).putIfAbsent("<", "r57");
        entradasTerminais.get(117).putIfAbsent(">", "r57");
        entradasTerminais.get(117).putIfAbsent("<=", "r57");
        entradasTerminais.get(117).putIfAbsent(">=", "r57");
        entradasTerminais.get(117).putIfAbsent("<>", "r57");
        entradasTerminais.get(117).putIfAbsent("or", "r57");
        entradasTerminais.get(117).putIfAbsent("xor", "r57");
        
        // Estado 118
        entradasTerminais.get(118).putIfAbsent(";", "r61");
        entradasTerminais.get(118).putIfAbsent(",", "r61");
        entradasTerminais.get(118).putIfAbsent(")", "r61");
        entradasTerminais.get(118).putIfAbsent("then", "r61");
        entradasTerminais.get(118).putIfAbsent("else", "r61");
        entradasTerminais.get(118).putIfAbsent("to", "r61");
        entradasTerminais.get(118).putIfAbsent("do", "r61");
        entradasTerminais.get(118).putIfAbsent("and", "r61");
        entradasTerminais.get(118).putIfAbsent("+", "r61");
        entradasTerminais.get(118).putIfAbsent("-", "r61");
        entradasTerminais.get(118).putIfAbsent("*", "r61");
        entradasTerminais.get(118).putIfAbsent("/", "r61");
        entradasTerminais.get(118).putIfAbsent("%", "r61");
        entradasTerminais.get(118).putIfAbsent("=", "r61");
        entradasTerminais.get(118).putIfAbsent("<", "r61");
        entradasTerminais.get(118).putIfAbsent(">", "r61");
        entradasTerminais.get(118).putIfAbsent("<=", "r61");
        entradasTerminais.get(118).putIfAbsent(">=", "r61");
        entradasTerminais.get(118).putIfAbsent("<>", "r61");
        entradasTerminais.get(118).putIfAbsent("or", "r61");
        entradasTerminais.get(118).putIfAbsent("xor", "r61");
        
        // Estado 119
        entradasTerminais.get(119).putIfAbsent(";", "r48");
        entradasTerminais.get(119).putIfAbsent(",", "r48");
        entradasTerminais.get(119).putIfAbsent(")", "r48");
        entradasTerminais.get(119).putIfAbsent("else", "r48");
        entradasTerminais.get(119).putIfAbsent("to", "r48");
        entradasTerminais.get(119).putIfAbsent("and", "e104");
        entradasTerminais.get(119).putIfAbsent("or", "r48");
        entradasTerminais.get(119).putIfAbsent("xor", "r48");
        
        // Estado 120
        entradasTerminais.get(120).putIfAbsent(";", "r50");
        entradasTerminais.get(120).putIfAbsent(",", "r50");
        entradasTerminais.get(120).putIfAbsent(")", "r50");
        entradasTerminais.get(120).putIfAbsent("else", "r50");
        entradasTerminais.get(120).putIfAbsent("to", "r50");
        entradasTerminais.get(120).putIfAbsent("and", "r50");
        entradasTerminais.get(120).putIfAbsent("or", "r50");
        entradasTerminais.get(120).putIfAbsent("xor", "r50");
        
        // Estado 121
        entradasTerminais.get(121).putIfAbsent(")", "r42");
        
        // Estado 122
        entradasTerminais.get(122).putIfAbsent("begin", "e17");
        entradasTerminais.get(122).putIfAbsent("if", "e32");
        entradasTerminais.get(122).putIfAbsent("variavel", "e35");
        entradasTerminais.get(122).putIfAbsent("proc", "e36");
        entradasTerminais.get(122).putIfAbsent("for", "e37");
        entradasTerminais.get(122).putIfAbsent("while", "e38");
        
        // Estado 123
        entradasTerminais.get(123).putIfAbsent(";", "r17");
        entradasTerminais.get(123).putIfAbsent(")", "r17");
        
        // Estado 124
        entradasTerminais.get(124).putIfAbsent(";", "r30");
        entradasTerminais.get(124).putIfAbsent("else", "r30");
        
        // Estado 125
        entradasTerminais.get(125).putIfAbsent(";", "r36");
        entradasTerminais.get(125).putIfAbsent("else", "r36");
        
        // Estado 126
        entradasTerminais.get(126).putIfAbsent("(", "r65");
        entradasTerminais.get(126).putIfAbsent("variavel", "r65");
        entradasTerminais.get(126).putIfAbsent("numero", "r65");
        entradasTerminais.get(126).putIfAbsent("not", "r65");
        entradasTerminais.get(126).putIfAbsent("+", "e49");
        entradasTerminais.get(126).putIfAbsent("-", "e50");
      
        // Estado 127
        entradasTerminais.get(127).putIfAbsent("variavel", "e35");
      
        // Estado 128
        entradasTerminais.get(128).putIfAbsent("(", "r65");
        entradasTerminais.get(128).putIfAbsent("variavel", "r65");
        entradasTerminais.get(128).putIfAbsent("numero", "r65");
        entradasTerminais.get(128).putIfAbsent("not", "r65");
        entradasTerminais.get(128).putIfAbsent("+", "e49");
        entradasTerminais.get(128).putIfAbsent("-", "e50");
      
        // Estado 129
        entradasTerminais.get(129).putIfAbsent(";", "r37");
        entradasTerminais.get(129).putIfAbsent("begin", "e17");
        entradasTerminais.get(129).putIfAbsent("if", "e126");
        entradasTerminais.get(129).putIfAbsent("else", "r37");
        entradasTerminais.get(129).putIfAbsent("variavel", "e35");
        entradasTerminais.get(129).putIfAbsent("proc", "e36");
        entradasTerminais.get(129).putIfAbsent("for", "e127");
        entradasTerminais.get(129).putIfAbsent("while", "e128");
      
        // Estado 130
        entradasTerminais.get(130).putIfAbsent(";", "r33");
      
        // Estado 131
        entradasTerminais.get(131).putIfAbsent("(", "r65");
        entradasTerminais.get(131).putIfAbsent("variavel", "r65");
        entradasTerminais.get(131).putIfAbsent("numero", "r65");
        entradasTerminais.get(131).putIfAbsent("not", "r65");
        entradasTerminais.get(131).putIfAbsent("+", "e49");
        entradasTerminais.get(131).putIfAbsent("-", "e50");
      
        // Estado 132
        entradasTerminais.get(132).putIfAbsent("variavel", "e35");
      
        // Estado 133
        entradasTerminais.get(133).putIfAbsent("(", "r65");
        entradasTerminais.get(133).putIfAbsent("variavel", "r65");
        entradasTerminais.get(133).putIfAbsent("numero", "r65");
        entradasTerminais.get(133).putIfAbsent("not", "r65");
        entradasTerminais.get(133).putIfAbsent("+", "e49");
        entradasTerminais.get(133).putIfAbsent("-", "e50");
      
        // Estado 134
        entradasTerminais.get(134).putIfAbsent(";", "r44");
        entradasTerminais.get(134).putIfAbsent("else", "r44");
        
        // Estado 135
        entradasTerminais.get(135).putIfAbsent(";", "r45");
      
        // Estado 136
        entradasTerminais.get(136).putIfAbsent("then", "e143");
      
        // Estado 137
        entradasTerminais.get(137).putIfAbsent("to", "e144");
        
        // Estado 138
        entradasTerminais.get(138).putIfAbsent("do", "e145");
      
        // Estado 139
        entradasTerminais.get(139).putIfAbsent(";", "r31");
        entradasTerminais.get(139).putIfAbsent("else", "r31");
      
        // Estado 140
        entradasTerminais.get(140).putIfAbsent("then", "e146");
        
        // Estado 141
        entradasTerminais.get(141).putIfAbsent("to", "e147");
        
        // Estado 142
        entradasTerminais.get(142).putIfAbsent("do", "e148");
      
        // Estado 143
        entradasTerminais.get(143).putIfAbsent(";", "r37");
        entradasTerminais.get(143).putIfAbsent("begin", "e17");
        entradasTerminais.get(143).putIfAbsent("if", "e126");
        entradasTerminais.get(143).putIfAbsent("else", "r37");
        entradasTerminais.get(143).putIfAbsent("variavel", "e35");
        entradasTerminais.get(143).putIfAbsent("proc", "e36");
        entradasTerminais.get(143).putIfAbsent("for", "e127");
        entradasTerminais.get(143).putIfAbsent("while", "e128");
        
        // Estado 144
        entradasTerminais.get(144).putIfAbsent("(", "e75");
        entradasTerminais.get(144).putIfAbsent("variavel", "e74");
        entradasTerminais.get(144).putIfAbsent("numero", "e73");
        entradasTerminais.get(144).putIfAbsent("not", "e76");
      
        // Estado 145
        entradasTerminais.get(145).putIfAbsent("begin", "e17");
        entradasTerminais.get(145).putIfAbsent("if", "e126");
        entradasTerminais.get(145).putIfAbsent("variavel", "e35");
        entradasTerminais.get(145).putIfAbsent("proc", "e36");
        entradasTerminais.get(145).putIfAbsent("for", "e127");
        entradasTerminais.get(145).putIfAbsent("while", "e128");
        
        // Estado 146
        entradasTerminais.get(146).putIfAbsent("begin", "e17");
        entradasTerminais.get(146).putIfAbsent("if", "e32");
        entradasTerminais.get(146).putIfAbsent("variavel", "e35");
        entradasTerminais.get(146).putIfAbsent("proc", "e36");
        entradasTerminais.get(146).putIfAbsent("for", "e37");
        entradasTerminais.get(146).putIfAbsent("while", "e38");
      
        // Estado 147
        entradasTerminais.get(147).putIfAbsent("(", "e75");
        entradasTerminais.get(147).putIfAbsent("variavel", "e74");
        entradasTerminais.get(147).putIfAbsent("numero", "e73");
        entradasTerminais.get(147).putIfAbsent("not", "e76");
      
        // Estado 148
        entradasTerminais.get(148).putIfAbsent("if", "e131");
        entradasTerminais.get(148).putIfAbsent("for", "e132");
        entradasTerminais.get(148).putIfAbsent("while", "e133");
        
        // Estado 149
        entradasTerminais.get(149).putIfAbsent("do", "e152");
      
        // Estado 150
        entradasTerminais.get(150).putIfAbsent(";", "r23");
        entradasTerminais.get(150).putIfAbsent("else", "e115");
      
        // Estado 151
        entradasTerminais.get(151).putIfAbsent("do", "e153");
      
        // Estado 152
        entradasTerminais.get(152).putIfAbsent("begin", "e17");
        entradasTerminais.get(152).putIfAbsent("if", "e126");
        entradasTerminais.get(152).putIfAbsent("variavel", "e35");
        entradasTerminais.get(152).putIfAbsent("proc", "e36");
        entradasTerminais.get(152).putIfAbsent("for", "e127");
        entradasTerminais.get(152).putIfAbsent("while", "e128");
      
        // Estado 153
        entradasTerminais.get(153).putIfAbsent("if", "e131");
        entradasTerminais.get(153).putIfAbsent("for", "e132");
        entradasTerminais.get(153).putIfAbsent("while", "e133");
    }
}