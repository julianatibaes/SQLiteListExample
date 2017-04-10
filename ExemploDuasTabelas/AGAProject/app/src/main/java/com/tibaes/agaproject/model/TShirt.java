package com.tibaes.agaproject.model;

import java.io.Serializable;

/**
 * Created by Juliana Tibães on 26/03/2017.
 */

public class TShirt implements Serializable{

    private Long id;
    private String modelo;
    private String descricao;
    private String caminhoFoto;
    private Integer quantidade;
    private Float valor;

    public TShirt() { }

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

    @Override
    public String toString() {
        return  modelo + '\n' +
                "R$ " + valor ;
    }
}
