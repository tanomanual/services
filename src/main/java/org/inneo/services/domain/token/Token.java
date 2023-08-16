package org.inneo.services.domain.token;

import java.util.Date;

import org.inneo.services.domain.GenericEntity;
import org.inneo.services.domain.enums.TokenType;
import org.inneo.services.domain.usuario.Login;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_token")
public class Token extends GenericEntity{
	private static final long serialVersionUID = 1L;

	@Column(unique = true)
	public String token;

	@Enumerated(EnumType.STRING)
	public TokenType tokenType;

	@Column(name = "renovado")
	private boolean revoked;
	
	@Column(name = "expirado")
	private boolean expired;
	
	@Column(name = "data_cadastro")
	private Date dataCadastro; 
	
	@Column(name = "data_inativacao")
	private Date dataInativacao;

	@ManyToOne
	@JoinColumn(name = "login_id")
	public Login login;
}
