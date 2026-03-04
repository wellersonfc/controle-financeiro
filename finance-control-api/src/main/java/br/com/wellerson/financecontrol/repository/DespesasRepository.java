package br.com.wellerson.financecontrol.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wellerson.financecontrol.entity.Despesa;
import br.com.wellerson.financecontrol.entity.Remuneracao;


public interface DespesasRepository extends JpaRepository<Despesa, Long> {
    List<Despesa> findByUsuarioId(Long usuarioId);

    List<Despesa> findByUsuarioIdAndDataDeEntradaBetween(
            Long usuarioId,
            LocalDate inicio,
            LocalDate fim
    );
} 