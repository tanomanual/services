package org.inneo.services.domain.usuario;

import lombok.Getter;
import java.util.UUID;
import lombok.Builder;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.inneo.services.domain.GenericEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_usuario")
public class Usuario extends GenericEntity{
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String sobrenome;	

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "login_id", 
			nullable = false)
	public UUID loginId;
}
