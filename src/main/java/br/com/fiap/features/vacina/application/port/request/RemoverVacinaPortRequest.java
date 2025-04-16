package br.com.fiap.features.vacina.application.port.request;

import lombok.Builder;

@Builder
public record RemoverVacinaPortRequest(
        String nome
) {

}