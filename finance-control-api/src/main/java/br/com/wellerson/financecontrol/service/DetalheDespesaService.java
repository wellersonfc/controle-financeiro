package br.com.wellerson.financecontrol.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.wellerson.financecontrol.dto.DetalheDespesaRequest;
import br.com.wellerson.financecontrol.dto.DetalheDespesaResponse;
import br.com.wellerson.financecontrol.entity.CategoriaDespesa;
import br.com.wellerson.financecontrol.entity.Despesa;
import br.com.wellerson.financecontrol.entity.DetalheDespesa;
import br.com.wellerson.financecontrol.repository.CategoriaDespesaRepository;
import br.com.wellerson.financecontrol.repository.DespesasRepository;
import br.com.wellerson.financecontrol.repository.DetalheDespesaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class DetalheDespesaService {

    private final DetalheDespesaRepository detalheDespesaRepository;
    private final DespesasRepository despesaRepository;
   private final CategoriaDespesaRepository categoriaDespesaRepository;

    public DetalheDespesaService(DetalheDespesaRepository detalheDespesaRepository, DespesasRepository despesaRepository, CategoriaDespesaRepository categoriaDespesaRepository) {
        this.detalheDespesaRepository = detalheDespesaRepository;
        this.despesaRepository = despesaRepository;
        this.categoriaDespesaRepository = categoriaDespesaRepository;
    }

    @Transactional
    public DetalheDespesaResponse criar(DetalheDespesaRequest request) {
        DetalheDespesa detalhe = new DetalheDespesa();
        detalhe.setDespesaId(request.despesaId());
        detalhe.setCategoriaDespesaId(request.categoriaDespesaId());
        detalhe.setNome(request.nome());
        detalhe.setValor(request.valor());
        detalhe.setParcela(request.parcela());
        detalhe.setDescricao(request.descricao());

        if (request.origem() != null && !request.origem().isBlank()) {
            detalhe.setOrigem(DetalheDespesa.OrigemDetalhe.valueOf(request.origem().toUpperCase()));
        }

        detalhe = detalheDespesaRepository.save(detalhe);
        return toResponse(detalhe);
    }

    @Transactional(readOnly = true)
    public List<DetalheDespesaResponse> listarPorDespesa(Long despesaId) {
        return detalheDespesaRepository.findByDespesaId(despesaId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public DetalheDespesaResponse atualizar(Long id, DetalheDespesaRequest request) {
        DetalheDespesa detalhe = detalheDespesaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Detalhe da despesa não encontrado."));

        detalhe.setCategoriaDespesaId(request.categoriaDespesaId());
        detalhe.setNome(request.nome());
        detalhe.setValor(request.valor());
        detalhe.setParcela(request.parcela());
        detalhe.setDescricao(request.descricao());

        if (request.origem() != null && !request.origem().isBlank()) {
            detalhe.setOrigem(DetalheDespesa.OrigemDetalhe.valueOf(request.origem().toUpperCase()));
        }

        detalhe = detalheDespesaRepository.save(detalhe);
        return toResponse(detalhe);
    }

    @Transactional
    public void deletarPorId(Long id) {
        if (!detalheDespesaRepository.existsById(id)) {
            throw new EntityNotFoundException("Detalhe da despesa não encontrado.");
        }
        detalheDespesaRepository.deleteById(id);
    }

    @Transactional
    public void deletarPorDespesa(Long despesaId) {
        detalheDespesaRepository.deleteByDespesaId(despesaId);
    }


    private DetalheDespesaResponse toResponse(DetalheDespesa r) {

        Despesa despesa = despesaRepository.findById(r.getDespesaId())
                .orElseThrow(() -> new RuntimeException("Despesa não encontrada"));

        CategoriaDespesa categoria = categoriaDespesaRepository.findById(r.getCategoriaDespesaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        return new DetalheDespesaResponse(
                r.getId(),
                despesa.getNome(),
                categoria.getNome(),
                r.getNome(),
                r.getValor(),
                r.getParcela(),
                r.getDescricao()
        );
    }
}