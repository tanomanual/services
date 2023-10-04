package org.inneo.services.domain.enums;

public enum MenuEnum {
	MENU("Menu"),
    SOCIAL("Social");
	
	private String descricao;

    private MenuEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
