package org.inneo.services.repository;

import org.inneo.services.domain.cidades.Cidade;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface CidadeRep extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade>{
	Cidade findByCodigo(Long id);	

}
