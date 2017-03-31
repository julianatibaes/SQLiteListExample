package com.tibaes.modelobanco.model;

/**
 * Created by julia on 31/03/2017.
 */

public class TShirt {

    private Long id;
    private String modelo;
    private String descricao;
    private String foto;
    private Integer quantidade;
    private Float valor;
    private int icon;

    public TShirt() {
    }

    public TShirt(String modelo, Float valor) {
        this.modelo = modelo;
        this.valor = valor;
    }

    public TShirt(String modelo, Float valor, int icon) {
        this.modelo = modelo;
        this.valor = valor;
        this.icon = icon;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return  modelo + '\n' +
                "R$ " + valor ;
    }
}
