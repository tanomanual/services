package org.inneo.services.domain.enums;

public enum Tipo {
	HISTORIA("Historia"),
    COMENTARIO("Comentario");
	
	private String descricao;

    private Tipo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
