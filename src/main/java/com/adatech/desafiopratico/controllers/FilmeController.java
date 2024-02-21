package com.adatech.desafiopratico.controllers;

import com.adatech.desafiopratico.dto.filme.FilmeDto;
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
    private final FilmeService filmeService;

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

    @PatchMapping("/{idFilme}")
    public ResponseEntity<Filme> atualizarFilme(@PathVariable("idFilme") Integer idFilme, @RequestBody FilmeDto filmeDto) throws NaoEncontradoException {
        Filme filmeAtualizado = filmeService.atualizarFilme(idFilme, filmeDto);
        return new ResponseEntity<>(filmeAtualizado, HttpStatus.OK);
    }
}
