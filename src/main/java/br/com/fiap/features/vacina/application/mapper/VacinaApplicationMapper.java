package br.com.fiap.features.vacina.application.mapper;

import br.com.fiap.features.vacina.application.port.request.AtualizarVacinaPortRequest;
import br.com.fiap.features.vacina.application.port.request.BuscarVacinaPorNomePortRequest;
import br.com.fiap.features.vacina.application.port.request.CriarVacinaPortRequest;
import br.com.fiap.features.vacina.application.port.request.RemoverVacinaPortRequest;
import br.com.fiap.features.vacina.application.port.response.VacinaPortResponse;
import br.com.fiap.features.vacina.application.usecase.request.AtualizarVacinaUseCaseRequest;
import br.com.fiap.features.vacina.application.usecase.request.BuscarVacinaPorNomeUseCaseRequest;
import br.com.fiap.features.vacina.application.usecase.request.CriarVacinaUseCaseRequest;
import br.com.fiap.features.vacina.application.usecase.request.RemoverVacinaUseCaseRequest;
import br.com.fiap.features.vacina.application.usecase.response.VacinaUseCaseResponse;
import org.mapstruct.AnnotateWith;
import org.mapstruct.AnnotateWith.Element;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@AnnotateWith(value = Component.class, elements = @Element(strings = "vacinaApplicationMapper"))
@Mapper(componentModel = "spring")
public interface VacinaApplicationMapper {

    CriarVacinaPortRequest paraCriarVacinaPortRequest(CriarVacinaUseCaseRequest request);

    VacinaUseCaseResponse paraVacinaUseCaseResponse(VacinaPortResponse response);

    BuscarVacinaPorNomePortRequest paraBuscarVacinaPorNomePortRequest(BuscarVacinaPorNomeUseCaseRequest useCaseRequest);

    AtualizarVacinaPortRequest paraAtualizarVacinaPortRequest(AtualizarVacinaUseCaseRequest useCaseRequest);

    RemoverVacinaPortRequest paraRemoverVacinaPortRequest(RemoverVacinaUseCaseRequest useCaseRequest);
}
