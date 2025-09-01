package com.ijosidele.stockAnalystMvp.models;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.*;

@Entity
@Table(name="stock_cache")
public class StockCache {
	
	  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;

	  @Column(nullable=false, length=16, unique=true)
	  private String ticker;

	  @Column(nullable=false, precision=18, scale=4)
	  private BigDecimal price;

	  private String currency;

	  @Column(name="fetched_at", nullable=false)
	  private Instant fetchedAt;
}
