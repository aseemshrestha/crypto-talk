package com.cryyptotalk.generated;

public class Quote
{
    private String time_coinapi;

    private Last_trade last_trade;

    private String bid_price;

    private String ask_price;

    private String bid_size;

    private String ask_size;

    private String symbol_id;

    private String time_exchange;

    public String getTime_coinapi()
    {
        return time_coinapi;
    }

    public void setTime_coinapi(String time_coinapi)
    {
        this.time_coinapi = time_coinapi;
    }

    public Last_trade getLast_trade()
    {
        return last_trade;
    }

    public void setLast_trade(Last_trade last_trade)
    {
        this.last_trade = last_trade;
    }

    public String getBid_price()
    {
        return bid_price;
    }

    public void setBid_price(String bid_price)
    {
        this.bid_price = bid_price;
    }

    public String getAsk_price()
    {
        return ask_price;
    }

    public void setAsk_price(String ask_price)
    {
        this.ask_price = ask_price;
    }

    public String getBid_size()
    {
        return bid_size;
    }

    public void setBid_size(String bid_size)
    {
        this.bid_size = bid_size;
    }

    public String getAsk_size()
    {
        return ask_size;
    }

    public void setAsk_size(String ask_size)
    {
        this.ask_size = ask_size;
    }

    public String getSymbol_id()
    {
        return symbol_id;
    }

    public void setSymbol_id(String symbol_id)
    {
        this.symbol_id = symbol_id;
    }

    public String getTime_exchange()
    {
        return time_exchange;
    }

    public void setTime_exchange(String time_exchange)
    {
        this.time_exchange = time_exchange;
    }

    @Override
    public String toString()
    {
        return "Quote [time_coinapi = " + time_coinapi + ", last_trade = " + last_trade + ", bid_price = "
            + bid_price + ", ask_price = " + ask_price + ", bid_size = " + bid_size + ", ask_size = " + ask_size
            + ", symbol_id = " + symbol_id + ", time_exchange = " + time_exchange + "]";
    }
}
