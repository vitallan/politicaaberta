package com.allanvital.politicaaberta;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@EnableBatchProcessing
@SpringBootApplication
public class PoliticaabertaApplication implements CommandLineRunner{

    @Autowired
    private Job expenseBatch;

    @Autowired
    private JobLauncher launcher;

	public static void main(String[] args) {
		SpringApplication.run(PoliticaabertaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        Map<String, JobParameter> parameterMap = new HashMap<>();
        parameterMap.put("outputZippedFilename", new JobParameter("file2015.zip"));
        parameterMap.put("downloadUrl", new JobParameter("http://www.camara.leg.br/cotas/Ano-2015.xml.zip"));
        parameterMap.put("intermediaryXml", new JobParameter("raw.xml"));
		JobParameters parameters = new JobParameters(parameterMap);

        launcher.run(expenseBatch, parameters);
	}

}
