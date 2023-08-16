package org.inneo.services.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocumentationConfig {
	
	@Bean
	public OpenAPI openApi() {
		return new OpenAPI()
			.info(new Info().title("Services 1.0")
			.version("1.0")
			.description("API INNEO SERVICES")					
			.contact(new Contact().name("inneo services")
					.url("https://inneo.org")
					.email("eduardo.cruz@inneo.org"))
			.license(new License()
					.name("Apache License Version 2.0")
					.url("https://www.apache.org/licenses/LICENSE-2.0.txt"))
			);
	}
}