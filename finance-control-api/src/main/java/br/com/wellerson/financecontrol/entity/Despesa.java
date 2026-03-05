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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "despesas")
public class Despesa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoDespesa tipo;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @Column(name = "data_competencia")
    private LocalDate dataCompetencia;

    @Column(name = "data_lancamento")
    private LocalDate dataLancamento;

   @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    private String origem;
    
    @Enumerated(EnumType.STRING)
    private StatusDespesa status;

    @Column(name = "created_at")
    private LocalDate createdAt;

    private String descricao;

    public enum TipoDespesa {
        CONTA,
        COMPRA,
        ASSINATURA,
        PARCELAMENTO,
        EMPRESTIMO,
        OUTROS
    }

    public enum FormaPagamento {
        CARTAO_CREDITO,
        CARTAO_DEBITO,
        PIX,
        BOLETO,
        DINHEIRO,
        TRANSFERENCIA
    }

    public enum StatusDespesa {
        PENDENTE,
        PAGO,
        ATRASADO,
        CANCELADO
    }
    
}
