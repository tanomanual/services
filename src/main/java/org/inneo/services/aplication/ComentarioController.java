package org.inneo.services.aplication;


import java.util.UUID;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import org.inneo.services.servicos.ComentarioService;
import org.inneo.services.domain.comentario.Comentario;
import org.inneo.services.domain.dtos.ComentarioResponse;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestParam;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Comentarios")
@RequestMapping("/v1/comentarios")
public class ComentarioController {
	private final ComentarioService comentarioService;
	
	
	@Operation(summary = "Comentar historia", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Comentario publicado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})
	@PostMapping
	public ResponseEntity<Comentario> comentar(
			@RequestBody Comentario request,
			@RequestParam(name = "uuid", defaultValue = "") UUID uuid) {
		return new ResponseEntity<>(comentarioService.comentar(request, uuid), HttpStatus.CREATED);		
	}
	
	@Operation(summary = "Buscar feeds", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Historias encontradas com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})
	@GetMapping
	public ResponseEntity<Page<ComentarioResponse>> postagens(
			@RequestParam(name = "uuid", defaultValue = "") UUID uuid,
			@PageableDefault(page = 0, size = 18, sort = "published", direction = Sort.Direction.DESC) Pageable pageable) {
	   return ResponseEntity.ok(comentarioService.postagens(uuid, pageable));
	}
	

}
