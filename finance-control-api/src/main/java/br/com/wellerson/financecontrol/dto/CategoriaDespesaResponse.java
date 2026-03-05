package br.com.wellerson.financecontrol.dto;

public record CategoriaDespesaResponse(
    Long id,
    String nome,
    String descricao,
    String createdAt
) {}