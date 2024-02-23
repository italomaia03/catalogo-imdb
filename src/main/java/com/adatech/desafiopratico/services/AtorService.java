package com.adatech.desafiopratico.services;

import com.adatech.desafiopratico.dto.AtorDto;
import com.adatech.desafiopratico.dto.exceptions.DtoInvalidoException;
import com.adatech.desafiopratico.models.Ator;
import com.adatech.desafiopratico.repository.AtorRepository;
import com.adatech.desafiopratico.services.exceptions.CampoInvalidoException;
import com.adatech.desafiopratico.services.exceptions.NaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtorService {
    private final AtorRepository atorRepository;

    public AtorService(AtorRepository atorRepository) {
        this.atorRepository = atorRepository;
    }

    public List<Ator> listarAtores() {
        return atorRepository.findAll();
    }


    public Ator cadastrarNovoAtor(AtorDto atorDto){
        try {
            atorDto.validarPessoaDto();
            Ator novoAtor = (Ator) atorDto.mapearParaEntidade();
            return atorRepository.save(novoAtor);
        } catch (DtoInvalidoException exception) {
            throw new CampoInvalidoException(exception.getMessage());
        }
    }

    public Ator verificarAtorEstaCadastrado(AtorDto atorDto) {
        Ator ator = atorRepository.findAtorByNomeAtor(atorDto.getNome());
        if (ator == null) {
            throw new NaoEncontradoException("Não foi encontrado ator com o nome " + atorDto.getNome() + ".");
        }
        return ator;
    }
}
