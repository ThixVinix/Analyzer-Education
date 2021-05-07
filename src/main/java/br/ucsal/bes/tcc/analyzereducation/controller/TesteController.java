package br.ucsal.bes.tcc.analyzereducation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ucsal.bes.tcc.analyzereducation.util.BancoDeDados;

@Controller
@RequestMapping("home")
public class TesteController {

	
	@GetMapping("/testes")
	public String testes(Model model) {

		model.addAttribute("testes", BancoDeDados.obterTestes());
		return "home/testes";

	}
	
}
