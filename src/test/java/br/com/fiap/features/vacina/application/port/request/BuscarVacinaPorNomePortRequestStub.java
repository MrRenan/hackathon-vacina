package br.com.fiap.features.vacina.application.port.request;

import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class BuscarVacinaPorNomePortRequestStub {

    protected static final Faker faker = new Faker();

    public static BuscarVacinaPorNomePortRequestStub novo() {
        return new BuscarVacinaPorNomePortRequestStub();
    }

    public BuscarVacinaPorNomePortRequest build() {
        return BuscarVacinaPorNomePortRequest.builder()
                .nome(faker.lorem().word())
                .build();
    }

}