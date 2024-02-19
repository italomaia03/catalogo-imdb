package com.adatech.desafiopratico.dto;

import com.adatech.desafiopratico.models.Filme;

import java.math.BigInteger;
import java.util.List;

public record FilmeDto(
        String nomeFilme,
        Integer duracaoTotalMinutos,
        Double avaliacaoFilme,
        Integer anoLancamentoFilme,
        BigInteger orcamentoFilme,
        String descricao,
        DiretorDto diretorFilme,
        List<AtorDto> atoresFilme
) {
    public Filme mapearParaEntidade() {
        Filme novoFilme = new Filme();

        novoFilme.setNomeFilme(this.nomeFilme());
        novoFilme.setDuracaoTotalMinutos(this.duracaoTotalMinutos());
        novoFilme.setAvaliacaoFilme(this.avaliacaoFilme());
        novoFilme.setAnoLancamentoFilme(this.anoLancamentoFilme());
        novoFilme.setOrcamentoFilme(this.orcamentoFilme());
        novoFilme.setDescricao(this.descricao());


        return novoFilme;
    }

    public FilmeDto mapearParaDto(Filme filme) {
        List<AtorDto> atoresDto = filme.getAtoresFilme().stream().map(
                atores -> (AtorDto) new AtorDto().mapearParaDto(atores)
        ).toList();
        DiretorDto diretorFilmeDto = (DiretorDto) new DiretorDto().mapearParaDto(filme.getDiretorFilme());
        return new FilmeDto(
                filme.getNomeFilme(),
                filme.getDuracaoTotalMinutos(),
                filme.getAvaliacaoFilme(),
                filme.getAnoLancamentoFilme(),
                filme.getOrcamentoFilme(),
                filme.getDescricao(),
                diretorFilmeDto,
                atoresDto
        );
    }
}
