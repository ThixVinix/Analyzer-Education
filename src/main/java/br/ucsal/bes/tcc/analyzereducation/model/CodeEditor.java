package br.ucsal.bes.tcc.analyzereducation.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.ucsal.bes.tcc.analyzereducation.util.Util;

@Entity
@Table(name = "CODE_EDITOR")
public class CodeEditor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "AUTOR", columnDefinition = "VARCHAR", length = 255, nullable = true)
	private String autor;

	@Column(name = "NOME_ARQUIVO", columnDefinition = "VARCHAR", length = 255, nullable = false)
	private String nomeArquivo;

	@Column(name = "ENTRADA", columnDefinition = "LONGTEXT", nullable = false)
	private String entrada;

	@Column(name = "SAIDA", columnDefinition = "LONGTEXT", nullable = true)
	private String saida;

	@Column(name = "DATA_CRIACAO", columnDefinition = "DATETIME", nullable = true)
	private LocalDate dataCriacao;

	@Column(name = "DATA_ALTERACAO", columnDefinition = "DATETIME", nullable = true)
	private LocalDate dataAlteracao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String obterDataCriacaoFormatada() {
		if (Util.isNotNullOrEmpty(this.dataCriacao)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			return this.dataCriacao.format(formatter);
		}
		return "";
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDate getDataAlteracao() {
		return dataAlteracao;
	}

	public String obterDataAlteracaoFormatada() {
		if (Util.isNotNullOrEmpty(this.dataAlteracao)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			return this.dataAlteracao.format(formatter);
		}
		return "";
	}

	public void setDataAlteracao(LocalDate dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

}
