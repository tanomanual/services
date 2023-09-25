package org.inneo.services.repository.login;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.inneo.services.domain.token.Token;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TokenRep extends JpaRepository<Token, Long>{
	@Query(value = """
			select t from Token t inner join Login log 
			on t.login.uuid = log.uuid 
			where log.uuid = :uuid and (t.expired = false or t.revoked = false)""")
	List<Token> findAllValidTokenByUsuario(UUID uuid);	
	Optional<Token> findByToken(String uuid);

}
