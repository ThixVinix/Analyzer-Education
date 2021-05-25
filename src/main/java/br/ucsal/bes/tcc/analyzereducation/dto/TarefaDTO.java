package br.ucsal.bes.tcc.analyzereducation.dto;

import java.util.ArrayList;
import java.util.List;

import br.ucsal.bes.tcc.analyzereducation.model.Tarefa;

public class TarefaDTO {

	private Long id;

	private String titulo;

	private String descricao;
	
	private List<Tarefa> tarefas;

	public TarefaDTO() {
		tarefas = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

}
