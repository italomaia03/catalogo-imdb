package com.adatech.desafiopratico.services;

import com.adatech.desafiopratico.dto.FilmeAtorDto;
import com.adatech.desafiopratico.dto.FilmeDto;
import com.adatech.desafiopratico.excecoes.NaoEncontradoException;
import com.adatech.desafiopratico.models.Ator;
import com.adatech.desafiopratico.models.Diretor;
import com.adatech.desafiopratico.models.Filme;
import com.adatech.desafiopratico.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FilmeService {

    private final FilmeRepository filmeRepository;
    private final AtorService atorService;
    private final DiretorService diretorService;

    public FilmeService(FilmeRepository filmeRepository, AtorService atorService, DiretorService diretorService) {
        this.filmeRepository = filmeRepository;
        this.atorService = atorService;
        this.diretorService = diretorService;
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

    public Filme cadastrarNovoFilme(FilmeDto filmeDto) {
        if (!temAtores(filmeDto) && !temDiretor(filmeDto)) {
            return cadastrarNovoFilmeSemDiretorEAtores(filmeDto);
        } else if (!temAtores(filmeDto) && temDiretor(filmeDto)) {
            return cadastrarNovoFilmeSemAtores(filmeDto);
        } else if (temAtores(filmeDto) && !temDiretor(filmeDto)) {
            return cadastrarNovoFilmeSemDiretor(filmeDto);
        } else {
            return cadastrarNovoFilmeCompleto(filmeDto);
        }
    }

    private void vincularAtoresFilme(Filme filme, Set<Ator> atoresFilme) {
        atoresFilme.forEach(
                ator -> filmeRepository
                        .vincularAtoresFilme(new FilmeAtorDto(filme.getIdFilme(), ator.getIdAtor())));
        filme.setAtoresFilme(atoresFilme);
    }


    private Filme cadastrarNovoFilmeSemDiretorEAtores(FilmeDto filmeDto) {
        Filme novoFilme = filmeDto.mapearParaEntidade();
        return filmeRepository.save(novoFilme);
    }

    private Filme cadastrarNovoFilmeSemAtores(FilmeDto filmeDto) {
        Filme novoFilme = cadastrarNovoFilmeSemDiretorEAtores(filmeDto);
        Diretor diretorFilme = diretorService.cadastrarNovoDiretor(filmeDto.diretorFilme());
        novoFilme.setDiretorFilme(diretorFilme);
        return filmeRepository.save(novoFilme);
    }

    private Filme cadastrarNovoFilmeSemDiretor(FilmeDto filmeDto) {
        Filme novoFilme = cadastrarNovoFilmeSemDiretorEAtores(filmeDto);
        Set<Ator> atoresFilme = mapearAtoresDtoAtoresFilme(filmeDto);
        vincularAtoresFilme(novoFilme, atoresFilme);
        return novoFilme;
    }

    private Filme cadastrarNovoFilmeCompleto(FilmeDto filmeDto) {
        Filme novoFilme = cadastrarNovoFilmeSemDiretor(filmeDto);
        Diretor diretorFilme = diretorService.cadastrarNovoDiretor(filmeDto.diretorFilme());
        novoFilme.setDiretorFilme(diretorFilme);

        return novoFilme;
    }

    private Set<Ator> mapearAtoresDtoAtoresFilme(FilmeDto filmeDto) {
        Set<Ator> atoresFilme = new HashSet<>();
        filmeDto.atoresFilme()
                .stream()
                .map(atorService::cadastrarNovoAtor)
                .forEach(atoresFilme::add);
        return atoresFilme;
    }

    private Boolean temDiretor(FilmeDto filmeDto) {
        return filmeDto.diretorFilme() != null;
    }

    private Boolean temAtores(FilmeDto filmeDto) {
        return filmeDto.atoresFilme() != null && !filmeDto.atoresFilme().isEmpty();
    }

}
