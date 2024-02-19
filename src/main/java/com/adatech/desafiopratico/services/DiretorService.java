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
        Diretor novoDiretor = new Diretor();
        novoDiretor.setNomeDiretor(diretorDto.getNome());
        return diretorRepository.adicionarNovoDiretor(novoDiretor);
    }
}
