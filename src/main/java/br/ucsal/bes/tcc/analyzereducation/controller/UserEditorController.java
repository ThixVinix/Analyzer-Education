package br.ucsal.bes.tcc.analyzereducation.controller;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

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
import br.ucsal.bes.tcc.analyzereducation.model.ArquivoMetrica;
import br.ucsal.bes.tcc.analyzereducation.model.Autor;
import br.ucsal.bes.tcc.analyzereducation.model.Resultado;
import br.ucsal.bes.tcc.analyzereducation.model.ResultadoTeste;
import br.ucsal.bes.tcc.analyzereducation.model.Tarefa;
import br.ucsal.bes.tcc.analyzereducation.model.Teste;
import br.ucsal.bes.tcc.analyzereducation.repository.CodeEditorRepository;
import br.ucsal.bes.tcc.analyzereducation.util.BancoDeDados;
import br.ucsal.bes.tcc.analyzereducation.util.Constante;
import br.ucsal.bes.tcc.analyzereducation.util.Util;

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
		Resultado resultado;
		resultado = javaCode.executarCodigo(codeEditorDto.getEntrada(), tarefa);

		StringBuilder sb = new StringBuilder();
//		int i = 1;
//		for (String saida : resultado.getSaidasObtidas()) {
//
//			i++;
//		}
		
		int j = 1;
		for (int i = 0; i < resultado.getSaidasObtidas().size(); i++) {
			String str = "-----------------------------------------------------------------------------------Resultado da execução #" + j + ":-----------------------------------------------------------------------------------";
			String saida = resultado.getSaidasObtidas().get(i);
		//	boolean isCorrect = resultado.getResultadosTestes().get(i);
			sb.append(str + Constante.QUEBRA_LINHA);
			sb.append(saida + Constante.QUEBRA_LINHA);
			codeEditorDto.getSaidasTesteObtidas().add(saida);
		//	codeEditorDto.getResultadosTestes().add(isCorrect);
			j++;
		}
		
		double totalTestes = 0;
		double qtdTestesCorretos = 0;
		for (Map.Entry<Teste, ResultadoTeste> rTest : resultado.getMapResultTest().entrySet()) {
			codeEditorDto.getMapResultTest().put(rTest.getKey(), rTest.getValue());
			boolean isCorrect = rTest.getValue().getCorrect();
			if (isCorrect) {
				qtdTestesCorretos += 1;
			}
			totalTestes += 1;
		}
		
		if (totalTestes > 0) {
		double percentualConclusao = (qtdTestesCorretos / totalTestes);
		Double porcentagemAcerto = percentualConclusao * 100;
		codeEditorDto.setPercentualAcerto(porcentagemAcerto.intValue());
		} else {
			codeEditorDto.setPercentualAcerto(null);
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
