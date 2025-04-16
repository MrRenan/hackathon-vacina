package br.com.fiap.features.vacina.application.port.request;

import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class RemoverVacinaPortRequestStub {

    protected static final Faker faker = new Faker();

    public static RemoverVacinaPortRequestStub novo() {
        return new RemoverVacinaPortRequestStub();
    }

    public RemoverVacinaPortRequest build() {
        return RemoverVacinaPortRequest.builder()
                .nome(faker.lorem().word())
                .build();
    }

}