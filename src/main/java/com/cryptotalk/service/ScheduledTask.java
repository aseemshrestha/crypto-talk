package com.cryptotalk.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask
{
    @Autowired
    private DataLoaderService loaderService;
    @Autowired
    private NewsService newsService;

    private static final Logger LOG = LogManager.getLogger(ScheduledTask.class);

    // cron = "0 * * * * ?"
    @Scheduled( cron = "0 0/15 * * * ?" )
    public void updateBinanceLoader()
    {
        try {
            LOG.info("Running [updateBinanceLoader] scheduled task -> running BI loader.......");
            loaderService.loadBinanceData();
        } catch (Exception ex) {
            LOG.debug("Exception [updateBinanceLoader] while running BI data loader from task..." + ex);
        }
    }

    @Scheduled( cron = "0 0/15 * * * ?" )
    public void updateCryptopiaLoader()
    {
        try {
            LOG.info("Running [updateCryptopiaLoader] scheduled task -> running Cryptopia loader.......");
            loaderService.loadCryptopiaData();
        } catch (Exception ex) {
            LOG.debug("Exception [updateCryptopiaLoader] while running cryptopia data loader from task..." + ex);
        }
    }

    @Scheduled( cron = "0 0/15 * * * ?" )
    public void updateBittrexLoader()
    {
        try {
            LOG.info("Running [updateBittrexLoader] scheduled task -> running Bittrex loader.......");
            loaderService.loadBittrexData();
        } catch (Exception ex) {
            LOG.debug("Exception [updateBittrexLoader] while running bittrex data loader from task..." + ex);
        }
    }

    @Scheduled( cron = "0 0/60 * * * ?" )
    public void updateNewsLoader()
    {
        try {
            LOG.info("Running [updateNewsLoader] scheduled task running NEWS loader.......");
            newsService.getNews();
        } catch (Exception ex) {
            LOG.debug("Exception [updateNewsLoader] while running news loader from task..." + ex);
        }
    }

    @Scheduled( cron = "0 0/15 * * * ?" )
    public void updateCmcLoader()
    {
        try {
            LOG.info("Running [updateCMCLoader] scheduled task -> running CMC loader.......");
            loaderService.loadCoinMarketCapData();
        } catch (Exception ex) {
            LOG.debug("Exception [updateCMCLoader] while running CMC data loader from task..." + ex);
        }
    }

}
