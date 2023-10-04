package org.inneo.services.servicos;

import java.util.List;
import lombok.AllArgsConstructor;

import org.inneo.services.domain.enums.MenuEnum;
import org.inneo.services.domain.options.Menu;
import org.inneo.services.domain.specs.MenuSpec;
import org.inneo.services.repository.MenuRep;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MenuService {
	private final MenuRep menuRep;
	
	public void save(Menu menu) {
		Menu create = menu.getUuid() != null? menuRep.getReferenceById(menu.getUuid()) : new Menu();
		BeanUtils.copyProperties(menu, create);
		menuRep.save(menu);
	}
	
	public List<Menu> findMenus() {
		return menuRep.findAll(MenuSpec.doTipo(MenuEnum.MENU));
	}
	
	public List<Menu> findSocial() {
		return menuRep.findAll(MenuSpec.doTipo(MenuEnum.SOCIAL));
	}

}
