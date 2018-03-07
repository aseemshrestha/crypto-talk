package com.cryptotalk.service;

import java.util.TimerTask;

public class ScheduledTask extends TimerTask
{

    public ScheduledTask()
    {

    }

    @Override
    public void run()
    {
        NewsService.newsMap.clear();
        //QuoteService.quoteMap.clear();
      //  DataLoaderService.quoteSetBin.clear();
    }

}
