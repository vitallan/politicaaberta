package com.allanvital.politicaaberta.batch.writer;

import com.allanvital.politicaaberta.batch.repository.dto.DeputyDto;
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
public class DeputyDtoWriter implements ItemWriter<DeputyDto> {

    private final static Logger log = Logger.getLogger(DeputyDtoWriter.class.getName());

    private DeputyRepository repository;
    private PartyRepository partyRepository;

    public DeputyDtoWriter(DeputyRepository repository, PartyRepository partyRepository) {
        this.repository = repository;
        this.partyRepository = partyRepository;
    }

    @Override
    public void write(List<? extends DeputyDto> items) throws Exception {
        items.forEach((item) -> {
            log.info("Checando a existencia do Deputy referente ao officialId" + item.getOfficialId());
            Party party = partyRepository.findOrCreate(item.getPartyName());
            Deputy deputy = repository.findByOfficialId(item.getOfficialId());
            if (deputy == null) {
                log.info("Persistindo Deputy referente ao officialId=" + item.getOfficialId());
                deputy = item.buildDeputy();
                deputy.setParty(party);
                repository.save(deputy);
            }
        });
    }

}
