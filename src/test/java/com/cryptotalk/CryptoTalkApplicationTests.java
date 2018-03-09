package com.cryptotalk;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cryptotalk.exchanges.Drivers;
import com.cryptotalk.exchanges.Exchanges;
import com.cryptotalk.util.AppConfig;
import com.cryptotalk.util.Util;
import com.cryyptotalk.generated.Cryptopia;
import com.cryyptotalk.generated.News;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RunWith( SpringRunner.class )
@SpringBootTest
public class CryptoTalkApplicationTests
{

    @Test
    public void test_news_api_local() throws JsonParseException, JsonMappingException, IOException
    {
        InputStream input = getClass().getClassLoader().getResourceAsStream("test_news_file.json");
        String expectedTitle = "Traders Cheer as South Korea Backpedals on Cryptocurrency Trading Ban";
        News news = Util.parseJsonAsStream(input, News.class);
        assertEquals(news.getStatus(), "ok");
        assertEquals(news.getTotalResults(), "1213");
        for (int i = 0; i < news.getArticles().length; i++) {
            assertEquals(expectedTitle, news.getArticles()[0].getTitle());
        }

    }

    @Test
    public void test_quote_api_local() throws JsonParseException, JsonMappingException, IOException
    {
      /*  InputStream input = getClass().getClassLoader().getResourceAsStream("test_quote_file.json");
        QuoteWci quote = Util.parseJsonAsStream(input, QuoteWci.class);
        for (int i = 0; i < quote.getMarkets().length; i++) {
            for (int j = 0; j < quote.getMarkets()[i].length; j++) {
                for (int k = 0; k < quote.getMarkets()[i][j].getName().length();) {
                    System.out.println(quote.getMarkets()[i][j].getName());
                    //  break;
                }
            }

        }*/
    	AppConfig config = new AppConfig();
    	Cryptopia market = Util.parseJsonFromUrl(config.getCryptopiaApi(), Cryptopia.class);
    	for(int i=0; i< market.getData().length;i++) {
    		System.out.println(market.getData()[i].getAskPrice());
    	}
		

    }

    @Test
    public void test_scrape()
    {
        WebDriver driver = null;
        try {
            ClassLoader loader = getClass().getClassLoader();
            File file = new File(loader.getResource(Drivers.CHROME_EXE.getValue()).getFile());
            System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
            driver = new ChromeDriver();
            // driver.get(Exchanges.BINANCE.getValue());
            driver.get(Exchanges.CRYPTOPIA.getValue());
            WebElement table_element = driver.findElement(By.id(Exchanges.CRYPTOPIA_TABLE_ID.getValue()));
            List<WebElement> tr_collection = table_element.findElements(By.xpath(Exchanges.BINANCE_XPATH.getValue()));

            //   System.out.println(tr_collection.get(0).getText());
            for (int j = 0; j < tr_collection.size(); j++) {

                //  List<WebElement> td_collection = tr_collection.get(j).findElements(By.xpath("td"));

                System.out.println(tr_collection.get(j).getText());

                // }
            }

        } catch (Exception ex) {
            System.out.println("Error" + ex);
        } finally {
            driver.quit();
        }

    }
}
