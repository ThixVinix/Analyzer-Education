package br.ucsal.bes.tcc.analyzereducation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.ucsal.bes.tcc.analyzereducation.enums.IntervaloFiltroEnum;

@Entity
@Table(name = "premissa")
public class Premissa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NOME_PREMISSA")
	private String nomeFiltro;

	@Column(name = "QTD_DEMANDADA")
	private Integer qtdDemandada;

	@Enumerated(EnumType.STRING)
	@Column(name = "INTERVALO")
	private IntervaloFiltroEnum intervalo;

	@ManyToOne()
	@JoinColumn(name = "codg_tarefa")
	private Tarefa tarefa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeFiltro() {
		return nomeFiltro;
	}

	public void setNomeFiltro(String nomeFiltro) {
		this.nomeFiltro = nomeFiltro;
	}

	public Integer getQtdDemandada() {
		return qtdDemandada;
	}

	public void setQtdDemandada(Integer qtdDemandada) {
		this.qtdDemandada = qtdDemandada;
	}

	public IntervaloFiltroEnum getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(IntervaloFiltroEnum intervalo) {
		this.intervalo = intervalo;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

}
