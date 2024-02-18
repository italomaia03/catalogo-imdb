package com.adatech.desafiopratico.controllers;

import com.adatech.desafiopratico.models.Ator;
import com.adatech.desafiopratico.repository.AtorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/atores")
public class AtorController {

    private AtorRepository atorRepository;

    public AtorController(AtorRepository atorRepository) {
        this.atorRepository = atorRepository;
    }

    @GetMapping
    public List<Ator> buscarTodosAtores() {
        return atorRepository.findAll();
    }
}
