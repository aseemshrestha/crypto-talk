package com.cryptotalk.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;
import com.cryptotalk.util.AppConfig;
import com.cryptotalk.util.Util;
import com.cryyptotalk.generated.News;

@Service
public class NewsService
{

    private final AppConfig config;

    static Map<String, News> newsMap = new ConcurrentHashMap<>();

    public NewsService(AppConfig config)
    {
        this.config = Objects.requireNonNull(config);
    }

    public Map<String, News> getNews()
    {
        try {
            if (newsMap.isEmpty()) {
                newsMap.put("news", Util.parseJsonFromUrl(this.config.getNewsApiUrl(), News.class));
            }
            return newsMap;

        } catch (IOException e) {
            System.out.printf("Couldn't get news", e);
        }
        return null;
    }

    public Map<String, News> getNewsMap()
    {
        return newsMap;
    }

}
