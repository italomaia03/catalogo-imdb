package com.adatech.desafiopratico.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "diretores")
public class Diretor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_diretor")
    private Integer idDiretor;
    @Column(name = "nome_diretor", nullable = false, unique = true)
    private String nomeDiretor;

    @OneToMany(mappedBy = "diretorFilme", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Filme> filmesDiretor;

    public Integer getIdDiretor() {
        return idDiretor;
    }

    public void setIdDiretor(Integer idDiretor) {
        this.idDiretor = idDiretor;
    }

    public String getNomeDiretor() {
        return nomeDiretor;
    }

    public void setNomeDiretor(String nomeDiretor) {
        this.nomeDiretor = nomeDiretor;
    }
    @JsonBackReference
    public Set<Filme> getFilmesDiretor() {
        return filmesDiretor;
    }
    public void setFilmesDiretor(Set<Filme> filmesDiretor) {
        this.filmesDiretor = filmesDiretor;
    }
}
