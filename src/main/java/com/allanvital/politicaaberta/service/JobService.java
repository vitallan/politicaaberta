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

import javax.xml.crypto.dsig.keyinfo.KeyValue;
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
    private Job commiteeBatch;
    private Job propositionTypeBatch;
    private Job propositionBatch;
    private JobLauncher launcher;

    public JobService(Job deputyBatch, JobLauncher launcher, Job expenseBatch, Job commiteeBatch, Job propositionTypeBatch, Job propositionBatch) {
        this.deputyBatch = deputyBatch;
        this.launcher = launcher;
        this.expenseBatch = expenseBatch;
        this.commiteeBatch = commiteeBatch;
        this.propositionTypeBatch = propositionTypeBatch;
        this.propositionBatch = propositionBatch;
    }

    @Scheduled(cron = "0 0 1 1 * ?") //at midnight on the first day of each month
    public void queueDeputyBatch() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.debug("Enfileirando batch de deputados...");
        this.jobs.add(this.buildSimpleJobExecutionRequest(deputyBatch));
    }

    public void queueExpenseBatch(Long officialId) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.debug("Enfileirando batch de despesas do deputado officialId=" + officialId + " ...");
        this.jobs.add(this.buildSimpleJobExecutionRequest(expenseBatch).addParameter("deputyOfficialId", officialId.toString()));
    }

    public void queueCommitteeBatch(Long officialId) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.debug("Enfileirando batch de comites do deputado officialId=" + officialId + " ...");
        this.jobs.add(this.buildSimpleJobExecutionRequest(commiteeBatch).addParameter("deputyOfficialId", officialId.toString()));
    }

    public void queuePropositionBatch(Long officialId) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.debug("Enfileirando batch de proposicoes do deputado officialId=" + officialId + " ...");
        this.jobs.add(this.buildSimpleJobExecutionRequest(propositionBatch).addParameter("deputyOfficialId", officialId.toString()));
    }

    public void queuePropositionTypeBatch() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        log.debug("Enfileirando batch de tipos de proposicoes");
        this.jobs.add(this.buildSimpleJobExecutionRequest(propositionTypeBatch));
    }

    @Scheduled(fixedDelay = 1000)
    private void executeNextJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        if (jobs.isEmpty() || (currentExecution != null && currentExecution.isRunning())) {
            return;
        }
        JobExecutionRequest request = jobs.poll();
        this.currentExecution = launcher.run(request.getJob(), request.getParameters());
    }

    private JobExecutionRequest buildSimpleJobExecutionRequest(Job job) {
        JobExecutionRequest request = new JobExecutionRequest(job);
        request.addParameter("executionStartTime", new Date().toString());
        return request;
    }

}
