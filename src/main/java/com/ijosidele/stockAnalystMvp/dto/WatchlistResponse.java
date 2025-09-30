package com.ijosidele.stockAnalystMvp.dto;

import java.time.Instant;
import java.util.List;

public record WatchlistResponse(Long id, List<Item> items) {
    public record Item(Long id, String ticker, Instant createdAt) {}
}
