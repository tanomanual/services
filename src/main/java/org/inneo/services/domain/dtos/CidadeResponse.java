package org.inneo.services.domain.dtos;

import org.inneo.services.domain.cidades.Cidade;

public record CidadeResponse(Long codigo, String cidade, String estado) {
	public CidadeResponse(Cidade cidade){
		 this(cidade.getCodigo(), cidade.getCidade(), cidade.getEstado());
	 }

}
