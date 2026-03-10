package br.com.wellerson.financecontrol.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import br.com.wellerson.financecontrol.dto.CadastroDetalheDespesaViaPdfResponse;
import br.com.wellerson.financecontrol.dto.DetalheDespesaRequest;
import br.com.wellerson.financecontrol.dto.DetalheDespesaResponse;
import br.com.wellerson.financecontrol.service.DetalheDespesaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/detalhes-despesa")
public class DetalheDespesaController {

    private final DetalheDespesaService detalheDespesaService;

    public DetalheDespesaController(DetalheDespesaService detalheDespesaService) {
        this.detalheDespesaService = detalheDespesaService;
    }

    @PostMapping
    public ResponseEntity<DetalheDespesaResponse> criar(@Valid @RequestBody DetalheDespesaRequest request) {
        DetalheDespesaResponse response = detalheDespesaService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/despesa/{despesaId}")
    public ResponseEntity<List<DetalheDespesaResponse>> listarPorDespesa(@PathVariable Long despesaId) {
        List<DetalheDespesaResponse> response = detalheDespesaService.listarPorDespesa(despesaId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalheDespesaResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody DetalheDespesaRequest request) {
        DetalheDespesaResponse response = detalheDespesaService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id) {
        detalheDespesaService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/despesa/{despesaId}")
    public ResponseEntity<Void> deletarPorDespesa(@PathVariable Long despesaId) {
        detalheDespesaService.deletarPorDespesa(despesaId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/cadastro-via-pdf-ia", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CadastroDetalheDespesaViaPdfResponse> cadastrarViaPdfIa(
            @RequestParam("despesaId") Long despesaId,
            @RequestParam("arquivo") MultipartFile arquivo
    ) {
        CadastroDetalheDespesaViaPdfResponse response =
                detalheDespesaIaService.cadastrarViaPdfIa(despesaId, arquivo);

        return ResponseEntity.ok(response);
    }
}