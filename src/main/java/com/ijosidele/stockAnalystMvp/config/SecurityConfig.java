package com.ijosidele.stockAnalystMvp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	  @Bean
	  SecurityFilterChain filterChain(HttpSecurity http, JwtAuthFilter jwt) throws Exception {
	    return http
	      .csrf(csrf -> csrf.disable())
	      .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	      .authorizeHttpRequests(auth -> auth
	        .requestMatchers("/auth/**","/actuator/**","/health").permitAll()
	        .anyRequest().authenticated()
	      )
	      .addFilterBefore(jwt, UsernamePasswordAuthenticationFilter.class)
	      .build();
	  }

	  @Bean PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

	  @Bean AuthenticationManager authenticationManager() { return authentication -> authentication; }
}
