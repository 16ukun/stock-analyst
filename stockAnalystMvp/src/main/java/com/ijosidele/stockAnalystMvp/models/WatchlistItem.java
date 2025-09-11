package com.ijosidele.stockAnalystMvp.models;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "watchlist_items",
uniqueConstraints = @UniqueConstraint(columnNames = {"watchlist_id", "ticker"}))public class WatchlistItem {
//	  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//	  private Long id;
//
//	  @Column(nullable=false, length=16, unique=true)
//	  private String ticker;
//
//	  @Column(nullable=false, precision=18, scale=4)
//	  private BigDecimal price; //WHY BIGDECIMALS, AND NOT DOUBLE?
//
//	  private String currency;
//
//	  @Column(name="fetched_at", nullable=false)
//	  private Instant fetchedAt;
//
//	  public WatchlistItem(Long id, String ticker, BigDecimal price, String currency, Instant fetchedAt) {
//		super();
//		this.id = id;
//		this.ticker = ticker;
//		this.price = price;
//		this.currency = currency;
//		this.fetchedAt = fetchedAt;
//	}
//
//	  public Long getId() {
//		  return id;
//	  }
//
//	  public void setId(Long id) {
//		  this.id = id;
//	  }
//
//	  public String getTicker() {
//		  return ticker;
//	  }
//
//	  public void setTicker(String ticker) {
//		  this.ticker = ticker;
//	  }
//
//	  public BigDecimal getPrice() {
//		  return price;
//	  }
//
//	  public void setPrice(BigDecimal price) {
//		  this.price = price;
//	  }
//
//	  public String getCurrency() {
//		  return currency;
//	  }
//
//	  public void setCurrency(String currency) {
//		  this.currency = currency;
//	  }
//
//	  public Instant getFetchedAt() {
//		  return fetchedAt;
//	  }
//
//	  public void setFetchedAt(Instant fetchedAt) {
//		  this.fetchedAt = fetchedAt;
//	  }
//	  
//	  

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "watchlist_id", nullable = false)
    private Watchlist watchlist;

    @Column(nullable = false, length = 16)
    private String ticker;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    // Constructors
    public WatchlistItem() {}
    public WatchlistItem(String ticker) { this.ticker = ticker; }

    // Getters/Setters
    public Long getId() { return id; }
    public Watchlist getWatchlist() { return watchlist; }
    public void setWatchlist(Watchlist watchlist) { this.watchlist = watchlist; }
    public String getTicker() { return ticker; }
    public void setTicker(String ticker) { this.ticker = ticker; }
    public Instant getCreatedAt() { return createdAt; }

    // equals/hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WatchlistItem)) return false;
        WatchlistItem item = (WatchlistItem) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
