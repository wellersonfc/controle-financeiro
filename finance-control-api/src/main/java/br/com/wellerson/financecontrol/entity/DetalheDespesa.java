package br.com.wellerson.financecontrol.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detalhe_despesa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalheDespesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "despesa_id", nullable = false)
    private Long despesaId;

    @Column(name = "categoria_despesa_id", nullable = false)
    private Long categoriaDespesaId;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(length = 20)
    private String parcela;

    @Column(length = 255)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrigemDetalhe origem;

    @Column(name = "created_at")
    private LocalDate createdAt;

    public enum OrigemDetalhe {
        MANUAL,
        IA
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDate.now();
        if (this.origem == null) {
            this.origem = OrigemDetalhe.MANUAL;
        }
    }
}