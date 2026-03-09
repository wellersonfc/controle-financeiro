package br.com.wellerson.financecontrol.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.wellerson.financecontrol.entity.Despesa.FormaPagamento;
import br.com.wellerson.financecontrol.entity.Despesa.StatusDespesa;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DespesaRequest(

        @NotNull(message = "É necessário informar uma identificação da despesa.")
        String nome,

        @NotNull(message = "É necessário informar a forma de pagamento.")
        FormaPagamento formaPagamento,

        LocalDate dataCompetencia,

        @NotNull(message = "É necessário informar a data de lançamento.")
        LocalDate dataLancamento,

        @NotNull(message = "É necessário informar o valor total da despesa.")
        @Positive(message = "O valor da despesa deve ser maior que zero.")
        BigDecimal total,

        String origem,

        StatusDespesa status,

        String descricao

) {}