package org.inneo.services.servicos;

import java.util.Date;
import lombok.RequiredArgsConstructor;

import org.inneo.services.domain.enums.Role;
import org.inneo.services.domain.token.Token;
import org.inneo.services.repository.LoginRep;
import org.inneo.services.repository.TokenRep;

import org.inneo.services.security.JwtService;
import org.springframework.stereotype.Service;
import org.inneo.services.domain.usuario.Login;

import org.inneo.services.domain.enums.TokenType;
import org.inneo.services.domain.dtos.LoginResquest;
import org.inneo.services.domain.dtos.TokenResponse;

import org.inneo.services.config.exceptions.ObjectDefaultException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Service
@RequiredArgsConstructor
public class LoginService {
	private final LoginRep loginRep;
	private final TokenRep tokenRep;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authentication;
	
	public TokenResponse register(LoginResquest request) {		
	if(request.username() == null && loginRep.findByUsername(request.username()) != null) {
		throw new ObjectDefaultException("Login indisponível.");
	}
		
	var usuario = Login.builder()
	   .username(request.username())
	   .password(passwordEncoder.encode(request.password()))
	   .role(Role.ROLE_USER)
	   .build();
	var savedUser = loginRep.save(usuario); 
	var jwtToken = jwtService.generateToken(usuario);
	registrarToken(savedUser, jwtToken);
	    
	return TokenResponse.builder()
	    .token(jwtToken)
	    .build();
	}
	
	public TokenResponse authenticate(LoginResquest request) {
		authentication.authenticate(
	        new UsernamePasswordAuthenticationToken(
	            request.username(),
	            request.password()
	        )
	    );
	    
	    var login = loginRep.findByUsername(request.username()).orElseThrow();
	    var jwtToken = jwtService.generateToken(login);
	    closeTokens(login);
	    registrarToken(login, jwtToken);	    
	    return TokenResponse.builder()
	        .token(jwtToken)
	        .build();
	}
	
	private void registrarToken(Login login, String jwtToken) {
		var token = Token.builder()
	        .login(login)
	        .token(jwtToken)
	        .tokenType(TokenType.BEARER)
	        .expired(false)
	        .revoked(false)
	        .dataCadastro(new Date())	        
	        .build();
		tokenRep.save(token);
	}
	
	private void closeTokens(Login login) {
	    var validUserTokens = tokenRep.findAllValidTokenByUsuario(login.getId());
	    if (validUserTokens.isEmpty()) return;
	    
	    validUserTokens.forEach(token -> {
	      token.setExpired(true);
	      token.setRevoked(true);
	      token.setDataInativacao(new Date());
	    });
	    tokenRep.saveAll(validUserTokens);
	  }
}
