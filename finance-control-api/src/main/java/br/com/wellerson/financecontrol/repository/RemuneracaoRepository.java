package br.com.wellerson.financecontrol.repository;
import br.com.wellerson.financecontrol.entity.Remuneracao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RemuneracaoRepository extends JpaRepository<Remuneracao, Long> {
    List<Remuneracao> findByUsuarioId(Long usuarioId);

    List<Remuneracao> findByUsuarioIdAndDataDeEntradaBetween(
            Long usuarioId,
            LocalDate inicio,
            LocalDate fim
    );
} 