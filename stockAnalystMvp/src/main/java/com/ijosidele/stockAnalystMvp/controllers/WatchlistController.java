package com.ijosidele.stockAnalystMvp.controllers;

import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ijosidele.stockAnalystMvp.config.WatchlistResponse;
import com.ijosidele.stockAnalystMvp.models.Watchlist;
import com.ijosidele.stockAnalystMvp.services.WatchlistService;

@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {
    private final WatchlistService svc;

    public WatchlistController(WatchlistService svc){
        this.svc = svc;
    }

    @GetMapping
    public ResponseEntity<WatchlistResponse> get(Authentication auth){
        String email = (String) auth.getPrincipal();
        Watchlist w = svc.getOrCreate(email);
        var items = w.getItems().stream()
            .map(i -> new WatchlistResponse.Item(i.getId(), i.getTicker(), i.getCreatedAt()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(new WatchlistResponse(w.getId(), items));
    }

    // @NotBlank
    public record AddRequest(String ticker) {}

    @PostMapping
    public ResponseEntity<WatchlistResponse> add(@RequestBody AddRequest req, Authentication auth){
        String email = (String) auth.getPrincipal();
        Watchlist w = svc.addItem(email, req.ticker());
        var items = w.getItems().stream()
            .map(i -> new WatchlistResponse.Item(i.getId(), i.getTicker(), i.getCreatedAt()))
            .collect(Collectors.toList());
        return ResponseEntity.ok(new WatchlistResponse(w.getId(), items));
    }

    @DeleteMapping("/{ticker}")
    public ResponseEntity<?> remove(@PathVariable String ticker, Authentication auth){
        String email = (String) auth.getPrincipal();
        svc.removeItem(email, ticker);
        return ResponseEntity.noContent().build();
    }
}
