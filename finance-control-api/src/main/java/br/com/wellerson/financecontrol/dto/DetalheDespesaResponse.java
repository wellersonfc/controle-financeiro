package br.com.wellerson.financecontrol.dto;

import java.math.BigDecimal;

public record DetalheDespesaResponse(
    Long id,
    String despesaNome,
    String categoriaDespesaNome,
    String nome,
    BigDecimal valor,
    String parcela,
    String descricao
) {}