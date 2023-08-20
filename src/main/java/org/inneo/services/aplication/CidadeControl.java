package org.inneo.services.aplication;

import java.util.List;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.inneo.services.domain.cidades.Cidade;
import org.inneo.services.domain.dtos.CidadeResponse;
import org.inneo.services.servicos.CidadeService;
import org.springframework.data.web.PageableDefault;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "localhost:8080")
@RequestMapping("/api/v1/cidades")
@Tag(name = "Cidades", description = "API´s Cidades brasileiras.")
public class CidadeControl {
	private final CidadeService cidadeService;
	
	@Operation(summary = "Cadastrar uma cidade", method = "POST")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cadastro realizado com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Servidor retornou uma resposta inesperada." ),
			@ApiResponse(responseCode = "401", description = "Você não possui autorização para cadastros!" )
	})
	@PostMapping
	public ResponseEntity<Cidade> create(@RequestBody @Valid Cidade cidade){
		return ResponseEntity.ok(cidadeService.create(cidade));
	}
	
	
	@Operation(summary = "Buscar uma cidade pelo código", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cidade localizada com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Servidor retornou uma resposta inesperada." ),
			@ApiResponse(responseCode = "401", description = "Você não possui autorização para visualizar!" )
	})
	@GetMapping("/codigo={codigo}")
	public ResponseEntity<Cidade> getCodigo(@PathVariable(name="codigo") Long codigo){
		return ResponseEntity.ok(cidadeService.getCod(codigo));
	}
	
	@Operation(summary = "Listar cidades", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cidades localizadas com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Servidor retornou uma resposta inesperada." ),
			@ApiResponse(responseCode = "401", description = "Você não possui autorização para visualizar!" )
	})
	@GetMapping("/listar")
	public ResponseEntity<List<CidadeResponse>> findAll(){
		return ResponseEntity.ok(cidadeService.findAll());
	}
	
	
	@Operation(summary = "Buscar páginada", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cidades localizadas com sucesso!" ),
			@ApiResponse(responseCode = "400", description = "Servidor retornou uma resposta inesperada." ),
			@ApiResponse(responseCode = "401", description = "Você não possui autorização para visualizar!" )
	})
	@GetMapping
	public ResponseEntity<Page<Cidade>> getPages(@PageableDefault(page = 0, size = 10, sort = "cidade", direction = Sort.Direction.ASC) Pageable pageable){
		return ResponseEntity.ok(cidadeService.getPages(pageable));
	}
}
