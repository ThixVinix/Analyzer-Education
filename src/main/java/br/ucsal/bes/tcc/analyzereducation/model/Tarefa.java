package br.ucsal.bes.tcc.analyzereducation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tarefa")
public class Tarefa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "TITULO")
	private String titulo;

	@Column(name = "DESCRICAO")
	private String descricao;

	@OneToMany(mappedBy = "tarefa")
	private List<Teste> testes;

	@OneToMany(mappedBy = "tarefa")
	private List<Premissa> filtros;

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

	public List<Teste> getTestes() {
		return testes;
	}

	public void setTestes(List<Teste> testes) {
		this.testes = testes;
	}

	public List<Premissa> getFiltros() {
		return filtros;
	}

	public void setFiltros(List<Premissa> filtros) {
		this.filtros = filtros;
	}

}
