package br.com.wellerson.financecontrol.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;

import br.com.wellerson.financecontrol.entity.Despesa;

public record DespesaResponse(

        Long id,
        String nome,
        Despesa.TipoDespesa tipo,
        Despesa.FormaPagamento formaPagamento,
        LocalDate dataCompetencia,
        LocalDate dataLancamento,
        BigDecimal total,
        String origem,
        Despesa.StatusDespesa status,
        LocalDate createdAt,
        String descricao
        

) {}