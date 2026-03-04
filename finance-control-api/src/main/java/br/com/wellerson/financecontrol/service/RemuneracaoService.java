package br.com.wellerson.financecontrol.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.wellerson.financecontrol.dto.RemuneracaoRequest;
import br.com.wellerson.financecontrol.dto.RemuneracaoResponse;
import br.com.wellerson.financecontrol.dto.RemuneracaoResumoResponse;
import br.com.wellerson.financecontrol.entity.Remuneracao;
import br.com.wellerson.financecontrol.repository.RemuneracaoRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RemuneracaoService {

    private final RemuneracaoRepository remuneracaoRepository;

    public RemuneracaoService(RemuneracaoRepository remuneracaoRepository) {
        this.remuneracaoRepository = remuneracaoRepository;
    }

    public RemuneracaoResponse criar(RemuneracaoRequest request) {

        Remuneracao remuneracao = new Remuneracao();
        remuneracao.setUsuarioId(request.usuarioId());
        remuneracao.setValor(request.valor());
        remuneracao.setDescricao(request.descricao());
        remuneracao.setCategoria(request.categoria());
        remuneracao.setDataDeEntrada(request.dataDeEntrada());

        remuneracao = remuneracaoRepository.save(remuneracao);

        return toResponse(remuneracao);
    }

    public List<RemuneracaoResponse> listar() {
        return remuneracaoRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public RemuneracaoResponse buscarPorId(Long id) {
        Remuneracao remuneracao = remuneracaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Remuneração não encontrada com ID: " + id));
        return toResponse(remuneracao);
    }
    
    public void deletar(Long id) {
        if (!remuneracaoRepository.existsById(id)) {
            throw new IllegalArgumentException("Remuneração não encontrada com ID: " + id);
        }
        remuneracaoRepository.deleteById(id);
    }

    public RemuneracaoResponse atualizar(Long id, RemuneracaoRequest request) {
        Remuneracao remuneracao = remuneracaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Remuneração não encontrada com ID: " + id));

        remuneracao.setValor(request.valor());
        remuneracao.setDescricao(request.descricao());
        remuneracao.setCategoria(request.categoria());
        remuneracao.setDataDeEntrada(request.dataDeEntrada());

        remuneracao = remuneracaoRepository.save(remuneracao);

        return toResponse(remuneracao);
    }

    public List<RemuneracaoResponse> listarPorUsuarioId(Long usuarioId) {
    return remuneracaoRepository.findByUsuarioId(usuarioId)
            .stream()
            .map(this::toResponse)
            .toList();
    }

   // Genérico: sem filtro / por ano / por mês / por mês+ano
    @Transactional(readOnly = true)
    public RemuneracaoResumoResponse buscar(Long usuarioId, Integer mes, Integer ano) {

        List<Remuneracao> entidades;

        if (mes == null && ano == null) {
            // sem filtro de data
            entidades = remuneracaoRepository.findByUsuarioId(usuarioId);

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

            entidades = remuneracaoRepository.findByUsuarioIdAndDataDeEntradaBetween(usuarioId, inicio, fim);
        }

        // Converte para DTO
        List<RemuneracaoResponse> lista = entidades.stream()
                .map(this::toResponse)
                .toList();

        // Soma total
        BigDecimal total = entidades.stream()
                .map(Remuneracao::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new RemuneracaoResumoResponse(total, lista.size(), lista);
    }

    private RemuneracaoResponse toResponse(Remuneracao r) {
        return new RemuneracaoResponse(
                r.getId(),
                r.getUsuarioId(),
                r.getValor(),
                r.getDescricao(),
                r.getCategoria(),
                r.getDataDeEntrada()
        );
    }
}