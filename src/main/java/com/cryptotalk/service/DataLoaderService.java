package com.cryptotalk.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.cryptotalk.models.QuoteModel;
import com.cryptotalk.worker.Worker;
import com.cryyptotalk.generated.CoinMarketCap;

@Service
public class DataLoaderService
{
    private static final Logger LOG = LogManager.getLogger(DataLoaderService.class);

    private final Map<String, List<QuoteModel>> binanceMap;
    private final Map<String, List<QuoteModel>> binanceMapTemp;
    private final Map<String, List<QuoteModel>> cryptopiaMap;
    private final Map<String, List<QuoteModel>> cryptopiaMapTemp;
    private final Map<String, List<QuoteModel>> bittrexMap;
    private final Map<String, List<QuoteModel>> bittrexMapTemp;
    private final Map<String, List<CoinMarketCap>> coinMarketCapMap;
    private final Map<String, List<CoinMarketCap>> coinMarketCapMapTemp;
    private final Worker worker;

    private DataLoaderService(Worker worker)
    {
        this.worker = worker;
        bittrexMapTemp = new LinkedHashMap<>();
        binanceMap = new LinkedHashMap<>();
        binanceMapTemp = new LinkedHashMap<>();
        bittrexMap = new LinkedHashMap<>();
        cryptopiaMapTemp = new LinkedHashMap<>();
        cryptopiaMap = new LinkedHashMap<>();
        coinMarketCapMap = new LinkedHashMap<>();
        coinMarketCapMapTemp = new LinkedHashMap<>();
    }

    public Optional<Map<String, List<CoinMarketCap>>> loadCoinMarketCapData()
    {
        coinMarketCapMap.clear();
        try {
            List<CoinMarketCap> cmcModel = this.worker.coinMarketCapWorker();
            coinMarketCapMap.put("cmc_quote", cmcModel);
            coinMarketCapMapTemp.put("cmc_quote", cmcModel);
        } catch (Exception ex) {
            LOG.debug("[DataLoaderService][loadBinanceData] Failed to load coin maket cap loader " + ex);
        }
        return Optional.ofNullable(coinMarketCapMap);
    }

    public Optional<Map<String, List<QuoteModel>>> loadBinanceData()
    {
        binanceMap.clear();
        try {
            List<QuoteModel> biQuoteModel = this.worker.binanceWorker();
            binanceMap.put("bi_quote", biQuoteModel);
            binanceMapTemp.put("bi_quote", biQuoteModel);
        } catch (Exception ex) {
            LOG.debug("[DataLoaderService][loadBinanceData] Failed to load bi loader " + ex);
        }
        return Optional.ofNullable(binanceMap);
    }

    public Optional<Map<String, List<QuoteModel>>> loadCryptopiaData()
    {
        cryptopiaMap.clear();
        try {
            List<QuoteModel> piaQuoteModel = this.worker.cryptopiaWorker();
            cryptopiaMap.put("cr_quote", piaQuoteModel);
            cryptopiaMapTemp.put("cr_quote", piaQuoteModel);
        } catch (Exception ex) {
            LOG.debug("[DataLoaderService][loadCryptopiaData] Failed to load cryptopia loader" + ex);
        }
        return Optional.ofNullable(cryptopiaMap);
    }

    public Optional<Map<String, List<QuoteModel>>> loadBittrexData()
    {
        bittrexMap.clear();
        try {
            List<QuoteModel> bittrexQuoteModel = this.worker.bittrexWorker();
            bittrexMap.put("bt_quote", bittrexQuoteModel);
            bittrexMapTemp.put("bt_quote", bittrexQuoteModel);
        } catch (Exception ex) {
            LOG.debug("[DataLoaderService][loadBittrexData] Failed to load bittrex data" + ex);
        }
        return Optional.ofNullable(bittrexMap);
    }

    public Optional<Map<String, List<CoinMarketCap>>> getCoinMarketCapData()
    {
        return Optional.ofNullable(coinMarketCapMap);
    }

    public Optional<Map<String, List<CoinMarketCap>>> getCoinMarketCapDataTemp()
    {
        return Optional.ofNullable(coinMarketCapMapTemp);
    }

    public Optional<Map<String, List<QuoteModel>>> getBinanceData()
    {
        return Optional.ofNullable(binanceMap);
    }

    public Optional<Map<String, List<QuoteModel>>> getBinanceDataTemp()
    {
        return Optional.ofNullable(binanceMapTemp);
    }

    public Optional<Map<String, List<QuoteModel>>> getCryptopiaData()
    {
        return Optional.ofNullable(cryptopiaMap);
    }

    public Optional<Map<String, List<QuoteModel>>> getCryptopiaDataTemp()
    {
        return Optional.ofNullable(cryptopiaMapTemp);
    }

    public Optional<Map<String, List<QuoteModel>>> getBittrexData()
    {
        return Optional.ofNullable(bittrexMap);
    }

    public Optional<Map<String, List<QuoteModel>>> getBittrexDataTemp()
    {
        return Optional.ofNullable(bittrexMapTemp);
    }

    @Async
    public CompletableFuture<Optional<Map<String, List<QuoteModel>>>> getBinanceDataAsync()
    {
        return CompletableFuture.completedFuture(Optional.ofNullable(binanceMap));
    }

    @Async
    public CompletableFuture<Optional<Map<String, List<QuoteModel>>>> getBinanceDataTempAsync()
    {
        return CompletableFuture.completedFuture(Optional.ofNullable(binanceMapTemp));
    }

    @Async
    public CompletableFuture<Optional<Map<String, List<QuoteModel>>>> getCryptopiaDataAsync()
    {
        return CompletableFuture.completedFuture(Optional.ofNullable(cryptopiaMap));
    }

    @Async
    public CompletableFuture<Optional<Map<String, List<QuoteModel>>>> getCryptopiaDataTempAsync()
    {
        return CompletableFuture.completedFuture(Optional.ofNullable(cryptopiaMap));
    }

    @Async
    public CompletableFuture<Optional<Map<String, List<QuoteModel>>>> getBittrexDataAsync()
    {
        return CompletableFuture.completedFuture(Optional.ofNullable(bittrexMap));
    }

    @Async
    public CompletableFuture<Optional<Map<String, List<QuoteModel>>>> getBittrexDataTempAsync()
    {
        return CompletableFuture.completedFuture(Optional.ofNullable(bittrexMapTemp));
    }

}
