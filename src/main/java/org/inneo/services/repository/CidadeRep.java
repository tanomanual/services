package org.inneo.services.repository;

import org.inneo.services.domain.cidades.Cidade;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CidadeRep extends JpaRepository<Cidade, Long>{
	Cidade findByCodigo(Long id);	

}
