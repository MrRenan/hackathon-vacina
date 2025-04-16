package br.com.fiap.features.vacina.application.usecase.request;

import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class AtualizarVacinaUseCaseRequestStub {

    protected static final Faker faker = new Faker();

    public static AtualizarVacinaUseCaseRequestStub novo() {
        return new AtualizarVacinaUseCaseRequestStub();
    }

    public AtualizarVacinaUseCaseRequest build() {
        return AtualizarVacinaUseCaseRequest.builder()
                .nome(faker.number().digits(11))
                .fabricante(faker.company().name())
                .doseAplicada(faker.number().numberBetween(1, 3))
                .doses(faker.number().numberBetween(3, 4))
                .intervaloEntreDosesEmDias(faker.number().numberBetween(30, 60))
                .descricao(faker.lorem().paragraph())
                .grupo(faker.lorem().word())
                .build();
    }

}