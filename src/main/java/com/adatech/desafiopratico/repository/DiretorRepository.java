package com.adatech.desafiopratico.repository;

import com.adatech.desafiopratico.models.Diretor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DiretorRepository extends JpaRepository<Diretor, Integer> {
    @Transactional
    @Query(value = "INSERT INTO diretores (nome_diretor) VALUES (:#{#diretor.getNomeDiretor()}) ON CONFLICT DO NOTHING RETURNING *", nativeQuery = true)
    Diretor adicionarNovoDiretor(@Param("diretor") Diretor diretor);
    Diretor findDiretorByNomeDiretor(String nomeDiretor);
}
