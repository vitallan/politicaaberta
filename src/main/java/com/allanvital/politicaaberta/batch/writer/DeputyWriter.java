package com.allanvital.politicaaberta.batch.writer;

import com.allanvital.politicaaberta.model.DeputadoXmlEntry;
import com.allanvital.politicaaberta.model.Deputy;
import com.allanvital.politicaaberta.model.Party;
import com.allanvital.politicaaberta.repository.DeputyRepository;
import com.allanvital.politicaaberta.repository.PartyRepository;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@StepScope
public class DeputyWriter implements ItemWriter<DeputadoXmlEntry>{

    private DeputyRepository repository;
    private PartyRepository partyRepository;

    public DeputyWriter(DeputyRepository repository, PartyRepository partyRepository) {
        this.repository = repository;
        this.partyRepository = partyRepository;
    }

    @Override
    public void write(List<? extends DeputadoXmlEntry> items) throws Exception {
        items.forEach((item) -> {
            Party party = partyRepository.findByName(item.getLegendaPartidoEleito());
            Deputy deputy = repository.findByDeputyXmlEntryId(item.getId()); //TODO: processor para salvar o xmlentryid
            if (deputy == null) {
                deputy = item.buildDeputy();
                deputy.setParty(party);
                repository.save(deputy);
            }
        });
    }

}
