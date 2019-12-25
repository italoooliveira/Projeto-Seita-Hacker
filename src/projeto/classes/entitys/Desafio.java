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
public class Desafio extends Cadastro {

    private int numero;
    private String nome;
    private int pontos;
    private int tempoLimite;
    private String descricao;
    private String opcaoA;
    private String opcaoB;
    private String opcaoC;
    private String opcaoD;
    private String respostaCerta;

    public Desafio() {

    }

    public Desafio(int numero, String nome, int pontos, int tempoLimite, String descricao, String opcaoA, String opcaoB, String opcaoC, String opcaoD, String resposta) {
        this.numero = numero;
        this.nome = nome;
        this.pontos = pontos;
        this.tempoLimite = tempoLimite;
        this.descricao = descricao;
        this.opcaoA = opcaoA;
        this.opcaoB = opcaoB;
        this.opcaoC = opcaoC;
        this.opcaoD = opcaoD;
        this.respostaCerta = resposta;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getTempoLimite() {
        return tempoLimite;
    }

    public void setTempoLimite(int tempoLimite) {
        this.tempoLimite = tempoLimite;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getOpcaoA() {
        return opcaoA;
    }

    public void setOpcaoA(String opcaoA) {
        this.opcaoA = opcaoA;
    }

    public String getOpcaoB() {
        return opcaoB;
    }

    public void setOpcaoB(String opcaoB) {
        this.opcaoB = opcaoB;
    }

    public String getOpcaoC() {
        return opcaoC;
    }

    public void setOpcaoC(String opcaoC) {
        this.opcaoC = opcaoC;
    }

    public String getOpcaoD() {
        return opcaoD;
    }

    public void setOpcaoD(String opcaoD) {
        this.opcaoD = opcaoD;
    }

    public String getRespostaCerta() {
        return respostaCerta;
    }

    public void setRespostaCerta(String respostaCerta) {
        this.respostaCerta = respostaCerta;
    }

    @Override
    public void salvarNoArquivo() throws IOException {
        String conteudo = 1 + "," + this.numero + "," + this.nome + "," + this.pontos + "," + this.tempoLimite + "," + this.descricao + "," + this.opcaoA + "," + this.opcaoB + "," + this.opcaoC + "," + this.opcaoD + "," + this.respostaCerta;

        String caminhoAtual = new File("").getAbsolutePath();
        String caminhoArquivo = caminhoAtual + File.separator + "src/projeto/files/desafios.csv";

        ManipulacaoArquivo.editarArquivo(caminhoArquivo, true, conteudo);

    }

}
