package org.inneo.services.repository.login;

import java.util.UUID;
import java.util.Optional;
import org.inneo.services.domain.usuario.Login;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LoginRep extends JpaRepository<Login, UUID> {
	Optional<Login> findByUsername(String username);
}
