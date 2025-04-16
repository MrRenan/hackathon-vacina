package br.com.fiap.features.vacina.adapter.out.client;

import br.com.fiap.features.vacina.adapter.out.client.mapper.VacinaOutMapper;
import br.com.fiap.features.vacina.application.port.VacinaPort;
import br.com.fiap.features.vacina.application.port.request.AtualizarVacinaPortRequest;
import br.com.fiap.features.vacina.application.port.request.BuscarVacinaPorNomePortRequest;
import br.com.fiap.features.vacina.application.port.request.CriarVacinaPortRequest;
import br.com.fiap.features.vacina.application.port.request.RemoverVacinaPortRequest;
import br.com.fiap.features.vacina.application.port.response.VacinaPortResponse;
import br.com.fiap.features.vacina.domain.exception.VacinaNaoEncontradaException;
import br.com.fiap.features.vacina.domain.exception.VacinaCadastradaException;
import br.com.fiap.infra.mongodb.vacina.document.VacinaDocument;
import br.com.fiap.infra.mongodb.vacina.repository.VacinaMongoDBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@RequiredArgsConstructor
@Component("vacinaAdapter")
public class VacinaAdapter implements VacinaPort {

    private final VacinaOutMapper mapper;
    private final VacinaMongoDBRepository repository;
    private final MongoTemplate mongoTemplate;

    @Override
    public VacinaPortResponse criarVacina(CriarVacinaPortRequest request) {

        var vacinaDocument = mapper.paraVacinaDocument(request);
        var buscarVacinaPorNome = buscarPorNome(request.nome());
        if (buscarVacinaPorNome.isPresent()) {
            throw new VacinaCadastradaException();
        } else {
            var vacinaCriada = criarVacina(vacinaDocument);
            return mapper.paraVacinaPortResponse(vacinaCriada);
        }

    }

    @Override
    public VacinaPortResponse buscarVacinaPorNome(BuscarVacinaPorNomePortRequest portRequest) {

        var vacinaDocument = buscarPorNome(portRequest.nome());
        if (vacinaDocument.isPresent()) {
            return mapper.paraVacinaPortResponse(vacinaDocument.get());
        } else {
            throw new VacinaNaoEncontradaException();
        }

    }

    @Override
    public List<VacinaPortResponse> listarTodosVacinas() {

        var todosVacinasDocument = listarTodos();
        return todosVacinasDocument.stream().map(mapper::paraVacinaPortResponse).toList();

    }

    @Override
    public VacinaPortResponse atualizarVacina(AtualizarVacinaPortRequest portRequest) {
        var vacinaAtualizado = atualizarVacina(mapper.paraVacinaDocument(portRequest));
        return mapper.paraVacinaPortResponse(vacinaAtualizado);
    }

    @Override
    public void removerVacina(RemoverVacinaPortRequest portRequest) {
        var contagemVacinaRemovido = mongoTemplate.remove(
                        query(where("nome").is(portRequest.nome())),
                        VacinaDocument.class)
                .getDeletedCount();

        if (contagemVacinaRemovido == 0) {
            throw new VacinaNaoEncontradaException();
        }
    }

    private VacinaDocument atualizarVacina(VacinaDocument document) {

        var update = mapper.paraUpdate(document);

        var vacinaAtualizada = mongoTemplate.findAndModify(
                query(where("nome").is(document.nome())),
                update,
                FindAndModifyOptions.options().returnNew(true),
                VacinaDocument.class
        );

        if (vacinaAtualizada == null) {
            throw new VacinaNaoEncontradaException();
        }

        return vacinaAtualizada;

    }

    private VacinaDocument criarVacina(VacinaDocument document) {
        return repository.save(document);
    }

    private Optional<VacinaDocument> buscarPorNome(String nome) {
        return repository.findByNome(nome);
    }

    private List<VacinaDocument> listarTodos() {
        return repository.findAll();
    }

}