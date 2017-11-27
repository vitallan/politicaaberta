package com.allanvital.politicaaberta.service;

import com.allanvital.politicaaberta.service.pojo.JobExecutionRequest;
import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

@Service
public class JobService {

    private final static Logger log = Logger.getLogger(JobService.class.getName());

    private Queue<JobExecutionRequest> jobs = new LinkedList<>();
    private JobExecution currentExecution = null;

    private Job deputyBatch;
    private Job expenseBatch;
    private JobLauncher launcher;

    public JobService(Job deputyBatch, JobLauncher launcher, Job expenseBatch) {
        this.deputyBatch = deputyBatch;
        this.launcher = launcher;
        this.expenseBatch = expenseBatch;
    }

    public void executeDeputyBatch() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("Enfileirando batch de deputados...");
        JobExecutionRequest request = new JobExecutionRequest(deputyBatch);
        request.addParameter("executionTime", new Date().toString());
        this.jobs.add(request);
    }

    public void executeExpenseBatch(Long officialId) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.info("Enfileirando batch de despesas do deputado officialId=" + officialId + " ...");
        JobExecutionRequest request = new JobExecutionRequest(expenseBatch);
        request.addParameter("deputyOfficialId", officialId.toString());
        request.addParameter("executionTime", new Date().toString());
        this.jobs.add(request);
    }

    @Scheduled(fixedDelay = 10000)
    public void executeNextJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        if (jobs.isEmpty() || (currentExecution != null && currentExecution.isRunning())) {
            return;
        }
        JobExecutionRequest request = jobs.poll();
        this.currentExecution = launcher.run(request.getJob(), request.getParameters());
    }

}
