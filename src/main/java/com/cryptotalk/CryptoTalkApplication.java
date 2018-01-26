package com.cryptotalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan( basePackages = "com.cryptotalk" ) 

public class CryptoTalkApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CryptoTalkApplication.class, args);
	}

	// provides options not to deploy in embedded tomcat
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CryptoTalkApplication.class);
	}

	
}
