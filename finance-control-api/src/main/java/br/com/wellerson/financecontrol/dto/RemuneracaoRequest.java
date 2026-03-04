package br.com.wellerson.financecontrol.dto;

import br.com.wellerson.financecontrol.entity.Remuneracao;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RemuneracaoRequest(

        @NotNull(message = "Usuário é obrigatório")
        Long usuarioId,

        @NotNull(message = "Valor é obrigatório")
        BigDecimal valor,

        String descricao,

        @NotNull(message = "Categoria é obrigatória")
        Remuneracao.CategoriaRemuneracao categoria,

        @NotNull(message = "Data de entrada é obrigatória")
        LocalDate dataDeEntrada

) {}