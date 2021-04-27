package br.ucsal.bes.tcc.analyzereducation.model;

import java.util.List;

public class Atividade {

	private Long id;
	private String titulo;
	private String descricao;
	private List<Tarefa> tarefas;
	private String porcentagemConclusao;
	private boolean concluido;

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

	public String getPorcentagemConclusao() {
		return porcentagemConclusao;
	}

	public void setPorcentagemConclusao(String porcentagemConclusao) {
		this.porcentagemConclusao = porcentagemConclusao;
	}

	public boolean isConcluido() {
		return concluido;
	}

	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}

}
