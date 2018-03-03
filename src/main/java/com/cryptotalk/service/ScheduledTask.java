package com.cryptotalk.service;

import java.util.TimerTask;

public class ScheduledTask extends TimerTask
{

    @Override
    public void run()
    {
        NewsService.newsMap.clear();
        QuoteService.quoteMap.clear();
    }

}
