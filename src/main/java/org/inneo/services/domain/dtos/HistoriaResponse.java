package org.inneo.services.domain.dtos;

import java.util.UUID;

import org.inneo.services.domain.historia.Historia;

public record HistoriaResponse(UUID uuid, String conteudo, String usuario, String publicado) {
	public HistoriaResponse(Historia historia){
		this(historia.getUuid(), historia.getConteudo(), historia.getUsuario().getNomeCompleto(), historia.getPublishedAt());
    }
}
