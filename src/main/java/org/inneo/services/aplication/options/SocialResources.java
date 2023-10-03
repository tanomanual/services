package org.inneo.services.aplication.options;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.Operation;

import org.inneo.services.domain.options.Social;
import org.inneo.services.servicos.SocialService;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/v1/social")
@Tag(name = "Redes sociais", description = "Redes sociais do perfil.")
public class SocialResources {
	private final SocialService socialService;
	
	@Operation(summary = "Cadastrar", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cadastrado com sucesso." ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})	
	@PostMapping
	public ResponseEntity<?> sending(@RequestBody Social social) {
		try{
			socialService.save(social);
			return ResponseEntity.ok().body("Rede social cadastrada sucesso.");
		}catch (Exception e) {
			return ResponseEntity.badRequest().body("Não foi possivel cadastrar.");
		}	  
	}	
	
	@Operation(summary = "Listar", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Encontradas com sucesso." ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})	
	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(socialService.fildAll());		  
	}

}
