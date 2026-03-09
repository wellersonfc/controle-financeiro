package br.com.wellerson.financecontrol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wellerson.financecontrol.entity.DetalheDespesa;

@Repository
public interface DetalheDespesaRepository extends JpaRepository<DetalheDespesa, Long> {

    List<DetalheDespesa> findByDespesaId(Long despesaId);

    void deleteByDespesaId(Long despesaId);
}