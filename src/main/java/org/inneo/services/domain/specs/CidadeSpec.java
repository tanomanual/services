package org.inneo.services.domain.specs;

import org.springframework.util.StringUtils;
import jakarta.persistence.criteria.Predicate;
import org.inneo.services.domain.cidades.Cidade;
import org.springframework.data.jpa.domain.Specification;

public class CidadeSpec {
	
	public static Specification<Cidade> doFiltro(String filter){
		return (root, query, builder) -> {
            if(StringUtils.hasText(filter)) {
            	Long codigo = null;
            	if(filter.matches("^\\d+$")) {
            		codigo = Long.parseLong(filter);
            	}
            	Predicate fieldCodigo = builder.equal(root.get("codigo"), codigo);
            	Predicate fieldCidade = builder.like(builder.upper(root.get("cidade")), "%" + filter.toUpperCase() + "%");
            	Predicate fieldEstado = builder.like(builder.upper(root.get("estado")), "%" + filter.toUpperCase() + "%");
            	return builder.or(fieldCodigo, fieldCidade, fieldEstado);
            }
            return builder.and(new Predicate[0]);
        };
	}
	
	
	public static Specification<Cidade> doCodigo(Long filter){
		return (root, query, builder) -> {
			if(filter != null) {
				return builder.equal(root.get("codigo"), filter);				
			}
			return builder.and(new Predicate[0]);			
		};
	}
	
	public static Specification<Cidade> doNome(String filter){
		return (root, query, builder) -> {
            if(StringUtils.hasText(filter)) {
            	return builder.like(builder.upper(root.get("cidade")), "%" + filter.toUpperCase() + "%"); 
            }
            return builder.and(new Predicate[0]);
        };
	}
	
	public static Specification<Cidade> doEstado(String filter){
		return (root, query, builder) -> {
            if(StringUtils.hasText(filter)) {
            	return builder.like(builder.upper(root.get("estado")), "%" + filter.toUpperCase() + "%"); 
            }
            return builder.and(new Predicate[0]);
        };
	}
}
