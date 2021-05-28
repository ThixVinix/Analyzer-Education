package br.ucsal.bes.tcc.analyzereducation.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ucsal.bes.tcc.analyzereducation.model.Premissa;
import br.ucsal.bes.tcc.analyzereducation.model.Tarefa;

@Repository
public interface PremissaRepository extends JpaRepository<Premissa, Long> {

	@Transactional
	void removeByTarefa(Tarefa tarefa);

	List<Premissa> findByTarefa(Tarefa tarefa);
}
