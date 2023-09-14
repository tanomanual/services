package org.inneo.services.domain.feed;


import org.inneo.services.domain.GenericEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
public class Post extends GenericEntity{
	private static final long serialVersionUID = 1L;
	
	@Lob
	@NotBlank
	private String texto;

	@Column(name = "username")
	private String username;

}
