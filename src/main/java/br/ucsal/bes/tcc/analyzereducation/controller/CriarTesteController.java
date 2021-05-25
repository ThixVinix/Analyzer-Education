package br.ucsal.bes.tcc.analyzereducation.controller;

import java.util.Optional;

import javax.validation.Valid;

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
import br.ucsal.bes.tcc.analyzereducation.util.BancoDeDados;
import br.ucsal.bes.tcc.analyzereducation.util.Util;

@Controller
@RequestMapping("home")
public class CriarTesteController {

	@GetMapping("criarTeste")
	public String criarTeste(TesteDTO testeDto, @RequestParam("tarefaId") Optional<Long> tarefaId) {

		if (tarefaId.isEmpty())
			return "redirect:/home/tarefas";

		var tarefas = BancoDeDados.obterTarefas();

		for (Tarefa tarefa : tarefas) {
			if (tarefa.getId().equals(tarefaId.get())) {
				testeDto.setCodgTarefa(tarefaId.get());
				return "home/criarTeste";
			}
		}

		return "redirect:/home/tarefas";
	}

	@PostMapping("novoTeste")
	public String novoTeste(@Valid TesteDTO testeDto, BindingResult result, RedirectAttributes attributes) {

		if (testeDto.getCodgTarefa() == null)
			return "redirect:/home/tarefas";

		var tarefas = BancoDeDados.obterTarefas();
		var existTask = false;

		for (Tarefa tarefa : tarefas) {
			if (tarefa.getId().equals(testeDto.getCodgTarefa())) {
				existTask = true;
				break;
			}
		}

		if (!existTask)
			return "redirect:/home/tarefas";

		if (result.hasErrors())
			return "redirect:/home/criarTeste?tarefaId=" + testeDto.getCodgTarefa();

		var teste = preencherTeste(testeDto);

		BancoDeDados.adicionarTeste(teste);
		
		var tarefa = BancoDeDados.obterTarefa(testeDto.getCodgTarefa());
		tarefa.getTestes().add(teste);

		return "redirect:/home/tarefas";
	}

	private Teste preencherTeste(TesteDTO testeDto) {
		var testes = BancoDeDados.obterTestes();

		Long id = 0L;
		for (var i = 0; i < testes.size(); i++) {
			if (testes.get(i).getId() > id) {
				id = testes.get(i).getId();
			}
		}

		var sbEntradas = Util.removeLeadingAndTrailing(testeDto.getEntradas());
		var sbSaidas = Util.removeLeadingAndTrailing(testeDto.getSaidas());

		var teste = new Teste();
		teste.setId(++id);
		teste.setNome(testeDto.getNome().trim());

		if (sbEntradas.isPresent()) {
			teste.setEntradas(sbEntradas.get().toString());
		} else {
			teste.setEntradas(null);
		}

		if (sbSaidas.isPresent()) {
			teste.setSaidas(sbSaidas.get().toString());
		}
		
		return teste;
	}

}
