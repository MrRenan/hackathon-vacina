package br.com.fiap.features.vacina.application.usecase.response;

import lombok.Builder;

@Builder
public record VacinaUseCaseResponse(
        String nome,
        String fabricante,
        Integer doseAplicada,
        Integer doses,
        Integer intervaloEntreDosesEmDias,
        String descricao,
        String grupo
) {

}