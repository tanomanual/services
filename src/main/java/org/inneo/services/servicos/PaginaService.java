package org.inneo.services.servicos;


import java.util.Optional;

import org.inneo.services.domain.publicacoes.Pagina;
import org.inneo.services.domain.specs.PaginaSpec;
import org.springframework.stereotype.Service;
import org.inneo.services.repository.PaginaRep;
import org.springframework.beans.BeanUtils;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaginaService {
	private final PaginaRep paginaRep;
	
	public Pagina save(Pagina pagina) {
		Pagina result = pagina.getUuid() != null? paginaRep.getReferenceById(pagina.getUuid()) : new Pagina();
		BeanUtils.copyProperties(pagina, result);
		return paginaRep.save(pagina);
	}
	
	public Optional<Pagina> getPagina(String nome) {
		return paginaRep.findOne(PaginaSpec.doNome(nome));
	}
	

}
