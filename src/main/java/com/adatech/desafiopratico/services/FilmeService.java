package com.adatech.desafiopratico.services;

import com.adatech.desafiopratico.excecoes.NaoEncontradoException;
import com.adatech.desafiopratico.models.Filme;
import com.adatech.desafiopratico.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    private FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public List<Filme> buscarFilmes(String nomeFilme) throws NaoEncontradoException {
        List<Filme> filmesEncontrados;
        if (nomeFilme == null || nomeFilme.trim().isEmpty()) {
            filmesEncontrados = filmeRepository.findAll();
        } else {
            filmesEncontrados = filmeRepository.buscarFilmePorNome(nomeFilme).get();
            if (filmesEncontrados.isEmpty()) {
                throw new NaoEncontradoException(String.format("Filme %s não foi encontrado.", nomeFilme));
            }
        }
        return filmesEncontrados;
    }
}
