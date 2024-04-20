package com.example.cryptotrading.service;

import com.example.cryptotrading.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.cryptotrading.repository.TradeRepository;

import java.util.List;

@Service
public class TradeService {
    @Autowired
    private TradeRepository tradeRepository;

    public List<Trade> getAllTrades() {
        return tradeRepository.getAllTrades();
    }

    public Trade createTrade(Trade trade) {
        return tradeRepository.save(trade);
    }

    public List<Trade> getTradesByUserName(String username) {
        return tradeRepository.getTradesByUsername(username);
    }
}
