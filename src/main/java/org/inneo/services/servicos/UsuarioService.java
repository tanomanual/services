package org.inneo.services.servicos;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.inneo.services.domain.usuario.Login;
import org.inneo.services.repository.UsuarioRep;
import org.inneo.services.domain.usuario.Usuario;

@Service
@RequiredArgsConstructor
public class UsuarioService {
	private final UsuarioRep usuarioRep;
	private final LoginService loginServer;
	
	public Usuario getUsuario() {
		Login login = loginServer.getLogado();
		Usuario usuario =  usuarioRep.findByLoginId(login.getUuid());
		return usuario;
	}

}
