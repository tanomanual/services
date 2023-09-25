package org.inneo.services.domain.feed;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import org.inneo.services.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;

import java.util.Collection;
import java.util.UUID;

import org.inneo.services.domain.GenericEntity;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.Lob;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_historia")
public class Historia  extends GenericEntity{
	private static final long serialVersionUID = 1L;
		
	@Lob
	@NotBlank
	@Column(name = "conteudo")
	private String conteudo;
	
	@ManyToOne
	@JsonProperty(access = Access.WRITE_ONLY)
	private Usuario usuario;
	
	@Column(name = "historia_uuid")
	private UUID publicacao;
	
	@Column(name = "comentario")
	private Boolean comentario;
	
	@Transient
	private Boolean podeEditar;
	
	@Transient
	private Integer comentaram;
	
	@Transient
	Collection<Historia> comentarios;
	
}
