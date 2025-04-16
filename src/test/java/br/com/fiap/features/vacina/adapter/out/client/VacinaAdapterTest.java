package br.com.fiap.features.vacina.adapter.out.client;

import br.com.fiap.features.vacina.adapter.out.client.mapper.VacinaOutMapper;
import br.com.fiap.features.vacina.application.port.request.AtualizarVacinaPortRequestStub;
import br.com.fiap.features.vacina.application.port.request.BuscarVacinaPorNomePortRequestStub;
import br.com.fiap.features.vacina.application.port.request.CriarVacinaPortRequestStub;
import br.com.fiap.features.vacina.application.port.request.RemoverVacinaPortRequestStub;
import br.com.fiap.features.vacina.domain.exception.VacinaCadastradaException;
import br.com.fiap.features.vacina.domain.exception.VacinaNaoEncontradaException;
import br.com.fiap.infra.mongodb.vacina.document.VacinaDocument;
import br.com.fiap.infra.mongodb.vacina.document.VacinaDocumentStub;
import br.com.fiap.infra.mongodb.vacina.repository.VacinaMongoDBRepository;
import com.mongodb.client.result.DeleteResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mapstruct.factory.Mappers.getMapper;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VacinaAdapterTest {

    @InjectMocks
    private VacinaAdapter adapter;
    @Mock
    private VacinaMongoDBRepository repository;
    @Mock
    private MongoTemplate mongoTemplate;
    @Spy
    private VacinaOutMapper mapper = getMapper(VacinaOutMapper.class);

    @Nested
    @DisplayName("Porta de criar vacina")
    class CriarVacina {

        @Test
        @DisplayName("Deve executar porta de criar vacina com sucesso")
        void test01() {
            // ASSETS
            var request = CriarVacinaPortRequestStub.novo().build();
            var document = VacinaDocumentStub.novo().build();
            when(repository.findByNome(any())).thenReturn(Optional.empty());
            when(repository.save(any())).thenReturn(document);

            // ACTION
            var result = adapter.criarVacina(request);

            // ASSERTIONS
            assertThat(result).usingRecursiveComparison().isEqualTo(document);
            verify(mapper).paraVacinaDocument(request);
            verify(mapper).paraVacinaPortResponse(document);

        }

        @Test
        @DisplayName("Deve executar porta de criar vacina com erro, quando vacina ja existir")
        void test02() {
            // ASSETS
            var request = CriarVacinaPortRequestStub.novo().build();
            var document = VacinaDocumentStub.novo().build();
            when(repository.findByNome(any())).thenReturn(Optional.of(document));

            // ACTION & ASSERTIONS
            assertThrows(VacinaCadastradaException.class, () -> adapter.criarVacina(request));
            verify(mapper).paraVacinaDocument(request);

        }

    }

    @Nested
    @DisplayName("Porta de buscar vacina por nome")
    class BuscarVacinaPorNome {

        @Test
        @DisplayName("Deve executar porta de buscar vacina por nome com sucesso")
        void test01() {
            // ASSETS
            var request = BuscarVacinaPorNomePortRequestStub.novo().build();
            var document = VacinaDocumentStub.novo().build();
            when(repository.findByNome(any())).thenReturn(Optional.of(document));

            // ACTION
            var result = adapter.buscarVacinaPorNome(request);

            // ASSERTIONS
            assertThat(result).usingRecursiveComparison().isEqualTo(document);
            verify(mapper).paraVacinaPortResponse(document);

        }

        @Test
        @DisplayName("Deve executar porta de criar vacina com erro, quando vacina ja existir")
        void test02() {
            // ASSETS
            var request = BuscarVacinaPorNomePortRequestStub.novo().build();
            when(repository.findByNome(any())).thenReturn(Optional.empty());

            // ACTION & ASSERTIONS
            assertThrows(VacinaNaoEncontradaException.class, () -> adapter.buscarVacinaPorNome(request));

        }

    }

    @Nested
    @DisplayName("Porta de listar todos vacinas")
    class ListarTodosVacinas {

        @Test
        @DisplayName("Deve executar porta de listar todos vacinas com sucesso")
        void test01() {
            // ASSETS
            var document = List.of(VacinaDocumentStub.novo().build());
            when(repository.findAll()).thenReturn(document);

            // ACTION
            var result = adapter.listarTodosVacinas();

            // ASSERTIONS
            assertThat(result).usingRecursiveComparison().isEqualTo(document);
            verify(mapper, times(document.size())).paraVacinaPortResponse(any());

        }

    }

    @Nested
    @DisplayName("Porta de atualizar vacina")
    class AtualizarVacina {

        @Test
        @DisplayName("Deve executar porta de atualizar vacina com sucesso")
        void test01() {
            // ASSETS
            var request = AtualizarVacinaPortRequestStub.novo().build();
            var document = VacinaDocument.builder()
                    .nome(request.nome())
                    .fabricante(request.fabricante())
                    .doseAplicada(request.doseAplicada())
                    .doses(request.doses())
                    .intervaloEntreDosesEmDias(request.intervaloEntreDosesEmDias())
                    .descricao(request.descricao())
                    .grupo(request.grupo())
                    .build();
            when(mongoTemplate.findAndModify(
                    any(Query.class),
                    any(Update.class),
                    any(FindAndModifyOptions.class),
                    eq(VacinaDocument.class)
            )).thenReturn(document);

            // ACTION
            var result = adapter.atualizarVacina(request);

            // ASSERTIONS
            assertThat(result).usingRecursiveComparison().isEqualTo(document);
            verify(mapper).paraVacinaDocument(request);
            verify(mapper).paraVacinaPortResponse(document);
            verify(mapper).paraUpdate(document);

        }

        @Test
        @DisplayName("Deve executar porta de atualizar vacina com erro, quando vacina não for encontrado")
        void test02() {
            // ASSETS
            var request = AtualizarVacinaPortRequestStub.novo().build();
            when(mongoTemplate.findAndModify(
                    any(Query.class),
                    any(Update.class),
                    any(FindAndModifyOptions.class),
                    eq(VacinaDocument.class)
            )).thenReturn(null);

            // ACTION & ASSERTIONS
            assertThrows(VacinaNaoEncontradaException.class, () -> adapter.atualizarVacina(request));

        }

    }

    @Nested
    @DisplayName("Porta de remover vacina")
    class RemoverVacina {

        @Test
        @DisplayName("Deve executar porta de remover vacina com sucesso")
        void test01() {
            // ASSETS
            var request = RemoverVacinaPortRequestStub.novo().build();
            var nome = request.nome();

            when(mongoTemplate.remove(
                    any(),
                    eq(VacinaDocument.class)
            )).thenReturn(DeleteResult.acknowledged(1L));

            // ACTION
            Assertions.assertDoesNotThrow(() -> adapter.removerVacina(request));

            // ASSERTIONS
            verify(mongoTemplate).remove(
                    Query.query(Criteria.where("nome").is(nome)),
                    VacinaDocument.class
            );

        }

        @Test
        @DisplayName("Deve executar porta de remover vacina com erro, quando vacina não for encontrado")
        void test02() {
            // ASSETS
            var request = RemoverVacinaPortRequestStub.novo().build();
            when(mongoTemplate.remove(
                    any(Query.class),
                    any(Class.class)
            )).thenReturn(DeleteResult.acknowledged(0L));

            // ACTION & ASSERTIONS
            assertThrows(VacinaNaoEncontradaException.class,
                    () -> adapter.removerVacina(request));

        }

    }

}