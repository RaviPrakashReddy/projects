package com.candela.forte.kafka.listener.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

import com.fasterxml.jackson.databind.ObjectMapper;


@Configuration
@EnableKafka
@ComponentScan({"com.candela.forte.kafka","com.candela.forte.integration"})
@SpringBootApplication
public class ForteKafkaListenerWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForteKafkaListenerWebApplication.class, args);
	}

	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}
}
