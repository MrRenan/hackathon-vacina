package br.com.fiap.features.vacina.application.usecase.request;

import lombok.Builder;

@Builder
public record AtualizarVacinaUseCaseRequest(
        String nome,
        String fabricante,
        Integer doseAplicada,
        Integer doses,
        Integer intervaloEntreDosesEmDias,
        String descricao,
        String grupo
) {

}