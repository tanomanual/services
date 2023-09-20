package org.inneo.services.aplication;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.inneo.services.domain.dtos.HistoriaResponse;
import org.inneo.services.domain.historia.Historia;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;

import org.inneo.services.servicos.HistoriaService;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Post")
@RequestMapping("/v1/historias")
public class HistoriaController {
	private final HistoriaService historiaService;
	
	@Operation(summary = "Nova historias", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Historias publicada com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})
	@PostMapping
	public ResponseEntity<Historia> postar(@RequestBody Historia request) {
	   return ResponseEntity.ok(historiaService.publicar(request));
	}
	
	@Operation(summary = "Delete historias", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Deletado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestParam UUID uuid) {
		try {
			historiaService.delete(uuid);
		    return new ResponseEntity<>("Historias removida com sucesso.", HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>("Não foi possivel remover a publicação.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@Operation(summary = "Buscar feeds", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Historias encontradas com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})
	@GetMapping
	public ResponseEntity<Page<HistoriaResponse>> postagens(@PageableDefault(page = 0, size = 18, sort = "published", direction = Sort.Direction.DESC) Pageable pageable) {
	   return ResponseEntity.ok(historiaService.postagens(pageable));
	}
	
	@Operation(summary = "Curtir historia", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Historias publicada com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})
	@PostMapping("/curtir")
	public ResponseEntity<?> curtir(@RequestParam(name = "uuid", defaultValue = "") UUID uuid) {
		try {
			historiaService.curtir(uuid);
			return new ResponseEntity<>("Historias curtida com sucesso.", HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>("Não foi possivel curtir a historia."+uuid, HttpStatus.BAD_REQUEST);
		}
	}
	
	

}
