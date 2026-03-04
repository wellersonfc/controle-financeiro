package br.com.wellerson.financecontrol.controller;

import br.com.wellerson.financecontrol.dto.RemuneracaoRequest;
import br.com.wellerson.financecontrol.dto.RemuneracaoResponse;
import br.com.wellerson.financecontrol.dto.RemuneracaoResumoResponse;
import br.com.wellerson.financecontrol.service.RemuneracaoService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/remuneracoes")
public class RemuneracaoController {

    private final RemuneracaoService remuneracaoService;

    public RemuneracaoController(RemuneracaoService remuneracaoService) {
        this.remuneracaoService = remuneracaoService;
    }

    @PostMapping
    public ResponseEntity<RemuneracaoResponse> criar(@RequestBody @Valid RemuneracaoRequest request) {
        RemuneracaoResponse response = remuneracaoService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RemuneracaoResponse> buscarPorId(@PathVariable Long id) {
        RemuneracaoResponse response = remuneracaoService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        remuneracaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RemuneracaoResponse> atualizar(@PathVariable Long id, @RequestBody @Valid RemuneracaoRequest request) {
        RemuneracaoResponse response = remuneracaoService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuarios/{usuarioId}")
    public ResponseEntity<List<RemuneracaoResponse>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(remuneracaoService.listarPorUsuarioId(usuarioId));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<RemuneracaoResumoResponse> buscar(
            @PathVariable Long usuarioId,
            @RequestParam(required = false) Integer mes,
            @RequestParam(required = false) Integer ano
    ) {
        return ResponseEntity.ok(remuneracaoService.buscar(usuarioId, mes, ano));
    }

}