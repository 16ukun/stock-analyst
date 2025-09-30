package com.ijosidele.stockAnalystMvp.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ijosidele.stockAnalystMvp.services.StockService;

@Controller
public class StockController {
	private final StockService stockService;
	
	public StockController (StockService stockService) {
		this.stockService = stockService;
	}
	
	@GetMapping("/{ticker}")
	public Map<String, Object> get(@PathVariable String ticker) {
		return stockService.analyze(ticker);
	}
}
