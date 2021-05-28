package br.ucsal.bes.tcc.analyzereducation.controller;

import java.util.List;
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

import br.ucsal.bes.tcc.analyzereducation.dto.TarefaDTO;
import br.ucsal.bes.tcc.analyzereducation.model.Tarefa;
import br.ucsal.bes.tcc.analyzereducation.repository.PremissaRepository;
import br.ucsal.bes.tcc.analyzereducation.repository.TarefaRepository;
import br.ucsal.bes.tcc.analyzereducation.repository.TesteRepository;

@Controller
@RequestMapping("home")
public class TarefaController {

	@Autowired
	private TarefaRepository tarefaRepository;

	@Autowired
	private TesteRepository testeRepository;

	@Autowired
	private PremissaRepository premissaRepository;

	@GetMapping("/tarefas")
	public String tarefas(TarefaDTO tarefaDto) {

		List<Tarefa> tarefas = tarefaRepository.findAll();

		if (tarefas.isEmpty()) {
			return "home/tarefas";
		} else {
			tarefaDto.getTarefas().addAll(tarefaRepository.findAll());
		}

		return "home/tarefas";
	}

	@GetMapping("/tarefas/editarTarefa")
	public String editarTarefa(TarefaDTO tarefaDto, @RequestParam("tarefaId") Optional<Long> tarefaId) {

		if (tarefaId.isEmpty())
			return "redirect:/home/tarefas";

		var tarefa = tarefaRepository.findById(tarefaId.get());

		if (tarefa.isEmpty())
			return "redirect:/home/tarefas";

		tarefaDto.setId(tarefa.get().getId());
		tarefaDto.setTitulo(tarefa.get().getTitulo());
		tarefaDto.setDescricao(tarefa.get().getDescricao());

		return "home/tarefas/editarTarefa";
	}

	@PostMapping("tarefas/editado")
	public String editado(@Valid TarefaDTO tarefaDto, BindingResult result, RedirectAttributes attributes) {

		if (tarefaDto.getId() == null)
			return "redirect:/home/tarefas";

		var tarefa = tarefaRepository.findById(tarefaDto.getId());

		if (tarefa.isEmpty())
			return "redirect:/home/tarefas";

		if (result.hasErrors())
			return "redirect:/home/tarefas/editarTarefa?tarefaId=" + tarefaDto.getId();

		tarefa.get().setId(tarefaDto.getId());
		tarefa.get().setTitulo(tarefaDto.getTitulo());
		tarefa.get().setDescricao(tarefaDto.getDescricao());

		tarefaRepository.save(tarefa.get());

		return "redirect:/home/tarefas";
	}

	@GetMapping("/tarefas/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, TarefaDTO tarefaDto) {

		var tarefa = tarefaRepository.findById(id);

		if (tarefa.isPresent()) {
			testeRepository.removeByTarefa(tarefa.get());
			premissaRepository.removeByTarefa(tarefa.get());
			tarefaRepository.deleteById(id);
		}

		return "redirect:/home/tarefas";
	}

}
