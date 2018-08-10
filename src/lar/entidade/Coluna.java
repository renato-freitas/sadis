/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lar.entidade;

/**
 *
 * @author renato
 */
public class Coluna {
    
    private String nome;
    private String tipoDado;
    private String tipoChave;
    private int tamanho;
    
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the tipoDado
     */
    public String getTipoDado() {
        return tipoDado;
    }

    /**
     * @param tipoDado the tipoDado to set
     */
    public void setTipoDado(String tipoDado) {
        this.tipoDado = tipoDado;
    }

    /**
     * @return the tipoChave
     */
    public String getTipoChave() {
        return tipoChave;
    }

    /**
     * @param tipoChave the tipoChave to set
     */
    public void setTipoChave(String tipoChave) {
        this.tipoChave = tipoChave;
    }

    /**
     * @return the tamanho
     */
    public int getTamanho() {
        return tamanho;
    }

    /**
     * @param tamanho the tamanho to set
     */
    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
}
