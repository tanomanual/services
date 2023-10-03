package org.inneo.services.servicos;

import java.util.List;
import lombok.AllArgsConstructor;
import org.inneo.services.domain.options.Menu;
import org.inneo.services.repository.MenuRep;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MenuService {
	private final MenuRep menuRep;
	
	public void save(Menu menu) {
		menuRep.save(menu);
	}
	
	public List<Menu> fildAll() {
		return menuRep.findAll();
	}

}
