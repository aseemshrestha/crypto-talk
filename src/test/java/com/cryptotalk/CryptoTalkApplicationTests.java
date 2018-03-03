package com.cryptotalk;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cryptotalk.util.Util;
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

}
