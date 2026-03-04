package br.com.wellerson.financecontrol.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "remuneracoes")
public class Remuneracao {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(nullable = false)
    private BigDecimal valor;

    private String descricao;

    @Enumerated(EnumType.STRING)
    private CategoriaRemuneracao categoria;

    private LocalDate dataDeEntrada;

    public enum CategoriaRemuneracao {
        SALARIO,
        FREELANCE,
        INVESTIMENTO,
        OUTROS
    }
}
