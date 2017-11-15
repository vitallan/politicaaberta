package com.allanvital.politicaaberta.batch;

import com.allanvital.politicaaberta.batch.reader.FileDownloadReader;
import com.allanvital.politicaaberta.batch.writer.FileUnzipWriter;
import com.allanvital.politicaaberta.model.DeputadoXmlEntry;
import com.allanvital.politicaaberta.model.DespesaXmlEntry;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

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
    public Step openAndPersistExpense(StepBuilderFactory stepBuilderFactory, StaxEventItemReader<DespesaXmlEntry> despesaReader, ItemWriter<DespesaXmlEntry> expenseWriter) {
        return stepBuilderFactory.get("Abre e processa arquivo de Despesas").<DespesaXmlEntry, DespesaXmlEntry>chunk(1)
                .reader(despesaReader).writer(expenseWriter).build();
    }

    @Bean
    public Step openAndPersistDeputy(StepBuilderFactory stepBuilderFactory, StaxEventItemReader<DeputadoXmlEntry> deputadoReader, ItemWriter<DeputadoXmlEntry> deputyWriter) {
        return stepBuilderFactory.get("Abre e processa arquivo de Despesas").<DeputadoXmlEntry, DeputadoXmlEntry>chunk(1)
                .reader(deputadoReader).writer(deputyWriter).build();
    }

    @Bean
    public Step downloadAndUnzipFile(StepBuilderFactory stepBuilderFactory, FileDownloadReader fileDownloadReader, FileUnzipWriter fileUnzipWriter) {
        return stepBuilderFactory.get("Download e unzip de Arquivo").<File, File>chunk(1)
                .reader(fileDownloadReader).writer(fileUnzipWriter).build();
    }

    @Bean
    @StepScope
    public StaxEventItemReader<DespesaXmlEntry> despesaReader(@Value("#{jobParameters['intermediaryXml']}") String fileName) {
        return this.buildReader(DespesaXmlEntry.class, "DESPESA", fileName);
    }

    @Bean
    @StepScope
    public StaxEventItemReader<DeputadoXmlEntry> deputadoReader(@Value("#{jobParameters['intermediaryXml']}") String fileName) {
        return this.buildReader(DeputadoXmlEntry.class, "Deputado", fileName);
    }

    public <T> StaxEventItemReader<T> buildReader(Class<T> clazz, String fragmentRootElement, String fileName) {
        StaxEventItemReader<T> reader = new StaxEventItemReader<>();
        reader.setFragmentRootElementName(fragmentRootElement);
        Jaxb2Marshaller unmarshaller = new Jaxb2Marshaller();
        unmarshaller.setClassesToBeBound(clazz);
        reader.setResource(new FileSystemResource(fileName));
        reader.setUnmarshaller(unmarshaller);
        return reader;
    }

    @Bean
    public ItemWriter<DespesaXmlEntry> expenseWriter() {
        return new ItemWriter<DespesaXmlEntry>() {
            @Override
            public void write(List<? extends DespesaXmlEntry> items) throws Exception {
                items.forEach((i) -> System.out.println(i));
            }
        };
    }

    @Bean
    public ItemWriter<DeputadoXmlEntry> deputyWriter() {
        return new ItemWriter<DeputadoXmlEntry>() {
            @Override
            public void write(List<? extends DeputadoXmlEntry> items) throws Exception {
                items.forEach((i) -> System.out.println(i));
            }
        };
    }

}
