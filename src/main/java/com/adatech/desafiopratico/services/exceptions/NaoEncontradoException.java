package com.adatech.desafiopratico.services.exceptions;

import com.adatech.desafiopratico.exceptions.ApiException;

public class NaoEncontradoException extends ApiException {

    public NaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
