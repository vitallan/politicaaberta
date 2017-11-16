package com.allanvital.politicaaberta.batch.reader;

import com.allanvital.politicaaberta.model.DespesaXmlEntry;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;

@Component
@StepScope
public class DespesaXmlEntryReader extends XmlEntryReader<DespesaXmlEntry> {

    public DespesaXmlEntryReader(@Value("#{jobParameters['intermediaryXml']}") String intermediaryXml) throws JAXBException {
        super(intermediaryXml, "DESPESA", DespesaXmlEntry.class);
    }

}
