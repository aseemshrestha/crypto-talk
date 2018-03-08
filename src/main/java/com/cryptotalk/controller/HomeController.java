package com.cryptotalk.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cryptotalk.exchanges.Drivers;
import com.cryptotalk.exchanges.Exchanges;
import com.cryptotalk.models.QuoteModel;
import com.cryptotalk.service.DataLoaderService;
import com.cryptotalk.service.NewsService;
import com.cryptotalk.util.ResourceNotFoundException;
import com.cryyptotalk.generated.News;

@RestController
public class HomeController {
	@Autowired
	private NewsService newsService;
	@Autowired
	private DataLoaderService loaderService;
	
	private static final Logger LOG = LogManager.getLogger(HomeController.class);
	
	@RequestMapping(value = "/news", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<?> getNews() throws IOException {
		Map<String, News> news = Collections.emptyMap();
		news = this.newsService.getNewsMap().get();
		if (news.isEmpty()) {
			LOG.info("Reloading News via temporary map.......");
			news = this.newsService.getNewsMapTemp().get();
			if (news.isEmpty()) {
				throw new ResourceNotFoundException("News Resource Not Found");
			}
		}

		return new ResponseEntity<>(news, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return new String("test");
	}

	@RequestMapping(value = "/quotes", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<?> getQuote() throws IOException {
		Map<String, Set<QuoteModel>> binanceQuote = Collections.emptyMap();
		binanceQuote = this.loaderService.getBinanceData().get();
		if (binanceQuote.isEmpty()) {
			LOG.info("Reloading BI data via temporary map.......");
			binanceQuote = this.loaderService.getBinanceDataTemp().get();
			if (binanceQuote.isEmpty()) {
				throw new ResourceNotFoundException("Quote Resource Not Found");
			}
			return new ResponseEntity<>(binanceQuote, HttpStatus.OK);
		}

		return new ResponseEntity<>(binanceQuote, HttpStatus.OK);
	}

	@RequestMapping(value = "/loaders", method = RequestMethod.GET)
	public ResponseEntity<?> runLoaders() {

		String driverProperty = Drivers.CHROME_PROPERTY.getValue();
		String driverExe = Drivers.CHROME_EXE.getValue();
		Map<String, Set<QuoteModel>> map_bi;
		try {
			map_bi = this.loaderService.setBinanceData(driverProperty, driverExe, Exchanges.BINANCE.getValue(),
					Exchanges.BINANCE_TABLE_ID.getValue(), Exchanges.BINANCE_XPATH.getValue()).get();

		} catch (Exception ex) {
			LOG.debug("Exception while running loaders:",ex);
			return new ResponseEntity<>("Data Loader failed", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("completed with " + map_bi.size() + "rows", HttpStatus.OK);
	}

	@RequestMapping(value = "/loadNews", method = RequestMethod.GET)
	public ResponseEntity<?> loadNews() {
		Optional<Map<String, News>> newsMap = newsService.setNews();
		if (newsMap.get().isEmpty()) {
			return new ResponseEntity<>("News Loader failed", HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>("News Loaded", HttpStatus.OK);

	}
}
