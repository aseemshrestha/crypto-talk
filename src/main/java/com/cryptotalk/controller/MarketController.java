package com.cryptotalk.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cryptotalk.models.QuoteModel;
import com.cryptotalk.service.DataLoaderService;
import com.cryptotalk.service.NewsService;
import com.cryyptotalk.generated.News;

@RestController
class MarketController {
	@Autowired
	private NewsService newsService;
	@Autowired
	private DataLoaderService loaderService;

	private static final Logger LOG = LogManager.getLogger(MarketController.class);

	@RequestMapping(value = "/news", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<?> getNews() {
		Map<String, News> news = this.newsService.getNewsMap().get();

		if (!news.isEmpty()) {
			return new ResponseEntity<>(news, HttpStatus.OK);
		}
		LOG.info("Reloading News via temporary map.......");
		news = this.newsService.getNewsMapTemp().get();
		assert !news.isEmpty() : "News Resource Not Found";

		return new ResponseEntity<>(news, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return new String("test");
	}

	@RequestMapping(value = "/quotes", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<?> getMarkets() throws InterruptedException, ExecutionException {
		// CompletableFuture<Optional<Map<String, List<QuoteModel>>>> binanceQuote;
		// CompletableFuture<Optional<Map<String, List<QuoteModel>>>> piaQuote;
		Map<String, Map<String, List<QuoteModel>>> marketMap = new LinkedHashMap<>();

		long start = System.currentTimeMillis();

		Optional<Map<String, List<QuoteModel>>> binanceQuote = this.loaderService.getBinanceData();
		Optional<Map<String, List<QuoteModel>>> piaQuote = this.loaderService.getCryptopiaData();
		Optional<Map<String, List<QuoteModel>>> bittrexQuote = this.loaderService.getBittrexData();

		if (binanceQuote.get().isEmpty()) {
			LOG.info("Reloading Binance data via temporary map.......");
			binanceQuote = this.loaderService.getBinanceDataTemp();
			assert !binanceQuote.get().isEmpty() : "Binance Resource Not Found";
		}
		if (piaQuote.get().isEmpty()) {
			LOG.info("Reloading Cryptopia data via temporary map.......");
			piaQuote = this.loaderService.getCryptopiaDataTemp();
			assert !piaQuote.get().isEmpty() : "Cryptopia Resource Not Found";
		}
		if (bittrexQuote.get().isEmpty()) {
			LOG.info("Reloading Bittrex data via temporary map.......");
			bittrexQuote = this.loaderService.getBittrexDataTemp();
			assert !bittrexQuote.get().isEmpty() : "Bittrex Resource Not Found";
		}

		// CompletableFuture.allOf(binanceQuote, piaQuote).join();
		marketMap.put("marketdata_bi", binanceQuote.get());
		marketMap.put("marketdata_pia", piaQuote.get());
		marketMap.put("marketdata_bt", bittrexQuote.get());

		LOG.info("[GetMarkets] time taken to get data--->:" + (System.currentTimeMillis() - start));

		return new ResponseEntity<>(marketMap, HttpStatus.OK);
	}

	@RequestMapping(value = "/loaders", method = RequestMethod.GET)
	public ResponseEntity<?> runLoaders() {
		try {
			this.loaderService.loadBinanceData();
			this.loaderService.loadCryptopiaData();
			this.loaderService.loadBittrexData();

		} catch (Exception ex) {
			LOG.debug("Exception while running loaders:", ex);
			return new ResponseEntity<>("Data Loader failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>("completed", HttpStatus.OK);
	}

	@RequestMapping(value = "/loadNews", method = RequestMethod.GET)
	public ResponseEntity<?> loadNews() {
		Optional<Map<String, News>> newsMap = newsService.setNews();
		return newsMap.get().isEmpty()
				? new ResponseEntity<String>("News Loader failed", HttpStatus.INTERNAL_SERVER_ERROR)
				: new ResponseEntity<String>("News Loaded", HttpStatus.OK);

	}
}
