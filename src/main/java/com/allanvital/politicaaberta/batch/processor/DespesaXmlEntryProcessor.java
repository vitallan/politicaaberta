package com.allanvital.politicaaberta.batch.processor;

import com.allanvital.politicaaberta.batch.writer.PartyWriter;
import com.allanvital.politicaaberta.model.DespesaXmlEntry;
import com.allanvital.politicaaberta.repository.DespesaXmlEntryRepository;
import org.apache.log4j.Logger;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@JobScope
public class DespesaXmlEntryProcessor implements ItemProcessor<DespesaXmlEntry, DespesaXmlEntry> {

    private final static Logger log = Logger.getLogger(DespesaXmlEntryProcessor.class.getName());

    private DespesaXmlEntryRepository repository;

    public DespesaXmlEntryProcessor(DespesaXmlEntryRepository repository) {
        this.repository = repository;
    }

    @Override
    public DespesaXmlEntry process(DespesaXmlEntry item) throws Exception {
        log.info("Processando despesaXmlEntry " + item);
        DespesaXmlEntry entry = repository.findByNumMesAndNumAnoAndVlrDocumentoAndTxtCNPJCPFAndIdeCadastro(
                item.getNumMes(), item.getNumAno(), item.getVlrDocumento(), item.getTxtCNPJCPF(), item.getIdeCadastro()
        );
        if (entry == null) {
            entry = repository.save(item);
        }
        return entry;
    }

}
