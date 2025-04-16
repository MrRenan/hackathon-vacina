package br.com.fiap.features.vacina.domain.exception;

public class VacinaCadastradaException extends RuntimeException {

    public VacinaCadastradaException() {
        super("Vacina possui cadastro.");
    }

}