package org.inneo.services.domain.usuario;

import lombok.Builder;
import java.util.List;
import java.util.Collection;

import lombok.NoArgsConstructor;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Enumerated;
import org.inneo.services.domain.enums.Role;
import org.inneo.services.domain.token.Token;
import org.inneo.services.domain.GenericEntity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_login")
public class Login extends GenericEntity implements UserDetails{	
	private static final long serialVersionUID = 1L;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(nullable = false)
	private String password;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "login")
	private List<Token> tokens;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
