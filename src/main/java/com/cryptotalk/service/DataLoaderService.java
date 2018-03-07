package com.cryptotalk.service;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import com.cryptotalk.models.QuoteModel;

@Service
public class DataLoaderService
{
    Map<String, Set<QuoteModel>> quoteMap = new LinkedHashMap<>();
    Map<String, Set<QuoteModel>> quoteMapTemp = new LinkedHashMap<>();
    private final String DELIMITER = " ";

    public Optional<Map<String, Set<QuoteModel>>> setBinanceData(String driverProperty, String driverexe,
        String exchangeName,
        String tableId,
        String xpath)
    {
        WebDriver driver = null;
        quoteMap.clear();

        try {
            ClassLoader loader = getClass().getClassLoader();
            File file = new File(loader.getResource(driverexe).getFile());
            System.setProperty(driverProperty, file.getAbsolutePath());
            driver = new ChromeDriver();
            driver.get(exchangeName);

            WebElement table_element = driver.findElement(By.id(tableId));
            List<WebElement> tr_collection = table_element.findElements(By.xpath(xpath));
            Set<QuoteModel> setModel = new LinkedHashSet<>();
            setModel.clear();
            for (int j = 1; j < tr_collection.size(); j++) {
                if (tr_collection.get(j).getText().isEmpty()) {
                    continue;
                }
                QuoteModel model = new QuoteModel();
                model.setTicker(tr_collection.get(j).getText().split(DELIMITER)[0]);
                model.setPrice(tr_collection.get(j).getText().split(DELIMITER)[1]
                    + tr_collection.get(j).getText().split(DELIMITER)[2]
                    + tr_collection.get(j).getText().split(DELIMITER)[3]);
                model.setChange24h(tr_collection.get(j).getText().split(DELIMITER)[4]);
                model.setHigh24hr(tr_collection.get(j).getText().split(DELIMITER)[5]);
                model.setLow24h(tr_collection.get(j).getText().split(DELIMITER)[6]);
                model.setVolume(tr_collection.get(j).getText().split(DELIMITER)[7]);
                setModel.add(model);

            }
            quoteMap.put("bi_quote", setModel);
            quoteMapTemp.put("bi_quote", setModel);

        } catch (Exception ex) {
            System.out.println("Error" + ex);
        } finally {
            driver.quit();
        }
        return Optional.ofNullable(quoteMap);
    }

    public Optional<Map<String, Set<QuoteModel>>> getBinanceData()
    {
        return Optional.ofNullable(quoteMap);
    }

    public Optional<Map<String, Set<QuoteModel>>> getBinanceDataTemp()
    {
        return Optional.ofNullable(quoteMapTemp);
    }

}
