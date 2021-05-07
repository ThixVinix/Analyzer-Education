package br.ucsal.bes.tcc.analyzereducation.util;

import java.util.ArrayList;
import java.util.List;

import br.ucsal.bes.tcc.analyzereducation.enums.IntervaloFiltroEnum;
import br.ucsal.bes.tcc.analyzereducation.model.Filtro;
import br.ucsal.bes.tcc.analyzereducation.model.Tarefa;
import br.ucsal.bes.tcc.analyzereducation.model.Teste;

public class BancoDeDados {

	private static List<Tarefa> tarefas = new ArrayList<>();

	private static List<Teste> testes = new ArrayList<>();
	
	private static List<Filtro> filtros = new ArrayList<>();

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
		Filtro filtro1 = new Filtro();
		filtro1.setId(1L);
		filtro1.setNomeFiltro("Scanner");
		filtro1.setQtdDemandada(1);
		filtro1.setIntervalo(IntervaloFiltroEnum.IGUAL);
		Filtro filtro2 = new Filtro();
		filtro2.setId(2L);
		filtro2.setNomeFiltro("System.out.println");
		filtro2.setQtdDemandada(1);
		filtro2.setIntervalo(IntervaloFiltroEnum.MAIOR_IGUAL);
		Filtro filtro3 = new Filtro();
		filtro3.setId(3L);
		filtro3.setNomeFiltro("try");
		filtro3.setQtdDemandada(1);
		filtro3.setIntervalo(IntervaloFiltroEnum.IGUAL);
		Filtro filtro4 = new Filtro();
		filtro4.setId(4L);
		filtro4.setNomeFiltro("catch");
		filtro4.setQtdDemandada(1);
		filtro4.setIntervalo(IntervaloFiltroEnum.IGUAL);
		Filtro filtro5 = new Filtro();
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
	
	public static List<Teste> obterTestes() {
		return testes;
	}
	
	public static Teste obterTeste(Long id) {

		return testes.get(id.intValue());

	}
	
	public static List<Filtro> obterFiltros() {
		return filtros;
	}
	
	public static Filtro obterFiltro(Long id) {

		return filtros.get(id.intValue());

	}

}
