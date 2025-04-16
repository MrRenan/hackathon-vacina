package br.com.fiap.features.vacina.adapter.in.rest.v1.controller;

import br.com.fiap.features.vacina.adapter.in.rest.v1.api.VacinaApi;
import br.com.fiap.features.vacina.adapter.in.rest.v1.mapper.VacinaInRestMapper;
import br.com.fiap.features.vacina.adapter.in.rest.v1.request.AtualizarVacinaRequest;
import br.com.fiap.features.vacina.adapter.in.rest.v1.request.CriarVacinaRequest;
import br.com.fiap.features.vacina.adapter.in.rest.v1.response.VacinaResponse;
import br.com.fiap.features.vacina.application.usecase.VacinaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class VacinaController implements VacinaApi {

    private final VacinaInRestMapper mapper;
    private final VacinaUseCase useCase;

    @Override
    public VacinaResponse criarVacina(CriarVacinaRequest request) {
        var useCaseRequest = mapper.paraCriarVacinaUseCaseRequest(request);
        var useCaseResponse = useCase.executarCriarVacina(useCaseRequest);
        return mapper.paraVacinaResponse(useCaseResponse);
    }

    @Override
    public VacinaResponse buscarVacinaPorNome(String nome) {
        var useCaseRequest = mapper.paraBuscarVacinaPorNomeUseCaseRequest(nome);
        var useCaseResponse = useCase.executarBuscarVacinaPorNome(useCaseRequest);
        return mapper.paraVacinaResponse(useCaseResponse);
    }

    @Override
    public List<VacinaResponse> listarTodosVacinas() {
        var useCaseResponse = useCase.executarListarTodosVacinas();
        return useCaseResponse.stream().map(mapper::paraVacinaResponse).toList();
    }

    @Override
    public VacinaResponse atualizarVacina(String nome, AtualizarVacinaRequest request) {
        var useCaseRequest = mapper.paraAtualizarVacinaUseCaseRequest(request);
        var useCaseResponse = useCase.executarAtualizarVacina(useCaseRequest);
        return mapper.paraVacinaResponse(useCaseResponse);
    }

    @Override
    public void removerVacina(String nome) {
        var useCaseRequest = mapper.paraRemoverVacinaUseCaseRequest(nome);
        useCase.executarRemoverVacina(useCaseRequest);
    }

}