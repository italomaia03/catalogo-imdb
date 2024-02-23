package com.adatech.desafiopratico.dto.exceptions;

import com.adatech.desafiopratico.exceptions.ApiException;

public class DtoInvalidoException extends ApiException {
    public DtoInvalidoException(String message) {
        super(message);
    }
}
