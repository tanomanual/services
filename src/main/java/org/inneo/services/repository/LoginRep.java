package org.inneo.services.repository;

import java.util.Optional;

import org.inneo.services.domain.usuario.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRep extends JpaRepository<Login, Long> {
	Optional<Login> findByUsername(String username);
}
