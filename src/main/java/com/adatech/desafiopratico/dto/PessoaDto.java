package com.adatech.desafiopratico.dto;

public abstract class PessoaDto {
    private String nome;

    public PessoaDto(String nome) {
        this.nome = nome;
    }

    public PessoaDto() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public abstract Object mapearParaDto(Object objeto);

    public abstract Object mapearParaEntidade();
}
