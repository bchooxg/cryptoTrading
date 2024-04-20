package com.example.cryptotrading.service;

import com.example.cryptotrading.model.Price;
import com.example.cryptotrading.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceService {
    @Autowired
    private PriceRepository priceRepository;

    public Price getLatestPrice(String symbol) {
        System.out.println("PriceService.getLatestPrice" + priceRepository.getLatestPrice(symbol));
        return priceRepository.getLatestPrice(symbol);
    }

    //public void updatePrice(String currencyPair, double askPrice, double bidPrice) {
    //    Price price = new Price();
    //    price.setCurrencyPair(currencyPair);
    //    price.setAskPrice(askPrice);
    //    price.setBidPrice(bidPrice);
    //    priceRepository.save(price);
    //}
}
