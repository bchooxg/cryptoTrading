package com.example.cryptotrading.repository;

import com.example.cryptotrading.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    @Query("SELECT w FROM Wallet w WHERE w.username = :user_id ")
    List<Wallet> getBalances(@Param("user_id") String user_id);

    Wallet getWalletByUsernameAndSymbol(String userName, String symbol);

}