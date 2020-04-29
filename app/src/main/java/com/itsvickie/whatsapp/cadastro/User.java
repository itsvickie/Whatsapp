package com.itsvickie.whatsapp.cadastro;

public class User {
    private String id;
    private String nome;
    private String telefone;
    private String senha;

    public User() {
    }

    public User(String id, String nome, String telefone, String senha) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.senha = senha;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
