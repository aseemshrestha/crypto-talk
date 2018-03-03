package com.cryptotalk.controller;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cryptotalk.service.NewsService;
import com.cryptotalk.util.ResourceNotFoundException;
import com.cryyptotalk.generated.News;

@RestController
public class HomeController
{

    private NewsService newsService;

    public HomeController(NewsService newsService)
    {
        this.newsService = newsService;
    }

    @RequestMapping( value = "/", method = RequestMethod.GET, produces = { "application/json" } )
    public ResponseEntity<?> Home(@RequestParam( value = "cache", required = false ) String cache)
    {
        if ("1".equals(cache)) {
            this.newsService.getNewsMap().clear();
        }

        Map<String, News> news = this.newsService.getNews();

        if (news == null) {
            throw new ResourceNotFoundException("News Resource Not Found");
        }

        return new ResponseEntity<>(news, HttpStatus.OK);

    }

}
