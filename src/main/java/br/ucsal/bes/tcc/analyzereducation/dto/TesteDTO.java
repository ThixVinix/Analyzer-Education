package br.ucsal.bes.tcc.analyzereducation.dto;

import java.util.ArrayList;
import java.util.List;

import br.ucsal.bes.tcc.analyzereducation.model.Teste;

public class TesteDTO {

	private Long id;
	
	private String nome;

	private String entradas;

	private String saidas;
	
	private List<Teste> testes;
	
	private Long codgTarefa;
	
	public TesteDTO() {
		testes = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEntradas() {
		return entradas;
	}

	public void setEntradas(String entradas) {
		this.entradas = entradas;
	}

	public String getSaidas() {
		return saidas;
	}

	public void setSaidas(String saidas) {
		this.saidas = saidas;
	}

	public Long getCodgTarefa() {
		return codgTarefa;
	}

	public void setCodgTarefa(Long codgTarefa) {
		this.codgTarefa = codgTarefa;
	}

	public List<Teste> getTestes() {
		return testes;
	}

	public void setTestes(List<Teste> testes) {
		this.testes = testes;
	}

}
