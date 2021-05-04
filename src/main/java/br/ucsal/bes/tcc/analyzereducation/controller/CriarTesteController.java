package br.ucsal.bes.tcc.analyzereducation.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ucsal.bes.tcc.analyzereducation.dto.TesteDTO;

@Controller
@RequestMapping("home")
public class CriarTesteController {

	@GetMapping("criarTeste")
	public String criarTeste(@Valid TesteDTO testeDto, BindingResult result, RedirectAttributes attributes) {

		return "home/criarTeste";
	}

}
