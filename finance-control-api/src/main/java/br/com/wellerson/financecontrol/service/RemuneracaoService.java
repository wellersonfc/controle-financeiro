package br.com.wellerson.financecontrol.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.wellerson.financecontrol.dto.RemuneracaoRequest;
import br.com.wellerson.financecontrol.dto.RemuneracaoResponse;
import br.com.wellerson.financecontrol.entity.Remuneracao;
import br.com.wellerson.financecontrol.repository.RemuneracaoRepository;

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

    public List<RemuneracaoResponse> listarPorUsuarioMesAno(Long usuarioId, int mes, int ano) {

        LocalDate inicio = LocalDate.of(ano, mes, 1);
        LocalDate fim = inicio.withDayOfMonth(inicio.lengthOfMonth());

        return remuneracaoRepository
                .findByUsuarioIdAndDataDeEntradaBetween(usuarioId, inicio, fim)
                .stream()
                .map(this::toResponse)
                .toList();
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