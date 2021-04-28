package br.ucsal.bes.tcc.analyzereducation.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;

import br.ucsal.bes.tcc.analyzereducation.model.ArquivoMetrica;
import br.ucsal.bes.tcc.analyzereducation.model.CodeEditor;
import br.ucsal.bes.tcc.analyzereducation.model.ResultadoTeste;
import br.ucsal.bes.tcc.analyzereducation.model.Teste;
import br.ucsal.bes.tcc.analyzereducation.util.Util;

public class CodeEditorDTO {

	private Long id;
	 
	private String autor;

	private String nomeArquivo;

	@NotBlank // NotBlank.codeEditor.entrada
	private String entrada;

	private String saida;

	private LocalDate dataCriacao;

	private LocalDate dataAlteracao;
		
	private List<String> saidasTesteObtidas;
	
	private List<String> saidasTesteEsperadas;
	
	private List<Boolean> resultadosTestes;
	
	private List<Boolean> resultadosFiltros;
	
	private List<Teste> testes;
	
	private Map<Teste, ResultadoTeste> mapResultTest;
	
	private Integer percentualAcerto;
	
	private String titulo;
	
	private String descricao;
	
	private ArquivoMetrica arquivoMetrica; 

	public CodeEditorDTO() {
		setSaidasTesteObtidas(new ArrayList<>());
		setSaidasTesteEsperadas(new ArrayList<>());
		setResultadosTestes(new ArrayList<>());
		setResultadosFiltros(new ArrayList<>());
		setMapResultTest(new HashMap<>());
	}
	
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public String getSaida() {
		return saida;
	}

	public void setSaida(String saida) {
		this.saida = saida;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDate getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDate dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	
	public List<String> getSaidasTesteObtidas() {
		return saidasTesteObtidas;
	}

	public void setSaidasTesteObtidas(List<String> saidasTesteObtidas) {
		this.saidasTesteObtidas = saidasTesteObtidas;
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

	public List<String> getSaidasTesteEsperadas() {
		return saidasTesteEsperadas;
	}

	public void setSaidasTesteEsperadas(List<String> saidasTesteEsperadas) {
		this.saidasTesteEsperadas = saidasTesteEsperadas;
	}

	public CodeEditor toCodeEditor() {
		CodeEditor codeEditor = new CodeEditor();

		if (Util.isNotNullOrEmpty(this.getAutor()))
			codeEditor.setAutor(this.getAutor());

		codeEditor.setNomeArquivo(this.getAutor());
		codeEditor.setEntrada(this.getEntrada());
		codeEditor.setSaida(this.getSaida());

		if (Util.isNotNullOrEmpty(this.getDataCriacao()))
			codeEditor.setDataCriacao(this.getDataCriacao());

		if (Util.isNotNullOrEmpty(this.getDataAlteracao()))
			codeEditor.setDataAlteracao(this.getDataAlteracao());

		return codeEditor;
	}

	public List<Teste> getTestes() {
		return testes;
	}

	public void setTestes(List<Teste> testes) {
		this.testes = testes;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map<Teste, ResultadoTeste> getMapResultTest() {
		return mapResultTest;
	}

	public void setMapResultTest(Map<Teste, ResultadoTeste> mapResultTest) {
		this.mapResultTest = mapResultTest;
	}

	public Integer getPercentualAcerto() {
		return percentualAcerto;
	}

	public void setPercentualAcerto(Integer percentualAcerto) {
		this.percentualAcerto = percentualAcerto;
	}

}
