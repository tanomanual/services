package org.inneo.services.domain.comentario;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.inneo.services.domain.historia.Historia;
import org.inneo.services.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import org.inneo.services.domain.GenericEntity;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "_comentarios")
public class Comentario extends GenericEntity{
	private static final long serialVersionUID = 1L;
	
	@Lob
	@NotBlank
	private String conteudo;
	
	@ManyToOne
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Usuario usuario;

	@ManyToOne
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Historia historia;

}
