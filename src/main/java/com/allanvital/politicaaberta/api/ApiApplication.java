package com.allanvital.politicaaberta.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
    	try {
    		SpringApplication.run(ApiApplication.class, args);
    	} catch (Throwable ex) {
            System.err.println("Uncaught exception - " + ex.getMessage());
            ex.printStackTrace(System.err);
        }
        
    }
    
    @Bean
    public MappingJackson2HttpMessageConverter buildConverter() {
    	MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.registerModule(new Hibernate4Module());
    	converter.setObjectMapper(mapper);
    	return converter;
    }

}
