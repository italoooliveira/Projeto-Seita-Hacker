
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.classes.util;

import java.io.File;
import projeto.classes.util.Login;
import projeto.classes.util.ManipulacaoArquivo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projeto.classes.entitys.Classificacao;
import projeto.interfaces.Listagem;

/**
 *
 * @author Italo
 */
public class Ranking implements Listagem {

    @Override
    public List<Classificacao> listar() {
        List<Classificacao> listaHackers = new ArrayList<Classificacao>();

        String caminhoAtual = new File("").getAbsolutePath();
        String caminhoArquivo = caminhoAtual + File.separator + "src/projeto/files/ranking.csv";

        try {
            ManipulacaoArquivo.lerArquivo(caminhoArquivo, ",");
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<String[]> linhas = ManipulacaoArquivo.getLinhasRanking();

        for (String[] linha : linhas) {
            Classificacao classificacao = new Classificacao(Integer.parseInt(linha[1]), linha[2], Integer.parseInt(linha[3]));

            listaHackers.add(classificacao);
        }

        return listaHackers;
    }

}
