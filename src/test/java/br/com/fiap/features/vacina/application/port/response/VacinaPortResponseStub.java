package br.com.fiap.features.vacina.application.port.response;

import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class VacinaPortResponseStub {

    protected static final Faker faker = new Faker();

    public static VacinaPortResponseStub novo() {
        return new VacinaPortResponseStub();
    }

    public VacinaPortResponse build() {
        return VacinaPortResponse.builder()
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