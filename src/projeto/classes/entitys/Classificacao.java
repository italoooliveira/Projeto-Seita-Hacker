/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto.classes.entitys;

/**
 *
 * @author Italo
 */
public class Classificacao extends Hacker {
    private int lugar;

    public int getLugar() {
        return lugar;
    }

    public void setLugar(int lugar) {
        this.lugar = lugar;
    }

    public Classificacao(int lugar, String codenome, int pontuacao) {
        super(codenome, pontuacao);
        this.lugar = lugar;
    }
    
    
}
