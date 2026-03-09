package br.com.wellerson.financecontrol.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.wellerson.financecontrol.dto.CategoriaDespesaRequest;
import br.com.wellerson.financecontrol.dto.CategoriaDespesaResponse;
import br.com.wellerson.financecontrol.service.CategoriaDespesaService;

@RestController
@RequestMapping("/api/categorias-despesa")
public class CategoriaDespesaController {

    private final CategoriaDespesaService categoriaDespesaService;

    public CategoriaDespesaController(CategoriaDespesaService categoriaDespesaService) {
        this.categoriaDespesaService = categoriaDespesaService;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<CategoriaDespesaResponse> cadastrarCategoriaDespesa(@RequestBody CategoriaDespesaRequest request) {
        CategoriaDespesaResponse response = categoriaDespesaService.criarCategoriaDespesa(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDespesaResponse> atualizarCategoriaDespesa(
            @PathVariable Long id,
            @RequestBody CategoriaDespesaRequest request) {
        CategoriaDespesaResponse response = categoriaDespesaService.atualizarCategoriaDespesa(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCategoriaDespesa(@PathVariable Long id) {
        categoriaDespesaService.excluirCategoriaDespesa(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDespesaResponse> obterCategoriaDespesaPorId(@PathVariable Long id) {
        CategoriaDespesaResponse response = categoriaDespesaService.obterCategoriaDespesaPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<java.util.List<CategoriaDespesaResponse>> listarCategoriasDespesas() {
        java.util.List<CategoriaDespesaResponse> response = categoriaDespesaService.listarCategoriasDespesas();
        return ResponseEntity.ok(response);
    }
}