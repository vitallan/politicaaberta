package com.allanvital.politicaaberta.batch;

import com.allanvital.politicaaberta.batch.reader.DeputadoXmlEntryReader;
import com.allanvital.politicaaberta.batch.reader.DespesaXmlEntryReader;
import com.allanvital.politicaaberta.batch.reader.FileDownloadReader;
import com.allanvital.politicaaberta.batch.writer.FileUnzipWriter;
import com.allanvital.politicaaberta.model.DeputadoXmlEntry;
import com.allanvital.politicaaberta.model.DespesaXmlEntry;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.List;

@Configuration
public class BatchConfiguration {

    @Bean
    public Job expenseBatch(JobBuilderFactory jobBuilderFactory, Step downloadAndUnzipFile, Step openAndPersistExpense) {
        return jobBuilderFactory.get("Download e processamento de despesas").incrementer(new RunIdIncrementer())
                .flow(downloadAndUnzipFile)
                .next(openAndPersistExpense)
                .end().build();
    }

    @Bean
    public Job deputyBatch(JobBuilderFactory jobBuilderFactory, Step downloadAndUnzipFile, Step openAndPersistDeputy) {
        return jobBuilderFactory.get("Download e processamento de deputados").incrementer(new RunIdIncrementer())
                .flow(downloadAndUnzipFile)
                .next(openAndPersistDeputy)
                .end().build();
    }

    @Bean
    public Step openAndPersistExpense(StepBuilderFactory stepBuilderFactory, DespesaXmlEntryReader despesaReader, ItemWriter<DespesaXmlEntry> expenseWriter) {
        return stepBuilderFactory.get("Abre e processa arquivo de Despesas").<DespesaXmlEntry, DespesaXmlEntry>chunk(1)
                .reader(despesaReader).writer(expenseWriter).build();
    }

    @Bean
    public Step openAndPersistDeputy(StepBuilderFactory stepBuilderFactory, DeputadoXmlEntryReader deputadoReader, ItemWriter<DeputadoXmlEntry> deputyWriter) {
        return stepBuilderFactory.get("Abre e processa arquivo de Despesas").<DeputadoXmlEntry, DeputadoXmlEntry>chunk(1)
                .reader(deputadoReader).writer(deputyWriter).build();
    }

    @Bean
    public Step downloadAndUnzipFile(StepBuilderFactory stepBuilderFactory, FileDownloadReader fileDownloadReader, FileUnzipWriter fileUnzipWriter) {
        return stepBuilderFactory.get("Download e unzip de Arquivo").<File, File>chunk(1)
                .reader(fileDownloadReader).writer(fileUnzipWriter).build();
    }

    @Bean
    public ItemWriter<DespesaXmlEntry> expenseWriter() {
        return items -> items.forEach((i) -> System.out.println(i));
    }

    @Bean
    public ItemWriter<DeputadoXmlEntry> deputyWriter() {
        return items -> items.forEach((i) -> System.out.println(i));
    }

}
