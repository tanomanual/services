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
@RequestMapping("/v1/public")
@Tag(name = "Menus", description = "Menus do site.")
public class MenuResources {
	private final MenuService menuService;
	
	@Operation(summary = "save nenu", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cadastrado com sucesso." ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})	
	@PostMapping
	public ResponseEntity<?> sending(@RequestBody Menu menu) {
		try{
			menuService.save(menu);
			return ResponseEntity.ok().body("Menu cadastrado sucesso.");
		}catch (Exception e) {
			return ResponseEntity.badRequest().body("Não foi possivel cadastrar.");
		}	  
	}	
	
	@Operation(summary = "get menus", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Encontrado com sucesso." ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})	
	@GetMapping("/menu")
	public ResponseEntity<?> findMenus() {
		return ResponseEntity.ok(menuService.findMenus());		  
	}
	
	@Operation(summary = "get social", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Encontrado com sucesso." ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})	
	@GetMapping("/social")
	public ResponseEntity<?> findSocial() {
		return ResponseEntity.ok(menuService.findSocial());		  
	}
}
