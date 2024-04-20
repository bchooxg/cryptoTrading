package com.example.cryptotrading.controller;

import com.example.cryptotrading.model.Wallet;
import com.example.cryptotrading.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wallets")
public class WalletController{
    @Autowired
    private WalletService walletService;

    // Considered to include the current market value of the wallet but currently

    @GetMapping("/{userName}")
    public List<Wallet> getBalances(@PathVariable String userName) {
        //System.out.println("WalletController.getBalances" + userName);
        //System.out.println("WalletController.getBalances" + walletService.getBalances(userName));
        return walletService.getWalletsByUsername(userName);
    }
    }
