package br.com.fiap.infra.mongodb.vacina.document;

import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class VacinaDocumentStub {

    protected static final Faker faker = new Faker();

    public static VacinaDocumentStub novo() {
        return new VacinaDocumentStub();
    }

    public VacinaDocument build() {
        return VacinaDocument.builder()
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