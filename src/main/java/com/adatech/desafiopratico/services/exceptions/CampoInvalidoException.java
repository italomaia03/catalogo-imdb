package com.adatech.desafiopratico.services.exceptions;

import com.adatech.desafiopratico.exceptions.ApiException;

public class CampoInvalidoException extends ApiException {
    public CampoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
