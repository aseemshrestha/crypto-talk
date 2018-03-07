package com.cryptotalk.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cryptotalk.exchanges.Drivers;
import com.cryptotalk.exchanges.Exchanges;
import com.cryptotalk.models.QuoteModel;
import com.cryptotalk.service.DataLoaderService;
import com.cryptotalk.service.NewsService;
import com.cryptotalk.service.QuoteService;
import com.cryptotalk.util.ResourceNotFoundException;
import com.cryyptotalk.generated.News;

@RestController
public class HomeController
{

    private NewsService newsService;
    private QuoteService quoteService;
    private DataLoaderService loaderService;

    public HomeController(NewsService newsService, QuoteService quoteService, DataLoaderService loaderService)
    {
        this.newsService = newsService;
        this.quoteService = quoteService;
        this.loaderService = loaderService;
    }

    @RequestMapping( value = "/news", method = RequestMethod.GET, produces = { "application/json" } )
    public ResponseEntity<?> getNews(@RequestParam( value = "cache", required = false ) String cache) throws IOException
    {
        if ("1".equals(cache)) {
            this.newsService.getNewsMap().clear();
        }
        Optional<Map<String, News>> news = this.newsService.getNews();

        if (news.get().isEmpty()) {
            throw new ResourceNotFoundException("News Resource Not Found");
        }

        return new ResponseEntity<>(news.get(), HttpStatus.OK);

    }

    @RequestMapping( value = "/", method = RequestMethod.GET, produces = { "application/json" } )
    public ResponseEntity<?> getQuote(@RequestParam( value = "cache", required = false ) String cache)
        throws IOException
    {

        Set<QuoteModel> binanceQuote = this.loaderService.getQuoteBinance();
        if (binanceQuote.isEmpty()) {
            throw new ResourceNotFoundException("Quote Resource Not Found");
        }

        return new ResponseEntity<>(binanceQuote, HttpStatus.OK);
    }

    @RequestMapping( value = "/loaders", method = RequestMethod.GET, produces = { "application/json" } )
    public ResponseEntity<?> runLoaders(@RequestParam( value = "cache", required = false ) String cache)
        throws IOException
    {

        String driverProperty = Drivers.CHROME_PROPERTY.getValue();
        String driverExe = Drivers.CHROME_EXE.getValue();

        Set<QuoteModel> setBinanceQuote =
            this.loaderService.loadBinanceData(driverProperty, driverExe, Exchanges.BINANCE.getValue(),
                                               Exchanges.BINANCE_TABLE_ID.getValue(),
                                               Exchanges.BINANCE_XPATH.getValue());
        if (setBinanceQuote == null) {
            throw new ResourceNotFoundException("Quote Resource Not Found");
        }
        return new ResponseEntity<>("completed", HttpStatus.OK);
    }

}
