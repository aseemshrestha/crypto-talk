package com.cryyptotalk.generated;

public class Markets
{
    private String Name;

    private String Volume_24h;

    private String Price;

    private String Label;

    private String Timestamp;

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public String getVolume_24h ()
    {
        return Volume_24h;
    }

    public void setVolume_24h (String Volume_24h)
    {
        this.Volume_24h = Volume_24h;
    }

    public String getPrice ()
    {
        return Price;
    }

    public void setPrice (String Price)
    {
        this.Price = Price;
    }

    public String getLabel ()
    {
        return Label;
    }

    public void setLabel (String Label)
    {
        this.Label = Label;
    }

    public String getTimestamp ()
    {
        return Timestamp;
    }

    public void setTimestamp (String Timestamp)
    {
        this.Timestamp = Timestamp;
    }

    @Override
    public String toString()
    {
        return "Markets [Name = "+Name+", Volume_24h = "+Volume_24h+", Price = "+Price+", Label = "+Label+", Timestamp = "+Timestamp+"]";
    }
}