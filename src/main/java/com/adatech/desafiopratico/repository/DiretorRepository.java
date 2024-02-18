package com.adatech.desafiopratico.repository;

import com.adatech.desafiopratico.models.Diretor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiretorRepository extends JpaRepository<Diretor, Integer> {
}
