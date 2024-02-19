package com.adatech.desafiopratico.repository;

import com.adatech.desafiopratico.models.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Integer> {
    @Query(value = "SELECT * FROM filmes WHERE LOWER(nome_filme) LIKE LOWER(:nomeFilme)||'%'", nativeQuery = true)
    Optional<List<Filme>> buscarFilmePorNome(@Param("nomeFilme") String nomeFilme);
}
