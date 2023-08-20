package org.inneo.services.servicos;

import java.util.List;
import lombok.AllArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.inneo.services.domain.cidades.Cidade;
import org.inneo.services.domain.dtos.CidadeResponse;
import org.inneo.services.repository.CidadeRep;

@Service
@AllArgsConstructor 
public class CidadeService {
	private final CidadeRep cidadeRep;
	
	public Cidade create(Cidade cidade) {		 
		if(cidade.getCodigo() != null && cidadeRep.findByCodigo(cidade.getCodigo()) != null) {
			Cidade create = cidadeRep.findByCodigo(cidade.getCodigo());
			BeanUtils.copyProperties(cidade, create);
			return cidadeRep.save(create);
		}		
		return cidadeRep.save(cidade);
	}
	
	public Cidade getCod(Long codigo) {
		return cidadeRep.findByCodigo(codigo);			
	}
	
	public List<CidadeResponse> findAll() {
		List<CidadeResponse> cidades = cidadeRep.findAll(orderByCodigo()).stream().map(CidadeResponse::new).toList();
		return cidades;
	}
	
	public Page<Cidade> getPages(Pageable pageable) {
		Page<Cidade> cidades = cidadeRep.findAll(pageable);
		return cidades;
	}
	
	public void delete(Long codigo) {
		cidadeRep.deleteById(codigo);
	}
	
	private Sort orderByCodigo() {
	    return Sort.by(Sort.Direction.ASC, "codigo");
	}

}
