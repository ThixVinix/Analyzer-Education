package br.ucsal.bes.tcc.analyzereducation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ucsal.bes.tcc.analyzereducation.dto.TarefaDTO;
import br.ucsal.bes.tcc.analyzereducation.model.Tarefa;
import br.ucsal.bes.tcc.analyzereducation.util.BancoDeDados;

@Controller
@RequestMapping("home")
public class CriarTarefaController {

	@GetMapping("criarTarefa")
	public String criarTarefa(@Valid TarefaDTO tarefaDto, BindingResult result, RedirectAttributes attributes) {
		
		return "home/criarTarefa";
	}
	
	@PostMapping("criar")
	public String criar(@Valid TarefaDTO tarefaDto, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			return "redirect:/home/criarTarefa";
		}

		List<Tarefa> tarefas = BancoDeDados.obterTarefas();

		Long id = 0L;
		for (int i = 0; i < tarefas.size(); i++) {
			if (tarefas.get(i).getId() > id) {
				id = tarefas.get(i).getId();
			}
		}
		
		Tarefa tarefa = new Tarefa();
		tarefa.setId(++id);
		tarefa.setTitulo(tarefaDto.getTitulo());
		tarefa.setDescricao(tarefaDto.getDescricao());

		BancoDeDados.adicionarTarefa(tarefa);

		return "redirect:/home/tarefas";
	}


	@GetMapping("voltar")
	public String voltar(Model model) {
		
		model.addAttribute("tarefas", BancoDeDados.obterTarefas());
		return "redirect:/home/tarefas";
	}

}
