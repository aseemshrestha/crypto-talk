package com.cryptotalk.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cryptotalk.models.QuoteModel;
import com.cryptotalk.service.DataLoaderService;
import com.cryptotalk.service.LoadersFacade;
import com.cryyptotalk.generated.CoinMarketCap;

@RestController
public class MarketController
{
    // @Autowired
    // private DataLoaderService loaderService;

    private final DataLoaderService loaderService;
    private final LoadersFacade facade;

    public MarketController(DataLoaderService loaderService, LoadersFacade facade)
    {
        this.loaderService = loaderService;
        this.facade = facade;
    }

    private static final Logger LOG = LogManager.getLogger(MarketController.class);

    @RequestMapping( value = "/", method = RequestMethod.GET )
    public String home()
    {
        return new String("test");
    }

    @RequestMapping( value = "/cmc", method = RequestMethod.GET, produces = { "application/json" } )
    public ResponseEntity<?> getCoinMarketCap()
    {
        Optional<Map<String, List<CoinMarketCap>>> cmcQuote = this.loaderService.getCoinMarketCapData();
        if (cmcQuote.get().isEmpty()) {
            LOG.info("Reloading CoinMarketCap data via temporary map");
            cmcQuote = this.loaderService.getCoinMarketCapData();
            assert !cmcQuote.get().isEmpty() : "CMC Resource Not Found";
        }
        return new ResponseEntity<>(cmcQuote.get(), HttpStatus.OK);

    }

    @RequestMapping( value = "/quotes-exchanges", method = RequestMethod.GET, produces = { "application/json" } )
    public ResponseEntity<?> getMarkets() throws InterruptedException, ExecutionException
    {
        // CompletableFuture<Optional<Map<String, List<QuoteModel>>>> binanceQuote;
        // CompletableFuture<Optional<Map<String, List<QuoteModel>>>> piaQuote;
        Map<String, Map<String, List<QuoteModel>>> marketMap = new LinkedHashMap<>();

        long start = System.currentTimeMillis();

        Optional<Map<String, List<QuoteModel>>> binanceQuote = this.loaderService.getBinanceData();
        Optional<Map<String, List<QuoteModel>>> piaQuote = this.loaderService.getCryptopiaData();
        Optional<Map<String, List<QuoteModel>>> bittrexQuote = this.loaderService.getBittrexData();

        if (binanceQuote.get().isEmpty()) {
            LOG.info("Reloading Binance data via temporary map");
            binanceQuote = this.loaderService.getBinanceDataTemp();
            assert !binanceQuote.get().isEmpty() : "Binance Resource Not Found";
        }
        if (piaQuote.get().isEmpty()) {
            LOG.info("Reloading Cryptopia data via temporary map");
            piaQuote = this.loaderService.getCryptopiaDataTemp();
            assert !piaQuote.get().isEmpty() : "Cryptopia Resource Not Found";
        }
        if (bittrexQuote.get().isEmpty()) {
            LOG.info("Reloading Bittrex data via temporary map");
            bittrexQuote = this.loaderService.getBittrexDataTemp();
            assert !bittrexQuote.get().isEmpty() : "Bittrex Resource Not Found";
        }

        // CompletableFuture.allOf(binanceQuote, piaQuote).join();
        marketMap.put("marketdata_bi", binanceQuote.get());
        marketMap.put("marketdata_pia", piaQuote.get());
        marketMap.put("marketdata_bt", bittrexQuote.get());

        LOG.info("[GetMarkets] time taken to get data--->:" + (System.currentTimeMillis() - start));

        return new ResponseEntity<>(marketMap, HttpStatus.OK);
    }

    @RequestMapping( value = "/loaders", method = RequestMethod.GET )
    public ResponseEntity<?> runLoaders()
    {
        int rows = this.facade.runLoaders();
        if (rows == 0) {
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("completed with " + rows, HttpStatus.OK);
    }

}
