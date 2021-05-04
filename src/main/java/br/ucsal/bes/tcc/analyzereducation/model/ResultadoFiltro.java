package br.ucsal.bes.tcc.analyzereducation.model;

public class ResultadoFiltro {

	private Integer qtdUtilizacoes;
	
	private Boolean usedCorrectly;

	public ResultadoFiltro(Integer qtdUtilizacoes, Boolean usedCorrectly) {
		super();
		this.qtdUtilizacoes = qtdUtilizacoes;
		this.usedCorrectly = usedCorrectly;
	}

	public Integer getQtdUtilizacoes() {
		return qtdUtilizacoes;
	}

	public void setQtdUtilizacoes(Integer qtdUtilizacoes) {
		this.qtdUtilizacoes = qtdUtilizacoes;
	}

	public Boolean getUsedCorrectly() {
		return usedCorrectly;
	}

	public void setUsedCorrectly(Boolean usedCorrectly) {
		this.usedCorrectly = usedCorrectly;
	}
	
}
