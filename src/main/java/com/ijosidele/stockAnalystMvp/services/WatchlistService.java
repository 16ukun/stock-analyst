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
@Transactional
public class WatchlistService {
	  private final UserRepository userRepo;
	  private final WatchlistRepository watchlistRepository;
	  private final WatchlistItemRepository itemRepository;

	  public WatchlistService(UserRepository userRepo, WatchlistRepository watchlistRepository, WatchlistItemRepository itemRepository) {
	    this.userRepo = userRepo; this.watchlistRepository = watchlistRepository; this.itemRepository = itemRepository;
	  }

	    public Watchlist getOrCreate(String email) {
	        User user = userRepo.findByEmail(email)
	                .orElseThrow(() -> new RuntimeException("User not found: " + email));

	        return watchlistRepository.findByUser(user)
	                .orElseGet(() -> {
	                    Watchlist wl = new Watchlist(user);
	                    return watchlistRepository.save(wl);
	                });
	    }

	    public Watchlist addItem(String email, String ticker) {
	        Watchlist wl = getOrCreate(email);
	        // Avoid duplicates
	        boolean exists = wl.getItems().stream()
	                .anyMatch(item -> item.getTicker().equalsIgnoreCase(ticker));
	        if (!exists) {
	            WatchlistItem item = new WatchlistItem(ticker);
	            wl.addItem(item);
	            watchlistRepository.save(wl); // cascades save to item
	        }
	        return wl;
	    }

	    public void removeItem(String email, String ticker) {
	        Watchlist wl = getOrCreate(email);
	        itemRepository.deleteByWatchlistIdAndTicker(wl.getId(), ticker);
	    }
}
