package br.ucsal.bes.tcc.analyzereducation.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import br.ucsal.bes.tcc.analyzereducation.model.CodeEditor;
import br.ucsal.bes.tcc.analyzereducation.util.Util;

public class CodeEditorDTO {

	private String autor;

	private String nomeArquivo;

	@NotBlank // NotBlank.codeEditor.entrada
	private String entrada;

	private String saida;

	private LocalDate dataCriacao;

	private LocalDate dataAlteracao;

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

}
