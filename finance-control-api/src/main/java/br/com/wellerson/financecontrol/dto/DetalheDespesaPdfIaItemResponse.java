package br.com.wellerson.financecontrol.dto;

import java.math.BigDecimal;

public record DetalheDespesaPdfIaItemResponse(
        String nome,
        BigDecimal valor,
        String parcela,
        String descricao,
        String categoriaSugerida
) {}