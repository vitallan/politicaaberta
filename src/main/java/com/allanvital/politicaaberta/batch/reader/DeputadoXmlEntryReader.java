package com.allanvital.politicaaberta.batch.reader;

import com.allanvital.politicaaberta.model.DeputadoXmlEntry;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;

@Component
@StepScope
public class DeputadoXmlEntryReader extends XmlEntryReader<DeputadoXmlEntry> {

    public DeputadoXmlEntryReader(@Value("#{jobParameters['intermediaryXml']}") String intermediaryXml) throws JAXBException {
        super(intermediaryXml, "Deputado", DeputadoXmlEntry.class);
    }

}
