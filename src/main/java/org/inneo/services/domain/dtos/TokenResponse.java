package org.inneo.services.domain.dtos;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
	private String token;
	private Collection<?> role;
}
