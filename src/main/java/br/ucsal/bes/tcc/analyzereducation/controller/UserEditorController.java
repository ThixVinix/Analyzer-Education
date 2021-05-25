package br.ucsal.bes.tcc.analyzereducation.controller;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ucsal.bes.tcc.analyzereducation.analyzer.JavaCode;
import br.ucsal.bes.tcc.analyzereducation.dto.CodeEditorDTO;
import br.ucsal.bes.tcc.analyzereducation.model.Autor;
import br.ucsal.bes.tcc.analyzereducation.model.Filtro;
import br.ucsal.bes.tcc.analyzereducation.model.Resultado;
import br.ucsal.bes.tcc.analyzereducation.model.ResultadoFiltro;
import br.ucsal.bes.tcc.analyzereducation.model.ResultadoTeste;
import br.ucsal.bes.tcc.analyzereducation.model.Tarefa;
import br.ucsal.bes.tcc.analyzereducation.model.Teste;
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
			if (!tarefa.getFiltros().isEmpty()) {
					codeEditorDto.setFiltros(tarefa.getFiltros());
			} else {
				codeEditorDto.setFiltros(null);
			}
		}

		return "home/usereditor";
	}

	@PostMapping("novo")
	public String novo(@Valid CodeEditorDTO codeEditorDto, BindingResult result, RedirectAttributes attributes) {

		Tarefa tarefa = BancoDeDados.obterTarefa(codeEditorDto.getId());

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem",
					"Preencha o campo do editor de código-fonte para poder executá-lo.");
			return "redirect:/home/usereditor?tarefaId=" + tarefa.getId();
		}

		JavaCode javaCode = new JavaCode();
		Resultado resultado;

		resultado = javaCode.executarCodigo(codeEditorDto.getEntrada(), tarefa);

		StringBuilder sb = preencherConsole(codeEditorDto, resultado);

		preencherInformacoesAdicionais(codeEditorDto, resultado);
		
		preencherResultadosFiltros(codeEditorDto, resultado);

		preencherResultadosTestes(codeEditorDto, resultado);

		codeEditorDto.setId(tarefa.getId());
		codeEditorDto.setTestes(tarefa.getTestes());
		codeEditorDto.setTitulo(tarefa.getTitulo());
		codeEditorDto.setDescricao(tarefa.getDescricao());
		codeEditorDto.setFiltros(tarefa.getFiltros());
		codeEditorDto.setSaida(sb.toString());

		System.out.println(codeEditorDto.getSaida());

		// CodeEditor codeEditor = codeEditorDto.toCodeEditor();
//		codeEditorRepository.save(codeEditor);

		return "home/usereditor";
	}

	private StringBuilder preencherConsole(CodeEditorDTO codeEditorDto, Resultado resultado) {
		StringBuilder sb = new StringBuilder();

		if (resultado.getSaidasObtidas() != null && resultado.getSaidasObtidas().size() == 1) {
			sb.append(resultado.getSaidasObtidas().get(0));
		
		} else {
			int j = 1;
			for (int i = 0; i < resultado.getSaidasObtidas().size(); i++) {
				String str = "--------------------------------------------------------------------[Resultado da execução #"
						+ j + "]--------------------------------------------------------------------";
				String saida = resultado.getSaidasObtidas().get(i);
				sb.append(str + Constante.QUEBRA_LINHA);
				sb.append(saida + Constante.QUEBRA_LINHA);
				codeEditorDto.getSaidasTesteObtidas().add(saida);
				j++;
			}

		}
		return sb;
	}

	private void preencherInformacoesAdicionais(CodeEditorDTO codeEditorDto, Resultado resultado) {
		if (resultado.getArquivosMetrica() != null && !resultado.getArquivosMetrica().isEmpty()) {
			codeEditorDto.setArquivoMetrica(resultado.getArquivosMetrica().get(0));
		} else {
			codeEditorDto.setArquivoMetrica(null);
		}
	}
	
	private void preencherResultadosFiltros(CodeEditorDTO codeEditorDto, Resultado resultado) {
		double totalFiltros = 0;
		double qtdFiltrosUtilizadosCorretamente = 0;
		for (Map.Entry<Filtro, ResultadoFiltro> rFilter : resultado.getMapResultFilter().entrySet()) {
			codeEditorDto.getMapResultFilter().put(rFilter.getKey(), rFilter.getValue());
			boolean isCorrect = rFilter.getValue().getUsedCorrectly();
			if (isCorrect) {
				qtdFiltrosUtilizadosCorretamente += 1;
			}
			totalFiltros += 1;
		}

		if (totalFiltros > 0) {
			double percentualConclusao = (qtdFiltrosUtilizadosCorretamente / totalFiltros);
			Double porcentagemAcerto = percentualConclusao * 100;
			codeEditorDto.setPercentualUtilizacoes(porcentagemAcerto.intValue());
		} else {
			codeEditorDto.setPercentualUtilizacoes(null);
		}
	}

	private void preencherResultadosTestes(CodeEditorDTO codeEditorDto, Resultado resultado) {
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
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

}
