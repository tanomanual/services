package org.inneo.services.domain.usuario;

import lombok.Getter;
import lombok.Builder;

import lombok.NoArgsConstructor;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import jakarta.persistence.JoinColumn;

import org.inneo.services.domain.GenericEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_usuario")
public class Usuario extends GenericEntity{
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String sobrenome;	

	@Transient
	@Column(name = "nome_completo")
	private String nomeCompleto; 	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "nascimento")
	private String nascimento;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "mobile")
	private String mobile;
	
	@OneToOne
    @JoinColumn(name="id_login")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Login login;		

	public String getNomeCompleto() {
        return nome + " " + sobrenome;
    }
}
