package com.allanvital.politicaaberta;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableScheduling
@EnableBatchProcessing
@EnableFeignClients
@EnableWebMvc
@SpringBootApplication
public class PoliticaabertaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoliticaabertaApplication.class, args);
	}

}
