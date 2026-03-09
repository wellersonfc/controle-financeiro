package br.com.wellerson.financecontrol.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.wellerson.financecontrol.entity.Despesa;

public record DespesaResponse(
    Long id,
    String nome,
    Long despesaCategoriaId,
    String nomeCategoria,
    Despesa.FormaPagamento formaPagamento,
    LocalDate dataCompetencia,
    LocalDate dataLancamento,
    BigDecimal total,
    String origem,
    Despesa.StatusDespesa status,
    LocalDate createdAt,
    String descricao
) {}