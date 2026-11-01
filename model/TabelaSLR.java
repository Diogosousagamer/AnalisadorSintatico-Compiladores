/* ***************************************************************
* Autores............: Davi Gabrielli Santos
                       Diogo Oliveira de Sousa
                       Gustavo Henrique Oliveira Fernandes
* Matricula..........: 202410855 / 202411226 / 202410104
* Inicio.............: 21/10/2026
* Ultima alteracao...: 21/10/2026
* Nome...............: TabelaSLR
* Funcao.............: Classe que designa as operacoes da tabela SLR da gramatica do Analisador Sintatico do Mini Pascal.
                     
*************************************************************** */

package model;

import java.util.ArrayList;
import java.util.Hashtable;

public class TabelaSLR {
    private Hashtable<Integer, Hashtable<String, String>> entradasTerminais;
    private Hashtable<Integer, Hashtable<String, Integer>> entradasTransicoes;

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

         preencherTerminais():
         preencherNaoTerminais();
    }
   
    private void preencherTerminais() {
        // Entradas do estado 0
        entradasTerminais.get(0).putIfAbsent("program", "e2");

        // Entradas do estado 1
        entradasTerminais.get(1).putIfAbsent("$", "OK");

        // Entradas do estado 2
        entradasTerminais.get(2).putIfAbsent("ident", "e3");

        // Entradas do estado 3
        entradasTerminais.get(3).putIfAbsent(";", "e4");

        // Entradas do estado 4
        entradasTerminais.get(4).putIfAbsent("var", "e4");
        entradasTerminais.get(4).putIfAbsent("procedure", "r4");
         entradasTerminais.get(4).putIfAbsent("begin", "r4");

        // Entradas do estado 5
        entradasTerminais.get(5).putIfAbsent(".", "e5");

        // Entradas do estado 6
        entradasTerminais.get(6).putIfAbsent("procedure", "e11");
        entradasTerminais.get(6).putIfAbsent("begin", "r11");

        // Entradas do estado 7
        entradasTerminais.get(7).putIfAbsent("ident", "e15");

        // Entradas do estado 8
        entradasTerminais.get(8).putIfAbsent("$", "r1");

        // Entradas do estado 9
        entradasTerminais.get(9).putIfAbsent("begin", "e17");

        // Entradas do estado 10
        entradasTerminais.get(10).putIfAbsent("procedure", "e11");
        entradasTerminais.get(10).putIfAbsent("begin", "r11");

        // Entradas do estado 11
        entradasTerminais.get(11).putIfAbsent("ident", "e19");

       // Entradas do estado 12
       entradasTerminais.get(12).putIfAbsent(":", "r19");
       entradasTerminais.get(12).putIfAbsent(",", "e20");

       // Entradas do estado 13
       entradasTerminais.get(13).putIfAbsent("procedure", "r3");
       entradasTerminais.get(13).putIfAbsent("begin", "r3");

       // Entradas do estado 14
       entradasTerminais.get(14).putIfAbsent(";", "e21");

       // Entradas do estado 15
       entradasTerminais.get(15).putIfAbsent(":", "e22");

       // Entradas do estado 16
       entradasTerminais.get(16).putIfAbsent(";", "r2");
       entradasTerminais.get(16).putIfAbsent(“.”, "r2");

       // Entradas do estado 17
       entradasTerminais.get(17).putIfAbsent("begin", "e17");
       entradasTerminais.get(17).putIfAbsent("variavel", "e31");
       entradasTerminais.get(17).putIfAbsent("proc", "e32");
       entradasTerminais.get(17).putIfAbsent("if", "e33");
       entradasTerminais.get(17).putIfAbsent("for", "e34");
       entradasTerminais.get(17).putIfAbsent("while", "e35");

       // Entradas do estado 18
       entradasTerminais.get(18).putIfAbsent("begin", "r10");

        // Entradas do estado 19
       entradasTerminais.get(19).putIfAbsent(";", "r14");
       entradasTerminais.get(19).putIfAbsent("(", "e37");

       // Entradas do estado 20
       entradasTerminais.get(20).putIfAbsent("ident", "e12");

       // Entradas do estado 21
       entradasTerminais.get(21).putIfAbsent("ident", "e12");
       entradasTerminais.get(21).putIfAbsent("procedure", "r6");
       entradasTerminais.get(21).putIfAbsent("begin", "r6");

       // Entradas do estado 22
       entradasTerminais.get(22).putIfAbsent("tipo", "e40");

       // Entradas do estado 23
       entradasTerminais.get(23).putIfAbsent(";", "r28");

       // Entradas do estado 24
       entradasTerminais.get(24).putIfAbsent("end", "e41");

       // Entradas do estado 25
       entradasTerminais.get(25).putIfAbsent(";", "e42");

        // Entradas do estado 26
        entradasTerminais.get(26).putIfAbsent(";", "r23");

        // Entradas do estado 27
        entradasTerminais.get(27).putIfAbsent(";", "r24");

        // Entradas do estado 28
        entradasTerminais.get(28).putIfAbsent(";", "r25");

        // Entradas do estado 29
        entradasTerminais.get(29).putIfAbsent(";", "r26");

        // Entradas do estado 30
        entradasTerminais.get(30).putIfAbsent(";", "r27");

        // Entradas do estado 31
        entradasTerminais.get(31).putIfAbsent(":=", "e43");

        // Entradas do estado 32
        entradasTerminais.get(32).putIfAbsent("(", "e44");
        entradasTerminais.get(32).putIfAbsent(";", "r32");

        // Entradas do estado 33
        entradasTerminais.get(33).putIfAbsent("+", "e49");
        entradasTerminais.get(33).putIfAbsent("-", "e50");
        entradasTerminais.get(33).putIfAbsent("(", "r57");
        entradasTerminais.get(33).putIfAbsent("variavel", "r57");
        entradasTerminais.get(33).putIfAbsent("numero", "r57");
        entradasTerminais.get(33).putIfAbsent("not", "r57");

        // Entradas do estado 34
        entradasTerminais.get(34).putIfAbsent("variavel", "e31");

        // Entradas do estado 35
        entradasTerminais.get(35).putIfAbsent("+", "e49");
        entradasTerminais.get(35).putIfAbsent("-", "e50");
        entradasTerminais.get(35).putIfAbsent("(", "r57");
        entradasTerminais.get(35).putIfAbsent("variavel", "r57");
        entradasTerminais.get(35).putIfAbsent("numero", "r57");
        entradasTerminais.get(35).putIfAbsent("not", "r57");

        // Entradas do estado 36
        entradasTerminais.get(36).putIfAbsent(";", "e53");

        // Entradas do estado 37
        entradasTerminais.get(37).putIfAbsent("var", "e54");
        entradasTerminais.get(37).putIfAbsent("ident", "r19");

        // Entradas do estado 38
        entradasTerminais.get(38).putIfAbsent(":", "r8");

        // Entradas do estado 39
        entradasTerminais.get(39).putIfAbsent("procedure", "r5");
        entradasTerminais.get(39).putIfAbsent("begin", "r5");

        // Entradas do estado 40
        entradasTerminais.get(40).putIfAbsent(";", "r7");

        // Entradas do estado 41
        entradasTerminais.get(41).putIfAbsent(";", "r20");
        entradasTerminais.get(41).putIfAbsent(".", "r20");

        // Entradas do estado 42
        entradasTerminais.get(42).putIfAbsent("begin", "e17");
        entradasTerminais.get(42).putIfAbsent("end", "r22");
        entradasTerminais.get(42).putIfAbsent("variavel", "e31");
        entradasTerminais.get(42).putIfAbsent("proc", "e32");
        entradasTerminais.get(42).putIfAbsent("if", "e33");
        entradasTerminais.get(42).putIfAbsent("for", "e34");
        entradasTerminais.get(42).putIfAbsent("while", "e35");

        // Entradas do estado 43
        entradasTerminais.get(43).putIfAbsent("+", "e49");
        entradasTerminais.get(43).putIfAbsent("-", "e50");
        entradasTerminais.get(43).putIfAbsent("(", "r57");
        entradasTerminais.get(43).putIfAbsent("variavel", "r57");
        entradasTerminais.get(43).putIfAbsent("numero", "r57");
        entradasTerminais.get(43).putIfAbsent("not", "r57");

        // Entradas do estado 44
        entradasTerminais.get(44).putIfAbsent("+", "e49");
        entradasTerminais.get(44).putIfAbsent("-", "e50");
        entradasTerminais.get(44).putIfAbsent("(", "r57");
        entradasTerminais.get(44).putIfAbsent("variavel", "r57");
        entradasTerminais.get(44).putIfAbsent("numero", "r57");
        entradasTerminais.get(44).putIfAbsent("not", "r57");

        // Entradas do estado 45
        entradasTerminais.get(45).putIfAbsent(";", "r30");

        // Entradas do estado 46
        entradasTerminais.get(46).putIfAbsent("then", "e64");

        // Entradas do estado 47
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

        // Entradas do estado 48
        entradasTerminais.get(48).putIfAbsent("(", "e72");
        entradasTerminais.get(48).putIfAbsent("variavel", "e73");
        entradasTerminais.get(48).putIfAbsent("numero", "e77");
        entradasTerminais.get(48).putIfAbsent("not", "e78");

        // Entradas do estado 49
        entradasTerminais.get(49).putIfAbsent("(", "r55");
        entradasTerminais.get(49).putIfAbsent("variavel", "r55");
        entradasTerminais.get(49).putIfAbsent("numero", "r55");
        entradasTerminais.get(49).putIfAbsent("not", "r55");

        // Entradas do estado 50
        entradasTerminais.get(50).putIfAbsent("(", "r56");
        entradasTerminais.get(50).putIfAbsent("variavel", "r56");
        entradasTerminais.get(50).putIfAbsent("numero", "r56");
        entradasTerminais.get(50).putIfAbsent("not", "r56");

        // Entradas do estado 51
        entradasTerminais.get(51).putIfAbsent("to", "e79");

        // Entradas do estado 52
        entradasTerminais.get(52).putIfAbsent("do", "e80");

        // Entradas do estado 53
        entradasTerminais.get(53).putIfAbsent("var", "e7");
        entradasTerminais.get(53).putIfAbsent("procedure", "r4");
        entradasTerminais.get(53).putIfAbsent("begin", "r4");

        // Entradas do estado 54
        entradasTerminais.get(54).putIfAbsent("ident", "r18");

        // Entradas do estado 55
        entradasTerminais.get(55).putIfAbsent(")", "e82");

        // Entradas do estado 56
        entradasTerminais.get(56).putIfAbsent(";", "e83");
        entradasTerminais.get(56).putIfAbsent(")", "r16");

        // Entradas do estado 57
        entradasTerminais.get(57).putIfAbsent("ident", "e12");

        // Entradas do estado 58
        entradasTerminais.get(58).putIfAbsent("end", "r21");

        // Entradas do estado 59
        entradasTerminais.get(59).putIfAbsent(";", "r29");
        entradasTerminais.get(59).putIfAbsent("to", "r29");
        entradasTerminais.get(59).putIfAbsent("or", "e86");
        entradasTerminais.get(59).putIfAbsent("xor", "e87");

       // Entradas do estado 60
       entradasTerminais.get(60).putIfAbsent(";", "r43");
       entradasTerminais.get(60).putIfAbsent(",", "r43");
       entradasTerminais.get(60).putIfAbsent(")", "r43");
       entradasTerminais.get(60).putIfAbsent("to", "r43");
       entradasTerminais.get(60).putIfAbsent("and", "r43");
       entradasTerminais.get(60).putIfAbsent("or", "r43");
       entradasTerminais.get(60).putIfAbsent("xor", "r43");
      
       //Entradas do estado 61
       entradasTerminais.get(61).putIfAbsent(";", "r41");
       entradasTerminais.get(61).putIfAbsent(",", "r41");
       entradasTerminais.get(61).putIfAbsent(")", "r41");
       entradasTerminais.get(61).putIfAbsent("to", "r41");
       entradasTerminais.get(61).putIfAbsent("and", "e88");
       entradasTerminais.get(61).putIfAbsent("or", "r41");
       entradasTerminais.get(61).putIfAbsent("xor", "r41");
       entradasTerminais.get(61).putIfAbsent(",", "e89");
       entradasTerminais.get(61).putIfAbsent(",", "r34");
       entradasTerminais.get(61).putIfAbsent("or", "e89");
       entradasTerminais.get(61).putIfAbsent("xor", "e87");
       
       //Entradas do estado 62
       entradasTerminais.get(62).putIfAbsent(";", "e89");
       entradasTerminais.get(62).putIfAbsent(")", "r34");
       entradasTerminais.get(62).putIfAbsent("or", "e86");
       entradasTerminais.get(62).putIfAbsent("xor", "e87");

       // Entradas do estado 63
        entradasTerminais.get(63).putIfAbsent(")", "e90");
        // Estado 64
        entradasTerminais.get(64).putIfAbsent("begin", "e17");
        entradasTerminais.get(64).putIfAbsent("variavel", "e31");
        entradasTerminais.get(64).putIfAbsent("proc", "e32");
        entradasTerminais.get(64).putIfAbsent("if", "e33");
        entradasTerminais.get(64).putIfAbsent("for", "e34");
        entradasTerminais.get(64).putIfAbsent("while", "e35");

        // Estado 65
        entradasTerminais.get(65).putIfAbsent("begin", "e18");
        entradasTerminais.get(65).putIfAbsent("variavel", "e32");
        entradasTerminais.get(65).putIfAbsent("proc", "e33");
        entradasTerminais.get(65).putIfAbsent("if", "e34");
        entradasTerminais.get(65).putIfAbsent("for", "e35");
        entradasTerminais.get(65).putIfAbsent("while", "e36");
        entradasTerminais.get(65).putIfAbsent("xor", "e87");

        // Estado 66
        entradasTerminais.get(66).putIfAbsent("(", "r63");
        entradasTerminais.get(66).putIfAbsent("variavel", "r63");
        entradasTerminais.get(66).putIfAbsent("numero", "r63");
        entradasTerminais.get(66).putIfAbsent("not", "r63");
        entradasTerminais.get(66).putIfAbsent("\"+\"", "r63");
        entradasTerminais.get(66).putIfAbsent("-", "r63");

        // Estado 67
        entradasTerminais.get(67).putIfAbsent("(", "r64");
        entradasTerminais.get(67).putIfAbsent("variavel", "r64");
        entradasTerminais.get(67).putIfAbsent("numero", "r64");
        entradasTerminais.get(67).putIfAbsent("not", "r64");
        entradasTerminais.get(67).putIfAbsent("\"+\"", "r64");
        entradasTerminais.get(67).putIfAbsent("-", "r64");

        // Estado 68
        entradasTerminais.get(68).putIfAbsent("(", "r65");
        entradasTerminais.get(68).putIfAbsent("variavel", "r65");
        entradasTerminais.get(68).putIfAbsent("numero", "r65");
        entradasTerminais.get(68).putIfAbsent("not", "r65");
        entradasTerminais.get(68).putIfAbsent("\"+\"", "r65");
        entradasTerminais.get(68).putIfAbsent("-", "r65");

        // Estado 69
        entradasTerminais.get(69).putIfAbsent("(", "r66");
        entradasTerminais.get(69).putIfAbsent("variavel", "r66");
        entradasTerminais.get(69).putIfAbsent("numero", "r66");
        entradasTerminais.get(69).putIfAbsent("not", "r66");
        entradasTerminais.get(69).putIfAbsent("\"+\"", "r66");
        entradasTerminais.get(69).putIfAbsent("-", "r66");

        // Estado 70
        entradasTerminais.get(70).putIfAbsent("(", "r67");
        entradasTerminais.get(70).putIfAbsent("variavel", "r67");
        entradasTerminais.get(70).putIfAbsent("numero", "r67");
        entradasTerminais.get(70).putIfAbsent("not", "r67");
        entradasTerminais.get(70).putIfAbsent("\"+\"", "r67");
        entradasTerminais.get(70).putIfAbsent("-", "r67");

        // Estado 71
        entradasTerminais.get(71).putIfAbsent("(", "r68");
        entradasTerminais.get(71).putIfAbsent("variavel", "r68");
        entradasTerminais.get(71).putIfAbsent("numero", "r68");
        entradasTerminais.get(71).putIfAbsent("not", "r68");
        entradasTerminais.get(71).putIfAbsent("\"+\"", "r68");
        entradasTerminais.get(71).putIfAbsent("-", "r68");

        // Estado 72
        entradasTerminais.get(72).putIfAbsent("(", "r57");
        entradasTerminais.get(72).putIfAbsent("variavel", "r57");
        entradasTerminais.get(72).putIfAbsent("numero", "r57");
        entradasTerminais.get(72).putIfAbsent("not", "r57");
        entradasTerminais.get(72).putIfAbsent("\"+\"", "e49");
        entradasTerminais.get(72).putIfAbsent("-", "e50");

        // Estado 73
        entradasTerminais.get(73).putIfAbsent(";", "r52");
        entradasTerminais.get(73).putIfAbsent(",", "r52");
        entradasTerminais.get(73).putIfAbsent(")", "r52");
        entradasTerminais.get(73).putIfAbsent("then", "r52");
        entradasTerminais.get(73).putIfAbsent("to", "r52");
        entradasTerminais.get(73).putIfAbsent("do", "r52");
        entradasTerminais.get(73).putIfAbsent("and", "r52");
        entradasTerminais.get(73).putIfAbsent("\"+\"", "r52");
        entradasTerminais.get(73).putIfAbsent("-", "r52");
        entradasTerminais.get(73).putIfAbsent("*", "r52");
        entradasTerminais.get(73).putIfAbsent("/", "r52");
        entradasTerminais.get(73).putIfAbsent("%", "r52");
        entradasTerminais.get(73).putIfAbsent("=", "r52");
        entradasTerminais.get(73).putIfAbsent("<", "r52");
        entradasTerminais.get(73).putIfAbsent(">", "r52");
        entradasTerminais.get(73).putIfAbsent("<=", "r52");
        entradasTerminais.get(73).putIfAbsent(">=", "r52");
        entradasTerminais.get(73).putIfAbsent("<>", "r52");
        entradasTerminais.get(73).putIfAbsent("or", "r52");
        entradasTerminais.get(73).putIfAbsent("xor", "r52");

        // Estado 74
        entradasTerminais.get(74).putIfAbsent(";", "r50");
        entradasTerminais.get(74).putIfAbsent(",", "r50");
        entradasTerminais.get(74).putIfAbsent(")", "r50");
        entradasTerminais.get(74).putIfAbsent("then", "r50");
        entradasTerminais.get(74).putIfAbsent("to", "r50");
        entradasTerminais.get(74).putIfAbsent("do", "r50");
        entradasTerminais.get(74).putIfAbsent("and", "r50");
        entradasTerminais.get(74).putIfAbsent("\"+\"", "r50");
        entradasTerminais.get(74).putIfAbsent("-", "r50");
        entradasTerminais.get(74).putIfAbsent("*", "r50");
        entradasTerminais.get(74).putIfAbsent("/", "r50");
        entradasTerminais.get(74).putIfAbsent("%", "r50");
        entradasTerminais.get(74).putIfAbsent("=", "r50");
        entradasTerminais.get(74).putIfAbsent("<", "r50");
        entradasTerminais.get(74).putIfAbsent(">", "r50");
        entradasTerminais.get(74).putIfAbsent("<=", "r50");
        entradasTerminais.get(74).putIfAbsent(">=", "r50");
        entradasTerminais.get(74).putIfAbsent("<>", "r50");
        entradasTerminais.get(74).putIfAbsent("or", "r50");
        entradasTerminais.get(74).putIfAbsent("xor", "r50");

        // Estado 75
        entradasTerminais.get(75).putIfAbsent(";", "r46");
        entradasTerminais.get(75).putIfAbsent(",", "r46");
        entradasTerminais.get(75).putIfAbsent(")", "r46");
        entradasTerminais.get(75).putIfAbsent("then", "r46");
        entradasTerminais.get(75).putIfAbsent("to", "r46");
        entradasTerminais.get(75).putIfAbsent("do", "r46");
        entradasTerminais.get(75).putIfAbsent("and", "r46");
        entradasTerminais.get(75).putIfAbsent("\"+\"", "e95");
        entradasTerminais.get(75).putIfAbsent("-", "e96");
        entradasTerminais.get(75).putIfAbsent("=", "r46");
        entradasTerminais.get(75).putIfAbsent("<", "r46");
        entradasTerminais.get(75).putIfAbsent(">", "r46");
        entradasTerminais.get(75).putIfAbsent("<=", "r46");
        entradasTerminais.get(75).putIfAbsent(">=", "r46");
        entradasTerminais.get(75).putIfAbsent("<>", "r46");
        entradasTerminais.get(75).putIfAbsent("or", "r46");
        entradasTerminais.get(75).putIfAbsent("xor", "r46");

        // Estado 76
        entradasTerminais.get(76).putIfAbsent(";", "r48");
        entradasTerminais.get(76).putIfAbsent(",", "r48");
        entradasTerminais.get(76).putIfAbsent(")", "r48");
        entradasTerminais.get(76).putIfAbsent("then", "r48");
        entradasTerminais.get(76).putIfAbsent("to", "r48");
        entradasTerminais.get(76).putIfAbsent("do", "r48");
        entradasTerminais.get(76).putIfAbsent("and", "r48");
        entradasTerminais.get(76).putIfAbsent("\"+\"", "r48");
        entradasTerminais.get(76).putIfAbsent("-", "r48");
        entradasTerminais.get(76).putIfAbsent("*", "e98");
        entradasTerminais.get(76).putIfAbsent("/", "e99");
        entradasTerminais.get(76).putIfAbsent("%", "e100");
        entradasTerminais.get(76).putIfAbsent("=", "r48");
        entradasTerminais.get(76).putIfAbsent("<", "r48");
        entradasTerminais.get(76).putIfAbsent(">", "r48");
        entradasTerminais.get(76).putIfAbsent("<=", "r48");
        entradasTerminais.get(76).putIfAbsent(">=", "r48");
        entradasTerminais.get(76).putIfAbsent("<>", "r48");
        entradasTerminais.get(76).putIfAbsent("or", "r48");
        entradasTerminais.get(76).putIfAbsent("xor", "r48");

        // Estado 77
        entradasTerminais.get(77).putIfAbsent(";", "r51");
        entradasTerminais.get(77).putIfAbsent(",", "r51");
        entradasTerminais.get(77).putIfAbsent(")", "r51");
        entradasTerminais.get(77).putIfAbsent("then", "r51");
        entradasTerminais.get(77).putIfAbsent("to", "r51");
        entradasTerminais.get(77).putIfAbsent("do", "r51");
        entradasTerminais.get(77).putIfAbsent("and", "r51");
        entradasTerminais.get(77).putIfAbsent("\"+\"", "r51");
        entradasTerminais.get(77).putIfAbsent("-", "r51");
        entradasTerminais.get(77).putIfAbsent("*", "r51");
        entradasTerminais.get(77).putIfAbsent("/", "r51");
        entradasTerminais.get(77).putIfAbsent("%", "r51");
        entradasTerminais.get(77).putIfAbsent("=", "r51");
        entradasTerminais.get(77).putIfAbsent("<", "r51");
        entradasTerminais.get(77).putIfAbsent(">", "r51");
        entradasTerminais.get(77).putIfAbsent("<=", "r51");
        entradasTerminais.get(77).putIfAbsent(">=", "r51");
        entradasTerminais.get(77).putIfAbsent("<>", "r51");
        entradasTerminais.get(77).putIfAbsent("or", "r51");
        entradasTerminais.get(77).putIfAbsent("xor", "r51");

        // Estado 78
        entradasTerminais.get(78).putIfAbsent("(", "e72");
        entradasTerminais.get(78).putIfAbsent("variavel", "e73");
        entradasTerminais.get(78).putIfAbsent("numero", "e77");
        entradasTerminais.get(78).putIfAbsent("not", "e78");

        // Estado 79
        entradasTerminais.get(79).putIfAbsent("(", "e72");
        entradasTerminais.get(79).putIfAbsent("variavel", "e73");
        entradasTerminais.get(79).putIfAbsent("numero", "e77");
        entradasTerminais.get(79).putIfAbsent("not", "e78");

        // Estado 80
        entradasTerminais.get(80).putIfAbsent("begin", "e17");
        entradasTerminais.get(80).putIfAbsent("variavel", "e31");
        entradasTerminais.get(80).putIfAbsent("proc", "e32");
        entradasTerminais.get(80).putIfAbsent("if", "e33");
        entradasTerminais.get(80).putIfAbsent("for", "e34");
        entradasTerminais.get(80).putIfAbsent("while", "e35");

        // Estado 81
        entradasTerminais.get(81).putIfAbsent(";", "e104");

        // Estado 82
        entradasTerminais.get(82).putIfAbsent(";", "r13");

        // Estado 83
        entradasTerminais.get(83).putIfAbsent("ident", "r19");
        entradasTerminais.get(83).putIfAbsent("var", "e54");

        // Estado 84
        entradasTerminais.get(84).putIfAbsent(";", "e106");

        // Estado 85
        entradasTerminais.get(85).putIfAbsent("(", "r57");
        entradasTerminais.get(85).putIfAbsent("variavel", "r57");
        entradasTerminais.get(85).putIfAbsent("numero", "r57");
        entradasTerminais.get(85).putIfAbsent("not", "r57");
        entradasTerminais.get(85).putIfAbsent("\"+\"", "e49");
        entradasTerminais.get(85).putIfAbsent("-", "e50");

        // Estado 86
        entradasTerminais.get(86).putIfAbsent("(", "r69");
        entradasTerminais.get(86).putIfAbsent("variavel", "r69");
        entradasTerminais.get(86).putIfAbsent("numero", "r69");
        entradasTerminais.get(86).putIfAbsent("not", "r69");
        entradasTerminais.get(86).putIfAbsent("\"+\"", "r69");
        entradasTerminais.get(86).putIfAbsent("-", "r69");

        // Estado 87
        entradasTerminais.get(87).putIfAbsent("(", "r70");
        entradasTerminais.get(87).putIfAbsent("variavel", "r70");
        entradasTerminais.get(87).putIfAbsent("numero", "r70");
        entradasTerminais.get(87).putIfAbsent("not", "r70");
        entradasTerminais.get(87).putIfAbsent("\"+\"", "r70");
        entradasTerminais.get(87).putIfAbsent("-", "r70");

        // Estado 88
        entradasTerminais.get(88).putIfAbsent("(", "r57");
        entradasTerminais.get(88).putIfAbsent("variavel", "r57");
        entradasTerminais.get(88).putIfAbsent("numero", "r57");
        entradasTerminais.get(88).putIfAbsent("not", "r57");
        entradasTerminais.get(88).putIfAbsent("\"+\"", "e49");
        entradasTerminais.get(88).putIfAbsent("-", "e50");

        // Estado 89
        entradasTerminais.get(89).putIfAbsent("variavel", "r57");
        entradasTerminais.get(89).putIfAbsent("numero", "r57");
        entradasTerminais.get(89).putIfAbsent("\"+\"", "e49");
        entradasTerminais.get(89).putIfAbsent("-", "e50");

        // Estado 90
        entradasTerminais.get(90).putIfAbsent(";", "r32");
        entradasTerminais.get(90).putIfAbsent("else", "e111");

        // Estado 92
        entradasTerminais.get(92).putIfAbsent(";", "r44");
        entradasTerminais.get(92).putIfAbsent(",", "r44");
        entradasTerminais.get(92).putIfAbsent(")", "r44");
        entradasTerminais.get(92).putIfAbsent("then", "r44");
        entradasTerminais.get(92).putIfAbsent("to", "r44");
        entradasTerminais.get(92).putIfAbsent("do", "r44");
        entradasTerminais.get(92).putIfAbsent("and", "r44");
        entradasTerminais.get(92).putIfAbsent("or", "r44");
        entradasTerminais.get(92).putIfAbsent("xor", "r44");

        // Estado 93
        entradasTerminais.get(93).putIfAbsent(")", "e112");
        entradasTerminais.get(93).putIfAbsent("or", "e86");
        entradasTerminais.get(93).putIfAbsent("xor", "e87");

        // Estado 94
        entradasTerminais.get(94).putIfAbsent("(", "e72");
        entradasTerminais.get(94).putIfAbsent("variavel", "e73");
        entradasTerminais.get(94).putIfAbsent("numero", "e77");
        entradasTerminais.get(94).putIfAbsent("not", "e78");

        // Estado 95
        entradasTerminais.get(95).putIfAbsent("(", "r58");
        entradasTerminais.get(95).putIfAbsent("variavel", "r58");
        entradasTerminais.get(95).putIfAbsent("numero", "r58");
        entradasTerminais.get(95).putIfAbsent("not", "r58");

        // Estado 96
        entradasTerminais.get(96).putIfAbsent("(", "r59");
        entradasTerminais.get(96).putIfAbsent("variavel", "r59");
        entradasTerminais.get(96).putIfAbsent("numero", "r59");
        entradasTerminais.get(96).putIfAbsent("not", "r59");

        // Estado 97
        entradasTerminais.get(97).putIfAbsent("(", "e72");
        entradasTerminais.get(97).putIfAbsent("variavel", "e73");
        entradasTerminais.get(97).putIfAbsent("numero", "e77");
        entradasTerminais.get(97).putIfAbsent("not", "e78");

        // Estado 98
        entradasTerminais.get(98).putIfAbsent("(", "r60");
        entradasTerminais.get(98).putIfAbsent("variavel", "r60");
        entradasTerminais.get(98).putIfAbsent("numero", "r60");
        entradasTerminais.get(98).putIfAbsent("not", "r60");

        // Estado 99
        entradasTerminais.get(99).putIfAbsent("(", "r61");
        entradasTerminais.get(99).putIfAbsent("variavel", "r61");
        entradasTerminais.get(99).putIfAbsent("numero", "r61");
        entradasTerminais.get(99).putIfAbsent("not", "r61");

        // Estado 100
        entradasTerminais.get(100).putIfAbsent("(", "r62");
        entradasTerminais.get(100).putIfAbsent("variavel", "r62");
        entradasTerminais.get(100).putIfAbsent("numero", "r62");
        entradasTerminais.get(100).putIfAbsent("not", "r62");

        // Estado 101
        entradasTerminais.get(101).putIfAbsent(";", "r54");
        entradasTerminais.get(101).putIfAbsent(",", "r54");
        entradasTerminais.get(101).putIfAbsent(")", "r54");
        entradasTerminais.get(101).putIfAbsent("then", "r54");
        entradasTerminais.get(101).putIfAbsent("to", "r54");
        entradasTerminais.get(101).putIfAbsent("do", "r54");
        entradasTerminais.get(101).putIfAbsent("and", "r54");
        entradasTerminais.get(101).putIfAbsent("\"+\"", "r54");
        entradasTerminais.get(101).putIfAbsent("-", "r54");
        entradasTerminais.get(101).putIfAbsent("*", "r54");
        entradasTerminais.get(101).putIfAbsent("/", "r54");
        entradasTerminais.get(101).putIfAbsent("%", "r54");
        entradasTerminais.get(101).putIfAbsent("=", "r54");
        entradasTerminais.get(101).putIfAbsent("<", "r54");
        entradasTerminais.get(101).putIfAbsent(">", "r54");
        entradasTerminais.get(101).putIfAbsent("<=", "r54");
        entradasTerminais.get(101).putIfAbsent(">=", "r54");
        entradasTerminais.get(101).putIfAbsent("<>", "r54");
        entradasTerminais.get(101).putIfAbsent("or", "r54");
        entradasTerminais.get(101).putIfAbsent("xor", "r54");

        // Estado 102
        entradasTerminais.get(102).putIfAbsent("do", "e115");

        // Estado 103
        entradasTerminais.get(103).putIfAbsent(";", "r39");

        // Estado 104
        entradasTerminais.get(104).putIfAbsent("procedure", "r12");
        entradasTerminais.get(104).putIfAbsent("begin", "r12");

        // Estado 105
        entradasTerminais.get(105).putIfAbsent(")", "r15");

        // Estado 106
        entradasTerminais.get(106).putIfAbsent("tipo", "e116");

        // Estado 107
        entradasTerminais.get(107).putIfAbsent("and", "e88");

        // Estado 108
        entradasTerminais.get(108).putIfAbsent(",", "r42");
        entradasTerminais.get(108).putIfAbsent(")", "r42");
        entradasTerminais.get(108).putIfAbsent("or", "r42");
        entradasTerminais.get(108).putIfAbsent("xor", "r42");

        // Estado 109
        entradasTerminais.get(109).putIfAbsent(")", "r33");

        // Estado 110
        entradasTerminais.get(110).putIfAbsent(";", "r35");

        // Estado 111
        entradasTerminais.get(111).putIfAbsent("begin", "e17");
        entradasTerminais.get(111).putIfAbsent("variavel", "e31");
        entradasTerminais.get(111).putIfAbsent("proc", "e32");
        entradasTerminais.get(111).putIfAbsent("if", "e33");
        entradasTerminais.get(111).putIfAbsent("for", "e34");
        entradasTerminais.get(111).putIfAbsent("while", "e35");

        // Estado 112
        entradasTerminais.get(112).putIfAbsent(";", "r53");
        entradasTerminais.get(112).putIfAbsent(",", "r53");
        entradasTerminais.get(112).putIfAbsent(")", "r53");
        entradasTerminais.get(112).putIfAbsent("then", "r53");
        entradasTerminais.get(112).putIfAbsent("to", "r53");
        entradasTerminais.get(112).putIfAbsent("do", "r53");
        entradasTerminais.get(112).putIfAbsent("and", "r53");
        entradasTerminais.get(112).putIfAbsent("\"+\"", "r53");
        entradasTerminais.get(112).putIfAbsent("-", "r53");
        entradasTerminais.get(112).putIfAbsent("*", "r53");
        entradasTerminais.get(112).putIfAbsent("/", "r53");
        entradasTerminais.get(112).putIfAbsent("%", "r53");
        entradasTerminais.get(112).putIfAbsent("=", "r53");
        entradasTerminais.get(112).putIfAbsent("<", "r53");
        entradasTerminais.get(112).putIfAbsent(">", "r53");
        entradasTerminais.get(112).putIfAbsent("<=", "r53");
        entradasTerminais.get(112).putIfAbsent(">=", "r53");
        entradasTerminais.get(112).putIfAbsent("<>", "r53");
        entradasTerminais.get(112).putIfAbsent("or", "r53");
        entradasTerminais.get(112).putIfAbsent("xor", "r53");

        // Estado 113
        entradasTerminais.get(113).putIfAbsent("*", "e98");
        entradasTerminais.get(113).putIfAbsent("/", "e99");
        entradasTerminais.get(113).putIfAbsent("%", "e100");

        // Estado 114
        entradasTerminais.get(114).putIfAbsent(";", "r49");
        entradasTerminais.get(114).putIfAbsent(",", "r49");
        entradasTerminais.get(114).putIfAbsent(")", "r49");
        entradasTerminais.get(114).putIfAbsent("then", "r49");
        entradasTerminais.get(114).putIfAbsent("to", "r49");
        entradasTerminais.get(114).putIfAbsent("do", "r49");
        entradasTerminais.get(114).putIfAbsent("and", "r49");
        entradasTerminais.get(114).putIfAbsent("\"+\"", "r49");
        entradasTerminais.get(114).putIfAbsent("-", "r49");
        entradasTerminais.get(114).putIfAbsent("*", "r49");
        entradasTerminais.get(114).putIfAbsent("/", "r49");
        entradasTerminais.get(114).putIfAbsent("%", "r49");
        entradasTerminais.get(114).putIfAbsent("=", "r49");
        entradasTerminais.get(114).putIfAbsent("<", "r49");
        entradasTerminais.get(114).putIfAbsent(">", "r49");
        entradasTerminais.get(114).putIfAbsent("<=", "r49");
        entradasTerminais.get(114).putIfAbsent(">=", "r49");
        entradasTerminais.get(114).putIfAbsent("<>", "r49");
        entradasTerminais.get(114).putIfAbsent("or", "r49");
        entradasTerminais.get(114).putIfAbsent("xor", "r49");

        // Estado 115
        entradasTerminais.get(115).putIfAbsent("begin", "e17");
        entradasTerminais.get(115).putIfAbsent("variavel", "e31");
        entradasTerminais.get(115).putIfAbsent("proc", "e32");
        entradasTerminais.get(115).putIfAbsent("if", "e33");
        entradasTerminais.get(115).putIfAbsent("for", "e34");
        entradasTerminais.get(115).putIfAbsent("while", "e35");

        // Estado 116
        entradasTerminais.get(116).putIfAbsent(";", "r17");
        entradasTerminais.get(116).putIfAbsent(")", "r17");

        // Estado 117
        entradasTerminais.get(117).putIfAbsent(";", "r36");

        // Estado 118
        entradasTerminais.get(118).putIfAbsent(";", "r38");
    }

    private void preencherNaoTerminais() {
        entradasNaoTerminais.get(0).putIfAbsent("P", 1);
        entradasNaoTerminais.get(4).putIfAbsent("B", 5);
        entradasNaoTerminais.get(4).putIfAbsent("VAR_LIST", 6);
        entradasNaoTerminais.get(6).putIfAbsent("PROC_LIST", 9);
        entradasNaoTerminais.get(6).putIfAbsent("PROC", 10);
        entradasNaoTerminais.get(7).putIfAbsent("VAR_DECL_LIST", 13);
        entradasNaoTerminais.get(7).putIfAbsent("VAR_DECL", 14);
        entradasNaoTerminais.get(7).putIfAbsent("ID_LIST", 15);
        entradasNaoTerminais.get(9).putIfAbsent("C_SEC", 16);
        entradasNaoTerminais.get(10).putIfAbsent("PROC_LIST", 18);
        entradasNaoTerminais.get(10).putIfAbsent("PROC", 10);
        entradasNaoTerminais.get(17).putIfAbsent("C_SEC", 23);
        entradasNaoTerminais.get(17).putIfAbsent("C_LIST", 24);
        entradasNaoTerminais.get(17).putIfAbsent("C", 25);
        entradasNaoTerminais.get(17).putIfAbsent("VAR_ATR", 26);
        entradasNaoTerminais.get(17).putIfAbsent("PROC_CA", 27);
        entradasNaoTerminais.get(17).putIfAbsent("I", 28);
        entradasNaoTerminais.get(17).putIfAbsent("FOR", 29);
        entradasNaoTerminais.get(17).putIfAbsent("W", 30);
        entradasNaoTerminais.get(19).putIfAbsent("PA_LIST", 36);
        entradasNaoTerminais.get(20).putIfAbsent("ID_LIST", 38);
        entradasNaoTerminais.get(21).putIfAbsent("VAR_DECL_LIST", 39);
        entradasNaoTerminais.get(21).putIfAbsent("VAR_DECL", 14);
        entradasNaoTerminais.get(21).putIfAbsent("ID_LIST", 15);
        entradasNaoTerminais.get(32).putIfAbsent("PROC_ARGS", 45);
        entradasNaoTerminais.get(33).putIfAbsent("E", 46);
        entradasNaoTerminais.get(33).putIfAbsent("SI_E", 47);
        entradasNaoTerminais.get(33).putIfAbsent("SIGN_OPT", 48);
        entradasNaoTerminais.get(34).putIfAbsent("VAR_ATR", 51);
        entradasNaoTerminais.get(35).putIfAbsent("E", 52);
        entradasNaoTerminais.get(35).putIfAbsent("SI_E", 47);
        entradasNaoTerminais.get(35).putIfAbsent("SIGN_OPT", 48);
        entradasNaoTerminais.get(37).putIfAbsent("PA_DECL_LIST", 55);
        entradasNaoTerminais.get(37).putIfAbsent("PA_DECL", 56);
        entradasNaoTerminais.get(37).putIfAbsent("VAR_OPT", 57);
        entradasNaoTerminais.get(42).putIfAbsent("C_SEC", 23);
        entradasNaoTerminais.get(42).putIfAbsent("C_LIST", 58);
        entradasNaoTerminais.get(42).putIfAbsent("C", 25);
        entradasNaoTerminais.get(42).putIfAbsent("VAR_ATR", 26);
        entradasNaoTerminais.get(42).putIfAbsent("PROC_CA", 27);
        entradasNaoTerminais.get(42).putIfAbsent("I", 28);
        entradasNaoTerminais.get(42).putIfAbsent("FOR", 29);
        entradasNaoTerminais.get(42).putIfAbsent("W", 30);
        entradasNaoTerminais.get(43).putIfAbsent("EXPR", 59);
        entradasNaoTerminais.get(43).putIfAbsent("LOG_TERM", 61);
        entradasNaoTerminais.get(43).putIfAbsent("E", 60);
        entradasNaoTerminais.get(43).putIfAbsent("SI_E", 47);
        entradasNaoTerminais.get(43).putIfAbsent("SIGN_OPT", 48);
        entradasNaoTerminais.get(44).putIfAbsent("E_LIST", 63);
        entradasNaoTerminais.get(44).putIfAbsent("EXPR", 62);
        entradasNaoTerminais.get(44).putIfAbsent("LOG_TERM", 61);
        entradasNaoTerminais.get(44).putIfAbsent("E", 60);
        entradasNaoTerminais.get(44).putIfAbsent("SI_E", 47);
        entradasNaoTerminais.get(44).putIfAbsent("SIGN_OPT", 48);
        entradasNaoTerminais.get(47).putIfAbsent("REL_OP", 65);
        entradasNaoTerminais.get(48).putIfAbsent("SI_E_TAIL", 75);
        entradasNaoTerminais.get(48).putIfAbsent("TERM", 76);
        entradasNaoTerminais.get(48).putIfAbsent("F", 74);
        entradasNaoTerminais.get(53).putIfAbsent("B", 81);
        entradasNaoTerminais.get(53).putIfAbsent("VAR_LIST", 6);
        entradasNaoTerminais.get(57).putIfAbsent("ID_LIST", 84);
        entradasNaoTerminais.get(59).putIfAbsent("LOG_OP_OR_XOR", 85);
        entradasNaoTerminais.get(62).putIfAbsent("LOG_OP_OR_XOR", 85);
        entradasNaoTerminais.get(64).putIfAbsent("C_SEC", 23);
        entradasNaoTerminais.get(64).putIfAbsent("C", 91);
        entradasNaoTerminais.get(64).putIfAbsent("VAR_ATR", 26);
        entradasNaoTerminais.get(64).putIfAbsent("PROC_CA", 27);
        entradasNaoTerminais.get(64).putIfAbsent("I", 28);
        entradasNaoTerminais.get(64).putIfAbsent("FOR", 29);
        entradasNaoTerminais.get(64).putIfAbsent("W", 30);
        entradasNaoTerminais.get(65).putIfAbsent("C_SEC", 23);
        entradasNaoTerminais.get(65).putIfAbsent("C", 91);
        entradasNaoTerminais.get(65).putIfAbsent("VAR_ATR", 26);
        entradasNaoTerminais.get(65).putIfAbsent("PROC_CA", 27);
        entradasNaoTerminais.get(65).putIfAbsent("I", 28);
        entradasNaoTerminais.get(65).putIfAbsent("FOR", 29);
        entradasNaoTerminais.get(65).putIfAbsent("W", 30);
        entradasNaoTerminais.get(72).putIfAbsent("EXPR", 93);
        entradasNaoTerminais.get(72).putIfAbsent("LOG_TERM", 61);
        entradasNaoTerminais.get(72).putIfAbsent("E", 60);
        entradasNaoTerminais.get(72).putIfAbsent("SI_E", 47);
        entradasNaoTerminais.get(72).putIfAbsent("SIGN_OPT", 48);
        entradasNaoTerminais.get(75).putIfAbsent("ADD_OP", 94);
        entradasNaoTerminais.get(76).putIfAbsent("MULT_OP", 97);
        entradasNaoTerminais.get(78).putIfAbsent("F", 101);
        entradasNaoTerminais.get(79).putIfAbsent("F", 102);
        entradasNaoTerminais.get(80).putIfAbsent("C_SEC", 23);
        entradasNaoTerminais.get(80).putIfAbsent("C", 103);
        entradasNaoTerminais.get(80).putIfAbsent("VAR_ATR", 26);
        entradasNaoTerminais.get(80).putIfAbsent("PROC_CA", 27);
        entradasNaoTerminais.get(80).putIfAbsent("I", 28);
        entradasNaoTerminais.get(80).putIfAbsent("FOR", 29);
        entradasNaoTerminais.get(80).putIfAbsent("W", 30);
        entradasNaoTerminais.get(83).putIfAbsent("PA_DECL_LIST", 105);
        entradasNaoTerminais.get(83).putIfAbsent("PA_DECL", 56);
        entradasNaoTerminais.get(83).putIfAbsent("VAR_OPT", 57);
        entradasNaoTerminais.get(85).putIfAbsent("LOG_TERM", 107);
        entradasNaoTerminais.get(85).putIfAbsent("E", 60);
        entradasNaoTerminais.get(85).putIfAbsent("SI_E", 47);
        entradasNaoTerminais.get(85).putIfAbsent("SIGN_OPT", 48);
        entradasNaoTerminais.get(88).putIfAbsent("E", 108);
        entradasNaoTerminais.get(88).putIfAbsent("SI_E", 47);
        entradasNaoTerminais.get(88).putIfAbsent("SIGN_OPT", 48);
        entradasNaoTerminais.get(89).putIfAbsent("E_LIST", 109);
        entradasNaoTerminais.get(89).putIfAbsent("EXPR", 62);
        entradasNaoTerminais.get(89).putIfAbsent("LOG_TERM", 61);
        entradasNaoTerminais.get(89).putIfAbsent("E", 60);
        entradasNaoTerminais.get(89).putIfAbsent("SI_E", 47);
        entradasNaoTerminais.get(89).putIfAbsent("SIGN_OPT", 48);
        entradasNaoTerminais.get(91).putIfAbsent("ELSE_OPT", 110);
        entradasNaoTerminais.get(93).putIfAbsent("LOG_OP_OR_XOR", 85);
        entradasNaoTerminais.get(94).putIfAbsent("TERM", 113);
        entradasNaoTerminais.get(94).putIfAbsent("F", 74);
        entradasNaoTerminais.get(97).putIfAbsent("F", 114);
        entradasNaoTerminais.get(111).putIfAbsent("C_SEC", 23);
        entradasNaoTerminais.get(111).putIfAbsent("C", 117);
        entradasNaoTerminais.get(111).putIfAbsent("VAR_ATR", 26);
        entradasNaoTerminais.get(111).putIfAbsent("PROC_CA", 27);
        entradasNaoTerminais.get(111).putIfAbsent("I", 28);
        entradasNaoTerminais.get(111).putIfAbsent("FOR", 29);
        entradasNaoTerminais.get(111).putIfAbsent("W", 30);
        entradasNaoTerminais.get(113).putIfAbsent("MULT_OP", 97);
        entradasNaoTerminais.get(115).putIfAbsent("C_SEC", 23);
        entradasNaoTerminais.get(115).putIfAbsent("C", 118);
        entradasNaoTerminais.get(115).putIfAbsent("VAR_ATR", 26);
        entradasNaoTerminais.get(115).putIfAbsent("PROC_CA", 27);
        entradasNaoTerminais.get(115).putIfAbsent("I", 28);
        entradasNaoTerminais.get(115).putIfAbsent("FOR", 29);
        entradasNaoTerminais.get(115).putIfAbsent("W", 30);
    }
}