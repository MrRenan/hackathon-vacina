package br.com.fiap.features.vacina.adapter.in.rest.v1.controller;

import br.com.fiap.features.vacina.adapter.in.rest.v1.mapper.VacinaInRestMapper;
import br.com.fiap.features.vacina.adapter.in.rest.v1.request.AtualizarVacinaRequestStub;
import br.com.fiap.features.vacina.adapter.in.rest.v1.request.CriarVacinaRequestStub;
import br.com.fiap.features.vacina.adapter.in.rest.v1.response.VacinaResponse;
import br.com.fiap.features.vacina.application.usecase.VacinaUseCase;
import br.com.fiap.features.vacina.application.usecase.response.VacinaUseCaseResponseStub;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@DisplayName("Cliente / Rest / V1 / Vacina Controller")
class VacinaControllerTest {

    private static final String BASE_URL = "/vacina/v1";

    Faker faker = new Faker();

    @InjectMocks
    private VacinaController controller;
    @Spy
    private VacinaInRestMapper mapper = Mappers.getMapper(VacinaInRestMapper.class);
    @Mock
    private VacinaUseCase useCase;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .build();
        objectMapper = new ObjectMapper();
    }

    @Nested
    @DisplayName("API de criar vacina")
    class CriarVacina {

        @Test
        @DisplayName("Deve executar API V1 de criar vacina com sucesso")
        void test01() throws Exception {
            // ASSETS
            var request = CriarVacinaRequestStub.novo().build();
            var useCaseResponse = VacinaUseCaseResponseStub.novo().build();
            given(useCase.executarCriarVacina(any())).willReturn(useCaseResponse);
            // ACTION
            var result = mockMvc.perform(post(BASE_URL)
                    .content(objectMapper.writeValueAsBytes(request))
                    .contentType(APPLICATION_JSON)
                    .accept(APPLICATION_JSON));
            // ASSERTIONS
            result.andExpect(status().isCreated());
            var json = result.andReturn().getResponse().getContentAsString();
            var response = objectMapper.readValue(json, VacinaResponse.class);
            assertThat(response).usingRecursiveComparison().isEqualTo(useCaseResponse);
            verify(mapper).paraCriarVacinaUseCaseRequest(request);
            verify(mapper).paraVacinaResponse(useCaseResponse);
        }

    }

    @Nested
    @DisplayName("API de buscar vacina por nome")
    class BuscarVacinaPorNome {

        @Test
        @DisplayName("Deve executar API V1 de buscar vacina por nome com sucesso")
        void test01() throws Exception {
            // ASSETS
            var nome = faker.lorem().word();
            var useCaseResponse = VacinaUseCaseResponseStub.novo().build();
            given(useCase.executarBuscarVacinaPorNome(any())).willReturn(useCaseResponse);
            // ACTION
            var result = mockMvc.perform(get(BASE_URL.concat(String.format("/%s", nome)))
                    .contentType(APPLICATION_JSON)
                    .accept(APPLICATION_JSON));
            // ASSERTIONS
            result.andExpect(status().isOk());
            var json = result.andReturn().getResponse().getContentAsString();
            var response = objectMapper.readValue(json, VacinaResponse.class);
            assertThat(response).usingRecursiveComparison().isEqualTo(useCaseResponse);
            verify(mapper).paraBuscarVacinaPorNomeUseCaseRequest(nome);
            verify(mapper).paraVacinaResponse(useCaseResponse);
        }

    }

    @Nested
    @DisplayName("API de buscar todos os vacinas")
    class BuscarTodosVacinas {

        @Test
        @DisplayName("Deve executar API V1 de buscar todos os vacinas com sucesso")
        void test01() throws Exception {
            // ASSETS
            var useCaseResponse = List.of(VacinaUseCaseResponseStub.novo().build());
            given(useCase.executarListarTodosVacinas()).willReturn(useCaseResponse);
            // ACTION
            var result = mockMvc.perform(get(BASE_URL)
                    .contentType(APPLICATION_JSON)
                    .accept(APPLICATION_JSON));
            // ASSERTIONS
            result.andExpect(status().isOk());
            var json = result.andReturn().getResponse().getContentAsString();
            var response = objectMapper.readValue(json, new TypeReference<List<VacinaResponse>>() {
            });
            assertThat(response).usingRecursiveComparison().isEqualTo(useCaseResponse);
            verify(mapper, Mockito.times(useCaseResponse.size())).paraVacinaResponse(any());
        }

    }

    @Nested
    @DisplayName("API de atualizar dados do vacina")
    class AtualizarVacina {

        @Test
        @DisplayName("Deve executar API V1 de atualizar dados do vacina com sucesso")
        void test01() throws Exception {
            // ASSETS
            var request = AtualizarVacinaRequestStub.novo().build();
            var useCaseResponse = VacinaUseCaseResponseStub.novo().build();
            given(useCase.executarAtualizarVacina(any())).willReturn(useCaseResponse);
            // ACTION
            var result = mockMvc.perform(put(BASE_URL.concat(String.format("/%s", request.nome())))
                    .content(objectMapper.writeValueAsBytes(request))
                    .contentType(APPLICATION_JSON)
                    .accept(APPLICATION_JSON));
            // ASSERTIONS
            result.andExpect(status().isOk());
            var json = result.andReturn().getResponse().getContentAsString();
            var response = objectMapper.readValue(json, VacinaResponse.class);
            assertThat(response).usingRecursiveComparison().isEqualTo(useCaseResponse);
            verify(mapper).paraAtualizarVacinaUseCaseRequest(request);
            verify(mapper).paraVacinaResponse(useCaseResponse);
        }

    }

    @Nested
    @DisplayName("API de remover vacina")
    class RemoverVacina {

        @Test
        @DisplayName("Deve executar API V1 de remover vacina com sucesso")
        void test01() throws Exception {
            // ASSETS
            doNothing().when(useCase).executarRemoverVacina(any());
            var nome = faker.lorem().word();
            // ACTION
            var result = mockMvc.perform(delete(BASE_URL.concat(String.format("/%s", nome)))
                    .contentType(APPLICATION_JSON)
                    .accept(APPLICATION_JSON));
            // ASSERTIONS
            result.andExpect(status().isNoContent());
            verify(mapper).paraRemoverVacinaUseCaseRequest(nome);
        }

    }

}