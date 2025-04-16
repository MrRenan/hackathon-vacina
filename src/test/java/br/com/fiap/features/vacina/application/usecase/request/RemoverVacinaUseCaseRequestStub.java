package br.com.fiap.features.vacina.application.usecase.request;

import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class RemoverVacinaUseCaseRequestStub {

    protected static final Faker faker = new Faker();

    public static RemoverVacinaUseCaseRequestStub novo() {
        return new RemoverVacinaUseCaseRequestStub();
    }

    public RemoverVacinaUseCaseRequest build() {
        return RemoverVacinaUseCaseRequest.builder()
                .nome(faker.lorem().word())
                .build();
    }

}