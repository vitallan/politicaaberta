package com.allanvital.politicaaberta.controller;

import com.allanvital.politicaaberta.batch.repository.DeputiesChamberRepository;
import com.allanvital.politicaaberta.service.JobService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.batch.core.Job;
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
import java.util.Calendar;

@RestController
@RequestMapping("/admin")
public class AdminEndpoints {

    private String token;
    private JobService jobService;
    private DeputiesChamberRepository chamberRepository;

    public AdminEndpoints(@Value("${admin.api.token}") String token, JobService jobService, DeputiesChamberRepository chamberRepository) {
        this.token = token;
        this.jobService = jobService;
        this.chamberRepository = chamberRepository;
    }

    @PostMapping(value="/deputies")
    public void processDeputies(HttpServletRequest request, HttpServletResponse response) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        if (!token.equals(request.getHeader("token"))) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        jobService.executeDeputyBatch();
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
    }

    @PostMapping("/expenses")
    public void processExpenses(HttpServletRequest request, HttpServletResponse response) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        if (!token.equals(request.getHeader("token"))) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        int year = this.extractYear(request);
        jobService.executeExpenseBatch(1L);
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
    }

    private int extractYear(HttpServletRequest request) {
        String stringYear = request.getHeader("year");
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (stringYear == null || !NumberUtils.isCreatable(stringYear)) {
            return currentYear;
        }
        int year = NumberUtils.createInteger(stringYear);
        if (year < 2009 || year > currentYear) {
            return currentYear;
        }
        return year;
    }

}
