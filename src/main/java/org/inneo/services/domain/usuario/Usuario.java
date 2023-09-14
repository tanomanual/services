package org.inneo.services.domain.usuario;

import lombok.Builder;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.UUID;

import org.inneo.services.domain.GenericEntity;

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
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = true)
	private String mobile;

	@Column(name = "login_id", 
			nullable = false)
	public UUID loginId;
}
