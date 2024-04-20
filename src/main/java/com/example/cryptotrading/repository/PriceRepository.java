package com.example.cryptotrading.repository;

import com.example.cryptotrading.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PriceRepository extends JpaRepository<Price, Long> {
    @Query("SELECT p FROM Price p WHERE p.symbol = :symbol ORDER BY p.timestamp DESC")
    Price getLatestPrice(@Param("symbol") String symbol);
}