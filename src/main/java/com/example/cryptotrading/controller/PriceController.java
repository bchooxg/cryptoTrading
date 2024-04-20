package com.example.cryptotrading.controller;

import com.example.cryptotrading.model.Price;
import com.example.cryptotrading.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceController {
    @Autowired
    private PriceService priceService;

    @GetMapping("/prices/{currencyPair}")
    public Price getLatestPrice(@PathVariable String currencyPair) {
        return priceService.getLatestPrice(currencyPair);
    }
}
