/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.classes.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projeto.classes.entitys.Hacker;

/**
 *
 * @author Italo
 */
public class MontarRanking {

    public static Hacker hackerLogado;
    public static List<Hacker> listaHackers = new ArrayList<Hacker>();

    private static List<Hacker> montarListaHackers() {
        List<Hacker> listagemHacker = new ArrayList<Hacker>();
        
        String caminhoAtual = new File("").getAbsolutePath();
        String caminhoArquivo = caminhoAtual + File.separator + "src/projeto/files/hackers.csv";
        
        try {
            ManipulacaoArquivo.lerArquivo(caminhoArquivo, ",");
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<String[]> linhas = ManipulacaoArquivo.getLinhasHacker();

        for (String[] linha : linhas) {
            Hacker hacker = new Hacker(linha[4], Integer.parseInt(linha[6]));

            listagemHacker.add(hacker);
        }

        return listagemHacker;
    }

    private static List<Hacker> ordenarListaHackers() {
        List<Hacker> listagemHackers = montarListaHackers();

        Collections.sort(listagemHackers);

        return listagemHackers;
    }

    public static void gerarRankingHackers() throws IOException {
        String conteudo = "";
        String caminhoAtual = new File("").getAbsolutePath();
        String caminhoArquivo = caminhoAtual + File.separator + "src/projeto/files/ranking.csv";

        List<Hacker> listagemHackers = ordenarListaHackers();

        for (Hacker hacker : listagemHackers) {
            conteudo += 2 + "," + (listagemHackers.indexOf(hacker) + 1) + "," + hacker.getCodenome() + "," + hacker.getPontuacao() + "\n";
        }

        ManipulacaoArquivo.editarArquivo(caminhoArquivo, false, conteudo);
    }

    public static void atualizaRanking() throws IOException {
        String conteudo = "";
        String caminhoAtual = new File("").getAbsolutePath();
        String caminhoArquivo = caminhoAtual + File.separator + "src/projeto/files/hackers.csv";
        
        for (Hacker hacker : listaHackers) {
            if (hacker.getCodenome().equals(hackerLogado.getCodenome())) {
                hacker.setPontuacao(hackerLogado.getPontuacao());
            }
            
            conteudo += 3 + "," + hacker.getNome() + "," + hacker.getEmail() + "," + hacker.getTelefone() + "," + hacker.getCodenome() + "," + hacker.getSenha() + "," + hacker.getPontuacao()+ "\n";
        }

        ManipulacaoArquivo.editarArquivo(caminhoArquivo, false, conteudo);
    }

}
