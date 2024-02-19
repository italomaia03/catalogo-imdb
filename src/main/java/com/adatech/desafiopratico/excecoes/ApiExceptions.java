package com.adatech.desafiopratico.excecoes;

public abstract class ApiExceptions extends Exception{
    private Integer statusCode;

    public ApiExceptions(String mensagem, Integer statusCode) {
        super(mensagem);
        this.statusCode = statusCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
