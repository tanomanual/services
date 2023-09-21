package org.inneo.services.domain.dtos;

import java.util.UUID;
import org.inneo.services.domain.comentario.Comentario;

public record ComentarioResponse(UUID uuid, String conteudo, String usuario, String publicado) {
	public ComentarioResponse(Comentario comentario){
		this(comentario.getUuid(), comentario.getConteudo(), comentario.getUsuario().getNomeCompleto(), comentario.getPublishedAt());
    }

}
