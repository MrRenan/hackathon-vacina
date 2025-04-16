package br.com.fiap.features.vacina.adapter.in.rest.v1.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CriarVacinaRequest(
        @NotBlank
        String nome,
        @NotBlank
        String fabricante,
        Integer doseAplicada,
        Integer doses,
        @NotBlank
        Integer intervaloEntreDosesEmDias,
        @NotBlank
        String descricao,
        String grupo
) {

}