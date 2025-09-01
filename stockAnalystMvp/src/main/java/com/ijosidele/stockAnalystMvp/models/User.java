package com.ijosidele.stockAnalystMvp.models;

import java.time.Instant;

import jakarta.persistence.*;

@Entity 
@Table(name = "users")
public class User {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, unique=true)
	private String email;

	@Column(name="password_hash", nullable=false)
	private String passwordHash;

	@Column(name="created_at", nullable=false)
	private Instant createdAt = Instant.now();

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Watchlist watchlist;
}
