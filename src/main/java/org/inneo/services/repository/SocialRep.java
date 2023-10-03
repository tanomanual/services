package org.inneo.services.repository;

import java.util.UUID;
import org.inneo.services.domain.options.Social;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialRep extends JpaRepository<Social, UUID>{

}
