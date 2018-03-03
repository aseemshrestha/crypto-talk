package com.cryyptotalk.generated;

public class Last_trade
{
    private String time_coinapi;

    private String price;

    private String uuid;

    private String taker_side;

    private String size;

    private String time_exchange;

    public String getTime_coinapi()
    {
        return time_coinapi;
    }

    public void setTime_coinapi(String time_coinapi)
    {
        this.time_coinapi = time_coinapi;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    public String getTaker_side()
    {
        return taker_side;
    }

    public void setTaker_side(String taker_side)
    {
        this.taker_side = taker_side;
    }

    public String getSize()
    {
        return size;
    }

    public void setSize(String size)
    {
        this.size = size;
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
        return "Last_trade [time_coinapi = " + time_coinapi + ", price = " + price + ", uuid = " + uuid
            + ", taker_side = " + taker_side + ", size = " + size + ", time_exchange = " + time_exchange + "]";
    }
}
