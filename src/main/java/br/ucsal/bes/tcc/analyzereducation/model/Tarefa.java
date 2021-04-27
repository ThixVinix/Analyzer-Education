package br.ucsal.bes.tcc.analyzereducation.model;

import java.util.ArrayList;
import java.util.List;

public class Tarefa {

	private Long id;
	private String titulo;
	private String descricao;
	private List<Teste> testes;
	private List<Filtro> filtros;
	private boolean concluido;

	public Tarefa() {
		this.setTestes(new ArrayList<>());
		this.setFiltros(new ArrayList<>());
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

	public boolean isConcluido() {
		return concluido;
	}

	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}

	public List<Teste> getTestes() {
		return testes;
	}

	public void setTestes(List<Teste> testes) {
		this.testes = testes;
	}

	public List<Filtro> getFiltros() {
		return filtros;
	}

	public void setFiltros(List<Filtro> filtros) {
		this.filtros = filtros;
	}

}
