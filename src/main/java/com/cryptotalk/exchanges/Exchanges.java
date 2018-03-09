package com.cryptotalk.exchanges;

public enum Exchanges
{
    //binance
    BINANCE("https://www.binance.com"),
    BINANCE_XPATH("//*[@id='products']/tbody/tr"),
    BINANCE_TABLE_ID("products"),

    //bittrex
    BITTREX("https://bittrex.com/home/markets"),

    //cryptopia
    CRYPTOPIA("https://www.cryptopia.co.nz/Exchange/?baseMarket=USDT"),
    CRYPTOPIA_TABLE_ID("currencyData-USDT"),
    CRYPTOPIA_XPATH("//*[@id='currencyData-USDT']/tbody/tr");

    private final String value;

    Exchanges(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
