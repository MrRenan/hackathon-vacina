package br.com.fiap.features.vacina.application.port;

import br.com.fiap.features.vacina.application.port.request.AtualizarVacinaPortRequest;
import br.com.fiap.features.vacina.application.port.request.BuscarVacinaPorNomePortRequest;
import br.com.fiap.features.vacina.application.port.request.CriarVacinaPortRequest;
import br.com.fiap.features.vacina.application.port.request.RemoverVacinaPortRequest;
import br.com.fiap.features.vacina.application.port.response.VacinaPortResponse;

import java.util.List;

public interface VacinaPort {

    VacinaPortResponse criarVacina(CriarVacinaPortRequest request);

    VacinaPortResponse buscarVacinaPorNome(BuscarVacinaPorNomePortRequest portRequest);

    List<VacinaPortResponse> listarTodosVacinas();

    VacinaPortResponse atualizarVacina(AtualizarVacinaPortRequest portRequest);

    void removerVacina(RemoverVacinaPortRequest portRequest);
}
