package org.inneo.services.domain.historia;


import com.fasterxml.jackson.annotation.JsonProperty;

import org.inneo.services.domain.usuario.Usuario;
import org.inneo.services.domain.GenericEntity;
import jakarta.validation.constraints.NotBlank;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import jakarta.persistence.Lob;
import java.util.Collection;
import lombok.Builder;
import lombok.Setter;
import lombok.Getter;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_historia")
public class Historia extends GenericEntity{
	private static final long serialVersionUID = 1L;
	
	@Lob
	@NotBlank
	private String conteudo;
	
	@ManyToOne
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Usuario usuario;
	
	@JoinTable(name = "_curtidas")
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Usuario> curtidas;
}
