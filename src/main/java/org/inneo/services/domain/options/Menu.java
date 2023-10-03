package org.inneo.services.domain.options;


import jakarta.validation.constraints.NotBlank;
import org.inneo.services.domain.GenericEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_menu")
public class Menu extends GenericEntity{
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String classe;
	
	@NotBlank
	private String icon;
	
	@NotBlank
	@Column(unique = true)
	private String link;

}
