package com.adatech.desafiopratico.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Set;

@Entity
@Table(name = "filmes")
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_filme")
    private Integer idFilme;
    @Column(name = "nome_filme", nullable = false)
    private String nomeFilme;
    @Column(name = "duracao_total_min", nullable = false)
    private Integer duracaoTotalMinutos;
    @Column(name = "avaliacao_filme")
    private Double avaliacaoFilme;
    @Column(name = "ano_lancamento_filme", nullable = false)
    private Integer anoLancamentoFilme;
    @Column(name = "orcamento_filme", nullable = false)
    private BigInteger orcamentoFilme;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "diretor_filme", nullable = false)
    @JsonIgnore
    private Integer diretorFilmeId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "diretor_filme", referencedColumnName = "id_diretor", insertable = false, updatable = false)
    private Diretor diretorFilme;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "filme_ator",
            joinColumns = {@JoinColumn(name = "id_filme", insertable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "id_ator", insertable = false, updatable = false)}
    )
    private Set<Ator> atoresFilme;

    public Integer getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(Integer idFilme) {
        this.idFilme = idFilme;
    }

    public String getNomeFilme() {
        return nomeFilme;
    }

    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    public Integer getDuracaoTotalMinutos() {
        return duracaoTotalMinutos;
    }

    public void setDuracaoTotalMinutos(Integer duracaoTotalMinutos) {
        this.duracaoTotalMinutos = duracaoTotalMinutos;
    }

    public Double getAvaliacaoFilme() {
        return avaliacaoFilme;
    }

    public void setAvaliacaoFilme(Double avaliacaoFilme) {
        this.avaliacaoFilme = avaliacaoFilme;
    }

    public Integer getAnoLancamentoFilme() {
        return anoLancamentoFilme;
    }

    public void setAnoLancamentoFilme(Integer anoLancamentoFilme) {
        this.anoLancamentoFilme = anoLancamentoFilme;
    }

    @JsonManagedReference
    public Set<Ator> getAtoresFilme() {
        return atoresFilme;
    }

    public void setAtoresFilme(Set<Ator> atoresFilme) {
        this.atoresFilme = atoresFilme;
    }

    public BigInteger getOrcamentoFilme() {
        return orcamentoFilme;
    }

    public void setOrcamentoFilme(BigInteger orcamentoFilme) {
        this.orcamentoFilme = orcamentoFilme;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Integer getDiretorFilmeId() {
        return diretorFilmeId;
    }

    public void setDiretorFilmeId(Integer diretoresFilme) {
        this.diretorFilmeId = diretoresFilme;
    }

    @JsonManagedReference
    public Diretor getDiretorFilme() {
        return diretorFilme;
    }

    public void setDiretorFilme(Diretor diretorFilme) {
        this.diretorFilme = diretorFilme;
    }
}
