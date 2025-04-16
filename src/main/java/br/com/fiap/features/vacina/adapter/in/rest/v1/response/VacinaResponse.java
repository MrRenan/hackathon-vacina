package br.com.fiap.features.vacina.adapter.in.rest.v1.response;

import lombok.Builder;

@Builder
public record VacinaResponse(
        String nome,
        String fabricante,
        Integer doseAplicada,
        Integer doses,
        Integer intervaloEntreDosesEmDias,
        String descricao,
        String grupo
) {
}