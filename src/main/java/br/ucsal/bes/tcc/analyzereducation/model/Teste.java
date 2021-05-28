package br.ucsal.bes.tcc.analyzereducation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "teste")
public class Teste {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NOME_TESTE")
	private String nome;

	@Column(name = "ENTRADAS")
	private String entradas;

	@Column(name = "SAIDAS")
	private String saidas;

	@ManyToOne()
	@JoinColumn(name = "codg_tarefa")
	private Tarefa tarefa;

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

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

}
