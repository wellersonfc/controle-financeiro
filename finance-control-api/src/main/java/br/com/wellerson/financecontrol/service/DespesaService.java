package br.com.wellerson.financecontrol.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.wellerson.financecontrol.dto.DespesaRequest;
import br.com.wellerson.financecontrol.dto.DespesaResponse;
import br.com.wellerson.financecontrol.entity.Despesa;
import br.com.wellerson.financecontrol.repository.DespesasRepository;
import org.springframework.transaction.annotation.Transactional;
import br.com.wellerson.financecontrol.dto.DespesaResumoResponse;

@Service
public class DespesaService {

    private final DespesasRepository despesasRepository;

    public DespesaService(DespesasRepository despesasRepository) {
        this.despesasRepository = despesasRepository;
    }

    public DespesaResponse criarDespesa(Long usuarioId, DespesaRequest request) {

        Despesa despesa = new Despesa();
        despesa.setUsuarioId(usuarioId);
        despesa.setNome(request.nome());
        despesa.setTipo(request.tipo());
        despesa.setFormaPagamento(request.formaPagamento());
        despesa.setDataCompetencia(request.dataCompetencia());
        despesa.setDataLancamento(request.dataLancamento());
        despesa.setTotal(request.total());
        despesa.setOrigem(request.origem());
        despesa.setDescricao(request.descricao());

        despesa.setStatus(request.status() != null ? request.status() : Despesa.StatusDespesa.PENDENTE);
        despesa.setCreatedAt(LocalDate.now());

        Despesa salva = despesasRepository.save(despesa);
        return toResponse(salva);
    }

    private DespesaResponse toResponse(Despesa despesa) {
        return new DespesaResponse(
                despesa.getId(),
                despesa.getNome(),
                despesa.getTipo(),
                despesa.getFormaPagamento(),
                despesa.getDataCompetencia(),
                despesa.getDataLancamento(),
                despesa.getTotal(),
                despesa.getOrigem(),
                despesa.getStatus(),
                despesa.getCreatedAt(), 
                despesa.getDescricao()
        );
    }

    public <list> DespesaResponse listar() {
        return despesasRepository.findAll().stream()
                .map(this::toResponse)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Nenhuma despesa encontrada."));
    }

    public DespesaResponse buscarPorId(Long id) {
        Despesa despesa = despesasRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Despesa não encontrada com ID: " + id));
        return toResponse(despesa);
    }   

    public void deletar(Long id) {
        if (!despesasRepository.existsById(id)) {
            throw new IllegalArgumentException("Despesa não encontrada com ID: " + id);
        }
        despesasRepository.deleteById(id);
    }   

    public DespesaResponse atualizar(Long id, DespesaRequest request) {
        Despesa despesa = despesasRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Despesa não encontrada com ID: " + id));

        despesa.setNome(request.nome());
        despesa.setTipo(request.tipo());
        despesa.setFormaPagamento(request.formaPagamento());
        despesa.setDataCompetencia(request.dataCompetencia());
        despesa.setDataLancamento(request.dataLancamento());
        despesa.setTotal(request.total());
        despesa.setOrigem(request.origem());
        despesa.setDescricao(request.descricao());

        if (request.status() != null) {
            despesa.setStatus(request.status());
        }

        Despesa atualizada = despesasRepository.save(despesa);
        return toResponse(atualizada);
    }   

        // Genérico: sem filtro / por ano / por mês / por mês+ano
    @Transactional(readOnly = true)
    public DespesaResumoResponse buscar(Long usuarioId, Integer mes, Integer ano) {

        List<Despesa> entidades;

        if (mes == null && ano == null) {
            // sem filtro de data
            entidades = despesasRepository.findByUsuarioId(usuarioId);

        } else {
            // se passou só mês, usa ano atual (você pode mudar essa regra se quiser)
            int anoFinal = (ano != null) ? ano : LocalDate.now().getYear();

            LocalDate inicio;
            LocalDate fim;

            if (mes != null) {
                // mês + (ano ou ano atual)
                inicio = LocalDate.of(anoFinal, mes, 1);
                fim = inicio.withDayOfMonth(inicio.lengthOfMonth());
            } else {
                // apenas ano
                inicio = LocalDate.of(anoFinal, 1, 1);
                fim = LocalDate.of(anoFinal, 12, 31);
            }

            entidades = despesasRepository.findByUsuarioIdAndDataLancamentoBetween(usuarioId, inicio, fim);
            // Se quiser por competência, troque para:
            // entidades = despesasRepository.findByUsuarioIdAndDataCompetenciaBetween(usuarioId, inicio, fim);
        }

        // Converte para DTO
        List<DespesaResponse> lista = entidades.stream()
                .map(this::toResponse)
                .toList();

        // Soma total
        BigDecimal total = entidades.stream()
                .map(Despesa::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new DespesaResumoResponse(total, lista.size(), lista);
    }
}