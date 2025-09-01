package com.ijosidele.stockAnalystMvp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ijosidele.stockAnalystMvp.models.StockCache;

@Repository
public interface StockCacheRepository extends JpaRepository<StockCache, Long> {
	Optional<StockCache> findByTicker(String ticker);
}
