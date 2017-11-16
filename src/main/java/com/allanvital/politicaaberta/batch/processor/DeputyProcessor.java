package com.allanvital.politicaaberta.batch.processor;

import com.allanvital.politicaaberta.model.DeputadoXmlEntry;
import com.allanvital.politicaaberta.model.Deputy;
import com.allanvital.politicaaberta.model.Party;
import com.allanvital.politicaaberta.repository.DeputadoXmlEntryRepository;
import com.allanvital.politicaaberta.repository.DeputyRepository;
import com.allanvital.politicaaberta.repository.PartyRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@JobScope
@Component
public class DeputyProcessor implements ItemProcessor<DeputadoXmlEntry, Deputy> {

    private static final Log logger = LogFactory.getLog(DeputyProcessor.class);

    private DeputadoXmlEntryRepository xmlEntryRepository;
    private DeputyRepository deputyRepository;
    private PartyRepository partyRepository;

    public DeputyProcessor(DeputadoXmlEntryRepository xmlEntryRepository, DeputyRepository deputyRepository, PartyRepository partyRepository) {
        this.xmlEntryRepository = xmlEntryRepository;
        this.deputyRepository = deputyRepository;
        this.partyRepository = partyRepository;
    }

    @Override
    public Deputy process(DeputadoXmlEntry item) throws Exception {
        logger.debug("Checando existencia do registro ideCadastro=" + item.getIdeCadastro() + " nas tabelas de DeputadoXmlEntry e Deputy");
        DeputadoXmlEntry persistedXmlEntry = xmlEntryRepository.findByIdeCadastro(item.getIdeCadastro());
        if (persistedXmlEntry == null) {
            persistedXmlEntry = xmlEntryRepository.save(item);
        }
        Deputy deputy = deputyRepository.findByDeputyXmlEntryId(persistedXmlEntry.getId());
        if (deputy == null) {
            Party party = partyRepository.findOrCreate(persistedXmlEntry.getLegendaPartidoEleito());
            deputy = persistedXmlEntry.buildDeputy();
            deputy.setParty(party);
            deputy = deputyRepository.save(deputy);
        }
        return deputy;
    }

}
