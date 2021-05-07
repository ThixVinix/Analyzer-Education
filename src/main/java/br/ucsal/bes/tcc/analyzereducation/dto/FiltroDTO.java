package br.ucsal.bes.tcc.analyzereducation.dto;

import br.ucsal.bes.tcc.analyzereducation.enums.IntervaloFiltroEnum;

public class FiltroDTO {

	private String titulo;

	private Integer qtdDemandada;

	private IntervaloFiltroEnum intervalo;

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

}
