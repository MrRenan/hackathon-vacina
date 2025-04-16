package br.com.fiap.config.exception;

import br.com.fiap.features.vacina.domain.exception.VacinaCadastradaException;
import br.com.fiap.features.vacina.domain.exception.VacinaNaoEncontradaException;
import br.com.fiap.features.vacina.domain.exception.dto.SimpleError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VacinaCadastradaException.class)
    public ResponseEntity<SimpleError> handleVacinaCadastradaException(VacinaCadastradaException ex) {
        return ResponseEntity.status(CONFLICT).body(new SimpleError(ex.getMessage(), CONFLICT.toString()));
    }

    @ExceptionHandler(VacinaNaoEncontradaException.class)
    public ResponseEntity<SimpleError> handleVacinaNaoEncontradaException(VacinaNaoEncontradaException ex) {
        return ResponseEntity.status(NOT_FOUND).body(new SimpleError(ex.getMessage(), NOT_FOUND.toString()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<SimpleError> handleTodasExcessoes(Exception ex) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(new SimpleError("Ocorreu um erro interno: " + ex.getMessage(), INTERNAL_SERVER_ERROR.toString()));
    }

}