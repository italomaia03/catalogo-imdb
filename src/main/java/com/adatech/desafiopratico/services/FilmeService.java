package com.adatech.desafiopratico.services;

import com.adatech.desafiopratico.dto.AtorDto;
import com.adatech.desafiopratico.dto.DiretorDto;
import com.adatech.desafiopratico.dto.exceptions.DtoInvalidoException;
import com.adatech.desafiopratico.dto.filme.FilmeAtorDto;
import com.adatech.desafiopratico.dto.filme.FilmeDto;
import com.adatech.desafiopratico.services.exceptions.CampoInvalidoException;
import com.adatech.desafiopratico.services.exceptions.NaoEncontradoException;
import com.adatech.desafiopratico.models.Ator;
import com.adatech.desafiopratico.models.Diretor;
import com.adatech.desafiopratico.models.Filme;
import com.adatech.desafiopratico.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
            filmesEncontrados = buscarFilmePorNome(nomeFilme);
        }
        return filmesEncontrados;
    }

    private List<Filme> buscarFilmePorNome(String nomeFilme) {
        Optional<List<Filme>> filmesEncontrados = filmeRepository.buscarFilmePorNome(nomeFilme);
        if (filmesEncontrados.isEmpty()) {
            throw new NaoEncontradoException(String.format("Filme %s não foi encontrado.", nomeFilme));
        }
        return filmesEncontrados.get();
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

    public Filme atualizarFilme(Integer idFilme, FilmeDto filmeDto) throws NaoEncontradoException {
        Filme filmeInteresse = filmeRepository.findById(idFilme)
                .orElseThrow(() -> new NaoEncontradoException(String.format("Não foi encontrado filme com o ID = %d.", idFilme)));
        if(temDiretor(filmeDto) && !temAtores(filmeDto)){
            vincularDiretorFilme(filmeInteresse, filmeDto.diretorFilme());
        } else if (!temDiretor(filmeDto) && temAtores(filmeDto)) {
            Set<Ator> atoresFilme = mapearAtoresDtoAtoresFilme(filmeDto);
            vincularAtoresFilme(filmeInteresse, atoresFilme);
        } else if (temDiretor(filmeDto) && temAtores(filmeDto)){
            Set<Ator> atoresFilme = mapearAtoresDtoAtoresFilme(filmeDto);
            vincularDiretorFilme(filmeInteresse, filmeDto.diretorFilme());
            vincularAtoresFilme(filmeInteresse, atoresFilme);
        }
        return filmeInteresse;
    }

    private void vincularDiretorFilme(Filme filme, DiretorDto diretorDto) {
        Diretor diretorFilme;
        try {
            diretorFilme = diretorService.verificarDiretorEstaCadastrado(diretorDto);
        } catch (NaoEncontradoException e) {
            diretorFilme = diretorService.cadastrarNovoDiretor(diretorDto);
        }

        filme.setDiretorFilmeId(diretorFilme.getIdDiretor());
        filme.setDiretorFilme(diretorFilme);

        filmeRepository.vincularDiretorFilme(filme);
    }

    private void vincularAtoresFilme(Filme filme, Set<Ator> atoresFilme) {
        atoresFilme.forEach(
                ator -> filmeRepository
                        .vincularAtoresFilme(new FilmeAtorDto(filme.getIdFilme(), ator.getIdAtor())));
        filme.setAtoresFilme(atoresFilme);
    }

    private Filme cadastrarNovoFilmeSemDiretorEAtores(FilmeDto filmeDto) {
        try {
            filmeDto.verificarFilmeValido();
            Filme novoFilme = filmeDto.mapearParaEntidade();
            return filmeRepository.save(novoFilme);
        } catch (DtoInvalidoException exception) {
            throw new CampoInvalidoException(exception.getMessage());
        }
    }

    private Filme cadastrarNovoFilmeSemAtores(FilmeDto filmeDto) {
        Filme novoFilme = cadastrarNovoFilmeSemDiretorEAtores(filmeDto);
        vincularDiretorFilme(novoFilme, filmeDto.diretorFilme());
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
        vincularDiretorFilme(novoFilme,filmeDto.diretorFilme());
        vincularAtoresFilme(novoFilme, mapearAtoresDtoAtoresFilme(filmeDto));

        return novoFilme;
    }

    private Set<Ator> mapearAtoresDtoAtoresFilme(FilmeDto filmeDto) {
        Set<Ator> atoresFilme = new HashSet<>();
        filmeDto.atoresFilme()
                .stream()
                .map(this::cadastroCascadeAtorFilme)
                .forEach(atoresFilme::add);
        return atoresFilme;
    }

    private Ator cadastroCascadeAtorFilme(AtorDto atorFilme) {
        Ator ator;
        try {
            ator = atorService.verificarAtorEstaCadastrado(atorFilme);
        } catch (NaoEncontradoException e) {
            ator = atorService.cadastrarNovoAtor(atorFilme);
        }
        return ator;
    }

    private Boolean temDiretor(FilmeDto filmeDto) {
        return filmeDto.diretorFilme() != null;
    }

    private Boolean temAtores(FilmeDto filmeDto) {
        return filmeDto.atoresFilme() != null && !filmeDto.atoresFilme().isEmpty();
    }

}
