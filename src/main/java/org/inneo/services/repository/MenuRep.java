package org.inneo.services.repository;

import java.util.UUID;
import org.inneo.services.domain.options.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRep extends JpaRepository<Menu, UUID>{

}
