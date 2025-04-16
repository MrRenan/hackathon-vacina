package br.com.fiap.features.vacina.application.usecase.request;

import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class BuscarVacinaPorNomeUseCaseRequestStub {

    protected static final Faker faker = new Faker();

    public static BuscarVacinaPorNomeUseCaseRequestStub novo() {
        return new BuscarVacinaPorNomeUseCaseRequestStub();
    }

    public BuscarVacinaPorNomeUseCaseRequest build() {
        return BuscarVacinaPorNomeUseCaseRequest.builder()
                .nome(faker.lorem().word())
                .build();
    }

}