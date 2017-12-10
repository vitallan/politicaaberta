package com.allanvital.politicaaberta.controller;

import com.allanvital.politicaaberta.service.JobService;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/admin")
public class AdminEndpoints {

    private String token;
    private JobService jobService;

    public AdminEndpoints(@Value("${admin.api.token}") String token, JobService jobService) {
        this.token = token;
        this.jobService = jobService;
    }

    @PostMapping(value="/load")
    public void processDeputies(HttpServletRequest request, HttpServletResponse response) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        if (!token.equals(request.getHeader("token"))) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        jobService.queuePropositionTypeBatch();
        jobService.queueDeputyBatch();
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
    }

}
