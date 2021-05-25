package br.ucsal.bes.tcc.analyzereducation.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ucsal.bes.tcc.analyzereducation.dto.FiltroDTO;
import br.ucsal.bes.tcc.analyzereducation.model.Filtro;
import br.ucsal.bes.tcc.analyzereducation.model.Tarefa;
import br.ucsal.bes.tcc.analyzereducation.util.BancoDeDados;

@Controller
@RequestMapping("home")
public class CriarFiltroController {

	@GetMapping("criarPremissa")
	public String criarFiltro(FiltroDTO filtroDto, @RequestParam("tarefaId") Optional<Long> tarefaId) {

		if (tarefaId.isEmpty())
			return "redirect:/home/tarefas";

		var tarefas = BancoDeDados.obterTarefas();

		for (Tarefa tarefa : tarefas) {
			if (tarefa.getId().equals(tarefaId.get())) {
				filtroDto.setCodgTarefa(tarefaId.get());
				return "home/criarPremissa";
			}
		}

		return "redirect:/home/tarefas";
	}

	@PostMapping("novaPremissa")
	public String novaPremissa(@Valid FiltroDTO filtroDto, BindingResult result, RedirectAttributes attributes) {

		if (filtroDto.getCodgTarefa() == null)
			return "redirect:/home/tarefas";

		var tarefas = BancoDeDados.obterTarefas();
		var existTask = false;

		for (Tarefa tarefa : tarefas) {
			if (tarefa.getId().equals(filtroDto.getCodgTarefa())) {
				existTask = true;
				break;
			}
		}

		if (!existTask)
			return "redirect:/home/tarefas";

		if (result.hasErrors())
			return "redirect:/home/criarPremissa?tarefaId=" + filtroDto.getCodgTarefa();

		var premissa = preencherPremissa(filtroDto);

		BancoDeDados.adicionarFiltro(premissa);

		var tarefa = BancoDeDados.obterTarefa(filtroDto.getCodgTarefa());
		tarefa.getFiltros().add(premissa);

		return "redirect:/home/tarefas";
	}

	private Filtro preencherPremissa(FiltroDTO filtroDto) {
		var premissas = BancoDeDados.obterFiltros();

		Long id = 0L;
		for (var i = 0; i < premissas.size(); i++) {
			if (premissas.get(i).getId() > id) {
				id = premissas.get(i).getId();
			}
		}
		var premissa = new Filtro();
		premissa.setId(++id);
		premissa.setNomeFiltro(filtroDto.getTitulo());
		premissa.setIntervalo(filtroDto.getIntervalo());
		premissa.setQtdDemandada(filtroDto.getQtdDemandada());

		return premissa;
	}

}
