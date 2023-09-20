package org.inneo.services.repository;

import java.util.UUID;

import org.inneo.services.domain.historia.Historia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HistoriaRep extends JpaRepository<Historia, UUID> , JpaSpecificationExecutor<Historia>{
	Historia findByUuid(UUID uuid);
}
