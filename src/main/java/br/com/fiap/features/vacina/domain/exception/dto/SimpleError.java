package br.com.fiap.features.vacina.domain.exception.dto;

import lombok.Builder;

@Builder
public record SimpleError (
        String message,
        String code
) {
}
