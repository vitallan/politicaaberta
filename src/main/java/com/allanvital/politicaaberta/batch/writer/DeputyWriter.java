package com.allanvital.politicaaberta.batch.writer;

import com.allanvital.politicaaberta.model.DeputadoXmlEntry;
import com.allanvital.politicaaberta.model.Deputy;
import com.allanvital.politicaaberta.model.Party;
import com.allanvital.politicaaberta.repository.DeputyRepository;
import com.allanvital.politicaaberta.repository.PartyRepository;
import org.apache.log4j.Logger;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@StepScope
public class DeputyWriter implements ItemWriter<DeputadoXmlEntry> {

    private final static Logger log = Logger.getLogger(DeputyWriter.class.getName());

    private DeputyRepository repository;
    private PartyRepository partyRepository;

    public DeputyWriter(DeputyRepository repository, PartyRepository partyRepository) {
        this.repository = repository;
        this.partyRepository = partyRepository;
    }

    @Override
    public void write(List<? extends DeputadoXmlEntry> items) throws Exception {
        items.forEach((item) -> {
            log.info("Checando a existencia do Deputy referente ao DeputadoXmlEntry id=" + item.getIdeCadastro());
            Party party = partyRepository.findByName(item.getLegendaPartidoEleito());
            Deputy deputy = repository.findByIdeCadastro(item.getIdeCadastro());
            if (deputy == null) {
                log.info("Persistindo Deputy referente ao DeputadoXmlEntry id=" + item.getIdeCadastro());
                deputy = item.buildDeputy();
                deputy.setParty(party);
                repository.save(deputy);
            }
        });
    }

}
