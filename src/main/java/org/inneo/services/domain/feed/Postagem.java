package org.inneo.services.domain.feed;


import java.util.UUID;

import org.inneo.services.domain.GenericEntity;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class Postagem extends GenericEntity{
	private static final long serialVersionUID = 1L;
	
	@Lob
	@NotBlank
	private String texto;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "login_id", nullable = false)
	public UUID loginId;

}
