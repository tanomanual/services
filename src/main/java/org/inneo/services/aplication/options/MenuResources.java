package org.inneo.services.aplication.options;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.inneo.services.domain.options.Menu;
import org.inneo.services.servicos.MenuService;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/v1/menu")
@Tag(name = "Menu principal", description = "Menu do site.")
public class MenuResources {
	private final MenuService menuService;
	
	@Operation(summary = "Cadastrar", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cadastrado com sucesso." ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})	
	@PostMapping
	public ResponseEntity<?> sending(@RequestBody Menu menu) {
		try{
			 menuService.save(menu);
			return ResponseEntity.ok().body("Menu item cadastrado sucesso.");
		}catch (Exception e) {
			return ResponseEntity.badRequest().body("Não foi possivel cadastrar.");
		}	  
	}	
	
	@Operation(summary = "Listar", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Encontrado com sucesso." ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})	
	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(menuService.fildAll());		  
	}
}
