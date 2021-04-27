package br.ucsal.bes.tcc.analyzereducation.analyzer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.ucsal.bes.tcc.analyzereducation.abstracts.AbstractArquivoMetrica;
import br.ucsal.bes.tcc.analyzereducation.enums.ValidacaoArquivoEnum;
import br.ucsal.bes.tcc.analyzereducation.model.ArquivoMetrica;
import br.ucsal.bes.tcc.analyzereducation.model.Resultado;
import br.ucsal.bes.tcc.analyzereducation.model.Tarefa;
import br.ucsal.bes.tcc.analyzereducation.model.Teste;
import br.ucsal.bes.tcc.analyzereducation.util.Constante;
import br.ucsal.bes.tcc.analyzereducation.util.DirectoryUtil;
import br.ucsal.bes.tcc.analyzereducation.util.Util;

public class JavaCode extends AbstractArquivoMetrica {

	private static final Logger LOGGER = LogManager.getLogger(JavaCode.class.getName());

	private static final String MESSAGE_STARTED_ANALYZE = "Iniciando análise das métricas do arquivo: \"%s\"...";

	private static final String DIRETORIO_NOME_ARQUIVO = "C:\\Users\\Public\\Documents\\DefaultName";

	private static final String DIRETORIO = "C:\\Users\\Public\\Documents";

	private File fileJava;

	private Map<ArquivoMetrica, Boolean> filesJava;

	private StringBuilder output;

	public JavaCode() {
		super();
		setFilesJava(new HashMap<>());
		setOutput(new StringBuilder());
	}

	public ValidacaoArquivoEnum validarDiretorio(String diretorioString) {
		LOGGER.info("Validando campo do diretório...");

		if (Util.isNullOrEmpty(diretorioString)) {
			LOGGER.info(Constante.PREENCHIMENTO_OBRIGATORIO_DIRETORIO);
			return ValidacaoArquivoEnum.PREENCHIMENTO_OBRIGATORIO_DIRETORIO;
		}

		Path caminho = DirectoryUtil.obterPathPeloDiretorioString(diretorioString.trim());

		if (Util.isNullOrEmpty(caminho)) {
			LOGGER.error(Constante.ERRO_CONVERSAO_DIRETORIO);
			return ValidacaoArquivoEnum.ERRO_CONVERSAO_DIRETORIO;
		}
		if (!DirectoryUtil.validarDiretorioCompleto(caminho)) {
			LOGGER.info(Constante.DIRETORIO_NAO_ENCONTRADO);
			return ValidacaoArquivoEnum.DIRETORIO_NAO_ENCONTRADO;
		}

		if (!DirectoryUtil.isJavaFile(caminho.toFile())) {
			LOGGER.info(Constante.TIPO_ARQUIVO_INCORRETO);
			return ValidacaoArquivoEnum.TIPO_ARQUIVO_INCORRETO;
		}

		LOGGER.info("Validação do diretório efetuada com sucesso.");
		return ValidacaoArquivoEnum.SUCESSO;
	}

	public ValidacaoArquivoEnum validarCamposEntidadesDeusas(String qtdMinMetodoDeusString,
			String qtdMinClasseDeusString) {
		LOGGER.info("Validando campos das entidades deusas...");

		if (Util.isNullOrEmpty(qtdMinMetodoDeusString)) {
			LOGGER.info(Constante.PREENCHIMENTO_OBRIGATORIO_METODO_DEUS);
			return ValidacaoArquivoEnum.PREENCHIMENTO_OBRIGATORIO_METODO_DEUS;
		}

		if (Util.isNullOrEmpty(qtdMinClasseDeusString)) {
			LOGGER.info(Constante.PREENCHIMENTO_OBRIGATORIO_CLASSE_DEUS);
			return ValidacaoArquivoEnum.PREENCHIMENTO_OBRIGATORIO_CLASSE_DEUS;
		}

		if (!Util.isNumerico(qtdMinMetodoDeusString.trim())) {
			LOGGER.info(Constante.PREENCHIMENTO_INCORRETO_METODO_DEUS);
			return ValidacaoArquivoEnum.PREENCHIMENTO_INCORRETO_METODO_DEUS;
		}

		if (!Util.isNumerico(qtdMinClasseDeusString.trim())) {
			LOGGER.info(Constante.PREENCHIMENTO_INCORRETO_CLASSE_DEUS);
			return ValidacaoArquivoEnum.PREENCHIMENTO_INCORRETO_CLASSE_DEUS;
		}

		LOGGER.info("Validação das entidades deusas efetuada com sucesso.");
		return ValidacaoArquivoEnum.SUCESSO;
	}

	public void iniciarAnalise(File arquivo, Integer limiteMinMetodoDeus, Integer limiteMinClasseDeus)
			throws IOException {

		this.setArquivoPesquisado(new ArquivoMetrica());

		if (Util.isNullOrEmpty(limiteMinMetodoDeus) || limiteMinMetodoDeus < Constante.NUMBER_ZERO_INT)
			limiteMinMetodoDeus = Constante.NUMBER_ZERO_INT;

		if (Util.isNullOrEmpty(limiteMinClasseDeus) || limiteMinClasseDeus < Constante.NUMBER_ZERO_INT)
			limiteMinClasseDeus = Constante.NUMBER_ZERO_INT;

		if (Util.isNullOrEmpty(arquivo)) {
			LOGGER.warn("Arquivo para análise foi passado como nulo.");
			salvarMetricas();
			return;
		}

		BufferedReader arquivoReader = DirectoryUtil.lerArquivo(arquivo);

		if (Util.isNullOrEmpty(arquivoReader)) {
			LOGGER.warn("Não é possível realizar a leitura linha à linha do arquivo.");
			salvarMetricas();
			return;
		}

		String line;

		String message = String.format(MESSAGE_STARTED_ANALYZE, arquivo.getName());
		LOGGER.info(message);

		while ((line = arquivoReader.readLine()) != null) {
			analisarLinhaAtual(line, arquivo);
		}

		arquivoReader.close();
		LOGGER.info("Análise das métricas finalizada.");

		LOGGER.info("Iniciando análise de métodos deuses...");
		qtdMetodoDeus = verificarEntidadesDeusas(limiteMinMetodoDeus, true);
		LOGGER.info("Análise de métodos deuses finalizada.");

		LOGGER.info("Iniciando análise de classes deusas...");
		qtdClasseDeus = verificarEntidadesDeusas(limiteMinClasseDeus, false);
		LOGGER.info("Análise de classes deusas finalizada.");

		salvarMetricas();

		resetarMetricasProvisorias();
	}

	public Resultado executarCodigo(String conteudo, Tarefa tarefa) {

		Resultado result = new Resultado();

		setFileJava(DirectoryUtil.criarArquivoComExtensao(DIRETORIO_NOME_ARQUIVO, Constante.ARQUIVO_TIPO_JAVA));

		DirectoryUtil.adicionarConteudoArquivo(getFileJava(), conteudo);

		// PASTA ATUAL
//		System.out.println(FileSystems.getDefault().getPath("").toAbsolutePath().toString());

		output = new StringBuilder();

		try {
			iniciarAnalise(getFileJava(), Constante.QTD_MIN_LINHAS_METODO_DEUS, Constante.QTD_MIN_LINHAS_CLASSE_DEUS);
		} catch (IOException e) {
			LOGGER.catching(e.getCause());
			// output.append(e.getMessage() + Constante.QUEBRA_LINHA);
		}
		acessarArquivosAnalisados();

		ArquivoMetrica arquivoPrincipal = null;

		arquivoPrincipal = obterArquivoPrincipal();

		if (arquivoPrincipal != null) {
			result.setArquivoMetrica(arquivoPrincipal);
			String nomeArquivo = arquivoPrincipal.obterNomeArquivo();
			// Estagio 1 - Compilar
			output.append(executar(DIRETORIO, "javac", nomeArquivo));
			// Estagio 2 - Executar
			// output.append(executar(DIRETORIO, "java", nomeArquivo));

			for (Teste teste : tarefa.getTestes()) {
				// Second stage - Execute
				try {
					boolean r = executarInput(DIRETORIO, "java", nomeArquivo, teste.getEntradas(), teste.getSaidas());
					result.getResultadosTestes().add(r);
				} catch (IOException e) {
					output.append(e.getMessage() + Constante.QUEBRA_LINHA);
				}
				result.getSaidasObtidas().add(output.toString());
				output = new StringBuilder();

			}

		} else {
			LOGGER.warn("Não foi possível executar o código");
		}

		return result;
	}

	private ArquivoMetrica obterArquivoPrincipal() {
		ArquivoMetrica arquivoPrincipal = null;

		for (Map.Entry<ArquivoMetrica, Boolean> file : filesJava.entrySet()) {
			boolean isMain = file.getValue();
			if (isMain) {
				arquivoPrincipal = file.getKey();
				break;
			}
		}
		return arquivoPrincipal;
	}

	private void acessarArquivosAnalisados() {
		for (Map.Entry<ArquivoMetrica, File> mapArquivoAlvo : arquivosAnalisados.entrySet()) {
			if ((mapArquivoAlvo.getKey().getArquivo() != null && mapArquivoAlvo.getKey().getArquivo().exists())) {
				if (!mapArquivoAlvo.getValue().exists()) {
					renomearArquivoAlvo(mapArquivoAlvo);
				} else {
					String message = String.format(Constante.ARQUIVO_EXISTENTE, mapArquivoAlvo.getValue());
					LOGGER.warn(message);
					DirectoryUtil.deletarArquivo(mapArquivoAlvo.getValue().toPath());
					renomearArquivoAlvo(mapArquivoAlvo);
				}

				mapArquivoAlvo.getKey().setCaminho(mapArquivoAlvo.getValue().toPath());
				mapArquivoAlvo.getKey().setArquivo(mapArquivoAlvo.getValue());
			}

			verificarArquivosPrincipais(mapArquivoAlvo);
		}
	}

	private void renomearArquivoAlvo(Map.Entry<ArquivoMetrica, File> mapArquivoAlvo) {
		String resultado = DirectoryUtil.renomearArquivo(mapArquivoAlvo.getKey().getArquivo(),
				mapArquivoAlvo.getValue());
		LOGGER.info(resultado);
	}

	private void verificarArquivosPrincipais(Entry<ArquivoMetrica, File> mapArquivoAlvo) {

		boolean contemMetodoMain = false;
		String conteudo = mapArquivoAlvo.getKey().getConteudoCompactado();
		StringTokenizer st = new StringTokenizer(conteudo, Constante.QUEBRA_LINHA);
		while (st.hasMoreTokens()) {
			String line = st.nextToken();
			contemMetodoMain = verificarMetodoMain(line);
			if (contemMetodoMain) {
				filesJava.put(getArquivoPesquisado(), true);
				break;
			}
		}

		if (!contemMetodoMain) {
			filesJava.put(getArquivoPesquisado(), false);
		}
	}

	private boolean verificarMetodoMain(String line) {

		try {
			Pattern padrao = Pattern.compile(Constante.REGEX_METODO_MAIN);
			Matcher encontrador = padrao.matcher(line.trim());
			while (encontrador.find()) {
				return true;
			}

		} catch (PatternSyntaxException e) {
			LOGGER.catching(e);
		}

		return false;
	}

	public static String executar(String path, String comando, String arquivo) {
		// System.out.println("Executando comando:");
		StringBuilder sb = new StringBuilder();

		ProcessBuilder builder = new ProcessBuilder(comando, arquivo);
		/// path + "arquivos" = path/arquivos
		builder.directory(new File(path));
		Process process;
		try {
			process = builder.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				// System.out.println(line);
				sb.append(line + Constante.QUEBRA_LINHA);
			}

			while ((line = error.readLine()) != null) {
				// System.out.println(line);
				sb.append(line + Constante.QUEBRA_LINHA);
			}

			// int exitCode = process.waitFor();
			// System.out.println("\nExited with error code : " + exitCode);

		} catch (IOException e) {
			sb.append(e.getMessage() + Constante.QUEBRA_LINHA);
		}

		if (Util.isNullOrEmpty(sb.toString())) {
			return Constante.VAZIO;
		} else {
			return sb.toString();
		}

	}

	public Boolean executarInput(String path, String comando, String arquivo, String entrada, String saida)
			throws IOException {
		// System.out.println("Executando comando: " + comando + " " + arquivo);

		ProcessBuilder builder = new ProcessBuilder(comando, arquivo);
		builder.directory(new File(path));
		builder.redirectErrorStream(true);
		Process process = builder.start();

		OutputStream stdin = process.getOutputStream(); // <- Eh?
		InputStream stdout = process.getInputStream();

		BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));

		BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream()));

		boolean isFirstLine = true;
		StringTokenizer st = new StringTokenizer(entrada, Constante.QUEBRA_LINHA);
		while (st.hasMoreTokens()) {
			String line = st.nextToken();
			if (isFirstLine) {
				writer.write(line);
				isFirstLine = false;
			} else {
				writer.write(Constante.QUEBRA_LINHA + line);
			}
		}

		writer.flush();
		writer.close();

		Scanner scanner = new Scanner(stdout);
		StringBuilder reposta = new StringBuilder();
		isFirstLine = true;
		while (scanner.hasNextLine()) {
			if (isFirstLine) {
				reposta.append(scanner.nextLine());
				isFirstLine = false;
			} else {
				reposta.append(Constante.QUEBRA_LINHA + scanner.nextLine());
			}
		}

		scanner.close();

		System.out.println("Saída obtida: \n" + reposta.toString());
		System.out.println("Resposta: " + reposta.toString().equals(saida));

		output.append(reposta.toString());

		return reposta.toString().equals(saida);
	}

	public static void main(String[] args) {

		StringBuilder output = new StringBuilder();

		// First stage - Compiler
		output.append(executar(DIRETORIO, "javac", "ClassName.java"));

		String[] entradas = new String[] { "2\n2\n4\n4" };
		String[] saidas = new String[] { "4\n8" };

		if (entradas != null && saidas != null && entradas.length == saidas.length) {
			for (int i = 0; i < saidas.length; i++) {
				String entrada = entradas[i];
				String saida = saidas[i];
				// Second stage - Execute
				// executarInput(DIRETORIO, "java", "ClassName", entrada, saida);
			}
		} else {
			System.out.println("Vetores incompatíveis");
		}

		// System.out.println(output.toString());
	}

	public File getFileJava() {
		return fileJava;
	}

	public void setFileJava(File fileJava) {
		this.fileJava = fileJava;
	}

//	public String getAbsolutePath() {
//		if (Util.isNotNullOrEmpty(getArquivoPesquisado().getArquivo().getAbsoluteFile())) {
//			return getArquivoPesquisado().getArquivo().getAbsolutePath();
//		} else {
//			return getFileJava().getAbsolutePath();
//		}
//	}
//
//	public String getFileName() {
//		if (Util.isNotNullOrEmpty(getArquivoPesquisado().getArquivo().getAbsoluteFile())) {
//			return getArquivoPesquisado().getArquivo().getName();
//		} else {
//			return getFileJava().getName();
//		}
//	}

	public Map<ArquivoMetrica, Boolean> getFilesJava() {
		return filesJava;
	}

	public void setFilesJava(Map<ArquivoMetrica, Boolean> filesJava) {
		this.filesJava = filesJava;
	}

	public StringBuilder getOutput() {
		return output;
	}

	public void setOutput(StringBuilder output) {
		this.output = output;
	}

}
