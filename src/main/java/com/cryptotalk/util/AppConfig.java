package com.cryptotalk.util;

import org.springframework.stereotype.Component;

@Component
public class AppConfig
{
    private final String NEWS_API_URL =
        "https://newsapi.org/v2/everything?sources=crypto-coins-news&apiKey=c5665deea8514a0eb13ec06e34237ebd";

    private final String CRYPTOPIA_API = "https://www.cryptopia.co.nz/api/GetMarkets/BTC";

    private final String BITTREX_API = "https://bittrex.com/api/v1.1/public/getmarketsummaries";

    public String getCryptopiaApi()
    {
        return CRYPTOPIA_API;
    }

    public String getNewsApiUrl()
    {
        return NEWS_API_URL;
    }

    public String getBittrexApi()
    {
        return BITTREX_API;
    }

}
