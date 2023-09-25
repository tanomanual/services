package org.inneo.services.domain;

import lombok.Data;
import java.util.Date;
import java.util.UUID;
import java.io.Serializable;

import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.MappedSuperclass;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class GenericEntity implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private UUID uuid;
	
	@CreationTimestamp
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private Date published;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private Date dataInativacao;
}
