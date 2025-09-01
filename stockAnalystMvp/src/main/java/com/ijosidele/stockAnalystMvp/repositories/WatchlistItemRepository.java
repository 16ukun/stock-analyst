package com.ijosidele.stockAnalystMvp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ijosidele.stockAnalystMvp.models.WatchlistItem;

public interface WatchlistItemRepository extends JpaRepository<WatchlistItem, Long> {
	  Optional<WatchlistItem> findByWatchlist_IdAndTickerIgnoreCase(Long watchlistId, String ticker);
}
