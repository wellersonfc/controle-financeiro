package br.com.wellerson.financecontrol.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.wellerson.financecontrol.entity.Despesa;


public interface DespesasRepository extends JpaRepository<Despesa, Long> {
List<Despesa> findByUsuarioId(Long usuarioId);

List<Despesa> findByUsuarioIdAndDespesaCategoriaId(Long usuarioId, Long despesaCategoriaId);

List<Despesa> findByUsuarioIdAndDataLancamentoBetween(Long usuarioId, LocalDate inicio, LocalDate fim);

} 