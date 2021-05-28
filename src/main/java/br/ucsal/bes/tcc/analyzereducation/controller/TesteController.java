package br.ucsal.bes.tcc.analyzereducation.controller;

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

import br.ucsal.bes.tcc.analyzereducation.dto.TesteDTO;
import br.ucsal.bes.tcc.analyzereducation.model.Tarefa;
import br.ucsal.bes.tcc.analyzereducation.model.Teste;
import br.ucsal.bes.tcc.analyzereducation.repository.TarefaRepository;
import br.ucsal.bes.tcc.analyzereducation.repository.TesteRepository;
import br.ucsal.bes.tcc.analyzereducation.util.Util;

@Controller
@RequestMapping("home")
public class TesteController {

	@Autowired
	private TarefaRepository tarefaRepository;

	@Autowired
	private TesteRepository testeRepository;

	@GetMapping("testes")
	public String testes(TesteDTO testeDto, @RequestParam("tarefaId") Optional<Long> tarefaId) {

		if (tarefaId.isEmpty())
			return "redirect:/home/tarefas";

		var tarefa = tarefaRepository.findById(tarefaId.get());

		if (tarefa.isEmpty())
			return "redirect:/home/tarefas";

		testeDto.setCodgTarefa(tarefa.get().getId());

		var testes = testeRepository.findByTarefa(tarefa.get());

		testeDto.getTestes().addAll(testes);

		return "home/testes";
	}

	@GetMapping("/testes/editarTeste")
	public String editarTeste(TesteDTO testeDto, @RequestParam("testeId") Optional<Long> testeId) {

		if (testeId.isEmpty())
			return "redirect:/home/tarefas";

		var teste = testeRepository.findById(testeId.get());

		if (teste.isEmpty())
			return "redirect:/home/tarefas";

		if (teste.get().getTarefa().getId() == null)
			return "redirect:/home/tarefas";

		var tarefa = tarefaRepository.findById(teste.get().getTarefa().getId());

		if (tarefa.isEmpty())
			return "redirect:/home/tarefas";

		testeDto.setId(teste.get().getId());
		testeDto.setNome(teste.get().getNome());
		testeDto.setEntradas(teste.get().getEntradas());
		testeDto.setSaidas(teste.get().getSaidas());
		testeDto.setCodgTarefa(teste.get().getTarefa().getId());

		return "home/testes/editarTeste";
	}

	@PostMapping("testes/editado")
	public String editado(@Valid TesteDTO testeDto, BindingResult result, RedirectAttributes attributes) {

		if (testeDto.getId() == null)
			return "redirect:/home/tarefas";

		var teste = testeRepository.findById(testeDto.getId());

		if (teste.isEmpty())
			return "redirect:/home/tarefas";

		if (teste.get().getTarefa().getId() == null)
			return "redirect:/home/tarefas";

		var tarefa = tarefaRepository.findById(teste.get().getTarefa().getId());

		if (tarefa.isEmpty())
			return "redirect:/home/tarefas";

		if (result.hasErrors())
			return "redirect:/home/testes/editarTeste?testeId=" + testeDto.getId();

		var testeEditado = preencherTeste(testeDto, tarefa);

		testeRepository.save(testeEditado);

		return "redirect:/home/testes?tarefaId=" + testeDto.getCodgTarefa();
	}

	private Teste preencherTeste(TesteDTO testeDto, Optional<Tarefa> tarefa) {
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
		} else {
			teste.setSaidas(null);
		}
		
		if (tarefa.isPresent()) {
			teste.setTarefa(tarefa.get());
		}

		return teste;
	}

	@GetMapping("/testes/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, TesteDTO testeDto) {

		var teste = testeRepository.findById(id);

		if (teste.isPresent()) {
			var codgTarefa = teste.get().getTarefa().getId();
			testeRepository.delete(teste.get());
			return "redirect:/home/testes?tarefaId=" + codgTarefa;
		}

		return "redirect:/home/tarefas";
	}

}
