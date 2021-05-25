package br.ucsal.bes.tcc.analyzereducation.dto;

import java.util.ArrayList;
import java.util.List;

import br.ucsal.bes.tcc.analyzereducation.enums.IntervaloFiltroEnum;
import br.ucsal.bes.tcc.analyzereducation.model.Filtro;

public class FiltroDTO {

	private Long id;
	
	private String titulo;

	private Integer qtdDemandada;

	private IntervaloFiltroEnum intervalo;
	
	private List<Filtro> premissas;
	
	private Long codgTarefa;
	
	public FiltroDTO() {
		premissas = new ArrayList<>();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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

	public Long getCodgTarefa() {
		return codgTarefa;
	}

	public void setCodgTarefa(Long codgTarefa) {
		this.codgTarefa = codgTarefa;
	}

	public List<Filtro> getPremissas() {
		return premissas;
	}

	public void setPremissas(List<Filtro> premissas) {
		this.premissas = premissas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
