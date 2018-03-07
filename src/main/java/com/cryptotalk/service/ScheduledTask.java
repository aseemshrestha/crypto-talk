package com.cryptotalk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.cryptotalk.exchanges.Drivers;
import com.cryptotalk.exchanges.Exchanges;

@Component
public class ScheduledTask
{
    @Autowired
    private DataLoaderService loaderService;
    @Autowired
    private NewsService newsService;

    String driverProperty = Drivers.CHROME_PROPERTY.getValue();
    String driverExe = Drivers.CHROME_EXE.getValue();

    //cron = "0 * * * * ?"
    @Scheduled( cron = "0 0/5 * * * ?" )
    public void updateBinanceLoader()
    {
        try {
            loaderService.setBinanceData(driverProperty, driverExe, Exchanges.BINANCE.getValue(),
                                         Exchanges.BINANCE_TABLE_ID.getValue(),
                                         Exchanges.BINANCE_XPATH.getValue());
        } catch (Exception ex) {
            System.out.println("Exception while running data loader from task..." + ex);
        }
    }

    @Scheduled( cron = "0 0/60 * * * ?" )
    public void updateNewsLoader()
    {
        try {
            newsService.setNews();
        } catch (Exception ex) {
            System.out.println("Exception while running news loader from task..." + ex);
        }
    }

}
