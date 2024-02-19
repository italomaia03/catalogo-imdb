package com.adatech.desafiopratico.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NaoEncontradoException extends ApiExceptions{

    public NaoEncontradoException(String mensagem) {
        super(mensagem, 404);
    }
}
