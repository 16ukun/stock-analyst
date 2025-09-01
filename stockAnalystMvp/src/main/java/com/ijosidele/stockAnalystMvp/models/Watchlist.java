package com.ijosidele.stockAnalystMvp.models;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity @Table(name = "watchlists")
public class Watchlist {

	  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;

	  @OneToOne
	  @JoinColumn(name = "user_id", nullable=false, unique=true)
	  private User user;

	  @Column(name="created_at", nullable=false)
	  private Instant createdAt = Instant.now();

	  @OneToMany(mappedBy = "watchlist", cascade = CascadeType.ALL, orphanRemoval = true)
	  private List<WatchlistItem> items = new ArrayList();;

	  public void addItem(WatchlistItem item) {
	    items.add(item); 
//	    item.setWatchlist(this); // WHAT'S HAPPENING HERE?
	  }
	  public void removeItem(WatchlistItem item) {
	    items.remove(item); 
//	    item.setWatchlist(null); // WHAT'S HAPPENING HERE?
	  }
}
