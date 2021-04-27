package br.ucsal.bes.tcc.analyzereducation.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ucsal.bes.tcc.analyzereducation.analyzer.JavaCode;
import br.ucsal.bes.tcc.analyzereducation.dto.CodeEditorDTO;
import br.ucsal.bes.tcc.analyzereducation.model.Autor;
import br.ucsal.bes.tcc.analyzereducation.model.Resultado;
import br.ucsal.bes.tcc.analyzereducation.model.Tarefa;
import br.ucsal.bes.tcc.analyzereducation.repository.CodeEditorRepository;
import br.ucsal.bes.tcc.analyzereducation.util.BancoDeDados;
import br.ucsal.bes.tcc.analyzereducation.util.Constante;

@Controller
@RequestMapping("home")
public class UserEditorController {

	private Autor autor;

	@Autowired
	private CodeEditorRepository codeEditorRepository;

	@GetMapping("usereditor")
	public String usereditor(CodeEditorDTO codeEditorDto, @RequestParam("tarefaId") Optional<Long> tarefaId) {

		if (tarefaId.isPresent()) {
			System.out.println(tarefaId.get());
			Tarefa tarefa = BancoDeDados.obterTarefa(tarefaId.get());
			codeEditorDto.setId(tarefa.getId());
			codeEditorDto.setTestes(tarefa.getTestes());
			codeEditorDto.setTitulo(tarefa.getTitulo());
			codeEditorDto.setDescricao(tarefa.getDescricao());
		}

		return "home/usereditor";
	}

	@PostMapping("novo")
	public String novo(@Valid CodeEditorDTO codeEditorDto, BindingResult result) {

		if (result.hasErrors()) {
			return "home/usereditor";
		}

		Tarefa tarefa = BancoDeDados.obterTarefa(codeEditorDto.getId());

		JavaCode javaCode = new JavaCode();
		Resultado resultado = new Resultado();
		resultado = javaCode.executarCodigo(codeEditorDto.getEntrada(), tarefa);

		StringBuilder sb = new StringBuilder();
		int i = 1;
		for (String saida : resultado.getSaidasObtidas()) {
			String str = "Resultado da execução #" + i + ":";
			sb.append(str + Constante.QUEBRA_LINHA);
			sb.append(saida + Constante.QUEBRA_LINHA);
			codeEditorDto.getSaidasTesteObtidas().add(saida);
			i++;
		}

		codeEditorDto.setId(tarefa.getId());
		codeEditorDto.setTestes(tarefa.getTestes());
		codeEditorDto.setTitulo(tarefa.getTitulo());
		codeEditorDto.setDescricao(tarefa.getDescricao());

		codeEditorDto.setSaida(sb.toString());

		System.out.println(codeEditorDto.getSaida());

		// CodeEditor codeEditor = codeEditorDto.toCodeEditor();
//		codeEditorRepository.save(codeEditor);

		return "home/usereditor";
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

}
