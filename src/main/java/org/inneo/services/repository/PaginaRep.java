package org.inneo.services.repository;

import java.util.UUID;
import org.inneo.services.domain.publicacoes.Pagina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PaginaRep extends JpaRepository<Pagina, UUID>, JpaSpecificationExecutor<Pagina>{
	Pagina findByNome(String nome);
}
