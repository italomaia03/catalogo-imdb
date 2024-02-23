package com.adatech.desafiopratico.dto;

import com.adatech.desafiopratico.dto.exceptions.DtoInvalidoException;

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

    public abstract Object mapearParaEntidade();

    public void validarPessoaDto() {
        if (this.nome == null || this.nome.trim().isEmpty()){
            throw new DtoInvalidoException("O campo a seguir não pode ser nulo: nome");
        }
    }
}
