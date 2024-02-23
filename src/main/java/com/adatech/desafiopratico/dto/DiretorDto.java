package com.adatech.desafiopratico.dto;

import com.adatech.desafiopratico.models.Diretor;

public class DiretorDto extends PessoaDto{
    public DiretorDto(String nome) {
        super(nome);
    }

    public DiretorDto() {
    }

    @Override
    public Object mapearParaEntidade() {
        return new Diretor(this.getNome());
    }
}
