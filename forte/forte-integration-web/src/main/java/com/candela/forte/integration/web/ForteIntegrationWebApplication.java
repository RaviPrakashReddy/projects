package com.candela.forte.integration.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

import com.fasterxml.jackson.databind.ObjectMapper;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableKafka
@ComponentScan({"com.candela.forte.integration"})
@SpringBootApplication
public class ForteIntegrationWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForteIntegrationWebApplication.class, args);
	}
	
	 @Bean
	 public Docket databaseApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select().apis(RequestHandlerSelectors.basePackage("com.candela.forte.integration.web.controller"))
	                 .paths(PathSelectors.any())             
	                .build();
	 }

	@Bean
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}

}
