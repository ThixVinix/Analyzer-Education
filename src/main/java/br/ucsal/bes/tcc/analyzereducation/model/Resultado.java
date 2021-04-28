package br.ucsal.bes.tcc.analyzereducation.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Resultado {

	private List<String> saidasObtidas;
	
	private List<Boolean> resultadosTestes;
	
	private List<Boolean> resultadosFiltros;
	
	private List<ArquivoMetrica> arquivosMetrica;
	
	private Map<Teste, ResultadoTeste> mapResultTest;

	public Resultado() {
		setSaidasObtidas(new ArrayList<>());
		setResultadosTestes(new ArrayList<>());
		setResultadosFiltros(new ArrayList<>());
		setArquivosMetrica(new ArrayList<>());
		setMapResultTest(new HashMap<>());
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

	public List<ArquivoMetrica> getArquivosMetrica() {
		return arquivosMetrica;
	}

	public void setArquivosMetrica(List<ArquivoMetrica> arquivosMetrica) {
		this.arquivosMetrica = arquivosMetrica;
	}

	public Map<Teste, ResultadoTeste> getMapResultTest() {
		return mapResultTest;
	}

	public void setMapResultTest(Map<Teste, ResultadoTeste> mapResultTest) {
		this.mapResultTest = mapResultTest;
	}
	
}
