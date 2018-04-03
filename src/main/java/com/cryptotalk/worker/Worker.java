package com.cryptotalk.worker;

import static java.util.stream.IntStream.range;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import com.cryptotalk.models.QuoteModel;
import com.cryptotalk.service.DataLoaderService;
import com.cryptotalk.util.AppConfig;
import com.cryptotalk.util.Util;
import com.cryyptotalk.generated.Binance;
import com.cryyptotalk.generated.Bittrex;
import com.cryyptotalk.generated.CoinMarketCap;
import com.cryyptotalk.generated.Cryptopia;

@Component
public class Worker
{
    private final List<QuoteModel> binanceList = new LinkedList<>();
    private final List<QuoteModel> piaList = new LinkedList<>();
    private final List<QuoteModel> bittrexList = new LinkedList<>();

    private final AppConfig config;
    private static final Logger LOG = LogManager.getLogger(DataLoaderService.class);

    public Worker(AppConfig config)
    {
        this.config = Objects.requireNonNull(config);
    }

    public List<CoinMarketCap> coinMarketCapWorker()
    {
        try {
            
            List<CoinMarketCap> coinMarketCap =
                Util.parseJsonArrayFromUrl(this.config.getCoinMarketCapApi(), CoinMarketCap.class);
            return coinMarketCap;

        } catch (Exception ex) {
            LOG.debug("[Worker][CoinMarketCap] Cannot parse coin market cap json" + ex);
        }
        return null;
    }

    public List<QuoteModel> binanceWorker()
    {

        try {
            List<Binance> market = Util.parseJsonArrayFromUrl(this.config.getBinanceApi(), Binance.class);

            binanceList.clear();
            int bound = market.size();
            range(0, bound).forEach((int i) -> {
                QuoteModel model = new QuoteModel();
                if (market.get(i).getSymbol().endsWith("BTC")) {
                    model.setTicker(market.get(i).getSymbol());
                    model.setPrice(market.get(i).getLastPrice());
                    model.setChange24h(market.get(i).getPriceChangePercent());
                    model.setHigh24hr(market.get(i).getHighPrice());
                    model.setLow24h(market.get(i).getLowPrice());
                    model.setVolume(market.get(i).getVolume());
                    binanceList.add(model);
                }
            });

        } catch (Exception ex) {
            LOG.debug("[Worker][BinanceWorker] Cannot parse binance json" + ex);
        }
        return binanceList;
    }

    public List<QuoteModel> cryptopiaWorker()
    {
        try {
            Cryptopia market = Util.parseJsonFromUrl(this.config.getCryptopiaApi(), Cryptopia.class);
            piaList.clear();
            int bound = market.getData().length;
            range(0, bound).forEach((int i) -> {
                QuoteModel model = new QuoteModel();
                model.setTicker(market.getData()[i].getLabel());
                model.setPrice(market.getData()[i].getAskPrice());
                model.setChange24h(market.getData()[i].getChange());
                model.setHigh24hr(market.getData()[i].getHigh());
                model.setLow24h(market.getData()[i].getLow());
                model.setVolume(market.getData()[i].getVolume());
                piaList.add(model);
            });

        } catch (Exception ex) {
            LOG.debug("[Worker] [CryptopiaWorker] Cannot parse cryptopia" + ex);
        }
        return piaList;
    }

    public List<QuoteModel> bittrexWorker()
    {
        try {
            Bittrex market = Util.parseJsonFromUrl(this.config.getBittrexApi(), Bittrex.class);
            bittrexList.clear();
            int bound = market.getResult().length;
            range(0, bound).forEach(i -> {
                QuoteModel model = new QuoteModel();
                if (market.getResult()[i].getMarketName().startsWith("BTC")) {
                    double _high = Double.parseDouble(market.getResult()[i].getHigh());
                    double _low = Double.parseDouble(market.getResult()[i].getLow());
                    model.setHigh24hr(market.getResult()[i].getHigh());
                    model.setTicker(market.getResult()[i].getMarketName());
                    model.setPrice(market.getResult()[i].getAsk());
                    model.setChange24h(String.valueOf(((_high - _low) / _low) * 100));
                    model.setHigh24hr(market.getResult()[i].getHigh());
                    model.setLow24h(market.getResult()[i].getLow());
                    model.setVolume(market.getResult()[i].getVolume());
                    bittrexList.add(model);
                }
            });

        } catch (Exception ex) {
            LOG.debug("[Worker] [BittrexWorker] Cannot parse bittrex json" + ex);
        }
        return bittrexList;
    }

}
