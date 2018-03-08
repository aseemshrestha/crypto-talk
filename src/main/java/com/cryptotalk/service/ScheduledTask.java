package com.cryptotalk.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cryptotalk.exchanges.Drivers;
import com.cryptotalk.exchanges.Exchanges;

@Component
public class ScheduledTask {
	@Autowired
	private DataLoaderService loaderService;
	@Autowired
	private NewsService newsService;

	String driverProperty = Drivers.CHROME_PROPERTY.getValue();
	String driverExe = Drivers.CHROME_EXE.getValue();
	private static final Logger LOG = LogManager.getLogger(ScheduledTask.class);

	// cron = "0 * * * * ?"
	@Scheduled(cron = "0 0/5 * * * ?")
	public void updateBinanceLoader() {
		try {
			LOG.info("Into scheduled task running BI loader.......");
			loaderService.setBinanceData(driverProperty, driverExe, Exchanges.BINANCE.getValue(),
					Exchanges.BINANCE_TABLE_ID.getValue(), Exchanges.BINANCE_XPATH.getValue());
		} catch (Exception ex) {
			LOG.debug("Exception while running data loader from task..." + ex);
		}
	}

	@Scheduled(cron = "0 0/60 * * * ?")
	public void updateNewsLoader() {
		try {
			LOG.info("Into scheduled task running NEWS loader.......");
			newsService.setNews();
		} catch (Exception ex) {
			LOG.debug("Exception while running news loader from task..." + ex);
		}
	}

}
