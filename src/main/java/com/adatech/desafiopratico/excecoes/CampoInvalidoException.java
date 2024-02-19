package com.adatech.desafiopratico.excecoes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CampoInvalidoException extends ApiExceptions {
    public CampoInvalidoException(String mensagem) {
        super(mensagem, 400);
    }
}
