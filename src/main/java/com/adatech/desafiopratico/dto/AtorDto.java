package com.adatech.desafiopratico.dto;

import com.adatech.desafiopratico.models.Ator;

public class AtorDto extends PessoaDto{

    public AtorDto(String nome) {
        super(nome);
    }

    public AtorDto() {
    }

    @Override
    public Object mapearParaEntidade() {
        return new Ator(this.getNome());
    }
}
