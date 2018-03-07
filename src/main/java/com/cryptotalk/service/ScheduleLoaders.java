package com.cryptotalk.service;

import java.util.TimerTask;

import com.cryptotalk.exchanges.Drivers;
import com.cryptotalk.exchanges.Exchanges;

public class ScheduleLoaders extends TimerTask
{
    private DataLoaderService loader;

    public ScheduleLoaders()
    {

    }

    public ScheduleLoaders(DataLoaderService loader)
    {
        this.loader = loader;
    }

    @Override
    public void run()
    {
        
        this.loader.quoteSetBin.clear();
        this.loader.loadBinanceData(Drivers.CHROME_PROPERTY.getValue(), Drivers.CHROME_EXE.getValue(),
                                    Exchanges.BINANCE.getValue(), Exchanges.BINANCE_TABLE_ID.getValue(),
                                    Exchanges.BINANCE_XPATH.getValue());
        }

}
