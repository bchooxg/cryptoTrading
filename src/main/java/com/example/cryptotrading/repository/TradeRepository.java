package com.example.cryptotrading.repository;

import com.example.cryptotrading.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, Long> {
//    find all trades by user id
    @Query("SELECT t FROM Trade t WHERE t.username = :username ")
    Trade getTrades(@Param("username") String username);

//    Get all trades
    @Query("SELECT t FROM Trade t")
    List<Trade> getAllTrades();

    List<Trade> getTradesByUsername(String username);
}
