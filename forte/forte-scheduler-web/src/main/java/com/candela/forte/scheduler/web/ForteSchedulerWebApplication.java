package com.candela.forte.scheduler.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


@Configuration
@ComponentScan({"com.candela.forte.scheduler","com.candela.forte.integration"})
@EnableScheduling
@SpringBootApplication
public class ForteSchedulerWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForteSchedulerWebApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate()throws Exception,Error{
		try {
				return new RestTemplate();
		}catch(Exception xe) {
			xe.fillInStackTrace();
			throw xe;
		}catch(Error error) {
			error.fillInStackTrace();
			throw error;
		}
	}

}
