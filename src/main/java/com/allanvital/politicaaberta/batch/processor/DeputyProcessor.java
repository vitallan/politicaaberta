package com.allanvital.politicaaberta.batch.processor;

import com.allanvital.politicaaberta.model.DeputadoXmlEntry;
import com.allanvital.politicaaberta.model.Deputy;
import com.allanvital.politicaaberta.repository.DeputadoXmlEntryRepository;
import com.allanvital.politicaaberta.repository.DeputyRepository;
import org.springframework.batch.item.ItemProcessor;

import javax.persistence.Entity;

@Entity
public class DeputyProcessor implements ItemProcessor<DeputadoXmlEntry, Deputy> {

    private DeputadoXmlEntryRepository xmlEntryRepository;
    private DeputyRepository deputyRepository;

    public DeputyProcessor(DeputadoXmlEntryRepository xmlEntryRepository, DeputyRepository deputyRepository) {
        this.xmlEntryRepository = xmlEntryRepository;
        this.deputyRepository = deputyRepository;
    }

    @Override
    public Deputy process(DeputadoXmlEntry item) throws Exception {
        DeputadoXmlEntry persistedXmlEntry = xmlEntryRepository.findByIdeCadastro(item.getIdeCadastro());
        if (persistedXmlEntry == null) {
            persistedXmlEntry = xmlEntryRepository.save(item);
        }
        return null;
    }

}
