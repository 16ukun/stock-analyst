package com.ijosidele.stockAnalystMvp.services;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClient;

import com.ijosidele.stockAnalystMvp.models.StockCache;
import com.ijosidele.stockAnalystMvp.repositories.StockCacheRepository;

public class StockService {
	  private final StockCacheRepository cache;
	  private final RestClient http = RestClient.create();

	  @Value("${alphaVantage.apiKey:}") String apiKey;

	  public StockService(StockCacheRepository cache){ this.cache = cache; }

	  public Map<String,Object> analyze(String ticker) {
	    var q = fetchLatestPrice(ticker);
	    Map<String,Object> out = new LinkedHashMap<String, Object>();
	    out.put("ticker", ticker.toUpperCase());
	    out.put("price", q.price());
	    out.put("currency", q.currency());
	    out.put("signals", List.of("MA(20): TBD", "PE: TBD")); // placeholder; expand later
	    return out;
	  }

	  private record Quote(BigDecimal price, String currency) {}

	  private Quote fetchLatestPrice(String rawTicker) {
	    String ticker = rawTicker.toUpperCase();
	    var cached = cache.findByTicker(ticker)
	      .filter(c -> Duration.between(c.getFetchedAt(), Instant.now()).toSeconds() < 60)
	      .orElse(null);
	    if (cached != null) return new Quote(cached.getPrice(), cached.getCurrency());

	    if (apiKey == null || apiKey.isBlank())
	      throw new IllegalStateException("ALPHA_VANTAGE_API_KEY missing");

	    Map<?,?> resp = http.get()
	      .uri("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol={t}&apikey={k}", ticker, apiKey)
	      .retrieve()
	      .body(Map.class);

	    Map<String,String> quote = (Map<String,String>) resp.get("Global Quote");
	    BigDecimal price = new BigDecimal(quote.get("05. price"));
	    String currency = "USD";

	    var sc = cache.findByTicker(ticker).orElseGet(StockCache::new);
	    sc.setTicker(ticker); sc.setPrice(price); sc.setCurrency(currency); sc.setFetchedAt(Instant.now());
	    cache.save(sc);
	    return new Quote(price, currency);
	  }
}
