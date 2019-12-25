package projeto.classes.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

public class ManipulacaoArquivo {

    private static List<String[]> linhasLogin = new ArrayList<String[]>();
    private static List<String[]> linhasDesafios = new ArrayList<String[]>();
    private static List<String[]> linhasHacker = new ArrayList<String[]>();
    private static List<String[]> linhasRanking = new ArrayList<String[]>();

    public static List<String[]> getLinhasLogin() {
        return linhasLogin;
    }

    public static List<String[]> getLinhasDesafios() {
        return linhasDesafios;
    }

    public static List<String[]> getLinhasRanking() {
        return linhasRanking;
    }

    public static List<String[]> getLinhasHacker() {
        return linhasHacker;
    }

    //Métodos
    /* Públicos */
    public static void lerArquivo(String nomeArquivo, String separador) throws IOException {
        linhasLogin.clear();
        linhasDesafios.clear();
        linhasRanking.clear();
        linhasHacker.clear();
        
        FileReader arquivo = new FileReader(nomeArquivo);
        try (BufferedReader lerArquivo = new BufferedReader(arquivo)) {
            String linha = lerArquivo.readLine();

            while (linha != null) {
                pegarESepararValores(linha, separador);

                linha = lerArquivo.readLine();
            }
        }
    }

    public static void escreverNovoArquivo(String nomeArquivo, String conteudo) throws IOException {
        FileWriter arquivo = new FileWriter(nomeArquivo);

        try (BufferedWriter escrever = new BufferedWriter(arquivo)) {
            escrever.write(conteudo);

            escrever.close();
        }
    }

    public static void editarArquivo(String nomeArquivo, boolean acrescentarLinha, String conteudo) throws IOException {
        if (acrescentarLinha) {
            FileWriter arquivo = new FileWriter(nomeArquivo, acrescentarLinha);

            try (BufferedWriter escrever = new BufferedWriter(arquivo)) {
                escrever.write(conteudo);

                escrever.newLine();

                escrever.close();
            }
        } else {
            File arquivoExclusao = new File(nomeArquivo);

            arquivoExclusao.delete();

            escreverNovoArquivo(nomeArquivo, conteudo);
        }
    }

    /* Privadas */
    private static void pegarESepararValores(String linha, String separador) {
        String partes[] = linha.split(separador);

        switch (Integer.parseInt(partes[0])) {
            case 0:
                linhasLogin.add(partes);
                break;
            case 1:
                linhasDesafios.add(partes);
                break;
            case 2:
                linhasRanking.add(partes);
                break;
            default:
                linhasHacker.add(partes);
                break;
        }
    }
}
