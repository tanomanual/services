package org.inneo.services.domain.specs;

import org.inneo.services.domain.enums.Situacao;
import org.inneo.services.domain.historia.Historia;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;

public class PostagenSpec {
	public static Specification<Historia> doSituacao(Situacao situacao){
        return (root, query, builder) -> {
            if(situacao != null) {
                if(situacao.equals(Situacao.ATIVO)) {
                    return builder.isNull(root.get("dataInativacao"));
                }
                else if(situacao.equals(Situacao.INATIVO)) {
                    return builder.isNotNull(root.get("dataInativacao"));
                }
            }
            return builder.and(new Predicate[0]);
        };
    }

}
