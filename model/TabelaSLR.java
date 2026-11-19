/* ***************************************************************
* Autores............: Davi Gabrielli Santos
                       Diogo Oliveira de Sousa
                       Gustavo Henrique Oliveira Fernandes
* Matricula..........: 202410855 / 202411226 / 202410104
* Inicio.............: 01/11/2026
* Ultima alteracao...: 17/11/2026
* Nome...............: TabelaSLR
* Funcao.............: Classe que designa as operacoes da tabela SLR da gramatica do Analisador Sintatico do Mini Pascal.
                     
*************************************************************** */

package model;

import java.util.ArrayList;
import java.util.Hashtable;

public class TabelaSLR {
    private Hashtable<Integer, Hashtable<String, String>> entradasTerminais;
    private Hashtable<Integer, Hashtable<String, Integer>> entradasNaoTerminais;

    public TabelaSLR() {
        this.iniciarTabela();
    }   

    private void iniciarTabela() {
        entradasTerminais = new Hashtable<>();
        entradasNaoTerminais = new Hashtable<>();

        for (int i = 0; i < 119; i++) {
           entradasTerminais.putIfAbsent(i, new Hashtable<>());
           entradasNaoTerminais.putIfAbsent(i, new Hashtable<>());
        }

         preencherTerminais();
         preencherNaoTerminais();
    }

    public String pesquisarAcao(Integer estado, String terminal) {
        String acao = null;

        if (estado != null) {
            Hashtable<String, String> acoes = entradasTerminais.get(estado);

            if (acoes != null && terminal != null) {
                acao = acoes.get(terminal);
            }
        }

        return acao;
    }

    public int pesquisarTransicao(Integer estado, String naoTerminal) {
        Integer novoEstado = null;

        if (estado != null) {
            Hashtable<String, Integer> entradas = entradasNaoTerminais.get(estado);

            if (entradas != null && naoTerminal != null) {
                novoEstado = entradas.get(naoTerminal);
                if (novoEstado == null) novoEstado = -1;
            }
        }

        return novoEstado;
    }
   
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
        entradasTerminais.get(4).putIfAbsent("begin", "r4");
        entradasTerminais.get(4).putIfAbsent("procedure", "r4");
        entradasTerminais.get(4).putIfAbsent("var", "e7");

        // Estado 5
        entradasTerminais.get(5).putIfAbsent(".", "e8");

        // Estado 6
        entradasTerminais.get(6).putIfAbsent("begin", "r11");
        entradasTerminais.get(6).putIfAbsent("procedure", "e11");

        // Estado 7
        entradasTerminais.get(7).putIfAbsent("ident", "e15");

        // Estado 8
        entradasTerminais.get(8).putIfAbsent("$", "r1");

        // Estado 9
        entradasTerminais.get(9).putIfAbsent("begin", "e17");

        // Estado 10
        entradasTerminais.get(10).putIfAbsent("begin", "r11");
        entradasTerminais.get(10).putIfAbsent("procedure", "e11");

        // Estado 11
        entradasTerminais.get(11).putIfAbsent("ident", "e19");

        // Estado 12
        entradasTerminais.get(12).putIfAbsent("begin", "r3");
        entradasTerminais.get(12).putIfAbsent("procedure", "r3");

        // Estado 13
        entradasTerminais.get(13).putIfAbsent(";", "e20");

        // Estado 14
        entradasTerminais.get(14).putIfAbsent(":", "e21");

        // Estado 15 (Inclui a transição para vírgula e redução por FOLLOW)
        entradasTerminais.get(15).putIfAbsent(",", "e22");
        entradasTerminais.get(15).putIfAbsent(":", "r9");

        // Estado 16
        entradasTerminais.get(16).putIfAbsent(".", "r2");

        // Estado 17
        entradasTerminais.get(17).putIfAbsent("begin", "e17");
        entradasTerminais.get(17).putIfAbsent("end", "r22");
        entradasTerminais.get(17).putIfAbsent("for", "e34");
        entradasTerminais.get(17).putIfAbsent("if", "e33");
        entradasTerminais.get(17).putIfAbsent("proc", "e32");
        entradasTerminais.get(17).putIfAbsent("variavel", "e31");
        entradasTerminais.get(17).putIfAbsent("while", "e35");

        // Estado 18
        entradasTerminais.get(18).putIfAbsent("begin", "r10");
        entradasTerminais.get(18).putIfAbsent("procedure", "r10");

        // Estado 19
        entradasTerminais.get(19).putIfAbsent("(", "e37");
        entradasTerminais.get(19).putIfAbsent(";", "r14");

        // Estado 20
        entradasTerminais.get(20).putIfAbsent("ident", "e15");
        entradasTerminais.get(20).putIfAbsent("begin", "r6");
        entradasTerminais.get(20).putIfAbsent("procedure", "r6");

        // Estado 21
        entradasTerminais.get(21).putIfAbsent("tipo", "e39");

        // Estado 22
        entradasTerminais.get(22).putIfAbsent("ident", "e15");

        // Estado 23
        entradasTerminais.get(23).putIfAbsent("end", "e41");

        // Estado 24
        entradasTerminais.get(24).putIfAbsent(";", "e42");

        // Estados de Redução de Comandos (25 a 30)
        entradasTerminais.get(25).putIfAbsent(";", "r23");
        entradasTerminais.get(25).putIfAbsent("end", "r23");
        entradasTerminais.get(26).putIfAbsent(";", "r24");
        entradasTerminais.get(26).putIfAbsent("end", "r24");
        entradasTerminais.get(27).putIfAbsent(";", "r25");
        entradasTerminais.get(27).putIfAbsent("end", "r25");
        entradasTerminais.get(28).putIfAbsent(";", "r26");
        entradasTerminais.get(28).putIfAbsent("end", "r26");
        entradasTerminais.get(29).putIfAbsent(";", "r27");
        entradasTerminais.get(29).putIfAbsent("end", "r27");
        entradasTerminais.get(30).putIfAbsent(";", "r28");
        entradasTerminais.get(30).putIfAbsent("end", "r28");

        // Estado 31
        entradasTerminais.get(31).putIfAbsent(":=", "e43");

        // Estado 32
        entradasTerminais.get(32).putIfAbsent("(", "e45");
        entradasTerminais.get(32).putIfAbsent(";", "r32");
        entradasTerminais.get(32).putIfAbsent("end", "r32");

        // Estado 33
        entradasTerminais.get(33).putIfAbsent("+", "e49");
        entradasTerminais.get(33).putIfAbsent("-", "e50");
        entradasTerminais.get(33).putIfAbsent("(", "r57");
        entradasTerminais.get(33).putIfAbsent("numero", "r57");
        entradasTerminais.get(33).putIfAbsent("variavel", "r57");
        entradasTerminais.get(33).putIfAbsent("not", "r57");

        // Estado 34
        entradasTerminais.get(34).putIfAbsent("variavel", "e31");

        // Estado 35
        entradasTerminais.get(35).putIfAbsent("+", "e49");
        entradasTerminais.get(35).putIfAbsent("-", "e50");
        entradasTerminais.get(35).putIfAbsent("(", "r57");
        entradasTerminais.get(35).putIfAbsent("numero", "r57");
        entradasTerminais.get(35).putIfAbsent("variavel", "r57");
        entradasTerminais.get(35).putIfAbsent("not", "r57");

        // Estado 36
        entradasTerminais.get(36).putIfAbsent(";", "e53");

        // Estado 37
        entradasTerminais.get(37).putIfAbsent("var", "e57");
        entradasTerminais.get(37).putIfAbsent("ident", "r19");

        // Estado 38
        entradasTerminais.get(38).putIfAbsent("begin", "r5");
        entradasTerminais.get(38).putIfAbsent("procedure", "r5");

        // Estado 39
        entradasTerminais.get(39).putIfAbsent(";", "r7");
        entradasTerminais.get(39).putIfAbsent("end", "r7");

        // Estado 40
        entradasTerminais.get(40).putIfAbsent(":", "r8");
        entradasTerminais.get(40).putIfAbsent(";", "r8");

        // Estado 41
        entradasTerminais.get(41).putIfAbsent(";", "r20");
        entradasTerminais.get(41).putIfAbsent(".", "r20");

        // Estado 42
        entradasTerminais.get(42).putIfAbsent("begin", "e17");
        entradasTerminais.get(42).putIfAbsent("end", "r22");
        entradasTerminais.get(42).putIfAbsent("for", "e34");
        entradasTerminais.get(42).putIfAbsent("if", "e33");
        entradasTerminais.get(42).putIfAbsent("proc", "e32");
        entradasTerminais.get(42).putIfAbsent("variavel", "e31");
        entradasTerminais.get(42).putIfAbsent("while", "e35");

        // Estado 43
        entradasTerminais.get(43).putIfAbsent("+", "e49");
        entradasTerminais.get(43).putIfAbsent("-", "e50");
        entradasTerminais.get(43).putIfAbsent("(", "r57");
        entradasTerminais.get(43).putIfAbsent("numero", "r57");
        entradasTerminais.get(43).putIfAbsent("variavel", "r57");
        entradasTerminais.get(43).putIfAbsent("not", "r57");

        // Estado 44
        entradasTerminais.get(44).putIfAbsent(";", "r30");
        entradasTerminais.get(44).putIfAbsent("end", "r30");

        // Estado 45
        entradasTerminais.get(45).putIfAbsent("+", "e49");
        entradasTerminais.get(45).putIfAbsent("-", "e50");
        entradasTerminais.get(45).putIfAbsent("(", "r57");
        entradasTerminais.get(45).putIfAbsent("numero", "r57");
        entradasTerminais.get(45).putIfAbsent("variavel", "r57");
        entradasTerminais.get(45).putIfAbsent("not", "r57");

        // Estado 46
        entradasTerminais.get(46).putIfAbsent("then", "e64");

        // Estado 47
        entradasTerminais.get(47).putIfAbsent("=", "e66");
        entradasTerminais.get(47).putIfAbsent("<", "e67");
        entradasTerminais.get(47).putIfAbsent(">", "e68");
        entradasTerminais.get(47).putIfAbsent("<=", "e69");
        entradasTerminais.get(47).putIfAbsent(">=", "e70");
        entradasTerminais.get(47).putIfAbsent("<>", "e71");
        entradasTerminais.get(47).putIfAbsent(";", "r45");
        entradasTerminais.get(47).putIfAbsent(",", "r45");
        entradasTerminais.get(47).putIfAbsent(")", "r45");
        entradasTerminais.get(47).putIfAbsent("then", "r45");
        entradasTerminais.get(47).putIfAbsent("to", "r45");
        entradasTerminais.get(47).putIfAbsent("do", "r45");
        entradasTerminais.get(47).putIfAbsent("and", "r45");
        entradasTerminais.get(47).putIfAbsent("or", "r45");
        entradasTerminais.get(47).putIfAbsent("xor", "r45");

        // Estado 48
        entradasTerminais.get(48).putIfAbsent("(", "e77");
        entradasTerminais.get(48).putIfAbsent("numero", "e75");
        entradasTerminais.get(48).putIfAbsent("variavel", "e76");
        entradasTerminais.get(48).putIfAbsent("not", "e78");

        // Estado 49 e 50
        entradasTerminais.get(49).putIfAbsent("(", "r55");
        entradasTerminais.get(49).putIfAbsent("numero", "r55");
        entradasTerminais.get(49).putIfAbsent("variavel", "r55");
        entradasTerminais.get(49).putIfAbsent("not", "r55");
        entradasTerminais.get(50).putIfAbsent("(", "r56");
        entradasTerminais.get(50).putIfAbsent("numero", "r56");
        entradasTerminais.get(50).putIfAbsent("variavel", "r56");
        entradasTerminais.get(50).putIfAbsent("not", "r56");

        // Estado 51
        entradasTerminais.get(51).putIfAbsent("to", "e79");

        // Estado 52
        entradasTerminais.get(52).putIfAbsent("do", "e80");

        // Estado 53
        entradasTerminais.get(53).putIfAbsent("begin", "r4");
        entradasTerminais.get(53).putIfAbsent("procedure", "r4");
        entradasTerminais.get(53).putIfAbsent("var", "e7");

        // Estado 54
        entradasTerminais.get(54).putIfAbsent(")", "e82");

        // Estado 55
        entradasTerminais.get(55).putIfAbsent(";", "e83");
        entradasTerminais.get(55).putIfAbsent(")", "r16");

        // Estado 56
        entradasTerminais.get(56).putIfAbsent("ident", "e15");

        // Estado 57
        entradasTerminais.get(57).putIfAbsent("ident", "r18");

        // Estado 58
        entradasTerminais.get(58).putIfAbsent("end", "r21");

        // Estado 59
        entradasTerminais.get(59).putIfAbsent(";", "r29");
        entradasTerminais.get(59).putIfAbsent("end", "r29");
        entradasTerminais.get(59).putIfAbsent("or", "e86");
        entradasTerminais.get(59).putIfAbsent("xor", "e87");

        // Estado 60
        entradasTerminais.get(60).putIfAbsent(";", "r41");
        entradasTerminais.get(60).putIfAbsent(",", "r41");
        entradasTerminais.get(60).putIfAbsent(")", "r41");
        entradasTerminais.get(60).putIfAbsent("then", "r41");
        entradasTerminais.get(60).putIfAbsent("to", "r41");
        entradasTerminais.get(60).putIfAbsent("do", "r41");
        entradasTerminais.get(60).putIfAbsent("end", "r41");
        entradasTerminais.get(60).putIfAbsent("and", "e88");
        entradasTerminais.get(60).putIfAbsent("or", "r41");
        entradasTerminais.get(60).putIfAbsent("xor", "r41");

        // Estado 61
        entradasTerminais.get(61).putIfAbsent(";", "r43");
        entradasTerminais.get(61).putIfAbsent(",", "r43");
        entradasTerminais.get(61).putIfAbsent(")", "r43");
        entradasTerminais.get(61).putIfAbsent("then", "r43");
        entradasTerminais.get(61).putIfAbsent("to", "r43");
        entradasTerminais.get(61).putIfAbsent("do", "r43");
        entradasTerminais.get(61).putIfAbsent("end", "r43");
        entradasTerminais.get(61).putIfAbsent("and", "r43");
        entradasTerminais.get(61).putIfAbsent("or", "r43");
        entradasTerminais.get(61).putIfAbsent("xor", "r43");

        // Estado 62
        entradasTerminais.get(62).putIfAbsent(")", "e89");

        // Estado 63
        entradasTerminais.get(63).putIfAbsent(",", "e90");
        entradasTerminais.get(63).putIfAbsent(")", "r34");
        entradasTerminais.get(63).putIfAbsent("or", "e86");
        entradasTerminais.get(63).putIfAbsent("xor", "e87");

        // Estado 64
        entradasTerminais.get(64).putIfAbsent("begin", "e17");
        entradasTerminais.get(64).putIfAbsent("for", "e34");
        entradasTerminais.get(64).putIfAbsent("if", "e33");
        entradasTerminais.get(64).putIfAbsent("proc", "e32");
        entradasTerminais.get(64).putIfAbsent("variavel", "e31");
        entradasTerminais.get(64).putIfAbsent("while", "e35");

        // Estado 65
        entradasTerminais.get(65).putIfAbsent("+", "e49");
        entradasTerminais.get(65).putIfAbsent("-", "e50");
        entradasTerminais.get(65).putIfAbsent("(", "r57");
        entradasTerminais.get(65).putIfAbsent("numero", "r57");
        entradasTerminais.get(65).putIfAbsent("variavel", "r57");
        entradasTerminais.get(65).putIfAbsent("not", "r57");

        // Estados Relacionais (66 a 71)
        entradasTerminais.get(66).putIfAbsent("(", "r63");
        entradasTerminais.get(66).putIfAbsent("numero", "r63");
        entradasTerminais.get(66).putIfAbsent("variavel", "r63");
        entradasTerminais.get(66).putIfAbsent("not", "r63");
        entradasTerminais.get(67).putIfAbsent("(", "r64");
        entradasTerminais.get(67).putIfAbsent("numero", "r64");
        entradasTerminais.get(67).putIfAbsent("variavel", "r64");
        entradasTerminais.get(67).putIfAbsent("not", "r64");
        entradasTerminais.get(68).putIfAbsent("(", "r65");
        entradasTerminais.get(68).putIfAbsent("numero", "r65");
        entradasTerminais.get(68).putIfAbsent("variavel", "r65");
        entradasTerminais.get(68).putIfAbsent("not", "r65");
        entradasTerminais.get(69).putIfAbsent("(", "r66");
        entradasTerminais.get(69).putIfAbsent("numero", "r66");
        entradasTerminais.get(69).putIfAbsent("variavel", "r66");
        entradasTerminais.get(69).putIfAbsent("not", "r66");
        entradasTerminais.get(70).putIfAbsent("(", "r67");
        entradasTerminais.get(70).putIfAbsent("numero", "r67");
        entradasTerminais.get(70).putIfAbsent("variavel", "r67");
        entradasTerminais.get(70).putIfAbsent("not", "r67");
        entradasTerminais.get(71).putIfAbsent("(", "r68");
        entradasTerminais.get(71).putIfAbsent("numero", "r68");
        entradasTerminais.get(71).putIfAbsent("variavel", "r68");
        entradasTerminais.get(71).putIfAbsent("not", "r68");

        // Estado 72
        entradasTerminais.get(72).putIfAbsent("+", "e94");
        entradasTerminais.get(72).putIfAbsent("-", "e95");
        entradasTerminais.get(72).putIfAbsent(";", "r46");
        entradasTerminais.get(72).putIfAbsent(",", "r46");
        entradasTerminais.get(72).putIfAbsent(")", "r46");
        entradasTerminais.get(72).putIfAbsent("then", "r46");
        entradasTerminais.get(72).putIfAbsent("to", "r46");
        entradasTerminais.get(72).putIfAbsent("do", "r46");
        entradasTerminais.get(72).putIfAbsent("end", "r46");
        entradasTerminais.get(72).putIfAbsent("and", "r46");
        entradasTerminais.get(72).putIfAbsent("or", "r46");
        entradasTerminais.get(72).putIfAbsent("xor", "r46");
        entradasTerminais.get(72).putIfAbsent("=", "r46");
        entradasTerminais.get(72).putIfAbsent("<", "r46");
        entradasTerminais.get(72).putIfAbsent(">", "r46");
        entradasTerminais.get(72).putIfAbsent("<=", "r46");
        entradasTerminais.get(72).putIfAbsent(">=", "r46");
        entradasTerminais.get(72).putIfAbsent("<>", "r46");

        // Estado 73
        entradasTerminais.get(73).putIfAbsent("*", "e97");
        entradasTerminais.get(73).putIfAbsent("/", "e98");
        entradasTerminais.get(73).putIfAbsent("%", "e99");
        entradasTerminais.get(73).putIfAbsent(";", "r48");
        entradasTerminais.get(73).putIfAbsent(",", "r48");
        entradasTerminais.get(73).putIfAbsent(")", "r48");
        entradasTerminais.get(73).putIfAbsent("then", "r48");
        entradasTerminais.get(73).putIfAbsent("to", "r48");
        entradasTerminais.get(73).putIfAbsent("do", "r48");
        entradasTerminais.get(73).putIfAbsent("end", "r48");
        entradasTerminais.get(73).putIfAbsent("and", "r48");
        entradasTerminais.get(73).putIfAbsent("or", "r48");
        entradasTerminais.get(73).putIfAbsent("xor", "r48");
        entradasTerminais.get(73).putIfAbsent("=", "r48");
        entradasTerminais.get(73).putIfAbsent("<", "r48");
        entradasTerminais.get(73).putIfAbsent(">", "r48");
        entradasTerminais.get(73).putIfAbsent("<=", "r48");
        entradasTerminais.get(73).putIfAbsent(">=", "r48");
        entradasTerminais.get(73).putIfAbsent("<>", "r48");
        entradasTerminais.get(73).putIfAbsent("+", "r48");
        entradasTerminais.get(73).putIfAbsent("-", "r48");

        // Estados 74 a 76 (Reduções de F e Term)
        for (int s : new int[]{74, 75, 76}) {
            entradasTerminais.get(s).putIfAbsent(";", "r50"); // ou r51/r52 dependendo do estado
            entradasTerminais.get(s).putIfAbsent("end", "r50");
            entradasTerminais.get(s).putIfAbsent(")", "r50");
            entradasTerminais.get(s).putIfAbsent(",", "r50");
            entradasTerminais.get(s).putIfAbsent("then", "r50");
            entradasTerminais.get(s).putIfAbsent("to", "r50");
            entradasTerminais.get(s).putIfAbsent("do", "r50");
            entradasTerminais.get(s).putIfAbsent("and", "r50");
            entradasTerminais.get(s).putIfAbsent("or", "r50");
            entradasTerminais.get(s).putIfAbsent("xor", "r50");
            entradasTerminais.get(s).putIfAbsent("+", "r50");
            entradasTerminais.get(s).putIfAbsent("-", "r50");
            entradasTerminais.get(s).putIfAbsent("*", "r50");
            entradasTerminais.get(s).putIfAbsent("/", "r50");
            entradasTerminais.get(s).putIfAbsent("%", "r50");
            entradasTerminais.get(s).putIfAbsent("=", "r50");
            entradasTerminais.get(s).putIfAbsent("<", "r50");
            entradasTerminais.get(s).putIfAbsent(">", "r50");
            entradasTerminais.get(s).putIfAbsent("<=", "r50");
            entradasTerminais.get(s).putIfAbsent(">=", "r50");
            entradasTerminais.get(s).putIfAbsent("<>", "r50");
        }

        // Estado 77
        entradasTerminais.get(77).putIfAbsent("(", "e77");
        entradasTerminais.get(77).putIfAbsent("numero", "e75");
        entradasTerminais.get(77).putIfAbsent("variavel", "e76");
        entradasTerminais.get(77).putIfAbsent("not", "e78");

        // Estado 78
        entradasTerminais.get(78).putIfAbsent("(", "e77");
        entradasTerminais.get(78).putIfAbsent("numero", "e75");
        entradasTerminais.get(78).putIfAbsent("variavel", "e76");
        entradasTerminais.get(78).putIfAbsent("not", "e78");

        // Estado 79
        entradasTerminais.get(79).putIfAbsent("(", "e77");
        entradasTerminais.get(79).putIfAbsent("numero", "e75");
        entradasTerminais.get(79).putIfAbsent("variavel", "e76");
        entradasTerminais.get(79).putIfAbsent("not", "e78");

        // Estado 80
        entradasTerminais.get(80).putIfAbsent("begin", "e17");
        entradasTerminais.get(80).putIfAbsent("for", "e34");
        entradasTerminais.get(80).putIfAbsent("if", "e33");
        entradasTerminais.get(80).putIfAbsent("proc", "e32");
        entradasTerminais.get(80).putIfAbsent("variavel", "e31");
        entradasTerminais.get(80).putIfAbsent("while", "e35");

        // Estado 81
        entradasTerminais.get(81).putIfAbsent(";", "e104");

        // Estado 82
        entradasTerminais.get(82).putIfAbsent(";", "r13");
        entradasTerminais.get(82).putIfAbsent("end", "r13");

        // Estado 83
        entradasTerminais.get(83).putIfAbsent("ident", "r19");
        entradasTerminais.get(83).putIfAbsent("var", "e57");

        // Estado 84
        entradasTerminais.get(84).putIfAbsent(":", "e106");

        // Estado 85
        entradasTerminais.get(85).putIfAbsent("+", "e49");
        entradasTerminais.get(85).putIfAbsent("-", "e50");
        entradasTerminais.get(85).putIfAbsent("(", "r57");
        entradasTerminais.get(85).putIfAbsent("numero", "r57");
        entradasTerminais.get(85).putIfAbsent("variavel", "r57");
        entradasTerminais.get(85).putIfAbsent("not", "r57");

        // Estados 86 e 87
        entradasTerminais.get(86).putIfAbsent(";", "r69");
        entradasTerminais.get(86).putIfAbsent("end", "r69");
        entradasTerminais.get(86).putIfAbsent(")", "r69");
        entradasTerminais.get(86).putIfAbsent("then", "r69");
        entradasTerminais.get(86).putIfAbsent("to", "r69");
        entradasTerminais.get(86).putIfAbsent("do", "r69");
        entradasTerminais.get(87).putIfAbsent(";", "r70");
        entradasTerminais.get(87).putIfAbsent("end", "r70");
        entradasTerminais.get(87).putIfAbsent(")", "r70");
        entradasTerminais.get(87).putIfAbsent("then", "r70");
        entradasTerminais.get(87).putIfAbsent("to", "r70");
        entradasTerminais.get(87).putIfAbsent("do", "r70");

        // Estado 88
        entradasTerminais.get(88).putIfAbsent("+", "e49");
        entradasTerminais.get(88).putIfAbsent("-", "e50");
        entradasTerminais.get(88).putIfAbsent("(", "r57");
        entradasTerminais.get(88).putIfAbsent("numero", "r57");
        entradasTerminais.get(88).putIfAbsent("variavel", "r57");
        entradasTerminais.get(88).putIfAbsent("not", "r57");

        // Estado 89
        entradasTerminais.get(89).putIfAbsent(";", "r31");
        entradasTerminais.get(89).putIfAbsent("end", "r31");

        // Estado 90
        entradasTerminais.get(90).putIfAbsent("+", "e49");
        entradasTerminais.get(90).putIfAbsent("-", "e50");
        entradasTerminais.get(90).putIfAbsent("(", "r57");
        entradasTerminais.get(90).putIfAbsent("numero", "r57");
        entradasTerminais.get(90).putIfAbsent("variavel", "r57");
        entradasTerminais.get(90).putIfAbsent("not", "r57");

        // Estado 91
        entradasTerminais.get(91).putIfAbsent("else", "e111");
        entradasTerminais.get(91).putIfAbsent(";", "r37");
        entradasTerminais.get(91).putIfAbsent("end", "r37");

        // Estado 92
        entradasTerminais.get(92).putIfAbsent(";", "r44");
        entradasTerminais.get(92).putIfAbsent("end", "r44");
        entradasTerminais.get(92).putIfAbsent(")", "r44");
        entradasTerminais.get(92).putIfAbsent(",", "r44");
        entradasTerminais.get(92).putIfAbsent("then", "r44");
        entradasTerminais.get(92).putIfAbsent("to", "r44");
        entradasTerminais.get(92).putIfAbsent("do", "r44");
        entradasTerminais.get(92).putIfAbsent("and", "r44");
        entradasTerminais.get(92).putIfAbsent("or", "r44");
        entradasTerminais.get(92).putIfAbsent("xor", "r44");

        // Estado 93
        entradasTerminais.get(93).putIfAbsent("(", "e77");
        entradasTerminais.get(93).putIfAbsent("numero", "e75");
        entradasTerminais.get(93).putIfAbsent("variavel", "e76");
        entradasTerminais.get(93).putIfAbsent("not", "e78");

        // Estados 94 a 99
        entradasTerminais.get(94).putIfAbsent("(", "r58");
        entradasTerminais.get(95).putIfAbsent("(", "r59");
        entradasTerminais.get(96).putIfAbsent("(", "e77");
        entradasTerminais.get(97).putIfAbsent("(", "r60");
        entradasTerminais.get(98).putIfAbsent("(", "r61");
        entradasTerminais.get(99).putIfAbsent("(", "r62");

        // Estado 100
        entradasTerminais.get(100).putIfAbsent(")", "e114");
        entradasTerminais.get(100).putIfAbsent("or", "e86");
        entradasTerminais.get(100).putIfAbsent("xor", "e87");

        // Estado 101
        entradasTerminais.get(101).putIfAbsent(";", "r54");
        entradasTerminais.get(101).putIfAbsent("end", "r54");

        // Estado 102
        entradasTerminais.get(102).putIfAbsent("do", "e115");

        // Estado 103
        entradasTerminais.get(103).putIfAbsent(";", "r39");
        entradasTerminais.get(103).putIfAbsent("end", "r39");

        // Estado 104
        entradasTerminais.get(104).putIfAbsent("begin", "r12");
        entradasTerminais.get(104).putIfAbsent("procedure", "r12");

        // Estado 105
        entradasTerminais.get(105).putIfAbsent(";", "r15");
        entradasTerminais.get(105).putIfAbsent("end", "r15");

        // Estado 106
        entradasTerminais.get(106).putIfAbsent("tipo", "e116");

        // Estado 107
        entradasTerminais.get(107).putIfAbsent("and", "e88");
        entradasTerminais.get(107).putIfAbsent(";", "r40");
        entradasTerminais.get(107).putIfAbsent("end", "r40");

        // Estado 108
        entradasTerminais.get(108).putIfAbsent(";", "r42");
        entradasTerminais.get(108).putIfAbsent("end", "r42");

        // Estado 109
        entradasTerminais.get(109).putIfAbsent(")", "r33");

        // Estado 110
        entradasTerminais.get(110).putIfAbsent(";", "r35");
        entradasTerminais.get(110).putIfAbsent("end", "r35");

        // Estado 111
        entradasTerminais.get(111).putIfAbsent("begin", "e17");
        entradasTerminais.get(111).putIfAbsent("for", "e34");
        entradasTerminais.get(111).putIfAbsent("if", "e33");
        entradasTerminais.get(111).putIfAbsent("proc", "e32");
        entradasTerminais.get(111).putIfAbsent("variavel", "e31");
        entradasTerminais.get(111).putIfAbsent("while", "e35");

        // Estado 112
        entradasTerminais.get(112).putIfAbsent(";", "r47");
        entradasTerminais.get(112).putIfAbsent("end", "r47");

        // Estados 113 a 118 (Reduções finais)
        for (int s = 113; s <= 118; s++) {
            if (s == 115) {
                entradasTerminais.get(s).putIfAbsent("begin", "e17");
                entradasTerminais.get(s).putIfAbsent("for", "e34");
                entradasTerminais.get(s).putIfAbsent("if", "e33");
                entradasTerminais.get(s).putIfAbsent("proc", "e32");
                entradasTerminais.get(s).putIfAbsent("variavel", "e31");
                entradasTerminais.get(s).putIfAbsent("while", "e35");
            } else if (s == 117) {
                entradasTerminais.get(s).putIfAbsent(";", "r36");
                entradasTerminais.get(s).putIfAbsent("end", "r36");
            } else if (s == 118) {
                entradasTerminais.get(s).putIfAbsent(";", "r38");
                entradasTerminais.get(s).putIfAbsent("end", "r38");
            } else {
                entradasTerminais.get(s).putIfAbsent(";", "r53"); // r53/r60/r61/r62/etc.
                entradasTerminais.get(s).putIfAbsent("end", "r53");
            }
        }
    }

    private void preencherNaoTerminais() {
        entradasNaoTerminais.get(0).putIfAbsent("P", 1);
        entradasNaoTerminais.get(4).putIfAbsent("B", 5);
        entradasNaoTerminais.get(4).putIfAbsent("VAR_LIST", 6);
        entradasNaoTerminais.get(6).putIfAbsent("PROC", 10);
        entradasNaoTerminais.get(6).putIfAbsent("PROC_LIST", 9);
        entradasNaoTerminais.get(7).putIfAbsent("ID_LIST", 14);
        entradasNaoTerminais.get(7).putIfAbsent("VAR_DECL", 13);
        entradasNaoTerminais.get(7).putIfAbsent("VAR_DECL_LIST", 12);
        entradasNaoTerminais.get(9).putIfAbsent("C_SEC", 16);
        entradasNaoTerminais.get(10).putIfAbsent("PROC", 10);
        entradasNaoTerminais.get(10).putIfAbsent("PROC_LIST", 18);
        entradasNaoTerminais.get(11).putIfAbsent("PA_LIST", 36);
        entradasNaoTerminais.get(17).putIfAbsent("C", 24);
        entradasNaoTerminais.get(17).putIfAbsent("C_LIST", 23);
        entradasNaoTerminais.get(17).putIfAbsent("C_SEC", 30);
        entradasNaoTerminais.get(17).putIfAbsent("FOR", 28);
        entradasNaoTerminais.get(17).putIfAbsent("I", 27);
        entradasNaoTerminais.get(17).putIfAbsent("PROC_CA", 26);
        entradasNaoTerminais.get(17).putIfAbsent("VAR_ATR", 25);
        entradasNaoTerminais.get(17).putIfAbsent("W", 29);
        entradasNaoTerminais.get(20).putIfAbsent("ID_LIST", 14);
        entradasNaoTerminais.get(20).putIfAbsent("VAR_DECL", 13);
        entradasNaoTerminais.get(20).putIfAbsent("VAR_DECL_LIST", 38);
        entradasNaoTerminais.get(22).putIfAbsent("ID_LIST", 40);
        entradasNaoTerminais.get(34).putIfAbsent("VAR_ATR", 51);
        entradasNaoTerminais.get(37).putIfAbsent("PA_DECL", 55);
        entradasNaoTerminais.get(37).putIfAbsent("PA_DECL_LIST", 54);
        entradasNaoTerminais.get(37).putIfAbsent("VAR_OPT", 56);
        entradasNaoTerminais.get(42).putIfAbsent("C", 24);
        entradasNaoTerminais.get(42).putIfAbsent("C_LIST", 58);
        entradasNaoTerminais.get(42).putIfAbsent("C_SEC", 30);
        entradasNaoTerminais.get(42).putIfAbsent("FOR", 28);
        entradasNaoTerminais.get(42).putIfAbsent("I", 27);
        entradasNaoTerminais.get(42).putIfAbsent("PROC_CA", 26);
        entradasNaoTerminais.get(42).putIfAbsent("VAR_ATR", 25);
        entradasNaoTerminais.get(42).putIfAbsent("W", 29);
        entradasNaoTerminais.get(43).putIfAbsent("E", 61);
        entradasNaoTerminais.get(43).putIfAbsent("EXPR", 59);
        entradasNaoTerminais.get(43).putIfAbsent("LOG_TERM", 60);
        entradasNaoTerminais.get(43).putIfAbsent("SI_E", 47);
        entradasNaoTerminais.get(43).putIfAbsent("SIGN_OPT", 48);
        entradasNaoTerminais.get(45).putIfAbsent("E", 61);
        entradasNaoTerminais.get(45).putIfAbsent("E_LIST", 62);
        entradasNaoTerminais.get(45).putIfAbsent("EXPR", 63);
        entradasNaoTerminais.get(45).putIfAbsent("LOG_TERM", 60);
        entradasNaoTerminais.get(45).putIfAbsent("SI_E", 47);
        entradasNaoTerminais.get(45).putIfAbsent("SIGN_OPT", 48);
        entradasNaoTerminais.get(47).putIfAbsent("REL_OP", 65);
        entradasNaoTerminais.get(48).putIfAbsent("F", 74);
        entradasNaoTerminais.get(48).putIfAbsent("SI_E_TAIL", 72);
        entradasNaoTerminais.get(48).putIfAbsent("TERM", 73);
        entradasNaoTerminais.get(53).putIfAbsent("B", 81);
        entradasNaoTerminais.get(53).putIfAbsent("VAR_LIST", 6);
        entradasNaoTerminais.get(56).putIfAbsent("ID_LIST", 84);
        entradasNaoTerminais.get(64).putIfAbsent("C", 91);
        entradasNaoTerminais.get(64).putIfAbsent("C_SEC", 30);
        entradasNaoTerminais.get(64).putIfAbsent("FOR", 28);
        entradasNaoTerminais.get(64).putIfAbsent("I", 27);
        entradasNaoTerminais.get(64).putIfAbsent("PROC_CA", 26);
        entradasNaoTerminais.get(64).putIfAbsent("VAR_ATR", 25);
        entradasNaoTerminais.get(64).putIfAbsent("W", 29);
        entradasNaoTerminais.get(65).putIfAbsent("SI_E", 92);
        entradasNaoTerminais.get(65).putIfAbsent("SIGN_OPT", 48);
        entradasNaoTerminais.get(72).putIfAbsent("ADD_OP", 93);
        entradasNaoTerminais.get(73).putIfAbsent("MULT_OP", 96);
        entradasNaoTerminais.get(77).putIfAbsent("E", 61);
        entradasNaoTerminais.get(77).putIfAbsent("EXPR", 100);
        entradasNaoTerminais.get(77).putIfAbsent("LOG_TERM", 60);
        entradasNaoTerminais.get(77).putIfAbsent("SI_E", 47);
        entradasNaoTerminais.get(77).putIfAbsent("SIGN_OPT", 48);
        entradasNaoTerminais.get(78).putIfAbsent("F", 101);
        entradasNaoTerminais.get(79).putIfAbsent("F", 102);
        entradasNaoTerminais.get(80).putIfAbsent("C", 103);
        entradasNaoTerminais.get(80).putIfAbsent("C_SEC", 30);
        entradasNaoTerminais.get(80).putIfAbsent("FOR", 28);
        entradasNaoTerminais.get(80).putIfAbsent("I", 27);
        entradasNaoTerminais.get(80).putIfAbsent("PROC_CA", 26);
        entradasNaoTerminais.get(80).putIfAbsent("VAR_ATR", 25);
        entradasNaoTerminais.get(80).putIfAbsent("W", 29);
        entradasNaoTerminais.get(83).putIfAbsent("PA_DECL", 55);
        entradasNaoTerminais.get(83).putIfAbsent("PA_DECL_LIST", 105);
        entradasNaoTerminais.get(83).putIfAbsent("VAR_OPT", 56);
        entradasNaoTerminais.get(85).putIfAbsent("E", 61);
        entradasNaoTerminais.get(85).putIfAbsent("LOG_TERM", 107);
        entradasNaoTerminais.get(85).putIfAbsent("SI_E", 47);
        entradasNaoTerminais.get(85).putIfAbsent("SIGN_OPT", 48);
        entradasNaoTerminais.get(88).putIfAbsent("E", 108);
        entradasNaoTerminais.get(88).putIfAbsent("SI_E", 47);
        entradasNaoTerminais.get(88).putIfAbsent("SIGN_OPT", 48);
        entradasNaoTerminais.get(90).putIfAbsent("E", 61);
        entradasNaoTerminais.get(90).putIfAbsent("E_LIST", 109);
        entradasNaoTerminais.get(90).putIfAbsent("EXPR", 63);
        entradasNaoTerminais.get(90).putIfAbsent("LOG_TERM", 60);
        entradasNaoTerminais.get(90).putIfAbsent("SI_E", 47);
        entradasNaoTerminais.get(90).putIfAbsent("SIGN_OPT", 48);
        entradasNaoTerminais.get(91).putIfAbsent("ELSE_OPT", 110);
        entradasNaoTerminais.get(93).putIfAbsent("F", 74);
        entradasNaoTerminais.get(93).putIfAbsent("TERM", 112);
        entradasNaoTerminais.get(96).putIfAbsent("F", 113);
        entradasNaoTerminais.get(111).putIfAbsent("C", 117);
        entradasNaoTerminais.get(111).putIfAbsent("C_SEC", 30);
        entradasNaoTerminais.get(111).putIfAbsent("FOR", 28);
        entradasNaoTerminais.get(111).putIfAbsent("I", 27);
        entradasNaoTerminais.get(111).putIfAbsent("PROC_CA", 26);
        entradasNaoTerminais.get(111).putIfAbsent("VAR_ATR", 25);
        entradasNaoTerminais.get(111).putIfAbsent("W", 29);
        entradasNaoTerminais.get(115).putIfAbsent("C", 118);
        entradasNaoTerminais.get(115).putIfAbsent("C_SEC", 30);
        entradasNaoTerminais.get(115).putIfAbsent("FOR", 28);
        entradasNaoTerminais.get(115).putIfAbsent("I", 27);
        entradasNaoTerminais.get(115).putIfAbsent("PROC_CA", 26);
        entradasNaoTerminais.get(115).putIfAbsent("VAR_ATR", 25);
        entradasNaoTerminais.get(115).putIfAbsent("W", 29);
    }
}