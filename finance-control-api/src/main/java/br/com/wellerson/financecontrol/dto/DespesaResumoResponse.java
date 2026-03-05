package br.com.wellerson.financecontrol.dto;

import java.math.BigDecimal;
import java.util.List;

public record DespesaResumoResponse(
        BigDecimal total,
        Integer quantidade,
        List<DespesaResponse> despesas
) {}