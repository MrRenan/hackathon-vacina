package br.com.fiap.features.vacina.adapter.out.client.mapper;

import br.com.fiap.features.vacina.application.port.request.AtualizarVacinaPortRequest;
import br.com.fiap.features.vacina.application.port.request.CriarVacinaPortRequest;
import br.com.fiap.features.vacina.application.port.response.VacinaPortResponse;
import br.com.fiap.infra.mongodb.vacina.document.VacinaDocument;
import org.mapstruct.AnnotateWith;
import org.mapstruct.AnnotateWith.Element;
import org.mapstruct.Mapper;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@AnnotateWith(value = Component.class, elements = @Element(strings = "vacinaOutMapper"))
@Mapper(componentModel = "spring")
public interface VacinaOutMapper {

    VacinaDocument paraVacinaDocument(CriarVacinaPortRequest request);

    VacinaDocument paraVacinaDocument(AtualizarVacinaPortRequest request);

    VacinaPortResponse paraVacinaPortResponse(VacinaDocument document);

    default Update paraUpdate(VacinaDocument document) {
        return new Update()
                .set("nome", document.nome())
                .set("fabricante", document.fabricante())
                .set("doseAplicada", document.doseAplicada())
                .set("doses", document.doses())
                .set("intervaloEntreDosesEmDias", document.intervaloEntreDosesEmDias())
                .set("descricao", document.descricao())
                .set("grupo", document.grupo());
    }

}
