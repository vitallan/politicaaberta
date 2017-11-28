package com.allanvital.politicaaberta.batch;

import com.allanvital.politicaaberta.batch.processor.ExpenseDtoProcessor;
import com.allanvital.politicaaberta.batch.reader.DeputyDtoReader;
import com.allanvital.politicaaberta.batch.reader.ExpenseDtoReader;
import com.allanvital.politicaaberta.batch.repository.dto.DeputyDto;
import com.allanvital.politicaaberta.batch.repository.dto.ExpenseDto;
import com.allanvital.politicaaberta.batch.writer.DeputyDtoWriter;
import com.allanvital.politicaaberta.batch.writer.ExpenseByMonthWriter;
import com.allanvital.politicaaberta.batch.writer.ExpenseWriter;
import com.allanvital.politicaaberta.batch.writer.listener.DeputyWriterListener;
import com.allanvital.politicaaberta.model.Expense;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class BatchConfiguration {

    @Bean
    public Job expenseBatch(JobBuilderFactory jobBuilderFactory, Step openAndPersistExpense) {
        return jobBuilderFactory.get("Download e processamento de despesas")
                .incrementer(new RunIdIncrementer())
                .flow(openAndPersistExpense)
                .end().build();
    }

    @Bean
    public Job deputyBatch(JobBuilderFactory jobBuilderFactory, Step openAndPersistDeputy) {
        return jobBuilderFactory.get("Download e processamento de deputados")
                .incrementer(new RunIdIncrementer())
                .flow(openAndPersistDeputy)
                .end().build();
    }

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
    public CompositeItemWriter<Expense> compositeWriter(ExpenseWriter expenseWriter, ExpenseByMonthWriter expenseByMonthWriter) {
         CompositeItemWriter<Expense> compositeWriter = new CompositeItemWriter<>();
         compositeWriter.setDelegates(Arrays.asList(expenseWriter, expenseByMonthWriter));
         return compositeWriter;
    }

}
