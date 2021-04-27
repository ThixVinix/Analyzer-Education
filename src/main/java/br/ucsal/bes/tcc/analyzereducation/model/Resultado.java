package br.ucsal.bes.tcc.analyzereducation.model;

import java.util.ArrayList;
import java.util.List;

public class Resultado {

	private List<String> saidasObtidas;
	
	private List<Boolean> resultadosTestes;
	
	private List<Boolean> resultadosFiltros;
	
	private ArquivoMetrica arquivoMetrica;

	public Resultado() {
		setSaidasObtidas(new ArrayList<>());
		setResultadosTestes(new ArrayList<>());
		setResultadosFiltros(new ArrayList<>());
	}

	public List<String> getSaidasObtidas() {
		return saidasObtidas;
	}

	public void setSaidasObtidas(List<String> saidasObtidas) {
		this.saidasObtidas = saidasObtidas;
	}

	public List<Boolean> getResultadosTestes() {
		return resultadosTestes;
	}

	public void setResultadosTestes(List<Boolean> resultadosTestes) {
		this.resultadosTestes = resultadosTestes;
	}

	public List<Boolean> getResultadosFiltros() {
		return resultadosFiltros;
	}

	public void setResultadosFiltros(List<Boolean> resultadosFiltros) {
		this.resultadosFiltros = resultadosFiltros;
	}

	public ArquivoMetrica getArquivoMetrica() {
		return arquivoMetrica;
	}

	public void setArquivoMetrica(ArquivoMetrica arquivoMetrica) {
		this.arquivoMetrica = arquivoMetrica;
	}



	
	
}
