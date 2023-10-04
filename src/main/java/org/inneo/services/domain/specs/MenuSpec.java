package org.inneo.services.domain.specs;

import org.inneo.services.domain.enums.MenuEnum;
import org.inneo.services.domain.options.Menu;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;

public class MenuSpec {
	public static Specification<Menu> doTipo(MenuEnum menuEnum){
		return (root, query, builder) -> {
            if(menuEnum != null) {
                return builder.equal(root.get("menuEnum"), menuEnum);
            }
            return builder.and(new Predicate[0]);
        };
    }

}
