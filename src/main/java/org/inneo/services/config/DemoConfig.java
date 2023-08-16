package org.inneo.services.config;

import lombok.Data;
import lombok.Builder;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/*")
@Tag(name = "Status", description = "API´s status.")
public class DemoConfig {
	
	@Operation(summary = "Verificar estado da aplicação", method = "GET")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna o status da aplicação!" ),
			@ApiResponse(responseCode = "400", description = "Servidor retornou uma resposta inesperada." )
	})
	@GetMapping
	public ResponseEntity<AplicationStatus> getStatus(){
		AplicationStatus aplicationStatus = AplicationStatus.builder().status(true).message("Aplication is runing...").build();
		return ResponseEntity.ok(aplicationStatus);
	}
}

@Data @Builder
class AplicationStatus {
	private Boolean status;
	private String message;
	
}
