package br.ucsal.bes.tcc.analyzereducation.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DirectoryUtil {

	private static final Logger LOGGER = LogManager.getLogger(DirectoryUtil.class.getName());

	private static Class<DirectoryUtil> c;
	private static Method[] methods;

	static {
		c = DirectoryUtil.class;
		methods = c.getMethods();
	}

	private DirectoryUtil() {
	}

	public static BufferedReader lerArquivo(File arquivo) {

		BufferedReader br = null;

		try {

			LOGGER.debug("Inicializando leitura do arquivo...");

			if (Util.isNullOrEmpty(arquivo))
				throw new NullPointerException();

			FileInputStream stream = new FileInputStream(arquivo);
			InputStreamReader reader = new InputStreamReader(stream);
			br = new BufferedReader(reader);

			LOGGER.info("Leitura realizada.");
		} catch (NullPointerException e) {
			LOGGER.warn("Não foi possível ler arquivo nulo: %s", e.getCause());
		} catch (FileNotFoundException e) {
			LOGGER.warn("Não foi possível encontrar arquivo \"%s\": %s", arquivo.getName(), e.getMessage());
		}

		return br;
	}

	public static File criarArquivo(String pathFileString) {

		File file = null;
//		String nomeMetodo = getCompleteMethodName("criarArquivo");

		try {
			LOGGER.debug("Criando arquivo...");

			if (Util.isNullOrEmpty(pathFileString))
				throw new NullPointerException();

			file = new File(pathFileString);

			if (!file.createNewFile())
				throw new IOException();

			LOGGER.info("Criação efetuada.");
		} catch (NullPointerException e) {
			LOGGER.warn("Não foi possível criar o arquivo por um diretório vazio/nulo %n %n %s", e.getMessage());
		} catch (IOException e) {
			LOGGER.warn("Não foi possível criar o arquivo pelo diretório \"%s\"%n %s", pathFileString, e.getMessage());
		}

		return file;
	}

	public static void adicionarConteudoArquivo(File file, String conteudo) {
		FileWriter fileWritter = null;

		try {
			fileWritter = new FileWriter(file.getAbsolutePath(), true);
		} catch (IOException e) {
			LOGGER.warn(e.getMessage());
		}

		try (BufferedWriter bufferWritter = new BufferedWriter(fileWritter)) {
			bufferWritter.write(conteudo);
			bufferWritter.flush();
		} catch (IOException e) {
			LOGGER.warn(e.getMessage());
		}

	}

	/**
	 * Verifica se o diretório existe e se está completo (incluindo o arquivo alvo).
	 * 
	 * @param caminho
	 * @return true or false
	 * @throws SecurityException
	 */
	public static boolean validarDiretorioCompleto(Path caminho) {

		boolean isValid;

		try {
			isValid = caminho.toFile().exists() && caminho.isAbsolute();
		} catch (SecurityException e) {
			isValid = false;
		}
		return isValid;
	}

	public static boolean isJavaFile(File arquivo) {
		return arquivo.getName().endsWith(Constante.ARQUIVO_TIPO_JAVA);
	}

	public static File[] procurarArquivosJava(File diretorio) {
		File[] listFiles = diretorio.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				return isJavaFile(pathname);
			}
		});

		return listFiles;
	}

	public static File[] procurarArquivosJava2(File diretorio) {
		File[] listFiles = diretorio.listFiles(DirectoryUtil::isJavaFile);

		return listFiles;
	}

	/**
	 * Retorna um diretório(path) através de um diretório(String).
	 * 
	 * @param diretorioString
	 * @return path or null
	 * @throws NullPointerException, InvalidPathException
	 */
	public static Path obterPathPeloDiretorioString(String diretorioString) {
		Path path;

		try {
			LOGGER.debug("Convertendo String para Path...");

			if (Util.isNullOrEmpty(diretorioString))
				throw new NullPointerException();

			path = Paths.get(diretorioString);
			LOGGER.info("Path obtido.");
		} catch (NullPointerException e) {
			LOGGER.warn("Não foi possível obter o diretório vazio/nulo: %s", e.getCause());
			path = null;
		} catch (InvalidPathException e) {
			LOGGER.warn("Não foi possível obter o diretório: \"%s\"%n: %s", diretorioString, e.getMessage());
			path = null;
		}
		return path;
	}

	public static Path obterPathPorArquivoExistente(File file) {

		Path path = null;
//		String nomeMetodo = getCompleteMethodName("obterPathPorArquivoExistente");

		try {
			LOGGER.debug("Convertendo File para Path...");

			if (Util.isNullOrEmpty(file))
				throw new NullPointerException();

			path = file.toPath();
			LOGGER.info("Path obtido.");
		} catch (NullPointerException e) {
			LOGGER.warn("Não é possível obter o caminho de um arquivo vazio/nulo: %s", e.getCause());
		} catch (InvalidPathException e) {
			LOGGER.warn("Não foi possível obter o diretório do arquivo: %s %n %s", file.toString(), e.getMessage());
		}

		return path;
	}

	/**
	 * Retorna um arquivo através do caminho(path).
	 * 
	 * @param caminho
	 * @return file or null
	 */
	public static File obterArquivoPorDiretorio(Path caminho) {
		File arquivo = null;

		try {
			LOGGER.debug("Obtendo arquivo através de um diretório...");

			if (Util.isNullOrEmpty(caminho))
				throw new NullPointerException();

			arquivo = caminho.toFile();
			LOGGER.info("Arquivo obtido.");
		} catch (NullPointerException e) {
			LOGGER.warn("Não foi possível obter o arquivo através de um diretório vazio/nulo: %s", e.getCause());
		} catch (UnsupportedOperationException e) {
			LOGGER.warn("Não foi possível obter o diretório através do diretório digitado: %s %n %s",
					caminho.toString(), e.getMessage());
		}

		return arquivo;
	}

	public static void deletarArquivo(Path path) {
		try {
			Files.delete(path);
		} catch (IOException e) {
			LOGGER.warn(e.getCause());
		}
	}

	@SuppressWarnings(value = "unused")
	private static String getCompleteMethodName(String nameMethodString) {
		for (Method method : methods) {
			if (nameMethodString.equalsIgnoreCase(method.getName())) {
				return method.toGenericString();
			}
		}
		return Constante.VAZIO;
	}

	/*
	 * 
	 * Copia Arquivo de um local para outro. Considerações: Se existir o arquivo de
	 * destino, este é sobreposto. Se não existir o diretório de destino, este é
	 * criado.
	 * 
	 */
	public static int copiar(String original, String copia) {
		File origem = null;
		File destino = null;

		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;

		try {
			origem = new File(original);
			destino = new File(copia);

			// cria diretorio se nao existir
			if (!destino.getParentFile().exists())
				destino.getParentFile().mkdirs();

			if (destino.exists())
				Files.delete(destino.toPath());

			fileOutputStream = new FileOutputStream(destino);
			fileInputStream = new FileInputStream(origem);

			byte[] buffer = new byte[8192];

			for (int bytes = fileInputStream.read(buffer); bytes >= 0; bytes = fileInputStream.read(buffer)) {
				fileOutputStream.write(buffer, 0, bytes);
			}

			fileOutputStream.flush();

			return 1;
		} catch (Exception e) {
			LOGGER.catching(e);

			return 0;
		} finally {
			try {
				fileInputStream.close();
				fileInputStream = null;

				fileOutputStream.close();
				fileOutputStream = null;
			} catch (IOException e1) {
			LOGGER.catching(e1);
			}
		}
	}

	public static int mover(String arquivoAntigo, String arquivoNovo) {
		File origem = null;

		try {
			if (copiar(arquivoAntigo, arquivoNovo) == 1) {
				origem = new File(arquivoAntigo);

				origem.delete();

				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}
	}

}
