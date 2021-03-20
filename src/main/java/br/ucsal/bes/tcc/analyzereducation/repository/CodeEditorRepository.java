package br.ucsal.bes.tcc.analyzereducation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ucsal.bes.tcc.analyzereducation.model.CodeEditor;

@Repository
public interface CodeEditorRepository extends JpaRepository<CodeEditor, Long> {

}
