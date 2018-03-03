package com.cryptotalk.util;

import org.springframework.stereotype.Component;

@Component
public class AppConfig
{
    // c5665deea8514a0eb13ec06e34237ebd
    private String newsApiUrl =
        "https://newsapi.org/v2/everything?sources=crypto-coins-news&apiKey=c5665deea8514a0eb13ec06e34237ebd";

    public String getNewsApiUrl()
    {
        return newsApiUrl;
    }

    public AppConfig setNewsApiUrl(String newsApiUrl)
    {
        this.newsApiUrl = newsApiUrl;
        return this;
    }

}
