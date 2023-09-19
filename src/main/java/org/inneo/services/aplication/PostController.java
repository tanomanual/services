package org.inneo.services.aplication;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.inneo.services.domain.dtos.PostagemResponse;
import org.inneo.services.domain.feed.Postagem;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;

import org.inneo.services.servicos.PostagemService;
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
@RequestMapping("/v1/postagens")
public class PostController {
	private final PostagemService postagemService;
	
	@Operation(summary = "Nova postagem", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Post realizado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})
	@PostMapping
	public ResponseEntity<Postagem> postar(@RequestBody  Postagem request) {
	   return ResponseEntity.ok(postagemService.publicar(request));
	}
	
	@Operation(summary = "Delete post", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Deletado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestParam UUID uuid) {
		try {
			postagemService.delete(uuid);
		    return new ResponseEntity<>("Publicação removida com sucesso.", HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>("Não foi possivel remover a publicação.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@Operation(summary = "Buscar feeds", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Feed encontrados com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})
	@GetMapping
	public ResponseEntity<Page<PostagemResponse>> postagens(@PageableDefault(page = 0, size = 18, sort = "published", direction = Sort.Direction.ASC) Pageable pageable) {
	   return ResponseEntity.ok(postagemService.postagens(pageable));
	}
	
	

}
