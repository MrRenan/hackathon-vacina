package br.com.fiap.config.exception;

import br.com.fiap.features.vacina.domain.exception.VacinaNaoEncontradaException;
import br.com.fiap.features.vacina.domain.exception.VacinaCadastradaException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler handler;

    @Test
    @DisplayName("Deve retornar status 409 CONFLICT quando VacinaCadastradaException for lançada")
    void test01() {
        // ASSETS
        var mensagemEsperada = "Vacina possui cadastro.";
        var ex = new VacinaCadastradaException();

        // ACTION
        var response = handler.handleVacinaCadastradaException(ex);

        // ASSERTIONS
        assertAll(
                () -> assertEquals(CONFLICT, response.getStatusCode()),
                () -> assertEquals(mensagemEsperada, Objects.requireNonNull(response.getBody()).message()),
                () -> assertEquals(CONFLICT.toString(), Objects.requireNonNull(response.getBody()).code())
        );
    }

    @Test
    @DisplayName("Deve retornar status 404 NOT FOUND quando VacinaNaoEncontradaException for lançada")
    void test02() {
        // ASSETS
        var mensagemEsperada = "Vacina não encontrada.";
        var ex = new VacinaNaoEncontradaException();

        // ACTION
        var response = handler.handleVacinaNaoEncontradaException(ex);

        // ASSERTIONS
        assertAll(
                () -> assertEquals(NOT_FOUND, response.getStatusCode()),
                () -> assertEquals(mensagemEsperada, Objects.requireNonNull(response.getBody()).message()),
                () -> assertEquals(NOT_FOUND.toString(), Objects.requireNonNull(response.getBody()).code())
        );
    }

    @Test
    @DisplayName("Deve retornar status 500 INTERNAL SERVER ERROR quando Exception for lançada")
    void test03() {
        // ASSETS
        var ex = new RuntimeException("Erro genérico");
        var mensagemEsperada = "Ocorreu um erro interno: ".concat(ex.getMessage());

        // ACTION
        var response = handler.handleTodasExcessoes(ex);

        // ASSERTIONS
        assertAll(
                () -> assertEquals(INTERNAL_SERVER_ERROR, response.getStatusCode()),
                () -> assertEquals(mensagemEsperada, Objects.requireNonNull(response.getBody()).message()),
                () -> assertEquals(INTERNAL_SERVER_ERROR.toString(), Objects.requireNonNull(response.getBody()).code())
        );
    }

}