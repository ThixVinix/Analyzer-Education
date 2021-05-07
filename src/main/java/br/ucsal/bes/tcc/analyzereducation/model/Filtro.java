package br.ucsal.bes.tcc.analyzereducation.model;

import br.ucsal.bes.tcc.analyzereducation.enums.IntervaloFiltroEnum;

public class Filtro {

	private Long id;

	private String nomeFiltro;

	private Integer qtdDemandada;
	
	private IntervaloFiltroEnum intervalo;

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

}
