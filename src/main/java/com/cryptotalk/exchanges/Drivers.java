package com.cryptotalk.exchanges;

public enum Drivers
{
    CHROME_EXE("chromedriver.exe"),
    CHROME_PROPERTY("webdriver.chrome.driver");

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
