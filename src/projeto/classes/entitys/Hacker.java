/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.classes.entitys;

import java.io.File;
import java.io.IOException;
import projeto.classes.util.ManipulacaoArquivo;

/**
 *
 * @author Italo
 */
public class Hacker extends Pessoa implements Comparable<Hacker> {

    private String codenome;
    private String senha;
    private int pontuacao;
    
    public Hacker() {

    }

    public Hacker(String codenome, String senha) {
        this.codenome = codenome;
        this.senha = senha;
    }

    public Hacker(String codenome, int pontuacao) {
        this.codenome = codenome;
        this.pontuacao = pontuacao;
    }

    public Hacker(String nome, String email, String telefone, String codenome, String senha) {
        super(nome, email, telefone);
        this.codenome = codenome;
        this.senha = senha;
    }
    
    public Hacker(String nome, String email, String telefone, String codenome, String senha, int pontuacao) {
        super(nome, email, telefone);
        this.codenome = codenome;
        this.senha = senha;
        this.pontuacao = pontuacao;
    }
    
    public String getCodenome() {
        return codenome;
    }

    public void setCodenome(String codenome) {
        this.codenome = codenome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao += pontuacao;
    }


    @Override
    public void salvarNoArquivo() throws IOException {
        String conteudo = 3 + "," + this.nome + "," + this.email + "," + this.telefone + "," + this.codenome + "," + this.senha + "," + 0;
        String conteudo2 = 0 + "," + this.codenome + "," + this.senha + "," + 0;
        
        String caminhoAtual = new File("").getAbsolutePath();
        String caminhoArquivoHacker = caminhoAtual + File.separator + "src/projeto/files/hackers.csv";
        String caminhoArquivoLogin = caminhoAtual + File.separator + "src/projeto/files/login.csv";
        
        ManipulacaoArquivo.editarArquivo(caminhoArquivoHacker, true, conteudo);
        ManipulacaoArquivo.editarArquivo(caminhoArquivoLogin, true, conteudo2);
    }

    @Override
    public int compareTo(Hacker other) {
        if (this.pontuacao > other.getPontuacao()) {
            return -1;
        }
        if (this.pontuacao < other.getPontuacao()) {
            return 1;
        }

        return 0;
    }
}
