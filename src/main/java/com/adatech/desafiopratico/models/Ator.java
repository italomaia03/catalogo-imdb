package com.adatech.desafiopratico.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "atores")
public class Ator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_ator")
    private Integer idAtor;
    @Column(name = "nome_ator", nullable = false, unique = true)
    private String nomeAtor;

    @ManyToMany(mappedBy = "atoresFilme", cascade = CascadeType.ALL)
    private Set<Filme> filmesAtor;


    public Integer getIdAtor() {
        return idAtor;
    }

    public void setIdAtor(Integer idAtor) {
        this.idAtor = idAtor;
    }

    public String getNomeAtor() {
        return nomeAtor;
    }

    public void setNomeAtor(String nomeAtor) {
        this.nomeAtor = nomeAtor;
    }
    @JsonBackReference
    public Set<Filme> getFilmesAtor() {
        return filmesAtor;
    }

    public void setFilmesAtor(Set<Filme> filmesAtor) {
        this.filmesAtor = filmesAtor;
    }
}
