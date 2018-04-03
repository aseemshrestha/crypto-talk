package com.cryptotalk.controller;

import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cryptotalk.service.NewsService;
import com.cryyptotalk.generated.News;

@RestController
public class NewsController
{

    @Autowired
    private NewsService newsService;

    private static final Logger LOG = LogManager.getLogger(NewsController.class);

    @RequestMapping( value = "/news", method = RequestMethod.GET, produces = { "application/json" } )
    public ResponseEntity<?> getNews()
    {
        Map<String, News> news = this.newsService.getNewsMap().get();

        if (!news.isEmpty()) {
            return new ResponseEntity<>(news, HttpStatus.OK);
        }
        LOG.info("[NewsController][getNews] Reloading News via temporary map");
        news = this.newsService.getNewsMapTemp().get();
        assert !news.isEmpty() : "News Resource Not Found";

        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @RequestMapping( value = "/loadNews", method = RequestMethod.GET )
    public ResponseEntity<?> loadNews()
    {
        Map<String, News> newsMap = newsService.getNews();
        return newsMap.isEmpty()
            ? new ResponseEntity<String>("News Loader failed", HttpStatus.INTERNAL_SERVER_ERROR)
            : new ResponseEntity<String>("News Loaded", HttpStatus.OK);

    }
}
