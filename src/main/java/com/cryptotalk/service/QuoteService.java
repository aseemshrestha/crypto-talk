package com.cryptotalk.service;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.cryptotalk.util.AppConfig;
import com.cryyptotalk.generated.Quote;

@Service
public class QuoteService
{
    private final AppConfig config;

    static Map<String, Quote[]> quoteMap = new ConcurrentHashMap<>();

    public QuoteService(AppConfig config)
    {
        this.config = Objects.requireNonNull(config);
    }

    public Optional<Map<String, Quote[]>> getQuotes()
    {
        if (quoteMap.isEmpty()) {
            RestTemplate restTemplate = new RestTemplate();
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.set(this.config.getQuoteApiHeaderKey(), this.config.getQuoteApiHeaderValue());
                HttpEntity<Quote> entity = new HttpEntity<Quote>(headers);
                ResponseEntity<Quote[]> response =
                    restTemplate.exchange(this.config.getQuoteApi(), HttpMethod.GET, entity, Quote[].class);
                quoteMap.put("quote", response.getBody());

            } catch (Exception ex) {
                System.out.println("exception ex" + ex);
            }

        }

        return Optional.ofNullable(quoteMap);
    }

    public Map<String, Quote[]> getQuoteMap()
    {
        return quoteMap;
    }

}
