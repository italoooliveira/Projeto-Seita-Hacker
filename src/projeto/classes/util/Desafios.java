/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.classes.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projeto.classes.entitys.Desafio;
import projeto.interfaces.Listagem;

/**
 *
 * @author Italo
 */
public class Desafios implements Listagem {

    public List<Desafio> listar() {
        List<Desafio> listaDesafios = new ArrayList<Desafio>();

        String caminhoAtual = new File("").getAbsolutePath();
        String caminhoArquivo = caminhoAtual + File.separator + "src/projeto/files/desafios.csv";
        
        try {
            ManipulacaoArquivo.lerArquivo(caminhoArquivo, ",");
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<String[]> linhas = ManipulacaoArquivo.getLinhasDesafios();

        for (String[] linha : linhas) {
            Desafio desafio = new Desafio(Integer.parseInt(linha[1]), linha[2], Integer.parseInt(linha[3]), Integer.parseInt(linha[4]), linha[5], linha[6], linha[7], linha[8], linha[9], linha[10]);

            listaDesafios.add(desafio);
        }

        return listaDesafios;
    }
}
