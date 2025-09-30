package com.ijosidele.stockAnalystMvp.controllers;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ijosidele.stockAnalystMvp.models.User;
import com.ijosidele.stockAnalystMvp.repositories.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.constraints.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
	  private final UserRepository users; private final PasswordEncoder encoder;
	  public AuthController(UserRepository users, PasswordEncoder encoder){ this.users=users; this.encoder=encoder; }

	  @Value("${security.jwt.secret}") String secret;
	  @Value("${security.jwt.issuer}") String issuer;
	  @Value("${security.jwt.expirationMinutes}") long expirationMinutes;

	  public record RegisterRequest(@Email String email, @Size(min=6) String password) {}
	  public record LoginRequest(@Email String email, @NotBlank String password) {}
	  public record TokenResponse(String token) {}

	  @PostMapping("/register")
	  public ResponseEntity<?> register(@RequestBody RegisterRequest req){
	    if (users.findByEmail(req.email()).isPresent()) return ResponseEntity.status(409).body("Email in use");
	    User u = new User(); u.setEmail(req.email()); u.setPasswordHash(encoder.encode(req.password()));
	    users.save(u);
	    return ResponseEntity.ok().build();
	  }

	  @PostMapping("/login")
	  public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest req){
	    var u = users.findByEmail(req.email()).orElse(null);
	    if (u==null || !encoder.matches(req.password(), u.getPasswordHash()))
	      return ResponseEntity.status(401).build();
	    var now = Instant.now();
	    var exp = now.plus(Duration.ofMinutes(expirationMinutes));
	    String token = Jwts.builder()
	      .setSubject(u.getEmail()).setIssuer(issuer)
	      .setIssuedAt(Date.from(now)).setExpiration(Date.from(exp))
	      .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
	      .compact();
	    return ResponseEntity.ok(new TokenResponse(token));
	  }
}
