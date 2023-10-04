package org.inneo.services.domain.options;


import org.inneo.services.domain.GenericEntity;
import org.inneo.services.domain.enums.MenuEnum;
import jakarta.validation.constraints.NotBlank;

import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import jakarta.persistence.Table;
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
	private String name;
	
	@NotBlank
	private String icon;
	
	@NotBlank
	@Column(unique = true)
	private String caminho;
	
	@Enumerated(EnumType.STRING)
	private MenuEnum menuEnum;

}
