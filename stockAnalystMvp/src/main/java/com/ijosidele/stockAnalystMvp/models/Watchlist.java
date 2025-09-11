package com.ijosidele.stockAnalystMvp.models;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity @Table(name = "watchlists")
public class Watchlist {

//	  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//	  private Long id;
//
//	  @OneToOne
//	  @JoinColumn(name = "user_id", nullable=false, unique=true)
//	  private User user;
//
//	  @Column(name="created_at", nullable=false)
//	  private Instant createdAt = Instant.now();
//
//	  @OneToMany(mappedBy = "watchlist", cascade = CascadeType.ALL, orphanRemoval = true)
//	  private List<WatchlistItem> items = new ArrayList();;
//
//	  public void addItem(WatchlistItem item) {
//	    items.add(item); 
////	    item.setWatchlist(this); // WHAT'S HAPPENING HERE?
//	  }
//	  public void removeItem(WatchlistItem item) {
//	    items.remove(item); 
////	    item.setWatchlist(null); // WHAT'S HAPPENING HERE?
//	  }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    @OneToMany(mappedBy = "watchlist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WatchlistItem> items = new ArrayList<>();

    // Constructors
    public Watchlist() {}
    public Watchlist(User user) { this.user = user; }

    // Relationship helpers
    public void addItem(WatchlistItem item) {
        items.add(item);
        item.setWatchlist(this);
    }

    public void removeItem(WatchlistItem item) {
        items.remove(item);
        item.setWatchlist(null);
    }

    // Getters/Setters
    public Long getId() { return id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Instant getCreatedAt() { return createdAt; }
    public List<WatchlistItem> getItems() { return items; }
    public void setItems(List<WatchlistItem> items) { this.items = items; }

    // equals/hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Watchlist)) return false;
        Watchlist that = (Watchlist) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
