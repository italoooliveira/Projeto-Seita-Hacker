/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.classes.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projeto.classes.entitys.Hacker;

/**
 *
 * @author Italo
 */
public class Login {

    public boolean[] validarAcesso(Hacker hacker) throws IOException {
        boolean informacoesExistem = false;
        boolean ehPrimeiroAcesso = false;
        boolean[] existeEhPrimeiro = new boolean[2];
        String caminhoAtual = new File("").getAbsolutePath();
        String caminhoArquivo = caminhoAtual + File.separator + "src/projeto/files/login.csv";
                
        try {
            ManipulacaoArquivo.lerArquivo(caminhoArquivo, ",");
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<String[]> linhas = ManipulacaoArquivo.getLinhasLogin();

        for (String[] linha : linhas) {
            if (linha[1].equals(hacker.getCodenome()) && linha[2].equals(hacker.getSenha())) {
                informacoesExistem = true;

                int acesso = Integer.parseInt(linha[3]);

                acesso++;

                if (acesso == 1) {
                    ehPrimeiroAcesso = true;
                }

                existeEhPrimeiro[0] =  informacoesExistem;
                existeEhPrimeiro[1] = ehPrimeiroAcesso;
                
                linha[3] = String.valueOf(acesso);

                String conteudo = EngenhariaReversa.converterListaArrayEmString(linhas, "Login");

                ManipulacaoArquivo.editarArquivo(caminhoArquivo, false, conteudo);
                                
                break;
            }
        }

        return existeEhPrimeiro;
    }

}
