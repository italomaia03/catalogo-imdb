package com.adatech.desafiopratico.controllers;

import com.adatech.desafiopratico.dto.FilmeDto;
import com.adatech.desafiopratico.excecoes.NaoEncontradoException;
import com.adatech.desafiopratico.models.Filme;
import com.adatech.desafiopratico.services.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/filmes")
public class FilmeController {
    private FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @GetMapping
    public ResponseEntity<List<Filme>> listarFilmes(@RequestParam(required = false) String nomeFilme) throws NaoEncontradoException {
        List<Filme> filmesEncontrados = filmeService.buscarFilmes(nomeFilme);
        return new ResponseEntity<>(filmesEncontrados, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Filme> cadastrarNovoFilme(@RequestBody FilmeDto filmeDto) {
        Filme novoFilme = filmeService.cadastrarNovoFilme(filmeDto);
        return new ResponseEntity<>(novoFilme, HttpStatus.CREATED);
    }
}
