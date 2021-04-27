package br.ucsal.bes.tcc.analyzereducation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ucsal.bes.tcc.analyzereducation.model.Atividade;
import br.ucsal.bes.tcc.analyzereducation.model.Autor;
import br.ucsal.bes.tcc.analyzereducation.model.Tarefa;
import br.ucsal.bes.tcc.analyzereducation.util.BancoDeDados;

@Controller
@RequestMapping("home")
public class AtividadeController {

	private Autor autor;

	@GetMapping("/atividades")
	public String atividades(Model model) {

		carregarRegistros();

		model.addAttribute("atividades", getAutor().getAtividades());

		return "home/atividades";
	}

	@GetMapping("/tarefas")
	public String tarefas(Model model) {

		model.addAttribute("tarefas", BancoDeDados.obterTarefas());
		return "home/tarefas";

	}

//	@RequestMapping(value="/atividades", method = RequestMethod.GET)
//	public String getItem(@RequestParam("id") Optional<Long> id, Tarefa tarefa ) { 
//		carregarRegistros();
//		
//	
//		
//		if( id.isPresent()){
//	      getAutor().getAtividades().get(id);
//			
//			System.out.println(id.get()); 
//	                    
//	    }
//	    return "home/atividades";
//	}

	private void carregarRegistros() {
		Tarefa tarefa1 = new Tarefa();
		tarefa1.setId(1L);
		tarefa1.setTitulo("Soma entre dois números");
		tarefa1.setDescricao("Faça a soma entre 452 + 267");
		// tarefa1.setResultadoEsperado("719");
		tarefa1.setConcluido(false);
		Tarefa tarefa2 = new Tarefa();
		tarefa2.setId(2L);
		tarefa2.setTitulo("Subtração entre dois números");
		tarefa2.setDescricao("Faça a subtração entre 452 - 267");
		// tarefa2.setResultadoEsperado("185");
		tarefa2.setConcluido(false);
		Tarefa tarefa3 = new Tarefa();
		tarefa3.setId(3L);
		tarefa3.setTitulo("Multiplicação entre dois números");
		tarefa3.setDescricao("Faça a multiplicação entre 5 x 5");
		// tarefa3.setResultadoEsperado("25");
		tarefa3.setConcluido(false);
		Tarefa tarefa4 = new Tarefa();
		tarefa4.setId(4L);
		tarefa4.setTitulo("Divisão entre dois números");
		tarefa4.setDescricao("Faça a divisão entre 25 / 5");
		// tarefa4.setResultadoEsperado("5");
		tarefa4.setConcluido(false);
		Atividade atividade1 = new Atividade();
		atividade1.setId(1L);
		atividade1.setTitulo("Soma e Subtração");
		atividade1.setDescricao("Realize as operações de soma e subtração");
		List<Tarefa> tarefas1 = new ArrayList<>();
		tarefas1.add(tarefa1);
		tarefas1.add(tarefa2);
		atividade1.setTarefas(tarefas1);
		atividade1.setConcluido(false);
		Atividade atividade2 = new Atividade();
		atividade2.setId(2L);
		atividade2.setTitulo("Multiplicação e Divisão");
		atividade2.setDescricao("Realize as operações de multiplicação e divisão");
		List<Tarefa> tarefas2 = new ArrayList<>();
		tarefas2.add(tarefa3);
		tarefas2.add(tarefa4);
		atividade2.setTarefas(tarefas2);
		atividade2.setConcluido(false);
		setAutor(new Autor());
		getAutor().setId(1L);
		getAutor().setNome("João");
		List<Atividade> atividades = new ArrayList<>();
		atividades.add(atividade1);
		atividades.add(atividade2);
		getAutor().setAtividades(atividades);
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

}
