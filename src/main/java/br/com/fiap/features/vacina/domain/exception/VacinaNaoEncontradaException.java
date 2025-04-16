package br.com.fiap.features.vacina.domain.exception;

public class VacinaNaoEncontradaException extends RuntimeException {

    public VacinaNaoEncontradaException() {
        super("Vacina não encontrada.");
    }

}