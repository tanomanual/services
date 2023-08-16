package org.inneo.services.aplication;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import org.inneo.services.servicos.LoginService;

import org.inneo.services.domain.dtos.LoginResquest;
import org.inneo.services.domain.dtos.TokenResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Authentication")
@RequestMapping("/auth")
public class LoginControl {
	private final LoginService loginService;
	
	@Operation(summary = "Cadastrar novo login", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})
	@PostMapping("/register")
	public ResponseEntity<TokenResponse> register(@RequestBody  @Valid LoginResquest request) {
	   return ResponseEntity.ok(loginService.register(request));
	}
  
	@Operation(summary = "Autenticar login", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})
	@PostMapping("/login")
	public ResponseEntity<TokenResponse> authenticate(@RequestBody  @Valid LoginResquest request) {
	   return ResponseEntity.ok(loginService.authenticate(request));
	}	
}
