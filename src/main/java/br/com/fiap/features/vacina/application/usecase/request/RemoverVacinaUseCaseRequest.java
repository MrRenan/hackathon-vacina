package br.com.fiap.features.vacina.application.usecase.request;

import lombok.Builder;

@Builder
public record RemoverVacinaUseCaseRequest(
        String nome
) {

}