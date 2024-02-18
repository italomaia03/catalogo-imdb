package com.adatech.desafiopratico.repository;

import com.adatech.desafiopratico.models.Ator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtorRepository extends JpaRepository<Ator, Integer> {
}
