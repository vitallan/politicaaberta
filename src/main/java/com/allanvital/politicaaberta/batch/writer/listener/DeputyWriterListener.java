package com.allanvital.politicaaberta.batch.writer.listener;

import com.allanvital.politicaaberta.batch.repository.dto.DeputyDto;
import com.allanvital.politicaaberta.service.JobService;
import org.apache.log4j.Logger;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@StepScope
public class DeputyWriterListener implements ItemWriteListener<DeputyDto> {

    private final static Logger log = Logger.getLogger(DeputyWriterListener.class.getName());

    private JobService jobService;

    public DeputyWriterListener(JobService jobService) {
        this.jobService = jobService;
    }

    @Override
    public void beforeWrite(List<? extends DeputyDto> items) {

    }

    @Override
    public void afterWrite(List<? extends DeputyDto> items) {
        items.forEach(item -> {
            try {
                jobService.executeCommitteeBatch(item.getOfficialId());
                jobService.executeExpenseBatch(item.getOfficialId());
            } catch (JobParametersInvalidException | JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException e) {
                log.error("Erro ao agendar execucao dos jobs colaterais a deputado " + Arrays.toString(e.getStackTrace()));
            }
        });
    }

    @Override
    public void onWriteError(Exception exception, List<? extends DeputyDto> items) {

    }

}
