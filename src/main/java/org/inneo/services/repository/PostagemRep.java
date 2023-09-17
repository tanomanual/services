package org.inneo.services.repository;

import java.util.UUID;

import org.inneo.services.domain.feed.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostagemRep extends JpaRepository<Postagem, UUID> , JpaSpecificationExecutor<Postagem>{
	Postagem findByUuid(UUID uuid);
}
