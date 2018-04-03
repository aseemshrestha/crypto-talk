package com.cryptotalk;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import com.cryptotalk.controller.NewsController;
import com.cryptotalk.service.NewsService;
import com.cryptotalk.util.Util;
import com.cryyptotalk.generated.News;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RunWith( SpringJUnit4ClassRunner.class )
@SpringBootTest
public class CryptoTalkApplicationTests
{

    @Mock
    private NewsService newsService;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    Map<String, News> newsMap = new ConcurrentHashMap<>();

    @Test
    public void test_news_api_mock() throws JsonParseException, JsonMappingException, IOException
    {
        NewsController newsController = new NewsController();
        ReflectionTestUtils.setField(newsController, "newsService", newsService);
        InputStream input = getClass().getClassLoader().getResourceAsStream("test_news_file.json");
        News market = Util.parseJsonAsStream(input, News.class);
        newsMap.put("news", market);
        when(newsService.getNews()).thenReturn(newsMap);

    }

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
