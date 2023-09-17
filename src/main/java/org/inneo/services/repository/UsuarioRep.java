package org.inneo.services.repository;

import java.util.UUID;
import org.inneo.services.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRep extends JpaRepository<Usuario, UUID>{
	Usuario findByLoginId(UUID uuid);
}
