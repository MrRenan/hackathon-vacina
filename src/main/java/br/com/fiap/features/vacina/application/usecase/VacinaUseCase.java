package br.com.fiap.features.vacina.application.usecase;

import br.com.fiap.features.vacina.application.mapper.VacinaApplicationMapper;
import br.com.fiap.features.vacina.application.port.VacinaPort;
import br.com.fiap.features.vacina.application.usecase.request.AtualizarVacinaUseCaseRequest;
import br.com.fiap.features.vacina.application.usecase.request.BuscarVacinaPorNomeUseCaseRequest;
import br.com.fiap.features.vacina.application.usecase.request.CriarVacinaUseCaseRequest;
import br.com.fiap.features.vacina.application.usecase.request.RemoverVacinaUseCaseRequest;
import br.com.fiap.features.vacina.application.usecase.response.VacinaUseCaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component("vacinaUseCase")
public class VacinaUseCase {

    private final VacinaApplicationMapper mapper;
    private final VacinaPort port;

    public VacinaUseCaseResponse executarCriarVacina(CriarVacinaUseCaseRequest useCaseRequest) {
        var portRequest = mapper.paraCriarVacinaPortRequest(useCaseRequest);
        var portResponse = port.criarVacina(portRequest);
        return mapper.paraVacinaUseCaseResponse(portResponse);
    }

    public VacinaUseCaseResponse executarBuscarVacinaPorNome(BuscarVacinaPorNomeUseCaseRequest useCaseRequest) {
        var portRequest = mapper.paraBuscarVacinaPorNomePortRequest(useCaseRequest);
        var portResponse = port.buscarVacinaPorNome(portRequest);
        return mapper.paraVacinaUseCaseResponse(portResponse);
    }

    public List<VacinaUseCaseResponse> executarListarTodosVacinas() {
        var portResponse = port.listarTodosVacinas();
        return portResponse.stream().map(mapper::paraVacinaUseCaseResponse).toList();
    }

    public VacinaUseCaseResponse executarAtualizarVacina(AtualizarVacinaUseCaseRequest useCaseRequest) {
        var portRequest = mapper.paraAtualizarVacinaPortRequest(useCaseRequest);
        var portResponse = port.atualizarVacina(portRequest);
        return mapper.paraVacinaUseCaseResponse(portResponse);
    }

    public void executarRemoverVacina(RemoverVacinaUseCaseRequest useCaseRequest) {
        var portRequest = mapper.paraRemoverVacinaPortRequest(useCaseRequest);
        port.removerVacina(portRequest);
    }
}