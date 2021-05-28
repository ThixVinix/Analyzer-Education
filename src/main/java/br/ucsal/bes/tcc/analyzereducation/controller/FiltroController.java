package br.ucsal.bes.tcc.analyzereducation.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ucsal.bes.tcc.analyzereducation.dto.FiltroDTO;
import br.ucsal.bes.tcc.analyzereducation.model.Premissa;
import br.ucsal.bes.tcc.analyzereducation.model.Tarefa;
import br.ucsal.bes.tcc.analyzereducation.repository.PremissaRepository;
import br.ucsal.bes.tcc.analyzereducation.repository.TarefaRepository;

@Controller
@RequestMapping("home")
public class FiltroController {

	@Autowired
	private TarefaRepository tarefaRepository;

	@Autowired
	private PremissaRepository premissaRepository;

	@GetMapping("premissas")
	public String premissas(FiltroDTO filtroDto, @RequestParam("tarefaId") Optional<Long> tarefaId) {

		if (tarefaId.isEmpty())
			return "redirect:/home/tarefas";

		var tarefa = tarefaRepository.findById(tarefaId.get());

		if (tarefa.isEmpty())
			return "redirect:/home/tarefas";

		filtroDto.setCodgTarefa(tarefa.get().getId());

		var premissas = premissaRepository.findByTarefa(tarefa.get());

		filtroDto.getPremissas().addAll(premissas);

		return "home/premissas";
	}

	@GetMapping("/premissas/editarPremissa")
	public String editarPremissa(FiltroDTO filtroDto, @RequestParam("premissaId") Optional<Long> premissaId) {

		if (premissaId.isEmpty())
			return "redirect:/home/tarefas";

		var premissa = premissaRepository.findById(premissaId.get());

		if (premissa.isEmpty())
			return "redirect:/home/tarefas";

		if (premissa.get().getTarefa().getId() == null)
			return "redirect:/home/tarefas";

		var tarefa = tarefaRepository.findById(premissa.get().getTarefa().getId());

		if (tarefa.isEmpty())
			return "redirect:/home/tarefas";

		filtroDto.setId(premissa.get().getId());
		filtroDto.setTitulo(premissa.get().getNomeFiltro());
		filtroDto.setIntervalo(premissa.get().getIntervalo());
		filtroDto.setQtdDemandada(premissa.get().getQtdDemandada());
		filtroDto.setCodgTarefa(premissa.get().getTarefa().getId());

		return "home/premissas/editarPremissa";
	}

	@PostMapping("premissas/editado")
	public String editado(@Valid FiltroDTO filtroDto, BindingResult result, RedirectAttributes attributes) {

		if (filtroDto.getId() == null)
			return "redirect:/home/tarefas";

		var premissa = premissaRepository.findById(filtroDto.getId());

		if (premissa.isEmpty())
			return "redirect:/home/tarefas";

		if (premissa.get().getTarefa().getId() == null)
			return "redirect:/home/tarefas";

		var tarefa = tarefaRepository.findById(premissa.get().getTarefa().getId());

		if (tarefa.isEmpty())
			return "redirect:/home/tarefas";

		if (result.hasErrors())
			return "redirect:/home/premissas/editarPremissa?premissaId=" + filtroDto.getId();

		var testeEditado = preencherPremissa(filtroDto, tarefa);

		premissaRepository.save(testeEditado);

		return "redirect:/home/premissas?tarefaId=" + filtroDto.getCodgTarefa();
	}

	private Premissa preencherPremissa(FiltroDTO filtroDto, Optional<Tarefa> tarefa) {

		var premissa = new Premissa();
		premissa.setId(filtroDto.getId());
		premissa.setNomeFiltro(filtroDto.getTitulo());
		premissa.setIntervalo(filtroDto.getIntervalo());
		premissa.setQtdDemandada(filtroDto.getQtdDemandada());
		
		if (tarefa.isPresent()) {
			premissa.setTarefa(tarefa.get());
		}

		return premissa;
	}

	@GetMapping("/premissas/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, FiltroDTO filtroDto) {

		var premissa = premissaRepository.findById(id);

		if (premissa.isPresent()) {
			var codgTarefa = premissa.get().getTarefa().getId();
			premissaRepository.delete(premissa.get());
			return "redirect:/home/premissas?tarefaId=" + codgTarefa;
		}

		return "redirect:/home/tarefas";
	}

}
