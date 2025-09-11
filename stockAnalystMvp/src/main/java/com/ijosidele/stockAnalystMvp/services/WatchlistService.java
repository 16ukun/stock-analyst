package com.ijosidele.stockAnalystMvp.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ijosidele.stockAnalystMvp.models.User;
import com.ijosidele.stockAnalystMvp.models.Watchlist;
import com.ijosidele.stockAnalystMvp.models.WatchlistItem;
import com.ijosidele.stockAnalystMvp.repositories.UserRepository;
import com.ijosidele.stockAnalystMvp.repositories.WatchlistItemRepository;
import com.ijosidele.stockAnalystMvp.repositories.WatchlistRepository;

import jakarta.transaction.Transactional;

@Service
public class WatchlistService {
	  private final UserRepository users;
	  private final WatchlistRepository watchlists;
	  private final WatchlistItemRepository items;

	  public WatchlistService(UserRepository users, WatchlistRepository watchlists, WatchlistItemRepository items) {
	    this.users = users; this.watchlists = watchlists; this.items = items;
	  }

	  /** Ensure the user has a watchlist; create if missing. */
	  @Transactional
	  public Watchlist getOrCreate(String userEmail) {
	    return watchlists.findByUser_Email(userEmail).orElseGet(() -> {
	      User u = users.findByEmail(userEmail).orElseThrow();
	      Watchlist w = new Watchlist();
	      w.setUser(u);
	      return watchlists.save(w);
	    });
	  }

	  @Transactional
	  public Watchlist addItem(String userEmail, String rawTicker) {
	    String ticker = rawTicker.toUpperCase().trim();
	    Watchlist w = getOrCreate(userEmail);
	    if (items.findByWatchlist_IdAndTickerIgnoreCase(w.getId(), ticker).isPresent()) return w;
	    WatchlistItem i = new WatchlistItem(); 
	    i.setTicker(ticker);
	    w.addItem(i);
	    return watchlists.save(w);
	  }

	  @Transactional
	  public void removeItem(String userEmail, String rawTicker) {
	    String ticker = rawTicker.toUpperCase().trim();
	    Watchlist w = getOrCreate(userEmail);
	    items.findByWatchlist_IdAndTickerIgnoreCase(w.getId(), ticker)
	      .ifPresent(i -> { w.removeItem(i); items.delete(i); });
	  }

	  public Optional<Watchlist> get(String userEmail) {
	    return watchlists.findByUser_Email(userEmail);
	  }
}
