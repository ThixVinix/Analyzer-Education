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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ucsal.bes.tcc.analyzereducation.dto.FiltroDTO;
import br.ucsal.bes.tcc.analyzereducation.model.Premissa;
import br.ucsal.bes.tcc.analyzereducation.model.Tarefa;
import br.ucsal.bes.tcc.analyzereducation.repository.PremissaRepository;
import br.ucsal.bes.tcc.analyzereducation.repository.TarefaRepository;

@Controller
@RequestMapping("home")
public class CriarFiltroController {

	@Autowired
	private TarefaRepository tarefaRepository;

	@Autowired
	private PremissaRepository premissaRepository;

	@GetMapping("criarPremissa")
	public String criarFiltro(FiltroDTO filtroDto, @RequestParam("tarefaId") Optional<Long> tarefaId) {

		if (tarefaId.isEmpty())
			return "redirect:/home/tarefas";

		var tarefa = tarefaRepository.findById(tarefaId.get());

		if (tarefa.isEmpty())
			return "redirect:/home/tarefas";

		filtroDto.setCodgTarefa(tarefa.get().getId());

		return "home/criarPremissa";
	}

	@PostMapping("novaPremissa")
	public String novaPremissa(@Valid FiltroDTO filtroDto, BindingResult result, RedirectAttributes attributes) {

		if (filtroDto.getCodgTarefa() == null)
			return "redirect:/home/tarefas";

		Optional<Tarefa> tarefa = tarefaRepository.findById(filtroDto.getCodgTarefa());

		if (tarefa.isEmpty())
			return "redirect:/home/tarefas";

		if (result.hasErrors())
			return "redirect:/home/criarPremissa?tarefaId=" + filtroDto.getCodgTarefa();

		var premissa = preencherPremissa(filtroDto, tarefa);

		premissaRepository.save(premissa);

		return "redirect:/home/premissas?tarefaId=" + filtroDto.getCodgTarefa();
//		return "redirect:/home/tarefas";
	}

	private Premissa preencherPremissa(FiltroDTO filtroDto, Optional<Tarefa> tarefa) {

		var premissa = new Premissa();

		premissa.setNomeFiltro(filtroDto.getTitulo());
		premissa.setIntervalo(filtroDto.getIntervalo());
		premissa.setQtdDemandada(filtroDto.getQtdDemandada());
		
		if (tarefa.isPresent()) {
			premissa.setTarefa(tarefa.get());
		}

		return premissa;
	}

}
