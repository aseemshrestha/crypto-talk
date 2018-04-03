package com.cryyptotalk.generated;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoinMarketCap
{
    @JsonProperty("24h_volume_usd")
    private String twenty_Four_Hr_volume_usd;

    private String symbol;

    private String available_supply;

    private String percent_change_1h;

    private String price_usd;

    private String price_btc;

    private String id;

    private String total_supply;

    private String percent_change_24h;

    private String max_supply;

    private String rank;

    private String name;

    private String last_updated;

    private String percent_change_7d;

    private String market_cap_usd;

    

    public String getTwenty_Four_Hr_volume_usd()
    {
        return twenty_Four_Hr_volume_usd;
    }

    public void setTwenty_Four_Hr_volume_usd(String twenty_Four_Hr_volume_usd)
    {
        this.twenty_Four_Hr_volume_usd = twenty_Four_Hr_volume_usd;
     }

    public String getSymbol ()
    {
        return symbol;
    }

    public void setSymbol (String symbol)
    {
        this.symbol = symbol;
    }

    public String getAvailable_supply ()
    {
        return available_supply;
    }

    public void setAvailable_supply (String available_supply)
    {
        this.available_supply = available_supply;
    }

    public String getPercent_change_1h ()
    {
        return percent_change_1h;
    }

    public void setPercent_change_1h (String percent_change_1h)
    {
        this.percent_change_1h = percent_change_1h;
    }

    public String getPrice_usd ()
    {
        return price_usd;
    }

    public void setPrice_usd (String price_usd)
    {
        this.price_usd = price_usd;
    }

    public String getPrice_btc ()
    {
        return price_btc;
    }

    public void setPrice_btc (String price_btc)
    {
        this.price_btc = price_btc;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getTotal_supply ()
    {
        return total_supply;
    }

    public void setTotal_supply (String total_supply)
    {
        this.total_supply = total_supply;
    }

    public String getPercent_change_24h ()
    {
        return percent_change_24h;
    }

    public void setPercent_change_24h (String percent_change_24h)
    {
        this.percent_change_24h = percent_change_24h;
    }

    public String getMax_supply ()
    {
        return max_supply;
    }

    public void setMax_supply (String max_supply)
    {
        this.max_supply = max_supply;
    }

    public String getRank ()
    {
        return rank;
    }

    public void setRank (String rank)
    {
        this.rank = rank;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getLast_updated ()
    {
        return last_updated;
    }

    public void setLast_updated (String last_updated)
    {
        this.last_updated = last_updated;
    }

    public String getPercent_change_7d ()
    {
        return percent_change_7d;
    }

    public void setPercent_change_7d (String percent_change_7d)
    {
        this.percent_change_7d = percent_change_7d;
    }

    public String getMarket_cap_usd ()
    {
        return market_cap_usd;
    }

    public void setMarket_cap_usd (String market_cap_usd)
    {
        this.market_cap_usd = market_cap_usd;
    }

    @Override
    public String toString()
    {
        return "CoinMarketCap [24h_volume_usd = "+twenty_Four_Hr_volume_usd+", symbol = "+symbol+", available_supply = "+available_supply+", percent_change_1h = "+percent_change_1h+", price_usd = "+price_usd+", price_btc = "+price_btc+", id = "+id+", total_supply = "+total_supply+", percent_change_24h = "+percent_change_24h+", max_supply = "+max_supply+", rank = "+rank+", name = "+name+", last_updated = "+last_updated+", percent_change_7d = "+percent_change_7d+", market_cap_usd = "+market_cap_usd+"]";
    }
}
            
