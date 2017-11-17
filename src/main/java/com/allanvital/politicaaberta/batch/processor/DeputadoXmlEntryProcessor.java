package com.allanvital.politicaaberta.batch.processor;

import com.allanvital.politicaaberta.model.DeputadoXmlEntry;
import com.allanvital.politicaaberta.repository.DeputadoXmlEntryRepository;
import org.apache.log4j.Logger;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class DeputadoXmlEntryProcessor implements ItemProcessor<DeputadoXmlEntry, DeputadoXmlEntry> {

    private final static Logger log = Logger.getLogger(DeputadoXmlEntryProcessor.class.getName());

    private DeputadoXmlEntryRepository repository;

    public DeputadoXmlEntryProcessor(DeputadoXmlEntryRepository repository) {
        this.repository = repository;
    }

    @Override
    public DeputadoXmlEntry process(DeputadoXmlEntry item) throws Exception {
        log.info("Checando a existencia do deputadoXmlEntry ideCadastro=" + item.getIdeCadastro());
        DeputadoXmlEntry persisted = repository.findByIdeCadastro(item.getIdeCadastro());
        if (persisted == null) {
            log.info("Persistindo deputadoXmlEntry ideCadastro=" + item.getIdeCadastro());
            persisted = repository.save(item);
        }
        return persisted;
    }

}
