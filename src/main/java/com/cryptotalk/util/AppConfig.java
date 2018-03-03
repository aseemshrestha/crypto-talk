package com.cryptotalk.util;

import org.springframework.stereotype.Component;

@Component
public class AppConfig
{
    // c5665deea8514a0eb13ec06e34237ebd
    //10503456-E37F-4771-802A-567F7171C2F6
    private final String newsApiUrl =
        "https://newsapi.org/v2/everything1?sources=crypto-coins-news&apiKey=c5665deea8514a0eb13ec06e34237ebd";

    //private final String quoteApiWCI = "https://www.worldcoinindex.com/apiservice/getmarkets?key=lgoOwhWr0AmKubEAEKXJNGqqefMJf3&fiat=usd";

    private final String QUOTE_API_CA = "https://rest.coinapi.io/v1/quotes/current";
    private final String QUOTE_API_LA = "https://rest.coinapi.io/v1/quotes/latest?limit={limit}";
    private final String QUOTE_API_HEADER_KEY = "X-CoinAPI-Key";
    private final String QUOTE_API_HEADER_VALUE = "10503456-E37F-4771-802A-567F7171C2F6";

    public String getQUOTE_API_CA()
    {
        return QUOTE_API_CA;
    }

    public String getQUOTE_API_LA()
    {
        return QUOTE_API_LA;
    }

    public String getQUOTE_API_HEADER_KEY()
    {
        return QUOTE_API_HEADER_KEY;
    }

    public String getQUOTE_API_HEADER_VALUE()
    {
        return QUOTE_API_HEADER_VALUE;
    }

    public String getQuoteApi_CA()
    {
        return QUOTE_API_CA;
    }

    public String getQuoteApiHeaderKey()
    {
        return QUOTE_API_HEADER_KEY;
    }

    public String getQuoteApiHeaderValue()
    {
        return QUOTE_API_HEADER_VALUE;
    }

    public String getNewsApiUrl()
    {
        return newsApiUrl;
    }

    public String getQuoteApi()
    {
        return QUOTE_API_CA;
    }

}
