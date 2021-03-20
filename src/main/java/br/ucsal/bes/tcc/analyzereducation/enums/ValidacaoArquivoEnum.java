package br.ucsal.bes.tcc.analyzereducation.enums;

public enum ValidacaoArquivoEnum {
	
	SUCESSO(""), 
	ARQUIVO_ANALISADO("Este arquivo já foi analisado com as configurações atuais.\nEscolha outro arquivo ou altere alguma configuração."),
	
	PREENCHIMENTO_OBRIGATORIO_DIRETORIO("Favor, preencher o campo do diretório para poder iniciar a análise."), 
	DIRETORIO_NAO_ENCONTRADO("O diretório não foi encontrado ou está incompleto.\n Tente digitar novamente."),
	TIPO_ARQUIVO_INCORRETO("O arquivo não possui o tipo permitido \".java\".\n Escolha outro arquivo do tipo \".java\"."),
	ERRO_CONVERSAO_DIRETORIO("Diretório inválido! Tente novamente digitar um diretório."),
	ARQUIVO_NAO_SUPORTADO(""),
	
	PREENCHIMENTO_OBRIGATORIO_METODO_DEUS("Campo \"Limite mínimo para método deus\" é de preenchimento obrigatório."),
	PREENCHIMENTO_OBRIGATORIO_CLASSE_DEUS("Campo \"Limite mínimo para classe deusa\" é de preenchimento obrigatório."), 
	PREENCHIMENTO_INCORRETO_METODO_DEUS("Campo \"Limite mínimo para método deus\" deve ser um campo preenchido somente com números."),
	PREENCHIMENTO_INCORRETO_CLASSE_DEUS("Campo \"Limite mínimo para classe deusa\" deve ser um campo preenchido somente com números."), 
	
	MESSAGE_SELECIONE_GRAFICO("Selecione um modelo de gráfico.");
	
	private String descricao;
	
	private ValidacaoArquivoEnum(String descricao) {
		this.descricao = descricao;
	}
	
	
    public String getDescricao() {
        return descricao;
    }

}