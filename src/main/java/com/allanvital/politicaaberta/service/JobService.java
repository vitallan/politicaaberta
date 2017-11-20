package com.allanvital.politicaaberta.service;

import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JobService {

    private final static Logger log = Logger.getLogger(JobService.class.getName());

    private Job deputyBatch;
    private Job expenseBatch;
    private JobLauncher launcher;

    public JobService(Job deputyBatch, JobLauncher launcher, Job expenseBatch) {
        this.deputyBatch = deputyBatch;
        this.launcher = launcher;
        this.expenseBatch = expenseBatch;
    }

    @Async
    public void executeDeputyBatch() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("Executando batch de deputado...");
        Map<String, JobParameter> parameterMap = new HashMap<>();
        parameterMap.put("outputZippedFilename", new JobParameter("deputies.zip"));
        parameterMap.put("downloadUrl", new JobParameter("http://www.camara.leg.br/internet/deputado/DeputadosXML_52a55.zip"));
        parameterMap.put("intermediaryXml", new JobParameter("raw.xml"));
        parameterMap.put("startingAt", new JobParameter(new Date()));
        JobParameters parameters = new JobParameters(parameterMap);
        launcher.run(deputyBatch, parameters);
    }

    @Async
    public void executeExpenseBatch(int year) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("Executando batch de despesas...");
        Map<String, JobParameter> parameterMap = new HashMap<>();
        parameterMap.put("outputZippedFilename", new JobParameter("file" + year + ".zip"));
        parameterMap.put("downloadUrl", new JobParameter("http://www.camara.leg.br/cotas/Ano-" + year + ".xml.zip"));
        parameterMap.put("intermediaryXml", new JobParameter("expense-" + year + ".xml"));
        parameterMap.put("startingAt", new JobParameter(new Date()));
        JobParameters parameters = new JobParameters(parameterMap);
        launcher.run(expenseBatch, parameters);
    }

}
