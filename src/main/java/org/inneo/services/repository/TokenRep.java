package org.inneo.services.repository;

import java.util.List;
import java.util.Optional;
import org.inneo.services.domain.token.Token;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TokenRep extends JpaRepository<Token, Long>{
	@Query(value = """
			select t from Token t inner join Login log 
			on t.login.id = log.id 
			where log.id = :id and (t.expired = false or t.revoked = false)""")
	List<Token> findAllValidTokenByUsuario(Long id);	
	Optional<Token> findByToken(String token);

}
