package br.com.wellerson.financecontrol.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DetalheDespesaRequest(

    @NotNull(message = "Despesa é obrigatória")
    Long despesaId,

    @NotNull(message = "Categoria é obrigatória")
    Long categoriaDespesaId,

    @NotBlank(message = "Nome é obrigatório")
    String nome,

    @NotNull(message = "Valor é obrigatório")
    BigDecimal valor,

    String parcela,

    String descricao,

    String origem

) {}