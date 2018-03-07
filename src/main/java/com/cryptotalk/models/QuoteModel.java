package com.cryptotalk.models;

public class QuoteModel
{
    private String ticker;
    private String price;
    private String change24h;
    private String high24hr;
    private String low24h;
    private String volume;

    public String getTicker()
    {
        return ticker;
    }

    public void setTicker(String ticker)
    {
        this.ticker = ticker;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getChange24h()
    {
        return change24h;
    }

    public void setChange24h(String change24h)
    {
        this.change24h = change24h;
    }

    public String getHigh24hr()
    {
        return high24hr;
    }

    public void setHigh24hr(String high24hr)
    {
        this.high24hr = high24hr;
    }

    public String getLow24h()
    {
        return low24h;
    }

    public void setLow24h(String low24h)
    {
        this.low24h = low24h;
    }

    public String getVolume()
    {
        return volume;
    }

    public void setVolume(String volume)
    {
        this.volume = volume;
    }

}
