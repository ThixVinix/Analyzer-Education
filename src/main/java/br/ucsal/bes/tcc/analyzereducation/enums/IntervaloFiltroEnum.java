package br.ucsal.bes.tcc.analyzereducation.enums;

public enum IntervaloFiltroEnum {

	IGUAL("= "),
	MAIOR_IGUAL(">= "),
	MENOR_IGUAL("<= "),
	MAIOR("> "),
	MENOR("< ");

	private String descricao;
	
	private IntervaloFiltroEnum(String descricao) {
		this.descricao = descricao;
	}
	
    public String getDescricao() {
        return descricao;
    }
	
}
