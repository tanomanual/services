package org.inneo.services.aplication.feed;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.inneo.services.domain.feed.Historia;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;

import org.inneo.services.servicos.HistoriaService;
import org.springframework.data.web.PageableDefault;
import org.inneo.services.domain.dtos.HistoriaResponse;

import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RequestParam;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/v1/historias")
@Tag(name = "Historia", description = "Historias do feed.")
public class HistoriaResources {
	private final HistoriaService historiaService;
	
	@Operation(summary = "Nova historias", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Publicado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})
	@PostMapping
	public ResponseEntity<HistoriaResponse> postar(@RequestBody Historia historia) {
	   return ResponseEntity.ok(historiaService.save(historia));
	}
	
	@Operation(summary = "Localizar historia", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Localizada com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})
	@GetMapping("/uuid=")
	public ResponseEntity<HistoriaResponse> getID(@RequestParam(required = true) UUID uuid) {
	   return ResponseEntity.ok(historiaService.getID(uuid));
	}
	
	@Operation(summary = "Deletar historia", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Deletado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})
	@DeleteMapping("/uuid=")
	public ResponseEntity<?> delete(@RequestParam(required = true) UUID uuid) {
		return ResponseEntity.ok(historiaService.delete(uuid));
	}
	
	@Operation(summary = "Localizar historias", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Localizadas com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})
	@GetMapping
	public ResponseEntity<Page<HistoriaResponse>> findAll(
			@PageableDefault(page = 0, size = 9, sort = "published", direction = Sort.Direction.DESC) Pageable pageable) {
	   return ResponseEntity.ok(historiaService.findAll(pageable));
	}
	
	
	@Operation(summary = "Novo comentario", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Publicado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})
	@PostMapping("/comentario")
	public ResponseEntity<HistoriaResponse> comentar(
			@RequestParam(required = true) UUID uuid,
			@RequestBody Historia historia) {
	   return ResponseEntity.ok(historiaService.comentar(uuid, historia));
	}	

}
