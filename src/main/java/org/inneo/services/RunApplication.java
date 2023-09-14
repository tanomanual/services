package org.inneo.services;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.boot.SpringApplication;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
import lombok.Data;

@RestController
@SpringBootApplication
@Tag(name = "Sistem", description = "Status do sistema")
public class RunApplication {

	public static void main(String[] args) {
		SpringApplication.run(RunApplication.class, args);
	}
	
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
