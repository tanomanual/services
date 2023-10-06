package org.inneo.services.domain.specs;

import jakarta.persistence.criteria.Predicate;
import org.inneo.services.domain.publicacoes.Pagina;
import org.springframework.data.jpa.domain.Specification;


public class PaginaSpec {
	public static Specification<Pagina> doNome(String nome){
		return (root, query, builder) -> {
            if(nome != null) {
                return builder.equal(root.get("nome"), nome);
            }
            return builder.and(new Predicate[0]);
        };
    }
}
