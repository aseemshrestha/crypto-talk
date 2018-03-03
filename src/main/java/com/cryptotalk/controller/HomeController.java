package com.cryptotalk.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cryptotalk.service.NewsService;
import com.cryptotalk.service.QuoteService;
import com.cryptotalk.util.ResourceNotFoundException;
import com.cryyptotalk.generated.News;
import com.cryyptotalk.generated.Quote;

@RestController
public class HomeController
{

    private NewsService newsService;
    private QuoteService quoteService;

    public HomeController(NewsService newsService, QuoteService quoteService)
    {
        this.newsService = newsService;
        this.quoteService = quoteService;
    }

    @RequestMapping( value = "/news", method = RequestMethod.GET, produces = { "application/json" } )
    public ResponseEntity<?> getNews(@RequestParam( value = "cache", required = false ) String cache)
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
    {
        if ("1".equals(cache)) {
            this.quoteService.getQuoteMap().clear();
        }

        Optional<Map<String, Quote[]>> quotes = this.quoteService.getQuotes();

        if (quotes.get().isEmpty()) {
            throw new ResourceNotFoundException("News Resource Not Found");
        }

        return new ResponseEntity<>(quotes.get(), HttpStatus.OK);

    }

}
