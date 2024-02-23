package com.adatech.desafiopratico.services;

import com.adatech.desafiopratico.dto.DiretorDto;
import com.adatech.desafiopratico.dto.exceptions.DtoInvalidoException;
import com.adatech.desafiopratico.models.Diretor;
import com.adatech.desafiopratico.repository.DiretorRepository;
import com.adatech.desafiopratico.services.exceptions.CampoInvalidoException;
import com.adatech.desafiopratico.services.exceptions.NaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiretorService {
    private final DiretorRepository diretorRepository;

    public DiretorService(DiretorRepository diretorRepository) {
        this.diretorRepository = diretorRepository;
    }

    public List<Diretor> listarDiretores() {
        return diretorRepository.findAll();
    }

    public Diretor cadastrarNovoDiretor(DiretorDto diretorDto){
        try {
            diretorDto.validarPessoaDto();
            Diretor novoDiretor = (Diretor) diretorDto.mapearParaEntidade();
            return diretorRepository.save(novoDiretor);
        } catch (DtoInvalidoException exception) {
            throw new CampoInvalidoException(exception.getMessage());
        }
    }

    public Diretor verificarDiretorEstaCadastrado(DiretorDto diretorDto) {
        Diretor diretor = diretorRepository.findDiretorByNomeDiretor(diretorDto.getNome());
        if (diretor == null) {
            throw new NaoEncontradoException("Não foi encontrado diretor com o nome " + diretorDto.getNome() + ".");
        }
        return diretor;
    }
}
