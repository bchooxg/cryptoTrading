package com.example.cryptotrading.service;

import com.example.cryptotrading.model.Price;
import com.example.cryptotrading.model.Wallet;
import com.example.cryptotrading.repository.PriceRepository;
import com.example.cryptotrading.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {
    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private WalletRepository walletRepository;

    public List<Wallet> getWalletsByUsername(String userName) {
        return walletRepository.getBalances(userName);
    }

    public Price getLatestPrice(String symbol) {
        return priceRepository.getLatestPrice(symbol);
    }

    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    public Wallet updateWallet (Wallet wallet){
        return walletRepository.save(wallet);
    }

    public Wallet getWalletByUsernameAndSymbol(String userName, String symbol) {
        return walletRepository.getWalletByUsernameAndSymbol(userName, symbol);
    }
}


