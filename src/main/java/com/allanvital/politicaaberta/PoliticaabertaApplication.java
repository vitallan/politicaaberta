package com.allanvital.politicaaberta;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@EnableAsync
@EnableBatchProcessing
@SpringBootApplication
public class PoliticaabertaApplication implements CommandLineRunner{

    //@Autowired
    //private Job expenseBatch;

    @Autowired
    private Job deputyBatch;

    @Autowired
    private JobLauncher launcher;

	public static void main(String[] args) {
		SpringApplication.run(PoliticaabertaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        Map<String, JobParameter> parameterMap = new HashMap<>();
        parameterMap.put("outputZippedFilename", new JobParameter("deputies.zip"));
        parameterMap.put("downloadUrl", new JobParameter("http://www.camara.leg.br/internet/deputado/DeputadosXML_52a55.zip"));
        parameterMap.put("intermediaryXml", new JobParameter("raw.xml"));
        parameterMap.put("startingAt", new JobParameter(new Date()));
		JobParameters parameters = new JobParameters(parameterMap);

        //launcher.run(deputyBatch, parameters);
	}

}
