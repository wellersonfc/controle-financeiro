package br.com.wellerson.financecontrol.dto;

import java.math.BigDecimal;
import java.util.List;

public record RemuneracaoResumoResponse(
        BigDecimal total,
        Integer quantidade,
        List<RemuneracaoResponse> remuneracoes
) {}