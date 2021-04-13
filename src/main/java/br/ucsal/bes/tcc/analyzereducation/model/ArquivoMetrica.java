package br.ucsal.bes.tcc.analyzereducation.model;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Path;

public class ArquivoMetrica implements Serializable, Comparable<ArquivoMetrica> {

	private static final long serialVersionUID = 1940808567436724957L;
	private Path caminho;
	private File arquivo;
	private String conteudoCompactado;
	private String conteudoCompleto;
	private String conteudoFormatado;
	private Long qtdLoc;
	private Long qtdMetodos;
	private Long qtdClasses;
	private Long qtdComentarios;
	private Long qtdMetodoDeus;
	private Long qtdClasseDeus;
	private boolean mainMethod;
	
	public String obterNomeArquivo() {
		if (getArquivo() != null) {
			return getArquivo().getName();
		}
		return "";
	}

	public String obterNomeDiretorio() {
		if (getCaminho() != null) {
			return getCaminho().getParent().toString();
		}
		return "";
	}

	public Integer obterQtdDiretoriosPassados() {
		if (getCaminho() != null) {
			return getCaminho().getNameCount();
		}
		return 0;
	}

	public Path getCaminho() {
		return caminho;
	}

	public void setCaminho(Path caminho) {
		this.caminho = caminho;
	}

	public File getArquivo() {
		return arquivo;
	}

	public void setArquivo(File arquivo) {
		this.arquivo = arquivo;
	}

	public String getConteudoCompactado() {
		return conteudoCompactado;
	}

	public void setConteudoCompactado(String conteudoCompactado) {
		this.conteudoCompactado = conteudoCompactado;
	}

	public String getConteudoCompleto() {
		return conteudoCompleto;
	}

	public void setConteudoCompleto(String conteudoCompleto) {
		this.conteudoCompleto = conteudoCompleto;
	}

	public String getConteudoFormatado() {
		return conteudoFormatado;
	}

	public void setConteudoFormatado(String conteudoFormatado) {
		this.conteudoFormatado = conteudoFormatado;
	}

	public Long getQtdLoc() {
		return qtdLoc;
	}

	public void setQtdLoc(Long qtdLoc) {
		this.qtdLoc = qtdLoc;
	}

	public Long getQtdMetodos() {
		return qtdMetodos;
	}

	public void setQtdMetodos(Long qtdMetodos) {
		this.qtdMetodos = qtdMetodos;
	}

	public Long getQtdClasses() {
		return qtdClasses;
	}

	public void setQtdClasses(Long qtdClasses) {
		this.qtdClasses = qtdClasses;
	}

	public Long getQtdComentarios() {
		return qtdComentarios;
	}

	public void setQtdComentarios(Long qtdComentarios) {
		this.qtdComentarios = qtdComentarios;
	}

	public Long getQtdMetodoDeus() {
		return qtdMetodoDeus;
	}

	public void setQtdMetodoDeus(Long qtdMetodoDeus) {
		this.qtdMetodoDeus = qtdMetodoDeus;
	}

	public Long getQtdClasseDeus() {
		return qtdClasseDeus;
	}

	public void setQtdClasseDeus(Long qtdClasseDeus) {
		this.qtdClasseDeus = qtdClasseDeus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arquivo == null) ? 0 : arquivo.hashCode());
		result = prime * result + ((caminho == null) ? 0 : caminho.hashCode());
		result = prime * result + ((conteudoCompactado == null) ? 0 : conteudoCompactado.hashCode());
		result = prime * result + ((conteudoCompleto == null) ? 0 : conteudoCompleto.hashCode());
		result = prime * result + ((conteudoFormatado == null) ? 0 : conteudoFormatado.hashCode());
		result = prime * result + ((qtdClasseDeus == null) ? 0 : qtdClasseDeus.hashCode());
		result = prime * result + ((qtdClasses == null) ? 0 : qtdClasses.hashCode());
		result = prime * result + ((qtdComentarios == null) ? 0 : qtdComentarios.hashCode());
		result = prime * result + ((qtdLoc == null) ? 0 : qtdLoc.hashCode());
		result = prime * result + ((qtdMetodoDeus == null) ? 0 : qtdMetodoDeus.hashCode());
		result = prime * result + ((qtdMetodos == null) ? 0 : qtdMetodos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArquivoMetrica other = (ArquivoMetrica) obj;
		if (arquivo == null) {
			if (other.arquivo != null)
				return false;
		} else if (!arquivo.equals(other.arquivo))
			return false;
		if (caminho == null) {
			if (other.caminho != null)
				return false;
		} else if (!caminho.equals(other.caminho))
			return false;
		if (conteudoCompactado == null) {
			if (other.conteudoCompactado != null)
				return false;
		} else if (!conteudoCompactado.equals(other.conteudoCompactado))
			return false;
		if (conteudoCompleto == null) {
			if (other.conteudoCompleto != null)
				return false;
		} else if (!conteudoCompleto.equals(other.conteudoCompleto))
			return false;
		if (conteudoFormatado == null) {
			if (other.conteudoFormatado != null)
				return false;
		} else if (!conteudoFormatado.equals(other.conteudoFormatado))
			return false;
		if (qtdClasseDeus == null) {
			if (other.qtdClasseDeus != null)
				return false;
		} else if (!qtdClasseDeus.equals(other.qtdClasseDeus))
			return false;
		if (qtdClasses == null) {
			if (other.qtdClasses != null)
				return false;
		} else if (!qtdClasses.equals(other.qtdClasses))
			return false;
		if (qtdComentarios == null) {
			if (other.qtdComentarios != null)
				return false;
		} else if (!qtdComentarios.equals(other.qtdComentarios))
			return false;
		if (qtdLoc == null) {
			if (other.qtdLoc != null)
				return false;
		} else if (!qtdLoc.equals(other.qtdLoc))
			return false;
		if (qtdMetodoDeus == null) {
			if (other.qtdMetodoDeus != null)
				return false;
		} else if (!qtdMetodoDeus.equals(other.qtdMetodoDeus))
			return false;
		if (qtdMetodos == null) {
			if (other.qtdMetodos != null)
				return false;
		} else if (!qtdMetodos.equals(other.qtdMetodos))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Diretório do arquivo:\t" + this.obterNomeDiretorio() + "\nQtd. pastas acessadas:\t"
				+ this.obterQtdDiretoriosPassados() + "\nNome do arquivo:\t" + this.obterNomeArquivo()
				+ "\nQuantidade de linhas:\t" + qtdLoc + "\nQuantidade de classes:\t" + qtdClasses
				+ "\nQuantidade de métodos:\t" + qtdMetodos + "\nQuant. de comentários:\t" + qtdComentarios
				+ "\nQuant. de classes deusas:\t" + qtdClasseDeus + "\nQuant. de métodos deuses:\t" + qtdMetodoDeus;
	}

	@Override
	public int compareTo(ArquivoMetrica o) {
		return 1;
	}

	public boolean containsMainMethod() {
		return mainMethod;
	}


	public void setMainMethod(boolean mainMethod) {
		this.mainMethod = mainMethod;
	}


}
