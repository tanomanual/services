package org.inneo.services.aplication;


import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.inneo.services.domain.feed.Post;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.inneo.services.servicos.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Post")
@RequestMapping("/v1/post")
public class PostController {
	private final PostService postService;
	
	@Operation(summary = "Nova postagem", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Post realizado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})
	@PostMapping
	public ResponseEntity<Post> postar(@RequestBody  Post request) {
	   return ResponseEntity.ok(postService.publicar(request));
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
			postService.delete(uuid);
		    return new ResponseEntity<>("Publicação removida com sucesso.", HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>("Não foi possivel remover a publicação.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@Operation(summary = "Buscar feeds", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Feed encontrados com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Requisição falhou." ),
			@ApiResponse(responseCode = "401", description = "Permissão negada!" )
	})
	@GetMapping
	public ResponseEntity<List<Post>> feeds() {
	   return ResponseEntity.ok(postService.feeds());
	}
	
	

}
