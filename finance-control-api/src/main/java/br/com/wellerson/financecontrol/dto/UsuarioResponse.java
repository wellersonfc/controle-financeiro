package br.com.wellerson.financecontrol.dto;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public record UsuarioResponse(

        Long id,
        String nome,
        String email,
        LocalDate dataNascimento,
        String telefone,
        String status,
        String perfil,
        OffsetDateTime criadoEm

) {}