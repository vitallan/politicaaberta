package com.allanvital.politicaaberta.batch.processor;

import com.allanvital.politicaaberta.model.DespesaXmlEntry;
import com.allanvital.politicaaberta.repository.DespesaXmlEntryRepository;
import org.springframework.batch.item.ItemProcessor;

public class DespesaXmlEntryProcessor implements ItemProcessor<DespesaXmlEntry, DespesaXmlEntry> {

    private DespesaXmlEntryRepository repository;

    public DespesaXmlEntryProcessor(DespesaXmlEntryRepository repository) {
        this.repository = repository;
    }

    @Override
    public DespesaXmlEntry process(DespesaXmlEntry item) throws Exception {
        DespesaXmlEntry entry = repository.findByNumMesAndNumAnoAndVlrDocumentoAndTxtCNPJCPFAndIdeCadastro(
                item.getNumMes(), item.getNumAno(), item.getVlrDocumento(), item.getTxtCNPJCPF(), item.getIdeCadastro()
        );
        if (entry == null) {
            entry = repository.save(entry);
        }
        return entry;
    }

}
