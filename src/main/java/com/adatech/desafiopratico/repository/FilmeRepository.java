package com.adatech.desafiopratico.repository;

import com.adatech.desafiopratico.models.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FilmeRepository extends JpaRepository<Filme, Integer> {}
