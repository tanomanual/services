package org.inneo.services.repository;

import java.util.UUID;

import org.inneo.services.domain.feed.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostRep extends JpaRepository<Post, UUID> , JpaSpecificationExecutor<Post>{
	Post findByUuid(UUID uuid);
}
