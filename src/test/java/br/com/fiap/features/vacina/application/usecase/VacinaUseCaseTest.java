package br.com.fiap.features.vacina.application.usecase;

import br.com.fiap.features.vacina.application.mapper.VacinaApplicationMapper;
import br.com.fiap.features.vacina.application.port.VacinaPort;
import br.com.fiap.features.vacina.application.port.response.VacinaPortResponseStub;
import br.com.fiap.features.vacina.application.usecase.request.AtualizarVacinaUseCaseRequestStub;
import br.com.fiap.features.vacina.application.usecase.request.BuscarVacinaPorNomeUseCaseRequestStub;
import br.com.fiap.features.vacina.application.usecase.request.CriarVacinaUseCaseRequestStub;
import br.com.fiap.features.vacina.application.usecase.request.RemoverVacinaUseCaseRequestStub;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mapstruct.factory.Mappers.getMapper;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Vacina / Vacina UseCase")
class VacinaUseCaseTest {

    @InjectMocks
    private VacinaUseCase useCase;
    @Mock
    private VacinaPort port;
    @Spy
    private VacinaApplicationMapper mapper = getMapper(VacinaApplicationMapper.class);

    @Nested
    @DisplayName("Caso de uso de criar vacina")
    class ExecutarCriarVacina {

        @Test
        @DisplayName("Deve executar caso de uso de criar vacina com sucesso")
        void test01() {
            // ASSETS
            var useCaseRequest = CriarVacinaUseCaseRequestStub.novo().build();
            var portResponse = VacinaPortResponseStub.novo().build();
            when(port.criarVacina(any())).thenReturn(portResponse);
            // ACTION
            var result = useCase.executarCriarVacina(useCaseRequest);
            // ASSERTIONS
            assertThat(result).usingRecursiveComparison().isEqualTo(portResponse);
            verify(mapper).paraVacinaUseCaseResponse(portResponse);
            verify(mapper).paraCriarVacinaPortRequest(useCaseRequest);
        }

    }

    @Nested
    @DisplayName("Caso de uso de buscar vacina por nome")
    class ExecutarBuscarVacinaPorNomeUseCase {

        @Test
        @DisplayName("Deve executar caso de uso de buscar vacina por nome com sucesso")
        void test01() {
            // ASSETS
            var useCaseRequest = BuscarVacinaPorNomeUseCaseRequestStub.novo().build();
            var portResponse = VacinaPortResponseStub.novo().build();
            when(port.buscarVacinaPorNome(any())).thenReturn(portResponse);
            // ACTION
            var result = useCase.executarBuscarVacinaPorNome(useCaseRequest);
            // ASSERTIONS
            assertThat(result).usingRecursiveComparison().isEqualTo(portResponse);
            verify(mapper).paraVacinaUseCaseResponse(portResponse);
            verify(mapper).paraBuscarVacinaPorNomePortRequest(useCaseRequest);
        }

    }

    @Nested
    @DisplayName("Caso de uso de listar todos os vacinas")
    class ExecutarListarTodosVacinasUseCase {

        @Test
        @DisplayName("Deve executar caso de uso de listar todos os vacinas com sucesso")
        void test01() {
            // ASSETS
            var portResponse = List.of(VacinaPortResponseStub.novo().build());
            when(port.listarTodosVacinas()).thenReturn(portResponse);
            // ACTION
            var result = useCase.executarListarTodosVacinas();
            // ASSERTIONS
            assertThat(result).usingRecursiveComparison().isEqualTo(portResponse);
            verify(mapper, times(portResponse.size())).paraVacinaUseCaseResponse(any());
        }

    }

    @Nested
    @DisplayName("Caso de uso de atualizar vacina")
    class ExecutarAtualizarVacina {

        @Test
        @DisplayName("Deve executar caso de uso de atualizar vacina com sucesso")
        void test01() {
            // ASSETS
            var useCaseRequest = AtualizarVacinaUseCaseRequestStub.novo().build();
            var portResponse = VacinaPortResponseStub.novo().build();
            when(port.atualizarVacina(any())).thenReturn(portResponse);
            // ACTION
            var result = useCase.executarAtualizarVacina(useCaseRequest);
            // ASSERTIONS
            assertThat(result).usingRecursiveComparison().isEqualTo(portResponse);
            verify(mapper).paraVacinaUseCaseResponse(portResponse);
            verify(mapper).paraAtualizarVacinaPortRequest(useCaseRequest);
        }

    }

    @Nested
    @DisplayName("Caso de uso de remover vacina")
    class ExecutarRemoverVacina {

        @Test
        @DisplayName("Deve executar caso de uso de remover vacina com sucesso")
        void test01() {
            // ASSETS
            var useCaseRequest = RemoverVacinaUseCaseRequestStub.novo().build();
            doNothing().when(port).removerVacina(any());
            // ACTION
            useCase.executarRemoverVacina(useCaseRequest);
            // ASSERTIONS
            verify(mapper).paraRemoverVacinaPortRequest(useCaseRequest);
        }

    }

}