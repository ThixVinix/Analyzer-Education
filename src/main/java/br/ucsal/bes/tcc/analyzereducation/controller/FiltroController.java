package br.ucsal.bes.tcc.analyzereducation.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class FiltroController {

//	@GetMapping("/filtros")
//	public String filtros(Model model) {
//
//		model.addAttribute("filtros", BancoDeDados.obterFiltros());
//		return "home/filtros";
//
//	}

	@GetMapping("premissas")
	public String premissas(FiltroDTO filtroDto, @RequestParam("tarefaId") Optional<Long> tarefaId) {

		if (tarefaId.isEmpty())
			return "redirect:/home/tarefas";

		var tarefas = BancoDeDados.obterTarefas();
		var existTask = false;

		for (Tarefa tarefa : tarefas) {
			if (tarefa.getId().equals(tarefaId.get())) {
				existTask = true;
				break;
			}
		}

		if (!existTask)
			return "redirect:/home/tarefas";

		var tarefa = BancoDeDados.obterTarefa(tarefaId.get());

		filtroDto.setCodgTarefa(tarefa.getId());
		filtroDto.getPremissas().addAll(tarefa.getFiltros());

		return "home/premissas";
	}

	@GetMapping("/premissas/editarPremissa")
	public String editarPremissa(FiltroDTO filtroDto, @RequestParam("premissaId") Optional<Long> premissaId) {

		if (premissaId.isEmpty())
			return "redirect:/home/tarefas";

		var premissas = BancoDeDados.obterFiltros();

		for (Filtro premissa : premissas) {
			if (premissa.getId().equals(premissaId.get())) {
				filtroDto.setId(premissa.getId());
				filtroDto.setTitulo(premissa.getNomeFiltro());
				filtroDto.setIntervalo(premissa.getIntervalo());
				filtroDto.setQtdDemandada(premissa.getQtdDemandada());
				return "home/premissas/editarPremissa";
			}
		}

		return "redirect:/home/tarefas";
	}

	@PostMapping("premissas/editado")
	public String editado(@Valid FiltroDTO filtroDto, BindingResult result, RedirectAttributes attributes) {

//		if (filtroDto.getId() == null)
//			return "redirect:/home/premissas?tarefaId=" + testeDto.getCodgTarefa();

		var tarefas = BancoDeDados.obterTarefas();
		var existTask = false;

		for (Tarefa tarefa : tarefas) {
			if (tarefa.getId().equals(filtroDto.getId())) {
				existTask = true;
				break;
			}
		}

//		if (!existTask)
//			return "redirect:/home/premissas?tarefaId=" + testeDto.getCodgTarefa();

		if (result.hasErrors())
			return "redirect:/home/premissas/editarPremissa?premissaId=" + filtroDto.getId();

		var premissa = preencherPremissa(filtroDto);

		BancoDeDados.alterarFiltro(premissa);

//		return "redirect:/home/premissas?tarefaId=" + testeDto.getCodgTarefa();
		return "redirect:/home/tarefas";
	}

	private Filtro preencherPremissa(FiltroDTO filtroDto) {

		var premissa = new Filtro();
		premissa.setId(filtroDto.getId());
		premissa.setNomeFiltro(filtroDto.getTitulo());
		premissa.setIntervalo(filtroDto.getIntervalo());
		premissa.setQtdDemandada(filtroDto.getQtdDemandada());

		return premissa;
	}

	@GetMapping("/premissas/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, FiltroDTO filtroDto) {

		var tarefas = BancoDeDados.obterTarefas();
		var premissas = BancoDeDados.obterFiltros();

		var found = false;

		for (var i = 0; i < tarefas.size(); i++) {

			if (found)
				break;

			for (var j = 0; j < premissas.size(); j++) {
				if (tarefas.get(i).getFiltros().get(j).getId().equals(id)) {
					var premissa = BancoDeDados.obterFiltro(id);
					tarefas.get(i).getFiltros().remove(premissa);
					BancoDeDados.deletarFiltro(id);
					found = true;
					break;
				}
			}

		}

		// tarefaDto.getTarefas().addAll(BancoDeDados.obterTarefas());
		return "redirect:/home/tarefas";
	}

}
