package br.ucsal.bes.tcc.analyzereducation.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ucsal.bes.tcc.analyzereducation.model.Tarefa;
import br.ucsal.bes.tcc.analyzereducation.model.Teste;

@Repository
public interface TesteRepository extends JpaRepository<Teste, Long> {

	@Transactional
	void removeByTarefa(Tarefa tarefa);

	List<Teste> findByTarefa(Tarefa tarefa);

}
