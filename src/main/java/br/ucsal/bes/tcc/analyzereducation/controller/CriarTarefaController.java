package br.ucsal.bes.tcc.analyzereducation.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ucsal.bes.tcc.analyzereducation.dto.TarefaDTO;
import br.ucsal.bes.tcc.analyzereducation.util.BancoDeDados;

@Controller
@RequestMapping("home")
public class CriarTarefaController {

	@GetMapping("criarTarefa")
	public String criarTarefa(@Valid TarefaDTO tarefaDto, BindingResult result, RedirectAttributes attributes) {

		return "home/criarTarefa";
	}
	
	@GetMapping("voltar")
	public String voltar(Model model) {
		
		model.addAttribute("tarefas", BancoDeDados.obterTarefas());
		return "redirect:/home/tarefas";
	}

}
