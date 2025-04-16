package br.com.fiap.features.vacina.adapter.in.rest.v1.api;

import br.com.fiap.features.vacina.adapter.in.rest.v1.Api;
import br.com.fiap.features.vacina.adapter.in.rest.v1.request.AtualizarVacinaRequest;
import br.com.fiap.features.vacina.adapter.in.rest.v1.request.CriarVacinaRequest;
import br.com.fiap.features.vacina.adapter.in.rest.v1.response.VacinaResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Validated
@RequestMapping(path = "/vacina/v1")
public interface VacinaApi extends Api {

    @Operation(summary = "Criar uma nova vacina")
    @PostMapping
    @ResponseStatus(CREATED)
    VacinaResponse criarVacina(@RequestBody CriarVacinaRequest request);

    @Operation(summary = "Buscar vacina por nome")
    @GetMapping(path = "/{nome}")
    @ResponseStatus(OK)
    VacinaResponse buscarVacinaPorNome(@PathVariable("nome") String nome);

    @Operation(summary = "Listar todas as vacinas")
    @GetMapping
    @ResponseStatus(OK)
    List<VacinaResponse> listarTodosVacinas();

    @Operation(summary = "Atualizar dados da vacina")
    @PutMapping(path = "/{nome}")
    @ResponseStatus(OK)
    VacinaResponse atualizarVacina(@PathVariable("nome") String nome,
                                     @RequestBody AtualizarVacinaRequest request);

    @Operation(summary = "Remover vacina")
    @DeleteMapping(path = "/{nome}")
    @ResponseStatus(NO_CONTENT)
    void removerVacina(@PathVariable("nome") String nome);
}