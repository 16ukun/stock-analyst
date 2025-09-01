package com.ijosidele.stockAnalystMvp.models;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.*;

@Entity
@Table(name="stock_cache")
public class WatchlistItem {
	  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;

	  @Column(nullable=false, length=16, unique=true)
	  private String ticker;

	  @Column(nullable=false, precision=18, scale=4)
	  private BigDecimal price; //WHY BIGDECIMALS, AND NOT DOUBLE?

	  private String currency;

	  @Column(name="fetched_at", nullable=false)
	  private Instant fetchedAt;

	  public WatchlistItem(Long id, String ticker, BigDecimal price, String currency, Instant fetchedAt) {
		super();
		this.id = id;
		this.ticker = ticker;
		this.price = price;
		this.currency = currency;
		this.fetchedAt = fetchedAt;
	}

	  public Long getId() {
		  return id;
	  }

	  public void setId(Long id) {
		  this.id = id;
	  }

	  public String getTicker() {
		  return ticker;
	  }

	  public void setTicker(String ticker) {
		  this.ticker = ticker;
	  }

	  public BigDecimal getPrice() {
		  return price;
	  }

	  public void setPrice(BigDecimal price) {
		  this.price = price;
	  }

	  public String getCurrency() {
		  return currency;
	  }

	  public void setCurrency(String currency) {
		  this.currency = currency;
	  }

	  public Instant getFetchedAt() {
		  return fetchedAt;
	  }

	  public void setFetchedAt(Instant fetchedAt) {
		  this.fetchedAt = fetchedAt;
	  }
	  
	  
}
