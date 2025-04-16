package br.com.fiap.features.vacina.application.port.request;

import lombok.Builder;

@Builder
public record BuscarVacinaPorNomePortRequest(
        String nome
) {

}