package org.inneo.services.repository;

import java.util.UUID;
import org.inneo.services.domain.comentario.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ComentarioRep extends JpaRepository<Comentario, UUID> , JpaSpecificationExecutor<Comentario>{
	Comentario findByUuid(UUID uuid);
}
 