package com.ijosidele.stockAnalystMvp.models;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name="stock_cache")
public class StockCache {
	
//	  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//	  private Long id;
//
//	  @Column(nullable=false, length=16, unique=true)
//	  private String ticker;
//
//	  @Column(nullable=false, precision=18, scale=4)
//	  private BigDecimal price;
//
//	  private String currency;
//
//	  @Column(name="fetched_at", nullable=false)
//	  private Instant fetchedAt;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 16, unique = true)
    private String ticker;

    @Column(nullable = false, precision = 18, scale = 4)
    private BigDecimal price;

    private String currency;

    @Column(name = "fetched_at", nullable = false)
    private Instant fetchedAt;

    // Constructors
    public StockCache() {}
    public StockCache(String ticker, BigDecimal price, String currency, Instant fetchedAt) {
        this.ticker = ticker;
        this.price = price;
        this.currency = currency;
        this.fetchedAt = fetchedAt;
    }

    // Getters/Setters
    public Long getId() { return id; }
    public String getTicker() { return ticker; }
    public void setTicker(String ticker) { this.ticker = ticker; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public Instant getFetchedAt() { return fetchedAt; }
    public void setFetchedAt(Instant fetchedAt) { this.fetchedAt = fetchedAt; }

    // equals/hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockCache)) return false;
        StockCache that = (StockCache) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
