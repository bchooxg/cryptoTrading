package com.example.cryptotrading.service;

import com.example.cryptotrading.model.Trade;
import com.example.cryptotrading.model.User;
import com.example.cryptotrading.model.Wallet;
import com.example.cryptotrading.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.cryptotrading.repository.TradeRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TradeService {
    @Autowired
    private TradeRepository tradeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PriceService priceService;
    @Autowired
    private WalletService walletService;

    public List<Trade> getAllTrades() {
        return tradeRepository.getAllTrades();
    }

    public Trade createTrade(Trade trade) {
        // Check if the user has enough balance to buy the trade
        User user = userRepository.getUserByUsername(trade.getUsername());
        //Throw an exception if the user is not found
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        double balance = user.getCashBalance();
        double ask_price = priceService.getLatestPrice(trade.getSymbol()).getAskPrice();
        double bid_price = priceService.getLatestPrice(trade.getSymbol()).getBidPrice();


        // Get the user's wallet for the stock
        Wallet w = walletService.getWalletByUsernameAndSymbol(user.getUsername(), trade.getSymbol());

        System.out.println("TradeService.createTrade: total_price: " + trade);
        if (trade.getTradeType().equals("BUY")) {
            // Set the price of the trade
            trade.setPrice(ask_price);
            double amount = trade.getAmount();
            double total_price = amount * ask_price;

            // Check if the user has enough balance to buy the trade
            if (balance < total_price) {
                throw new RuntimeException("Insufficient balance");
            }
            // Check if the user has a wallet for the stock
            if (w == null){
                w = new Wallet();
                w.setSymbol(trade.getSymbol());
                w.setAmount(amount);
                w.setUsername(user.getUsername());

                walletService.createWallet(w);
            }
            trade.setPrice(ask_price);
            // Increase holdings
            w.setAmount(amount);
            // Update the user's wallet
            w.setTimestamp(LocalDateTime.now());
            // Update the user's wallet
            walletService.updateWallet(w);

            // Update the user's balance
            user.setCashBalance(balance - total_price);


        } else if(trade.getTradeType().equals("SELL")) {
            // Not implementing short orders, just sell if he has the stock

            // Set the price of the trade
            trade.setPrice(bid_price);
            double amount = trade.getAmount();
            double total_price = amount * bid_price;

            // Check if the user has a wallet for the stock
            if (w == null){
                throw new RuntimeException("No wallet found for the stock");
            }

            // Check if the user has enough stock to sell
            if (w.getAmount() < amount) {
                throw new RuntimeException("Insufficient stock");
            }

            // Decrease holdings
            w.setAmount(w.getAmount() - amount);
            // Update the user's wallet
            walletService.updateWallet(w);


            // Update the user's balance
            user.setCashBalance(balance + total_price);
        }
        // Update trade timestamp
        trade.setTimestamp(LocalDateTime.now());
        return tradeRepository.save(trade);
    }

    public List<Trade> getTradesByUserName(String username) {
        return tradeRepository.getTradesByUsername(username);
    }
}
