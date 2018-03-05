package com.cryptotalk.exchanges;

public enum Drivers
{
    CHROME("chromedriver.exe");
    
    private final String value;

    private Drivers(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
