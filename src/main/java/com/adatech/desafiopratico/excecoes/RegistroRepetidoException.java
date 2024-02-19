package com.adatech.desafiopratico.excecoes;

public class RegistroRepetidoException extends ApiExceptions{
    public RegistroRepetidoException(String mensagem) {
        super(mensagem, 409);
    }
}
