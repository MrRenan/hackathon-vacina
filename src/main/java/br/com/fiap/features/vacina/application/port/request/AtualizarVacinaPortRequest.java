package br.com.fiap.features.vacina.application.port.request;

import lombok.Builder;

@Builder
public record AtualizarVacinaPortRequest(
        String nome,
        String fabricante,
        Integer doseAplicada,
        Integer doses,
        Integer intervaloEntreDosesEmDias,
        String descricao,
        String grupo
) {

}