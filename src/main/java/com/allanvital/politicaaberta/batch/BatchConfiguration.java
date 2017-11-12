package com.allanvital.politicaaberta.batch;

import com.allanvital.politicaaberta.model.DespesaXmlEntry;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.oxm.xmlbeans.XmlBeansMarshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.transform.Source;

@Configuration
public class BatchConfiguration {

    @Bean
    public Job reportJob(JobBuilderFactory jobBuilderFactory, Step step1) {
        return jobBuilderFactory.get("ma-role").incrementer(new RunIdIncrementer()).flow(step1).end().build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, StaxEventItemReader<DespesaXmlEntry> reader, ItemWriter<DespesaXmlEntry> writer ) {
        return stepBuilderFactory.get("job1").<DespesaXmlEntry, DespesaXmlEntry>chunk(10)
                .reader(reader).writer(writer).build();
    }

    @Bean
    public StaxEventItemReader<DespesaXmlEntry> reader() {
        StaxEventItemReader<DespesaXmlEntry> reader = new StaxEventItemReader<>();
        reader.setResource(new FileSystemResource("Ano-2017.xml"));
        reader.setFragmentRootElementName("DESPESA");
        Jaxb2Marshaller unmarshaller = new Jaxb2Marshaller();
        unmarshaller.setClassesToBeBound(DespesaXmlEntry.class);
        reader.setUnmarshaller(unmarshaller);
        return reader;
    }

    @Bean
    public ItemWriter<DespesaXmlEntry> writer() {
        return new ItemWriter<DespesaXmlEntry>() {
            @Override
            public void write(List<? extends DespesaXmlEntry> items) throws Exception {
                items.forEach((i) -> System.out.println(i));
            }
        };
    }

}
