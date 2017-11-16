package com.allanvital.politicaaberta.batch.writer;

import com.allanvital.politicaaberta.model.DeputadoXmlEntry;
import com.allanvital.politicaaberta.repository.PartyRepository;
import org.apache.log4j.Logger;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@JobScope
public class PartyWriter implements ItemWriter<DeputadoXmlEntry>{

    private final static Logger log = Logger.getLogger(PartyWriter.class.getName());

    private PartyRepository repository;

    public PartyWriter(PartyRepository repository) {
        this.repository = repository;
    }

    @Override
    public void write(List<? extends DeputadoXmlEntry> items) throws Exception {
        items.forEach((item) -> {
            String partyName = item.getLegendaPartidoEleito();
            log.info("Processando partido " + partyName);
            repository.findOrCreate(partyName);
        });
    }

}
