package com.adatech.desafiopratico.services;

import com.adatech.desafiopratico.dto.FilmeAtorDto;
import com.adatech.desafiopratico.dto.FilmeDto;
import com.adatech.desafiopratico.excecoes.NaoEncontradoException;
import com.adatech.desafiopratico.models.Ator;
import com.adatech.desafiopratico.models.Diretor;
import com.adatech.desafiopratico.models.Filme;
import com.adatech.desafiopratico.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FilmeService {

    private FilmeRepository filmeRepository;
    private AtorService atorService;
    private DiretorService diretorService;

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
        Set<Ator> atoresFilme = new HashSet<>();
        filmeDto.atoresFilme()
                .stream()
                .map(atorDto -> atorService.cadastrarNovoAtor(atorDto))
                .forEach(atoresFilme::add);
        Diretor diretorFilme = diretorService.cadastrarNovoDiretor(filmeDto.diretorFilme());
        Filme novoFilme = filmeRepository.save(montarNovoFilme(filmeDto, diretorFilme, atoresFilme));
        atoresFilme.forEach(ator -> vincularAtoresFilme(novoFilme, ator));
        return novoFilme;
    }

    private void vincularAtoresFilme(Filme filme, Ator ator) {
        FilmeAtorDto atorFilmeDto = new FilmeAtorDto(
                filme.getIdFilme(), ator.getIdAtor()
        );
        filmeRepository.vincularAtoresFilme(atorFilmeDto);
    }

    private Filme montarNovoFilme(FilmeDto filmeDto, Diretor diretorFilme, Set<Ator> atoresSet) {
        Filme novoFilme = new Filme();

        novoFilme.setNomeFilme(filmeDto.nomeFilme());
        novoFilme.setDuracaoTotalMinutos(filmeDto.duracaoTotalMinutos());
        novoFilme.setAvaliacaoFilme(filmeDto.avaliacaoFilme());
        novoFilme.setAnoLancamentoFilme(filmeDto.anoLancamentoFilme());
        novoFilme.setOrcamentoFilme(filmeDto.orcamentoFilme());
        novoFilme.setDescricao(filmeDto.descricao());
        novoFilme.setDiretorFilmeId(diretorFilme.getIdDiretor());
        novoFilme.setDiretorFilme(diretorFilme);
        novoFilme.setAtoresFilme(atoresSet);

        return novoFilme;
    }
}
