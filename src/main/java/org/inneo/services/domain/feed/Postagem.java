package org.inneo.services.domain.feed;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.inneo.services.domain.usuario.Usuario;
import org.inneo.services.domain.GenericEntity;
import jakarta.validation.constraints.NotBlank;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.Setter;
import lombok.Getter;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_post")
public class Postagem extends GenericEntity{
	private static final long serialVersionUID = 1L;
	
	@Lob
	@NotBlank
	private String texto;
	
	@ManyToOne
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Usuario usuario;
}
