package com.adatech.desafiopratico.services;

import com.adatech.desafiopratico.dto.AtorDto;
import com.adatech.desafiopratico.dto.DiretorDto;
import com.adatech.desafiopratico.dto.PessoaDto;
import com.adatech.desafiopratico.models.Ator;
import com.adatech.desafiopratico.models.Diretor;
import com.adatech.desafiopratico.repository.AtorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtorService {
    private AtorRepository atorRepository;

    public AtorService(AtorRepository atorRepository) {
        this.atorRepository = atorRepository;
    }

    public List<Ator> listarAtores() {
        return atorRepository.findAll();
    }

//    public Ator cadastrarNovoAtor(AtorDto atorDto) {
//        Ator novoAtor = new Ator();
//        novoAtor.setNomeAtor(atorDto.getNome());
//        novoAtor = atorRepository.adicionarNovoAtor(novoAtor);
//        return novoAtor;
//    }

    public Ator cadastrarNovoAtor(AtorDto atorDto){
        try {
            return verificarAtorEstaCadastrado(atorDto);
        } catch (NullPointerException e) {
            Ator novoAtor = new Ator(atorDto.getNome());
            return atorRepository.adicionarNovoAtor(novoAtor);
        }
    }

    private Ator verificarAtorEstaCadastrado(AtorDto atorDto) {
        Ator ator = atorRepository.findAtorByNomeAtor(atorDto.getNome());
        if (ator == null) {
            throw new NullPointerException();
        }
        return ator;
    }
}
