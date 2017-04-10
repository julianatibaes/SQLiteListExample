package com.tibaes.agaproject.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Juliana Tibães on 07/04/2017.
 */

public class Client implements Serializable {

    private Long id;
    private String nome;
    private String email;
    private String celular;
    private Date nascimento;
    private String caminhoFoto;

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    @Override
    public String toString() {
        return nome + '\'' +
               celular;
    }
}
