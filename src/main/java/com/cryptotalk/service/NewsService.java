package com.cryptotalk.service;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;
import com.cryptotalk.util.AppConfig;
import com.cryptotalk.util.Util;
import com.cryyptotalk.generated.News;

@Service
public class NewsService
{

    private final AppConfig config;

    Map<String, News> newsMap = new ConcurrentHashMap<>();
    Map<String, News> newsMapTemp = new ConcurrentHashMap<>();

    public NewsService(AppConfig config)
    {
        this.config = Objects.requireNonNull(config);
    }

    public Optional<Map<String, News>> setNews()
    {
        try {
            News news = Util.parseJsonFromUrl(this.config.getNewsApiUrl(), News.class);
            newsMap.put("news", news);
            newsMapTemp.put("news", news);
        } catch (Exception ex) {
            System.out.println("Error setting news:" + ex);
        }
        return Optional.ofNullable(newsMap);
    }

    public Optional<Map<String, News>> getNewsMap()
    {
        return Optional.of(newsMap);
    }

    public Optional<Map<String, News>> getNewsMapTemp()
    {
        return Optional.of(newsMapTemp);
    }

}
