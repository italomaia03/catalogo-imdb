package com.adatech.desafiopratico.services;

import com.adatech.desafiopratico.dto.DiretorDto;
import com.adatech.desafiopratico.models.Diretor;
import com.adatech.desafiopratico.repository.DiretorRepository;
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
            return verificarDiretorEstaCadastrado(diretorDto);
        } catch (NullPointerException e) {
            Diretor novoDiretor = (Diretor) diretorDto.mapearParaEntidade();
            return diretorRepository.adicionarNovoDiretor(novoDiretor);
        }
    }

    private Diretor verificarDiretorEstaCadastrado(DiretorDto diretorDto) {
        Diretor diretor = diretorRepository.findDiretorByNomeDiretor(diretorDto.getNome());
        if (diretor == null) {
            throw new NullPointerException();
        }
        return diretor;
    }
}
