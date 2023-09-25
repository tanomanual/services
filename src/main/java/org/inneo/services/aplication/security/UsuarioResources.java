package org.inneo.services.aplication.security;

import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.ResponseEntity;
import org.inneo.services.domain.usuario.Usuario;
import org.inneo.services.servicos.security.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.CrossOrigin;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Usuario")
@RequestMapping("/v1/usuario")
public class UsuarioResources {
	private final UsuarioService usuarioService;

	@Operation(summary = "Usuário", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})
	@GetMapping
	public ResponseEntity<Usuario> getUsuario() {
	   return ResponseEntity.ok(usuarioService.getLogado());
	}
}
