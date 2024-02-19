package com.adatech.desafiopratico.controllers;

import com.adatech.desafiopratico.dto.AtorDto;
import com.adatech.desafiopratico.excecoes.RegistroRepetidoException;
import com.adatech.desafiopratico.models.Ator;
import com.adatech.desafiopratico.services.AtorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/atores")
public class AtorController {

    private AtorService atorService;

    public AtorController(AtorService atorService) {
        this.atorService = atorService;
    }


    @GetMapping
    public ResponseEntity<List<Ator>> buscarTodosAtores() {
        return new ResponseEntity<>(atorService.listarAtores(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Ator> cadastrarNovoAtor(@RequestBody AtorDto atorDto) throws RegistroRepetidoException {
        Ator novoAtor = atorService.cadastrarNovoAtor(atorDto);
        if(novoAtor == null) {
            throw new RegistroRepetidoException(
                    String.format("Ator %s já está cadastrado.", atorDto.getNome())
            );
        }
        return new ResponseEntity<>(novoAtor, HttpStatus.CREATED);
    }
}
