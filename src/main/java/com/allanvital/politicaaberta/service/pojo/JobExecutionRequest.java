package com.allanvital.politicaaberta.service.pojo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;

import java.util.HashMap;
import java.util.Map;

public class JobExecutionRequest {

    private Job job;
    private Map<String, JobParameter> parameters = new HashMap<>();

    public JobExecutionRequest(Job job) {
        this.job = job;
    }

    public JobExecutionRequest addParameter(String key, String value) {
        parameters.put(key, new JobParameter(value));
        return this;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public JobParameters getParameters() {
        return new JobParameters(this.parameters);
    }

}
