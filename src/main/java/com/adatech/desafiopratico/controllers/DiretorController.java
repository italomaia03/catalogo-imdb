package com.adatech.desafiopratico.controllers;

import com.adatech.desafiopratico.dto.DiretorDto;
import com.adatech.desafiopratico.models.Diretor;
import com.adatech.desafiopratico.services.DiretorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/diretores")
public class DiretorController {

    private final DiretorService diretorService;

    public DiretorController(DiretorService diretorService) {
        this.diretorService = diretorService;
    }

    @GetMapping
    public List<Diretor> listarDiretores() {
        return diretorService.listarDiretores();
    }

    @PostMapping
    public ResponseEntity<Diretor> cadastrarNovoDiretor(@RequestBody DiretorDto diretorDto) {
        Diretor novoDiretor = diretorService.cadastrarNovoDiretor(diretorDto);
        return new ResponseEntity<>(novoDiretor, HttpStatus.CREATED);
    }
}
