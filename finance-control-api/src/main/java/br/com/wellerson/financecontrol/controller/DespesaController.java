package br.com.wellerson.financecontrol.controller;

import br.com.wellerson.financecontrol.dto.DespesaRequest;
import br.com.wellerson.financecontrol.dto.DespesaResponse;
import br.com.wellerson.financecontrol.dto.DespesaResumoResponse;
import br.com.wellerson.financecontrol.service.DespesaService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/despesas")
public class DespesaController {

    private final DespesaService despesaService;

    public DespesaController(DespesaService despesaService) {
        this.despesaService = despesaService;
    }

    // CREATE
    @PostMapping("/usuario/{usuarioId}/categoria/{despesaCategoriaId}")
    public ResponseEntity<DespesaResponse> criar( 
        @PathVariable Long usuarioId,
        @PathVariable Long despesaCategoriaId,
        @RequestBody @Valid DespesaRequest request
    ) {
        DespesaResponse response = despesaService.criarDespesa(usuarioId, despesaCategoriaId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

        @PutMapping("/{id}")
    public ResponseEntity<DespesaResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid DespesaRequest request
    ) {
        return ResponseEntity.ok(despesaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        despesaService.deletar(id);
        return ResponseEntity.noContent().build();
    }


    // LIST + RESUMO (dinâmico: sem filtro / ano / mês / mês+ano)
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<DespesaResumoResponse> buscar(
            @PathVariable Long usuarioId,
            @RequestParam(required = false) Integer mes,
            @RequestParam(required = false) Integer ano
    ) {
        DespesaResumoResponse response = despesaService.buscar(usuarioId, mes, ano);
        return ResponseEntity.ok(response);
    }
}