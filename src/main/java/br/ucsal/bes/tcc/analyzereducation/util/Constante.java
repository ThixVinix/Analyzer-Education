package br.ucsal.bes.tcc.analyzereducation.util;

public class Constante {

	private Constante() {
	}

	public static final String REGEX_COMENTARIOS = "(^(\\/\\*+[\\s\\S]*?\\*\\/)|(\\/\\*+.*\\*\\/)|\\/\\/.+|^\\/\\/.*?[\\r\\n])[\\r\\n]*";
	public static final String REGEX_COMENTARIOS1 = "(\\/\\*[\\s\\S]*?\\*\\/|\\/\\/[\\s\\S]*?(\\n|\\r))";
	public static final String REGEX_COMENTARIOS2 = "\\/\\*([\\S\\s]+?)\\*\\/";
	public static final String REGEX_COMENTARIOS3 = "(?s)/\\*.*?\\*/";
	public static final String REGEX_LINHA = ".*(\\S)";
	public static final String REGEX_METODO = "^(\\s|)+((([^\\sreturn])[\\w\\<\\>\\[\\]]+[\\s]+[\\w]+)|([\\w]+[\\s]+[\\w\\<\\>\\[\\]]+[\\s]+[\\w]+)|([\\w]+[\\s]+[\\w]+[\\s]+[\\w\\<\\>\\[\\]]+[\\s]+[\\w]+)|([\\w]+[\\s]+[\\w]+[\\s]+[\\w]+[\\s]+[\\w\\<\\>\\[\\]]+[\\s]+[\\w]+))(\\s|)+\\((?:\\s*(?:const\\s+)?([\\S]+?)\\s+([\\w\\d]+?)\\s*,?)*?\\)(\\s|)+((throws)(\\s)+([\\w,\\s]{1,})|)(\\s|)+(\\{|\\;|)(\\s|)+$";
	public static final String REGEX_CLASSE = "(public\\s+|private\\s+)?(static\\s+)?(abstract\\s+)?(final\\s+)?class\\s+([\\w_$]+)(\\s*<[\\w,\\s]*>)?(\\s+extends\\s([\\w\\.\\_\\$]+))?(\\s+implements\\s([\\w\\.\\_\\$\\,\\s]+))?\\s*(\\{|)";
	public static final String REGEX_METODO_MAIN = "(\\s|)+(public)(\\s)+(static)(\\s)+(void)(\\s)+(main)(\\s|)+(\\()(\\s|)+(String)(\\s|)+(\\[\\])(\\s)+(args)(\\s|)+(\\))(\\s|)+(\\s|)+(\\w||\\s|,)+(\\{|)(\\s|)";
	
	public static final Integer QTD_MIN_LINHAS_METODO_DEUS = 200;
	public static final Integer QTD_MIN_LINHAS_CLASSE_DEUS = 1000;
	public static final Long NUMBER_ZERO_LONG = 0L;
	public static final int NUMBER_ZERO_INT = 0;
	public static final char ASPAS_DUPLAS = '"';
	public static final char BARRA = '/';
	public static final char BARRA_INVERTIDA = '\\';
	public static final char ASTERISCO = '*';
	public static final String COMENTARIO_SIMPLES = "//";
	public static final String COMENTARIO_MULTI_LINHA_1 = "/*";
	public static final String COMENTARIO_MULTI_LINHA_2 = "*";
	public static final String COMENTARIO_MULTI_LINHA_3 = "*/";
	public static final String ABRE_CHAVES = "{";
	public static final String FECHA_CHAVES = "}";
	public static final String PONTO = ".";
	public static final String PONTO_VIRGULA = ";";
	public static final String VAZIO = "";
	public static final String QUEBRA_LINHA = "\n";
	public static final String OVERRIDE = "@Override";
	public static final String ABSTRACT = "abstract";
	public static final String URL_EXEMPLO = "https://raw.githubusercontent.com/junyanz/pytorch-CycleGAN-and-pix2pix/master/train.py";
	public static final String DIRETORIO_EXEMPLO = "C:/Users/tvaso/eclipse-workspace/Evolução de Software Console/src/testesArquivos/Teste2.java";

	public static final String URL_GIT = "https://raw.githubusercontent.com/ThixVinix/DataSet/master/";
	public static final String DISPATCH_QUEUE = "/DispatchQueue.java";
	public static final String FILE_LOADER = "/FileLoader.java";
	public static final String FILE_LOG = "/FileLog.java";
	public static final String FILE_UPLOAD_OPERATION = "/FileUploadOperation.java";
	public static final String USER_CONFIG = "/UserConfig.java";
	public static final String UTILITIES = "/Utilities.java";

	public static final String MENSAGEM_URL = "Lendo o arquivo encontrado na URL:\t";
	public static final String MENSAGEM_CONCLUIDA = "\t\t\t |.Leitura efetuada.|";
	public static final String MENSAGEM_CONCLUIDA_2 = "\t\t |.Leitura efetuada.|";

	public static final Double TAXA_DEFASAGEM = (2d / (2 + 1d));

	public static final String IMAGEM_MENU_INICIAL = "search-arquivos.jpg";
	public static final String REGEX_C_METHOD_ALL_SCOPE = "(\\w+)\\s+(\\w+)\\s*\\((?:\\s*(\\w+)\\s+(\\w+)\\s*,?)*\\)\\s*(?:[^}]*{[^}]*}|;)";

	public static final String REGEX_CLASSE_2 = "(public|private|protected|).*(class).*(\\\\()*(\\\\{)";

	public static final String ARQUIVO_TIPO_JAVA = ".java";
	public static final String DIRETORIO_EXEMPLO_1 = "C:/Users/tvaso/eclipse-workspace/Evolução de Software Console/src/testesArquivos/Teste1.java";
	public static final String DIRETORIO_EXEMPLO_2 = "C:/Users/tvaso/eclipse-workspace/Evolução de Software Console/src/testesArquivos/Teste2.java";

	public static final String CURRENT_DIRETORY_PATH_C = "C:\\\\";

	// Titles Window:

	public static final String TITLE_ANALIZADOR_CODIGO_JAVA = "Analisador de C\u00F3digo JAVA";
	public static final String TITLE_PESQUISA_ARQUIVO = "Pesquisa Arquivo";

	/** Menu initial Labels for: {@link MenuInicial}} */

	public static final String MENU_TITLE_PESQUISAR_UNICO_ARQUIVO = "Pesquisar por único arquivo";

	/** Labels Window for: {@link TelaPesquisaArquivo} */

	public static final String LABEL_CAMPO_DIRETORIO_COMPLETO = "Digite o diret\u00F3rio completo do arquivo (incluindo o arquivo):";
	public static final String LABEL_CONTEUDO_ARQUIVO = "Conte\u00FAdo do arquivo:";
	public static final String LABEL_CAMINHO_DIRETORIO = "Caminho do diret\u00F3rio:";
	public static final String LABEL_QTD_PASTAS_ACESSADAS = "Qtd. pastas acessadas:";
	public static final String LABEL_NOME_ARQUIVO = "Nome do arquivo:";
	public static final String LABEL_QTD_LINHAS_CODIGO = "Quantidade de linhas de c\u00F3digo:";
	public static final String LABEL_QTD_CLASSES = "Quantidade de Classes:";
	public static final String LABEL_QTD_METODOS = "Quantidade de M\u00E9todos:";
	public static final String LABEL_QTD_COMENTARIOS = "Qtd. de coment\u00E1rios:";
	public static final String LABEL_QTD_CLASSES_DEUSAS = "Qtd. de Classes deusas:";
	public static final String LABEL_QTD_METODOS_DEUSES = "Qtd. de M\u00E9todos deuses:";
	public static final String LABEL_INFORMATIVA = "ATEN\u00C7\u00C3O: coment\u00E1rios e linhas em branco N\u00C3O contam como \"linhas de c\u00F3digo\".";
	public static final String LABEL_LIMITE_METODO_DEUS = "Limite m\u00EDnimo para m\u00E9todo deus:";
	public static final String LABEL_LIMITE_CLASSE_DEUSA = "Limite m\u00EDnimo para classe deusa:";
	public static final String LABEL_DEFINIR_ENTIDADES_DEUSES = "Definir limites para entidades deuses";
	public static final String LABEL_EXIBIR_CONTEUDO_COMPACTADO = "Exibir conte\u00FAdo compactado";
	public static final String LABEL_EXIBIR_CONTEUDO_FORMATADO = "Exibir conte\u00FAdo formatado";
	public static final String LABEL_EXIBIR_CONTEUDO_COMPLETO = "Exibir conte\u00FAdo completo";

	/** Buttons Description: {@link TelaPesquisaArquivo} */

	public static final String BUTTON_TEXT_VOLTAR = "Voltar";
	public static final String BUTTON_TEXT_BUSCAR = "Buscar";
	public static final String BUTTON_TEXT_LIMPAR = "Limpar";
	public static final String BUTTON_TEXT_GERAR_GRAFICO = "Gerar gr\u00E1fico";
	public static final String BUTTON_TEXT_INICIAR_ANALISE = "Iniciar An\u00E1lise";

	/** Combo Box Options  */

	public static final String GRAFICO_SELECT_OPTION = "Selecione o modelo do gráfico";
	public static final String GRAFICO_PIZZA = "Gráfico de Pizza";
	public static final String GRAFICO_BARRA_HORIZONTAL = "Gráfico de Barra Horizontal";
	public static final String GRAFICO_BARRA_VERTICAL = "Gráfico de Barra Vertical";

	/** Messages Description */

	public static final String MESSAGE_SELECIONE_GRAFICO = "Selecione um modelo de gráfico.";
	public static final String PREENCHIMENTO_OBRIGATORIO_DIRETORIO = "Favor, preencher o campo do diretório para poder iniciar a análise.";
	public static final String DIRETORIO_NAO_ENCONTRADO = "O diretório não foi encontrado ou está incompleto.\n Tente digitar novamente.";
	public static final String TIPO_ARQUIVO_INCORRETO = "O arquivo não possui o tipo permitido \".java\".\n Escolha outro arquivo do tipo \".java\".";
	public static final String ERRO_CONVERSAO_DIRETORIO = "Diretório inválido! Tente novamente digitar um diretório.";
	public static final String PREENCHIMENTO_OBRIGATORIO_METODO_DEUS = "Campo \"Limite mínimo para método deus\" é de preenchimento obrigatório.";
	public static final String PREENCHIMENTO_OBRIGATORIO_CLASSE_DEUS = "Campo \"Limite mínimo para classe deusa\" é de preenchimento obrigatório.";
	public static final String PREENCHIMENTO_INCORRETO_METODO_DEUS = "Campo \"Limite mínimo para método deus\" deve ser um campo preenchido somente com números.";
	public static final String PREENCHIMENTO_INCORRETO_CLASSE_DEUS = "Campo \"Limite mínimo para classe deusa\" deve ser um campo preenchido somente com números.";
	public static final String ARQUIVO_ANALISADO = "Este arquivo já foi analisado com as configurações atuais.\nEscolha outro arquivo ou altere alguma configuração.";

	/** Warn Messages */
	
	public static final String RENOMEACAO_NAO_REALIZADA = "Não foi possível renomear arquivo \"%s\" para \"%s\".";
	public static final String ARQUIVO_RENOMEADO = "Arquivo \"%s\" renomeado para : \"%s\"";
	public static final String ARQUIVO_EXISTENTE = "O arquivo \"%s\" já existe. Não é possível conter mais de um arquivo com o mesmo nome no mesmo diretório.";
}

