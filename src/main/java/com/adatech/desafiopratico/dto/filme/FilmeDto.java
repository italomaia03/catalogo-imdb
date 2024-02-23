package com.adatech.desafiopratico.dto.filme;

import com.adatech.desafiopratico.dto.AtorDto;
import com.adatech.desafiopratico.dto.DiretorDto;
import com.adatech.desafiopratico.dto.exceptions.DtoInvalidoException;
import com.adatech.desafiopratico.models.Filme;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public record FilmeDto(
        String nomeFilme,
        Integer duracaoTotalMinutos,
        Double avaliacaoFilme,
        Integer anoLancamentoFilme,
        BigInteger orcamentoFilme,
        String descricao,
        DiretorDto diretorFilme,
        Set<AtorDto> atoresFilme
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

    private List<Field> verificarCamposNulos() throws NoSuchFieldException, IllegalAccessException {
        List<Field> list = new ArrayList<>();
        for (Field field : this.getClass()
                .getDeclaredFields()) {
            if (verificarValorAtributos(field.getName())) {
                list.add(field);
            }
        }
        return list;
    }

    private Boolean verificarValorAtributos(String nomeCampo) throws NoSuchFieldException, IllegalAccessException {
        Field atributo = this.getClass().getDeclaredField(nomeCampo);
        return (atributo.get(this) == null)
                && (!(nomeCampo.contains("descricao"))
                    && !(nomeCampo.contains("atores"))
                    && !(nomeCampo.contains("diretor")));
    }

    public void verificarFilmeValido() {
        try{
            if(verificarCamposNulos().isEmpty()) {
                return;
            }
            String camposNulos = formatarMensagemErro();
            throw new DtoInvalidoException(camposNulos);
        } catch (NoSuchFieldException | IllegalAccessException exception) {
            throw new DtoInvalidoException(exception.getMessage());
        }
    }

    private String formatarMensagemErro() throws NoSuchFieldException, IllegalAccessException {
        String camposNulosString;
        List<String> camposNulos = verificarCamposNulos().stream().map(Field::getName).toList();
        if (camposNulos.size() > 1) {
            StringBuilder camposNulosStringBuilder = new StringBuilder();
            for (String campoNulo : camposNulos) {
                camposNulosStringBuilder.append(campoNulo).append(", ");
            }
            camposNulosStringBuilder.replace(camposNulosStringBuilder.lastIndexOf(","), camposNulosStringBuilder.length(), ".");
            camposNulosString = String.format("Os campos a seguir não podem ser nulos: %s", camposNulosStringBuilder);
        } else {
            camposNulosString = String.format("O campo a seguir não pode ser nulo: %s", verificarCamposNulos().getFirst().getName());
        }
        return camposNulosString;
    }
}
