package br.com.fiap.features.vacina.adapter.in.rest.v1.mapper;

import br.com.fiap.features.vacina.adapter.in.rest.v1.request.AtualizarVacinaRequest;
import br.com.fiap.features.vacina.adapter.in.rest.v1.request.CriarVacinaRequest;
import br.com.fiap.features.vacina.adapter.in.rest.v1.response.VacinaResponse;
import br.com.fiap.features.vacina.application.usecase.request.AtualizarVacinaUseCaseRequest;
import br.com.fiap.features.vacina.application.usecase.request.BuscarVacinaPorNomeUseCaseRequest;
import br.com.fiap.features.vacina.application.usecase.request.CriarVacinaUseCaseRequest;
import br.com.fiap.features.vacina.application.usecase.request.RemoverVacinaUseCaseRequest;
import br.com.fiap.features.vacina.application.usecase.response.VacinaUseCaseResponse;
import org.mapstruct.AnnotateWith;
import org.mapstruct.AnnotateWith.Element;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@AnnotateWith(value = Component.class, elements = @Element(strings = "vacinaInRestMapper"))
@Mapper(componentModel = "spring")
public interface VacinaInRestMapper {

    CriarVacinaUseCaseRequest paraCriarVacinaUseCaseRequest(CriarVacinaRequest request);

    VacinaResponse paraVacinaResponse(VacinaUseCaseResponse useCaseResponse);

    BuscarVacinaPorNomeUseCaseRequest paraBuscarVacinaPorNomeUseCaseRequest(String nome);

    AtualizarVacinaUseCaseRequest paraAtualizarVacinaUseCaseRequest(AtualizarVacinaRequest request);

    RemoverVacinaUseCaseRequest paraRemoverVacinaUseCaseRequest(String nome);
}