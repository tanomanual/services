package org.inneo.services.servicos;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.inneo.services.repository.SocialRep;
import org.inneo.services.domain.options.Social;

@Service
@AllArgsConstructor
public class SocialService {
	private final SocialRep socialRep;
	
	public void save(Social social) {
		socialRep.save(social);
	}
	
	public List<Social> fildAll() {
		return socialRep.findAll();
	}

}
