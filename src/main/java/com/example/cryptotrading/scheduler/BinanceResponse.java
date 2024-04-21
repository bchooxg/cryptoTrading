package com.example.cryptotrading.scheduler;

import java.util.List;

public class BinanceResponse {
    private List<BinanceItem> binanceItems;

    public List<BinanceItem> getBinanceItems() {
        return binanceItems;
    }

    public void setBinanceItems(List<BinanceItem> binanceItems) {
        this.binanceItems = binanceItems;
    }

    public BinanceResponse(List<BinanceItem> binanceItems) {
        this.binanceItems = binanceItems;
    }
}
