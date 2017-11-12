package com.allanvital.politicaaberta;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class PoliticaabertaApplication {

    @Autowired
    private Job reportJob;

	public static void main(String[] args) {
		SpringApplication.run(PoliticaabertaApplication.class, args);
	}

    public void run() throws Exception {
        System.out.println("Vai job");
        JobExecution exec = new JobExecution(1L);
        reportJob.execute(exec);
    }
}
