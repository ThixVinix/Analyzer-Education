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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ucsal.bes.tcc.analyzereducation.dto.TesteDTO;
import br.ucsal.bes.tcc.analyzereducation.model.Tarefa;
import br.ucsal.bes.tcc.analyzereducation.model.Teste;
import br.ucsal.bes.tcc.analyzereducation.util.BancoDeDados;
import br.ucsal.bes.tcc.analyzereducation.util.Util;

@Controller
@RequestMapping("home")
public class TesteController {

//	@GetMapping("testes")
//	public String testes1(Model model) {
//
//		model.addAttribute("testes", BancoDeDados.obterTestes());
//		return "home/testes";
//
//	}

	@GetMapping("testes")
	public String testes(TesteDTO testeDto, @RequestParam("tarefaId") Optional<Long> tarefaId) {

		if (tarefaId.isEmpty())
			return "redirect:/home/tarefas";

		var tarefas = BancoDeDados.obterTarefas();
		var existTask = false;

		for (Tarefa tarefa : tarefas) {
			if (tarefa.getId().equals(tarefaId.get())) {
				existTask = true;
				break;
			}
		}

		if (!existTask)
			return "redirect:/home/tarefas";

		var tarefa = BancoDeDados.obterTarefa(tarefaId.get());

		testeDto.setCodgTarefa(tarefa.getId());
		testeDto.getTestes().addAll(tarefa.getTestes());

		return "home/testes";
	}

	@GetMapping("/testes/editarTeste")
	public String editarTeste(TesteDTO testeDto, @RequestParam("testeId") Optional<Long> testeId) {

		if (testeId.isEmpty())
			return "redirect:/home/tarefas";

		var testes = BancoDeDados.obterTestes();

		for (Teste teste : testes) {
			if (teste.getId().equals(testeId.get())) {
				testeDto.setId(teste.getId());
				testeDto.setNome(teste.getNome());
				testeDto.setEntradas(teste.getEntradas());
				testeDto.setSaidas(teste.getSaidas());
				return "home/testes/editarTeste";
			}
		}

		return "redirect:/home/tarefas";
	}

	@PostMapping("testes/editado")
	public String editado(@Valid TesteDTO testeDto, BindingResult result, RedirectAttributes attributes) {

//		if (testeDto.getId() == null)
//			return "redirect:/home/testes?tarefaId=" + testeDto.getCodgTarefa();

		var tarefas = BancoDeDados.obterTarefas();
		var existTask = false;

		for (Tarefa tarefa : tarefas) {
			if (tarefa.getId().equals(testeDto.getId())) {
				existTask = true;
				break;
			}
		}

//		if (!existTask)
//			return "redirect:/home/testes?tarefaId=" + testeDto.getCodgTarefa();

		if (result.hasErrors())
			return "redirect:/home/testes/editarTeste?testeId=" + testeDto.getId();

		var teste = preencherTeste(testeDto);

		BancoDeDados.alterarTeste(teste);

//		return "redirect:/home/testes?tarefaId=" + testeDto.getCodgTarefa();
		return "redirect:/home/tarefas";
	}

	private Teste preencherTeste(TesteDTO testeDto) {
		var sbEntradas = Util.removeLeadingAndTrailing(testeDto.getEntradas());
		var sbSaidas = Util.removeLeadingAndTrailing(testeDto.getSaidas());

		var teste = new Teste();
		teste.setId(testeDto.getId());
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

	@GetMapping("/testes/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, TesteDTO testeDto) {

		var tarefas = BancoDeDados.obterTarefas();
		var testes = BancoDeDados.obterTestes();

		var found = false;

		for (var i = 0; i < tarefas.size(); i++) {

			if (found)
				break;

			for (var j = 0; j < testes.size(); j++) {
				if (tarefas.get(i).getTestes().get(j).getId().equals(id)) {
					var teste = BancoDeDados.obterTeste(id);
					tarefas.get(i).getTestes().remove(teste);
					BancoDeDados.deletarTeste(id);
					found = true;
					break;
				}
			}

		}

		// tarefaDto.getTarefas().addAll(BancoDeDados.obterTarefas());
		return "redirect:/home/tarefas";
	}

}
