package com.adatech.desafiopratico.controllers;

import com.adatech.desafiopratico.dto.FilmeDto;
import com.adatech.desafiopratico.models.Filme;
import com.adatech.desafiopratico.repository.FilmeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/filmes")
public class FilmeController {
    private FilmeRepository filmeRepository;

    public FilmeController(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @GetMapping
    public List<Filme> listarTodosFilmes() {
        return filmeRepository.findAll();
    }

    @PostMapping
    public Filme cadastrarNovoFilme(@RequestBody FilmeDto filme) {
        return null;
    }
}
