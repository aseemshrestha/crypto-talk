package com.cryptotalk;

import java.util.Timer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import com.cryptotalk.service.ScheduledTask;

@SpringBootApplication
@ComponentScan( basePackages = "com.cryptotalk" )

public class CryptoTalkApplication extends SpringBootServletInitializer
{

    public static void main(String[] args)
    {
        SpringApplication.run(CryptoTalkApplication.class, args);
        Timer timer = new Timer();
        ScheduledTask st = new ScheduledTask();
        timer.schedule(st, 0l, (1000 * 60 * 60));
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(CryptoTalkApplication.class);
    }

}
