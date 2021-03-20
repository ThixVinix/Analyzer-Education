package br.ucsal.bes.tcc.analyzereducation.analyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.ucsal.bes.tcc.analyzereducation.abstracts.AbstractArquivoMetrica;
import br.ucsal.bes.tcc.analyzereducation.enums.ValidacaoArquivoEnum;
import br.ucsal.bes.tcc.analyzereducation.util.Constante;
import br.ucsal.bes.tcc.analyzereducation.util.DirectoryUtil;
import br.ucsal.bes.tcc.analyzereducation.util.Util;

public class JavaCode extends AbstractArquivoMetrica {

	private static final Logger LOGGER = LogManager.getLogger(JavaCode.class.getName());

	private static final String MESSAGE_STARTED_ANALYZE = "Iniciando análise das métricas do arquivo: \"%s\"...";
	
	private static final String DIRETORIO_NOME_ARQUIVO = "C:\\Users\\Public\\Documents\\DefaultName";

	private File fileJava;


	public JavaCode() {
		super();
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
	
	public String executarCodigo(String conteudo) {
		
			setFileJava(criarArquivoGenericoJava());
			
		   DirectoryUtil.adicionarConteudoArquivo(getFileJava(), conteudo);
		
		try {
			iniciarAnalise(getFileJava(), Constante.QTD_MIN_LINHAS_METODO_DEUS, Constante.QTD_MIN_LINHAS_CLASSE_DEUS);
		} catch (IOException e) {
			LOGGER.catching(e);
		}
		
		String output = "";
		
		output = executeCommand(getAbsolutePath());
		
		return output;
	}


	private File criarArquivoGenericoJava() {
		int count = Constante.NUMBER_ZERO_INT;
		File f = new File(DIRETORIO_NOME_ARQUIVO + Constante.ARQUIVO_TIPO_JAVA);
		
		while (f.exists() && !f.isDirectory()) {
			f = new File(DIRETORIO_NOME_ARQUIVO + (++count) + Constante.ARQUIVO_TIPO_JAVA);
		}
		
		return DirectoryUtil.criarArquivo(f.getAbsolutePath());
	}
	
	public String executeCommand(String command) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			output.append(e.getCause());
		}

		return output.toString();

	}


	public File getFileJava() {
		return fileJava;
	}


	public void setFileJava(File fileJava) {
		this.fileJava = fileJava;
	}

	public String getAbsolutePath() {
		if (Util.isNotNullOrEmpty(getArquivoPesquisado().getArquivo().getAbsoluteFile())) {
		return getArquivoPesquisado().getArquivo().getAbsolutePath();
		} else {
		return getFileJava().getAbsolutePath();
		}
	}
	
	public String getFileName() {
		if (Util.isNotNullOrEmpty(getArquivoPesquisado().getArquivo().getAbsoluteFile())) {
		return getArquivoPesquisado().getArquivo().getName();
		} else {
		return getFileJava().getName();
		}
	}


}
