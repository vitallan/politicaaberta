package com.allanvital.politicaaberta.batch;

import com.allanvital.politicaaberta.batch.processor.ExpenseDtoProcessor;
import com.allanvital.politicaaberta.batch.reader.*;
import com.allanvital.politicaaberta.batch.repository.dto.*;
import com.allanvital.politicaaberta.batch.writer.*;
import com.allanvital.politicaaberta.batch.writer.listener.DeputyWriterListener;
import com.allanvital.politicaaberta.model.Expense;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class StepsConfiguration {

    @Bean
    public Step openAndPersistDeputy(StepBuilderFactory stepBuilderFactory, DeputyDtoReader reader, DeputyDtoWriter writer, DeputyWriterListener listener) {
        return stepBuilderFactory.get("Consulta e persiste deputados")
                .<DeputyDto, DeputyDto>chunk(1)
                .reader(reader)
                .writer(writer)
                .listener(listener)
                .build();
    }

    @Bean
    public Step openAndPersistExpense(StepBuilderFactory stepBuilderFactory, ExpenseDtoReader reader, CompositeItemWriter<Expense> writer, ExpenseDtoProcessor processor) {
        return stepBuilderFactory.get("Consulta e persiste despesas")
                .<ExpenseDto, Expense>chunk(1)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Step openAndPersistCommittees(StepBuilderFactory stepBuilderFactory, CommitteeDtoReader reader, CommitteeDtoWriter writer) {
        return stepBuilderFactory.get("Consulta e persiste orgaos e comites")
                .<CommitteeDto, CommitteeDto>chunk(1)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    public Step openAndPersistPropositionTypes(StepBuilderFactory stepBuilderFactory, PropositionTypeDtoReader reader, PropositionTypeDtoWriter writer) {
        return stepBuilderFactory.get("Consulta e persiste tipos de proposicao")
                .<PropositionTypeDto, PropositionTypeDto>chunk(1)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    public Step openAndPersistPropositions(StepBuilderFactory stepBuilderFactory, PropositionDtoReader reader, PropositionDtoWriter writer) {
        return stepBuilderFactory.get("Consulta e persiste proposicoes")
                .<PropositionDto, PropositionDto>chunk(1)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean  //TODO: check if it is necessary
    public CompositeItemWriter<Expense> compositeWriter(ExpenseWriter expenseWriter, ExpenseByMonthWriter expenseByMonthWriter) {
        CompositeItemWriter<Expense> compositeWriter = new CompositeItemWriter<>();
        compositeWriter.setDelegates(Arrays.asList(expenseWriter, expenseByMonthWriter));
        return compositeWriter;
    }

}
