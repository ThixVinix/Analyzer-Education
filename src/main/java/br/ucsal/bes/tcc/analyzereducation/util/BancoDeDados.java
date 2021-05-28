package br.ucsal.bes.tcc.analyzereducation.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.ucsal.bes.tcc.analyzereducation.enums.IntervaloFiltroEnum;
import br.ucsal.bes.tcc.analyzereducation.model.Premissa;
import br.ucsal.bes.tcc.analyzereducation.model.Tarefa;
import br.ucsal.bes.tcc.analyzereducation.model.Teste;

public class BancoDeDados {

	private static List<Tarefa> tarefas = new ArrayList<>();

	private static List<Teste> testes = new ArrayList<>();

	private static List<Premissa> filtros = new ArrayList<>();

	static {
		Tarefa tarefa1 = new Tarefa();
		tarefa1.setId(0L);
		tarefa1.setTitulo("Soma entre dois números");
		tarefa1.setDescricao(
				"Faça um programa que realize a soma de \"A\" + \"B\" e realize a soma de \"C\" + \"D\". Em seguida, exiba o resultado da soma de \"A+B\" na primeira linha, e o resultado da soma de \"C+D\" na segunda linha do console. Caso seja inserido um número decimal, exiba no console a mensagem: \"Não é permitido a inserção de números decimais.\".");
		Teste teste1 = new Teste();
		teste1.setId(1L);
		teste1.setNome("T1");
		teste1.setEntradas("2\n2\n4\n4");
		teste1.setSaidas("4\n8");
		Teste teste2 = new Teste();
		teste2.setId(2L);
		teste2.setNome("T2");
		teste2.setEntradas("3\n3\n5\n5");
		teste2.setSaidas("6\n10");
		Teste teste3 = new Teste();
		teste3.setId(3L);
		teste3.setNome("T3");
		teste3.setEntradas("0.5\n0.5\n7\n5");
		teste3.setSaidas("Não é permitido a inserção de números decimais.");
		Teste teste4 = new Teste();
		teste4.setId(4L);
		teste4.setNome("T4");
		teste4.setEntradas("4\n4\n1\n1");
		teste4.setSaidas("8\n2");
		tarefa1.getTestes().add(teste1);
		tarefa1.getTestes().add(teste2);
		tarefa1.getTestes().add(teste3);
		tarefa1.getTestes().add(teste4);
		Premissa filtro1 = new Premissa();
		filtro1.setId(1L);
		filtro1.setNomeFiltro("Scanner");
		filtro1.setQtdDemandada(1);
		filtro1.setIntervalo(IntervaloFiltroEnum.IGUAL);
		Premissa filtro2 = new Premissa();
		filtro2.setId(2L);
		filtro2.setNomeFiltro("System.out.println");
		filtro2.setQtdDemandada(1);
		filtro2.setIntervalo(IntervaloFiltroEnum.MAIOR_IGUAL);
		Premissa filtro3 = new Premissa();
		filtro3.setId(3L);
		filtro3.setNomeFiltro("try");
		filtro3.setQtdDemandada(1);
		filtro3.setIntervalo(IntervaloFiltroEnum.IGUAL);
		Premissa filtro4 = new Premissa();
		filtro4.setId(4L);
		filtro4.setNomeFiltro("catch");
		filtro4.setQtdDemandada(1);
		filtro4.setIntervalo(IntervaloFiltroEnum.IGUAL);
		Premissa filtro5 = new Premissa();
		filtro5.setId(5L);
		filtro5.setNomeFiltro("public static void main(String[] args) {");
		filtro5.setQtdDemandada(1);
		filtro5.setIntervalo(IntervaloFiltroEnum.IGUAL);
		tarefa1.getFiltros().add(filtro1);
		tarefa1.getFiltros().add(filtro2);
		tarefa1.getFiltros().add(filtro3);
		tarefa1.getFiltros().add(filtro4);
		tarefa1.getFiltros().add(filtro5);

		Tarefa tarefa2 = new Tarefa();
		tarefa2.setId(1L);
		tarefa2.setTitulo("Subtração entre dois números");
		tarefa2.setDescricao("Faça a subtração entre 452 - 267");

		tarefas.add(tarefa1);
		tarefas.add(tarefa2);

		testes.add(teste1);
		testes.add(teste2);
		testes.add(teste3);
		testes.add(teste4);

		filtros.add(filtro1);
		filtros.add(filtro2);
		filtros.add(filtro3);
		filtros.add(filtro4);
		filtros.add(filtro5);

	}

	public static List<Tarefa> obterTarefas() {
		return tarefas;
	}

	public static Tarefa obterTarefa(Long id) {

		return tarefas.get(id.intValue());

	}

	public static void adicionarTarefa(Tarefa tarefa) {
		tarefas.add(tarefa);
	}

	public static void alterarTarefa(Tarefa tarefa) {
		for (var i = 0; i < tarefas.size(); i++) {
			if (tarefas.get(i).getId().equals(tarefa.getId())) {
				tarefas.get(i).setTitulo(tarefa.getTitulo());
				tarefas.get(i).setDescricao(tarefa.getDescricao());
				break;
			}
		}
	}

	public static void deletarTarefa(Long id) {
		for (Tarefa tarefa : tarefas) {
			if (tarefa.getId().equals(id)) {
				tarefas.remove(tarefa);
				break;
			}
		}

//	 tarefas.stream().filter((Tarefa tarefa) -> tarefa.getId().equals(id))
//	 				 .map(tarefa -> tarefas.remove(tarefa)).findAny();
	}

	public static List<Teste> obterTestes() {
		return testes;
	}

	public static Teste obterTeste(Long id) {

		return testes.get(id.intValue() -1);

	}

	public static void adicionarTeste(Teste teste) {
		testes.add(teste);
	}

	public static void alterarTeste(Teste teste) {
		for (var i = 0; i < testes.size(); i++) {
			if (testes.get(i).getId().equals(teste.getId())) {
				testes.get(i).setNome(teste.getNome());
				testes.get(i).setEntradas(teste.getEntradas());
				testes.get(i).setSaidas(teste.getSaidas());
				break;
			}
		}
	}

	public static void deletarTeste(Long id) {
		for (Teste teste : testes) {
			if (teste.getId().equals(id)) {
				testes.remove(teste);
				break;
			}
		}
	}

	public static List<Premissa> obterFiltros() {
		return filtros;
	}

	public static Premissa obterFiltro(Long id) {

		return filtros.get(id.intValue());

	}
	
	public static void adicionarFiltro(Premissa filtro) {
		filtros.add(filtro);
	}
	
	public static void alterarFiltro(Premissa filtro) {
		for (var i = 0; i < filtros.size(); i++) {
			if (filtros.get(i).getId().equals(filtro.getId())) {
				filtros.get(i).setNomeFiltro(filtro.getNomeFiltro());
				filtros.get(i).setIntervalo(filtro.getIntervalo());
				filtros.get(i).setQtdDemandada(filtro.getQtdDemandada());
				break;
			}
		}
	}

	public static void deletarFiltro(Long id) {
		for (Premissa filtro : filtros) {
			if (filtro.getId().equals(id)) {
				filtros.remove(filtro);
				break;
			}
		}
	}



}
