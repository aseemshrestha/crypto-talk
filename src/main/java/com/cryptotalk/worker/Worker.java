package com.cryptotalk.worker;

import static java.util.stream.IntStream.range;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

import com.cryptotalk.exchanges.Drivers;
import com.cryptotalk.exchanges.Exchanges;
import com.cryptotalk.models.QuoteModel;
import com.cryptotalk.service.DataLoaderService;
import com.cryptotalk.util.AppConfig;
import com.cryptotalk.util.Util;
import com.cryyptotalk.generated.Bittrex;
import com.cryyptotalk.generated.Cryptopia;

@Component
public class Worker
{
    private final List<QuoteModel> binanceList = new LinkedList<>();
    private final List<QuoteModel> piaList = new LinkedList<>();
    private final List<QuoteModel> bittrexList = new LinkedList<>();

    private final AppConfig config;
    private WebDriver driver = null;
    private static final Logger LOG = LogManager.getLogger(DataLoaderService.class);

    public Worker(AppConfig config)
    {
        this.config = Objects.requireNonNull(config);
    }

    public List<QuoteModel> binanceWorker()
    {
        String driverProperty = Drivers.CHROME_PROPERTY.getValue();
        String driverExe = Drivers.CHROME_EXE.getValue();
        String exchangeName = Exchanges.BINANCE.getValue();
        String tableId = Exchanges.BINANCE_TABLE_ID.getValue();
        String xpath = Exchanges.BINANCE_XPATH.getValue();

        try {
            ClassLoader loader = getClass().getClassLoader();
            File file = new File(Objects.requireNonNull(loader.getResource(driverExe)).getFile());
            System.setProperty(driverProperty, file.getAbsolutePath());
            driver = new ChromeDriver();
            driver.get(exchangeName);
            WebElement table_element = driver.findElement(By.id(tableId));
            List<WebElement> tr_collection = table_element.findElements(By.xpath(xpath));
            binanceList.clear();
            int bound = tr_collection.size();
            range(1, bound).filter(j -> !tr_collection.get(j).getText().isEmpty()).forEach(j -> {
                QuoteModel model = new QuoteModel();
                String DELIMITER = " ";
                model.setTicker(tr_collection.get(j).getText().split(DELIMITER)[0]);
                model.setPrice(tr_collection.get(j).getText().split(DELIMITER)[1]
                        + tr_collection.get(j).getText().split(DELIMITER)[2]
                        + tr_collection.get(j).getText().split(DELIMITER)[3]);
                model.setChange24h(tr_collection.get(j).getText().split(DELIMITER)[4]);
                model.setHigh24hr(tr_collection.get(j).getText().split(DELIMITER)[5]);
                model.setLow24h(tr_collection.get(j).getText().split(DELIMITER)[6]);
                model.setVolume(tr_collection.get(j).getText().split(DELIMITER)[7]);
                binanceList.add(model);
            });
        } catch (Exception ex) {
            LOG.debug("[Worker]Cannot get data from binance --------:" + ex);
        } finally {

            driver.close();
            driver.quit();
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
            LOG.debug("[Worker]Cannot parse cryptopia json --------:" + ex);
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
            LOG.debug("[Worker] Cannot parse bittrex json --------:" + ex);
        }
        return bittrexList;
    }

}
