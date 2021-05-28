package br.ucsal.bes.tcc.analyzereducation.model;

import java.text.DecimalFormat;
import java.util.List;

import br.ucsal.bes.tcc.analyzereducation.util.Util;

public class Autor {

	private Long id;
	private String nome;
	private List<Atividade> atividades;

	public void verificarConclusaoAtividade(Atividade atividade) {
		if (atividade != null && !atividade.getTarefas().isEmpty()) {
			int totalTarefas = atividade.getTarefas().size();
			int qtdTarefasConcluidas = 0;
			for (int i = 0; i < totalTarefas; i++) {
//				if (atividade.getTarefas().get(i).isConcluido()) {
//					qtdTarefasConcluidas++;
//				}
			}

			if (qtdTarefasConcluidas == totalTarefas) {
				atividade.setConcluido(true);
			} else {
				atividade.setConcluido(false);
			}

			double percentualConclusao = (qtdTarefasConcluidas / totalTarefas);
			obterPorcentagemAtividade(atividade, percentualConclusao);

		}

	}

	private void obterPorcentagemAtividade(Atividade atividade, double percentualConclusao) {
		String porcentagem;

		if (Util.isNotNullOrEmpty(atividade.getPorcentagemConclusao())) {
			DecimalFormat df = new DecimalFormat("#%");
			porcentagem = df.format(percentualConclusao * 1.0);
		} else {
			porcentagem = "0%";
		}

		atividade.setPorcentagemConclusao(porcentagem);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

}
