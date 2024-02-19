package com.adatech.desafiopratico.controllers;

import com.adatech.desafiopratico.dto.AtorDto;
import com.adatech.desafiopratico.models.Ator;
import com.adatech.desafiopratico.repository.AtorRepository;
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
    public ResponseEntity<Ator> cadastrarNovoAtor(@RequestBody AtorDto atorDto) {
        Ator novoAtor = atorService.cadastrarNovoAtor(atorDto);
        return new ResponseEntity<>(novoAtor, HttpStatus.CREATED);
    }
}
