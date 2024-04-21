package com.example.cryptotrading.scheduler;

import com.example.cryptotrading.model.Price;
import com.example.cryptotrading.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PriceAggregatorService {
    private final List<String> SYMBOLS = Arrays.asList("BTCUSDT", "ETHUSDT");

    private final RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private PriceRepository priceRepository;

    public List<BinanceItem> getBinancePrice() {
        String binanceUrl = "https://api.binance.com/api/v3/ticker/bookTicker";
        ResponseEntity<List<BinanceItem>> response = restTemplate.exchange(
                binanceUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BinanceItem>>(){}
        );
        // filter the response to only include the symbols we care about
        List<BinanceItem> filteredItems = response.getBody().stream()
                .filter(item -> SYMBOLS.contains(item.getSymbol()))
                .collect(Collectors.toList());
        return filteredItems;
    }

    public List<HuobiItem> getHuobiPrice() {
        String huobiUrl = "https://api.huobi.pro/market/tickers";
        ResponseEntity<HuobiResponse> response = restTemplate.exchange(
                huobiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<HuobiResponse>(){}
        );
        //System.out.println(response.getBody());

        List<HuobiItem> data = response.getBody().getData();
        //Filter the response to only include the symbols we care about
        List<HuobiItem> filteredItems = data.stream()
                .filter(item -> SYMBOLS.contains(item.getSymbol().toUpperCase()))
                .collect(Collectors.toList());

        return filteredItems;
    }

    public void storeBestPrice() {

        List<BinanceItem> binancePrice = getBinancePrice();
        List<HuobiItem> huobiPrice = getHuobiPrice();

        System.out.println("Binance price: " + binancePrice);
        System.out.println("Huobi price: " + huobiPrice);


        // Assuming the prices are stored in the "bidPrice" and "askPrice" fields
        for( int i = 0 ; i < SYMBOLS.size(); i++){
            String curr_symbol = SYMBOLS.get(i);

            List<BinanceItem> filtered_binance_items = binancePrice.stream()
                .filter(item -> item.getSymbol().equals(curr_symbol))
                .collect(Collectors.toList());
            List<HuobiItem> filtered_huobi_items = huobiPrice.stream()
                .filter(item -> item.getSymbol().equals(curr_symbol.toLowerCase()))
                .collect(Collectors.toList());
            if(filtered_binance_items.size() == 0 || filtered_huobi_items.size() == 0){
                continue;
            }


            double binanceBidPrice = Double.parseDouble(filtered_binance_items.get(0).getBidPrice());
            double binanceAskPrice = Double.parseDouble(filtered_binance_items.get(0).getAskPrice());
            double huobiBidPrice   = filtered_huobi_items.get(0).getBid();
            double huobiAskPrice   = filtered_huobi_items.get(0).getAsk();

            // Find the exchange with the lowest spread
            double binanceSpread = binanceAskPrice - binanceBidPrice;
            double huobiSpread = huobiAskPrice - huobiBidPrice;

            String bestExchange = binanceSpread < huobiSpread ? "Binance" : "Huobi";

            double bestBidPrice = bestExchange.equals("Binance") ? binanceBidPrice : huobiBidPrice;
            double bestAskPrice = bestExchange.equals("Binance") ? binanceAskPrice : huobiAskPrice;

            // Assuming you have a PriceRepository and a Price entity
            Price bestPrice = new Price(SYMBOLS.get(i), bestBidPrice, bestAskPrice, bestExchange, LocalDateTime.now());
            priceRepository.save(bestPrice);
        }

    }



    @Scheduled(fixedRate = 20000) // 10 seconds
    public void schedulePriceAggregation() {
        //System.out.println("Aggregating prices...");
        storeBestPrice();
    }
}