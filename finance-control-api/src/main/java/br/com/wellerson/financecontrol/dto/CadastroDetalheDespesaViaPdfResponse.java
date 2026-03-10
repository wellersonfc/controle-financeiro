package br.com.wellerson.financecontrol.dto;

import java.math.BigDecimal;
import java.util.List;

public record CadastroDetalheDespesaViaPdfResponse(
        Integer quantidadeItens,
        BigDecimal totalImportado,
        List<DetalheDespesaResponse> detalhes
) {}