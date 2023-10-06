package org.inneo.services.domain.publicacoes;


import org.inneo.services.domain.GenericEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Column;
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
@Table(name = "_pagina")
public class Pagina extends GenericEntity{
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Column(name = "titulo")
	private String titulo;
		
	@Lob
	@NotBlank
	@Column(name = "conteudo")
	private String conteudo;	
	
	@NotBlank
	@Column(name = "nome", unique = true)
	private String nome;

}
