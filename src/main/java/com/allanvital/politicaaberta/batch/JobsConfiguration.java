package com.allanvital.politicaaberta.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobsConfiguration {

    @Bean
    public Job expenseBatch(JobBuilderFactory jobBuilderFactory, Step openAndPersistExpense) {
        return this.newSingleStepJob(jobBuilderFactory, "Consulta e processamento de despesas", openAndPersistExpense);
    }

    @Bean
    public Job deputyBatch(JobBuilderFactory jobBuilderFactory, Step openAndPersistDeputy) {
        return this.newSingleStepJob(jobBuilderFactory, "Consulta e processamento de deputados", openAndPersistDeputy);
    }

    @Bean
    public Job commiteeBatch(JobBuilderFactory jobBuilderFactory, Step openAndPersistCommittees) {
        return this.newSingleStepJob(jobBuilderFactory, "Consulta e processamento de orgaos e comites", openAndPersistCommittees);
    }

    @Bean
    public Job propositionTypeBatch(JobBuilderFactory jobBuilderFactory, Step openAndPersistPropositionTypes) {
        return this.newSingleStepJob(jobBuilderFactory, "Consulta e processamento de tipos de proposicao", openAndPersistPropositionTypes);
    }

    @Bean
    public Job propositionBatch(JobBuilderFactory jobBuilderFactory, Step openAndPersistPropositions) {
        return this.newSingleStepJob(jobBuilderFactory, "Consulta e processamento de proposicoes", openAndPersistPropositions);
    }

    private Job newSingleStepJob(JobBuilderFactory jobBuilderFactory, String jobName, Step singleStep) {
        return jobBuilderFactory.get(jobName)
                .incrementer(new RunIdIncrementer())
                .flow(singleStep)
                .end()
                .build();
    }

}
