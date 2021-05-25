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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ucsal.bes.tcc.analyzereducation.dto.TarefaDTO;
import br.ucsal.bes.tcc.analyzereducation.model.Tarefa;
import br.ucsal.bes.tcc.analyzereducation.util.BancoDeDados;

@Controller
@RequestMapping("home")
public class TarefaController {

	@GetMapping("/tarefas")
	public String tarefas(TarefaDTO tarefaDto) {

		tarefaDto.getTarefas().addAll(BancoDeDados.obterTarefas());

		return "home/tarefas";
	}

	@GetMapping("/tarefas/editarTarefa")
	public String editarTarefa(TarefaDTO tarefaDto, @RequestParam("tarefaId") Optional<Long> tarefaId) {

		if (tarefaId.isEmpty())
			return "redirect:/home/tarefas";
		
		var tarefas = BancoDeDados.obterTarefas();

		for (Tarefa tarefa : tarefas) {
			if (tarefa.getId().equals(tarefaId.get())) {
				tarefaDto.setId(tarefa.getId());
				tarefaDto.setTitulo(tarefa.getTitulo());
				tarefaDto.setDescricao(tarefa.getDescricao());
				return "home/tarefas/editarTarefa";
			}
		}
		
			return "redirect:/home/tarefas";
	}

	@PostMapping("tarefas/editado")
	public String editado(@Valid TarefaDTO tarefaDto, BindingResult result, RedirectAttributes attributes) {

		
		if (tarefaDto.getId() == null)
			return "redirect:/home/tarefas";

		var tarefas = BancoDeDados.obterTarefas();
		var existTask = false;

		for (Tarefa tarefa : tarefas) {
			if (tarefa.getId().equals(tarefaDto.getId())) {
				existTask = true;
				break;
			}
		}

		if (!existTask)
			return "redirect:/home/tarefas";

		if (result.hasErrors())
			return "redirect:/home/tarefas/editarTarefa?tarefaId=" + tarefaDto.getId();
		
		var tarefa = BancoDeDados.obterTarefa(tarefaDto.getId());
		
		tarefa.setId(tarefaDto.getId());
		tarefa.setTitulo(tarefaDto.getTitulo());
		tarefa.setDescricao(tarefaDto.getDescricao());
		
		BancoDeDados.alterarTarefa(tarefa);

		return "redirect:/home/tarefas";
	}

//	@GetMapping("/tarefas/editar/{id}")
//	public ModelAndView editar(@PathVariable("id") Long id, TarefaDTO tarefaDto) {
//
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("/tarefas/editarTarefa/{id}");
//		Tarefa tarefa = BancoDeDados.obterTarefa(id);
//		mv.addObject("tarefa", tarefa);
//		return mv;
//	}
//
//	@PostMapping("tarefas/editarTarefa/{id}")
//	public ModelAndView editarTarefa(TarefaDTO tarefaDto) {
//		ModelAndView mv = new ModelAndView();
//		Tarefa tarefa = new Tarefa();
//		tarefa.setId(tarefaDto.getId());
//		tarefa.setTitulo(tarefaDto.getTitulo());
//		tarefa.setDescricao(tarefaDto.getDescricao());
//		BancoDeDados.alterarTarefa(tarefa);
//		mv.setViewName("redirect:/home/tarefas");
//		return mv;
//	}

	@GetMapping("/tarefas/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, TarefaDTO tarefaDto) {
		BancoDeDados.deletarTarefa(id);
		tarefaDto.getTarefas().addAll(BancoDeDados.obterTarefas());
		return "redirect:/home/tarefas";
	}

}
