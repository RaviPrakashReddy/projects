package com.candela.epos.solutions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@ComponentScan({ "com.candela.epos.solutions" })
@PropertySource("classpath:application.properties")
@SpringBootApplication
public class EposSoultionApplication {

	private static Logger log = LoggerFactory.getLogger(EposSoultionApplication.class);

	public static void main(String[] args) {
		log.info(":::::::::::::::: Started EPOS Soltion Application ::::::::::::::::");
		SpringApplication.run(EposSoultionApplication.class, args);
	}

	@Bean
	public Docket databaseApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.candela.epos.solutions.controller")).paths(PathSelectors.any())
				.build();
	}

}
