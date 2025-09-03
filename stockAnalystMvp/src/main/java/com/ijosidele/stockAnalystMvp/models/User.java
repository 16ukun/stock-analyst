package com.ijosidele.stockAnalystMvp.models;

import java.time.Instant;
import java.util.Objects;

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
	
    // Default no args constructor required by JPA
    public User() {
    	
    }

    public User(String email, String passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.createdAt = Instant.now();
    }

 // Getters
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Watchlist getWatchlist() {
        return watchlist;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

//    public void setWatchlist(Watchlist watchlist) {
//        this.watchlist = watchlist;
//        if (watchlist != null) {
//            watchlist.setUser(this); // maintain bidirectional relationship
//        }
//    }

    // equals & hashCode based on 'id' for entity identity
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
