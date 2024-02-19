package com.adatech.desafiopratico.repository;

import com.adatech.desafiopratico.dto.AtorDto;
import com.adatech.desafiopratico.models.Ator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AtorRepository extends JpaRepository<Ator, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO atores (nome_ator) VALUES (:#{#ator.getNomeAtor()}) ON CONFLICT DO NOTHING;", nativeQuery = true)
    Integer adicionarNovoAtor(@Param("ator") Ator ator);
}
