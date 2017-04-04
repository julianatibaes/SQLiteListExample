package com.tibaes.listexample.model;

import java.io.Serializable;

/**
 * Created by julia on 26/03/2017.
 */

public class TShirt implements Serializable{

    private Long id;
    private String modelo;
    private String descricao;
    private String caminhoFoto; // alterado o nome
    private Integer quantidade;
    private Float valor;
    private int iconFoto; // alterado o nome

    public TShirt() { }

    public TShirt(String modelo, Float valor) {
        this.modelo = modelo;
        this.valor = valor;
    }


    public TShirt(String modelo, Float valor, int iconFoto) {
        this.modelo = modelo;
        this.valor = valor;
        this.iconFoto = iconFoto;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public int getIconFoto() {
        return iconFoto;
    }

    public void setIconFoto(int iconFoto) {
        this.iconFoto = iconFoto;
    }

    @Override
    public String toString() {
        return  modelo + '\n' +
                "R$ " + valor ;
    }
}
