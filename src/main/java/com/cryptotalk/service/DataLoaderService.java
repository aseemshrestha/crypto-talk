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

@Service
public class DataLoaderService
{
    private static final Logger LOG = LogManager.getLogger(DataLoaderService.class);

    private final Map<String, List<QuoteModel>> binanceMap = new LinkedHashMap<>();
    private final Map<String, List<QuoteModel>> binanceMapTemp = new LinkedHashMap<>();
    private final Map<String, List<QuoteModel>> cryptopiaMap = new LinkedHashMap<>();
    private final Map<String, List<QuoteModel>> cryptopiaMapTemp = new LinkedHashMap<>();
    private final Map<String, List<QuoteModel>> bittrexMap = new LinkedHashMap<>();
    private final Map<String, List<QuoteModel>> bittrexMapTemp = new LinkedHashMap<>();
    private final Worker worker;

    private DataLoaderService(Worker worker)
    {
        this.worker = worker;
    }

    public Optional<Map<String, List<QuoteModel>>> setBinanceData()
    {

        binanceMap.clear();
        List<QuoteModel> biQuoteModel;
        try {
            biQuoteModel = this.worker.binanceWorker();
            binanceMap.put("bi_quote", biQuoteModel);
            binanceMapTemp.put("bi_quote", biQuoteModel);
        } catch (Exception ex) {
            LOG.debug("[DataLoaderService][Selenium]Failed to load bi logger===> " + ex);
        }
        return Optional.ofNullable(binanceMap);
    }

    public Optional<Map<String, List<QuoteModel>>> setCryptoPiaData()
    {
        cryptopiaMap.clear();
        List<QuoteModel> piaQuoteModel;
        try {
            piaQuoteModel = this.worker.cryptopiaWorker();
            cryptopiaMap.put("cr_quote", piaQuoteModel);
            cryptopiaMapTemp.put("cr_quote", piaQuoteModel);
        } catch (Exception ex) {
            LOG.debug("[DataLoaderService] Cannot parse cryptopia json --------:" + ex);
        }
        return Optional.ofNullable(cryptopiaMap);
    }

    public Optional<Map<String, List<QuoteModel>>> setBittrexData()
    {
        bittrexMap.clear();
        List<QuoteModel> bittrexQuoteModel;
        try {
            bittrexQuoteModel = this.worker.bittrexWorker();
            bittrexMap.put("bt_quote", bittrexQuoteModel);
            bittrexMapTemp.put("bt_quote", bittrexQuoteModel);
        } catch (Exception ex) {
            LOG.debug("[DataLoaderService] Cannot parse bittrex json --------:" + ex);
        }
        return Optional.ofNullable(bittrexMap);
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
