package com.adatech.desafiopratico.util;

import com.adatech.desafiopratico.excecoes.CampoInvalidoException;
import com.adatech.desafiopratico.excecoes.NaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExcecoesControllerHandler {

    @ExceptionHandler(value = {CampoInvalidoException.class})
    public ResponseEntity tratarCampoInvalidoException (CampoInvalidoException campoInvalidoException) {
        return ResponseEntity.badRequest().body(campoInvalidoException);
    }

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<Object> tratarNaoEncontradoException(NaoEncontradoException naoEncontradoException){
        Map<String, String> mensagem = new HashMap<>();
        mensagem.put("mensagem", naoEncontradoException.getMessage());
        return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    }
}
