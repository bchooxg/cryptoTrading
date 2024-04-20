package com.example.cryptotrading.controller;

import com.example.cryptotrading.model.Trade;
import com.example.cryptotrading.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trades")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    //get trades by username
    @GetMapping("/{username}")
    public List<Trade> getTradesByUserName(@PathVariable String username) {
        return tradeService.getTradesByUserName(username);
    }

    @GetMapping
    public List<Trade> getTrades() {
        return tradeService.getAllTrades();
    }

    @PostMapping
    public Trade createTrade(@RequestBody Trade trade) {
        return tradeService.createTrade(trade);
    }
}