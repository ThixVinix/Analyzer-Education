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

import br.ucsal.bes.tcc.analyzereducation.dto.TesteDTO;
import br.ucsal.bes.tcc.analyzereducation.model.Tarefa;
import br.ucsal.bes.tcc.analyzereducation.model.Teste;
import br.ucsal.bes.tcc.analyzereducation.repository.TarefaRepository;
import br.ucsal.bes.tcc.analyzereducation.repository.TesteRepository;
import br.ucsal.bes.tcc.analyzereducation.util.Util;

@Controller
@RequestMapping("home")
public class CriarTesteController {

	@Autowired
	private TarefaRepository tarefaRepository;

	@Autowired
	private TesteRepository testeRepository;

	@GetMapping("criarTeste")
	public String criarTeste(TesteDTO testeDto, @RequestParam("tarefaId") Optional<Long> tarefaId) {

		if (tarefaId.isEmpty())
			return "redirect:/home/tarefas";

		var tarefa = tarefaRepository.findById(tarefaId.get());

		if (tarefa.isEmpty())
			return "redirect:/home/tarefas";

		testeDto.setCodgTarefa(tarefa.get().getId());

		return "home/criarTeste";
	}

	@PostMapping("novoTeste")
	public String novoTeste(@Valid TesteDTO testeDto, BindingResult result, RedirectAttributes attributes) {

		if (testeDto.getCodgTarefa() == null)
			return "redirect:/home/tarefas";

		Optional<Tarefa> tarefa = tarefaRepository.findById(testeDto.getCodgTarefa());

		if (tarefa.isEmpty())
			return "redirect:/home/tarefas";

		if (result.hasErrors())
			return "redirect:/home/criarTeste?tarefaId=" + testeDto.getCodgTarefa();

		var teste = preencherTeste(testeDto, tarefa);

		testeRepository.save(teste);
		// home/testes?tarefaId=7
		return "redirect:/home/testes?tarefaId=" + testeDto.getCodgTarefa();
//		return "redirect:/home/tarefas";
	}

	private Teste preencherTeste(TesteDTO testeDto, Optional<Tarefa> tarefa) {

		var sbEntradas = Util.removeLeadingAndTrailing(testeDto.getEntradas());
		var sbSaidas = Util.removeLeadingAndTrailing(testeDto.getSaidas());

		var teste = new Teste();
		teste.setNome(testeDto.getNome().trim());

		if (sbEntradas.isPresent()) {
			teste.setEntradas(sbEntradas.get().toString());
		} else {
			teste.setEntradas(null);
		}

		if (sbSaidas.isPresent()) {
			teste.setSaidas(sbSaidas.get().toString());
		}

		if (tarefa.isPresent()) {
			teste.setTarefa(tarefa.get());
		}

		return teste;
	}

}
