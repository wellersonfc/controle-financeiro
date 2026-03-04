package br.com.wellerson.financecontrol.dto;

import br.com.wellerson.financecontrol.entity.Remuneracao;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RemuneracaoResponse(

        Long id,
        Long usuarioId,
        BigDecimal valor,
        String descricao,
        Remuneracao.CategoriaRemuneracao categoria,
        LocalDate dataDeEntrada

) {}