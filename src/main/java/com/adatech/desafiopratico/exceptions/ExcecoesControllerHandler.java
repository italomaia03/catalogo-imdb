package com.adatech.desafiopratico.exceptions;

import com.adatech.desafiopratico.services.exceptions.CampoInvalidoException;
import com.adatech.desafiopratico.services.exceptions.NaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExcecoesControllerHandler {

    @ExceptionHandler(value = {CampoInvalidoException.class})
    public ResponseEntity<Object> tratarCampoInvalidoException (CampoInvalidoException campoInvalidoException) {
        Map<String, String> mensagem = new HashMap<>();
        mensagem.put("Erro", campoInvalidoException.getMessage());
        return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<Object> tratarNaoEncontradoException(NaoEncontradoException naoEncontradoException){
        Map<String, String> mensagem = new HashMap<>();
        mensagem.put("Erro", naoEncontradoException.getMessage());
        return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    }
}
