package org.inneo.services.domain.dtos;

import java.util.UUID;
import org.inneo.services.domain.feed.Postagem;

public record PostagemResponse(UUID uuid, String texto, String usuario, String publishedAt) {
	public PostagemResponse(Postagem postagem){
		this(postagem.getUuid(), postagem.getTexto(), postagem.getUsuario().getNomeCompleto(), postagem.getPublishedAt());
    }
}
