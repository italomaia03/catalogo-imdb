package com.adatech.desafiopratico.exceptions;

public abstract class ApiException extends RuntimeException{
    public ApiException(String mensagem) {
        super(mensagem);
    }
}
