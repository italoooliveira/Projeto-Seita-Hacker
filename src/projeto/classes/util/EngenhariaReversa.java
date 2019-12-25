/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.classes.util;

import java.util.List;

/**
 *
 * @author Italo
 */
public class EngenhariaReversa {

    private static String conteudo = "";

    public static String converterListaArrayEmString(List<String[]> linhas, String nomeClasse) {
        for (String[] linha : linhas) {
            if (nomeClasse.equals("Login")) {
                conteudo += String.valueOf(linha[0]) + "," + String.valueOf(linha[1]) + "," + String.valueOf(linha[2]) + "," + String.valueOf(linha[3]) + "\n";
            }
        }

        return conteudo;
    }

}
