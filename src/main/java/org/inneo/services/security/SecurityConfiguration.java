package org.inneo.services.security;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
	
	
	private final AuthenticationProvider authenticationProvider;
	private final JwtAuthFilter jwtAuthFilter;
	private final LogoutHandler logoutHandler;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {		
	    http.csrf().disable()
	        .authorizeHttpRequests().requestMatchers(openUrl()).permitAll()
	        .anyRequest().authenticated()	       
	        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and().authenticationProvider(authenticationProvider)
	        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
	        .logout().logoutUrl("/auth/logout").addLogoutHandler(logoutHandler)
	        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());
	    http.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
	    
	    http.headers()
        .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin", "*"))
	    .addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy","script-src 'self'"))
        .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Headers", "x-requested-with"))
        .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE"));
	    
	    return http.build();
	}
	
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
	private static final String[] openUrl() {
		String[] whitelist = {
				"/",
				"/auth/login",
				"/auth/register",
				"/favicon.ico",  
				"/v3/api-docs/**",
	            "/swagger-ui/**",
	            "/v2/api-docs/**",
	            "/swagger-resources/**"
		};
		return whitelist;
	}
}
