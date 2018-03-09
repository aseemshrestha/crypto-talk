package com.cryyptotalk.generated;

public class Result
{
    private String OpenSellOrders;

    private String TimeStamp;

    private String Last;

    private String Low;

    private String Created;

    private String PrevDay;

    private String OpenBuyOrders;

    private String Volume;

    private String BaseVolume;

    private String Bid;

    private String Ask;

    private String MarketName;

    private String High;

    public String getOpenSellOrders()
    {
        return OpenSellOrders;
    }

    public void setOpenSellOrders(String OpenSellOrders)
    {
        this.OpenSellOrders = OpenSellOrders;
    }

    public String getTimeStamp()
    {
        return TimeStamp;
    }

    public void setTimeStamp(String TimeStamp)
    {
        this.TimeStamp = TimeStamp;
    }

    public String getLast()
    {
        return Last;
    }

    public void setLast(String Last)
    {
        this.Last = Last;
    }

    public String getLow()
    {
        return Low;
    }

    public void setLow(String Low)
    {
        this.Low = Low;
    }

    public String getCreated()
    {
        return Created;
    }

    public void setCreated(String Created)
    {
        this.Created = Created;
    }

    public String getPrevDay()
    {
        return PrevDay;
    }

    public void setPrevDay(String PrevDay)
    {
        this.PrevDay = PrevDay;
    }

    public String getOpenBuyOrders()
    {
        return OpenBuyOrders;
    }

    public void setOpenBuyOrders(String OpenBuyOrders)
    {
        this.OpenBuyOrders = OpenBuyOrders;
    }

    public String getVolume()
    {
        return Volume;
    }

    public void setVolume(String Volume)
    {
        this.Volume = Volume;
    }

    public String getBaseVolume()
    {
        return BaseVolume;
    }

    public void setBaseVolume(String BaseVolume)
    {
        this.BaseVolume = BaseVolume;
    }

    public String getBid()
    {
        return Bid;
    }

    public void setBid(String Bid)
    {
        this.Bid = Bid;
    }

    public String getAsk()
    {
        return Ask;
    }

    public void setAsk(String Ask)
    {
        this.Ask = Ask;
    }

    public String getMarketName()
    {
        return MarketName;
    }

    public void setMarketName(String MarketName)
    {
        this.MarketName = MarketName;
    }

    public String getHigh()
    {
        return High;
    }

    public void setHigh(String High)
    {
        this.High = High;
    }

    @Override
    public String toString()
    {
        return "Result [OpenSellOrders = " + OpenSellOrders + ", TimeStamp = " + TimeStamp + ", Last = " + Last
            + ", Low = " + Low + ", Created = " + Created + ", PrevDay = " + PrevDay + ", OpenBuyOrders = "
            + OpenBuyOrders + ", Volume = " + Volume + ", BaseVolume = " + BaseVolume + ", Bid = " + Bid + ", Ask = "
            + Ask + ", MarketName = " + MarketName + ", High = " + High + "]";
    }
}
