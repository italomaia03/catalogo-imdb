package com.adatech.desafiopratico.controllers;

import com.adatech.desafiopratico.models.Diretor;
import com.adatech.desafiopratico.repository.DiretorRepository;
import com.adatech.desafiopratico.services.DiretorService;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
