package org.inneo.services.servicos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.inneo.services.repository.UsuarioRep;
import org.inneo.services.domain.usuario.Usuario;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
@RequiredArgsConstructor
public class UsuarioService {
	private final UsuarioRep usuarioRep;
	
	public Usuario getLogado() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();       
        return usuarioRep.findByLoginUsername(username);
    }

}
