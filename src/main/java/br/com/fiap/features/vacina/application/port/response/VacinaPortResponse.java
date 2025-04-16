package br.com.fiap.features.vacina.application.port.response;

import lombok.Builder;

@Builder
public record VacinaPortResponse(
        String nome,
        String fabricante,
        Integer doseAplicada,
        Integer doses,
        Integer intervaloEntreDosesEmDias,
        String descricao,
        String grupo
) {

}