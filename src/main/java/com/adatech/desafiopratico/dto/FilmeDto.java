package com.adatech.desafiopratico.dto;

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
}
