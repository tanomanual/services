package org.inneo.services.domain.specs;

import org.springframework.data.jpa.domain.Specification;
import org.inneo.services.domain.enums.Situacao;
import org.inneo.services.domain.feed.Historia;
import jakarta.persistence.criteria.Predicate;
import java.util.UUID;

public class HistoriaSpec {	
	
	public static Specification<Historia> daPublicacao(UUID uuid){
        return (root, query, builder) -> {
            if(uuid != null) {
                return builder.equal(root.get("publicacao"), uuid);
            }
            return builder.and(new Predicate[0]);
        };
    }

	
	public static Specification<Historia> daSituacao(Situacao situacao){
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
