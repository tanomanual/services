package org.inneo.services.domain.dtos;

import java.util.Collection;
import java.util.UUID;
import org.inneo.services.domain.feed.Historia;

public record HistoriaResponse(UUID uuid, String conteudo, String administrador, Boolean podeEditar, Integer comentaram, Collection<Historia> comentarios) {
	public HistoriaResponse(Historia historia){
		this(historia.getUuid(), historia.getConteudo(), historia.getUsuario().getNomeCompleto(), historia.getPodeEditar(), historia.getComentaram(), historia.getComentarios());
    }
}
