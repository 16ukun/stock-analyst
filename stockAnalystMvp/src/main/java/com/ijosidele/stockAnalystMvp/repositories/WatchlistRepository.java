package com.ijosidele.stockAnalystMvp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ijosidele.stockAnalystMvp.models.User;
import com.ijosidele.stockAnalystMvp.models.Watchlist;

public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {
//	  @EntityGraph(attributePaths = {"items"})
//	  Optional<Watchlist> findByUser_Email(String email);
    Optional<Watchlist> findByUser(User user);

}
