package org.inneo.services.aplication.publicacoes;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import org.inneo.services.domain.publicacoes.Pagina;
import org.inneo.services.servicos.PaginaService;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/public/pagina")
@Tag(name = "Pagina", description = "Pagina site.")
public class PaginaResources {
	private final PaginaService paginaService;
	
	@Operation(summary = "Nova pagina", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Publicado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})
	@PostMapping
	public ResponseEntity<Pagina> publicar(@RequestBody Pagina pagina) {
	   return ResponseEntity.ok(paginaService.save(pagina));
	}
	
	@Operation(summary = "Carregar Pagina", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Localizada com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})
	@GetMapping
	public ResponseEntity<Optional<Pagina>> getPagina(@RequestParam(required = true) String nome) {
	   return ResponseEntity.ok(paginaService.getPagina(nome));
	}
	
	

}
