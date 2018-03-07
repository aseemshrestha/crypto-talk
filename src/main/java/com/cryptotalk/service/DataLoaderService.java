package com.cryptotalk.service;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.List;
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
    Set<QuoteModel> quoteSetBin = new LinkedHashSet<>();
    Set<QuoteModel> quoteSetBinTemp = new LinkedHashSet<>();
    public Set<QuoteModel> loadBinanceData(String driverProperty, String driverexe, String exchangeName, String tableId,
        String xpath)
    {

        WebDriver driver = null;

        if (!quoteSetBin.isEmpty()) {
            return quoteSetBin;
        }

        try {
            System.out.println("here-------");
            ClassLoader loader = getClass().getClassLoader();
            File file = new File(loader.getResource(driverexe).getFile());
            System.setProperty(driverProperty, file.getAbsolutePath());
            driver = new ChromeDriver();
            driver.get(exchangeName);

            WebElement table_element = driver.findElement(By.id(tableId));
            List<WebElement> tr_collection = table_element.findElements(By.xpath(xpath));

            for (int j = 1; j < tr_collection.size(); j++) {

                if (tr_collection.get(j).getText().isEmpty()) {
                    continue;
                }
                QuoteModel model = new QuoteModel();
                model.setTicker(tr_collection.get(j).getText().split(" ")[0]);
                model.setPrice(tr_collection.get(j).getText().split(" ")[1]
                    + tr_collection.get(j).getText().split(" ")[2] + tr_collection.get(j).getText().split(" ")[3]);
                model.setChange24h(tr_collection.get(j).getText().split(" ")[4]);
                model.setHigh24hr(tr_collection.get(j).getText().split(" ")[5]);
                model.setLow24h(tr_collection.get(j).getText().split(" ")[6]);
                model.setVolume(tr_collection.get(j).getText().split(" ")[7]);
                quoteSetBin.add(model);
                quoteSetBinTemp.add(model);
            }

            return quoteSetBin;

        } catch (Exception ex) {
            System.out.println("Error" + ex);
        } finally {
            driver.quit();
        }
        return null;
    }

    public Set<QuoteModel> getQuoteBinance()
    {   
        return quoteSetBin;
    }

    public Set<QuoteModel> getQuoteBinanceTemp()
    {
        return quoteSetBinTemp;
    }
}
