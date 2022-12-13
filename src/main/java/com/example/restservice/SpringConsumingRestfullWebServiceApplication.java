package com.example.restservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.example.restservice.dto.Quote;

@SpringBootApplication
public class SpringConsumingRestfullWebServiceApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringConsumingRestfullWebServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringConsumingRestfullWebServiceApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}
	
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception{
		return args -> {
			Quote quote = restTemplate.getForObject("http://localhost:8080/api/random", 
					Quote.class);
			log.info(quote.toString());
		};
	}

}
