package com.allanvital.politicaaberta.batch.writer;

import com.allanvital.politicaaberta.batch.repository.dto.PropositionTypeDto;
import com.allanvital.politicaaberta.model.PropositionType;
import com.allanvital.politicaaberta.repository.PropositionTypeRepository;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@StepScope
@Component
public class PropositionTypeDtoWriter implements ItemWriter<PropositionTypeDto> {

    private PropositionTypeRepository repository;

    public PropositionTypeDtoWriter(PropositionTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void write(List<? extends PropositionTypeDto> items) throws Exception {
        items.forEach(item -> {
            PropositionType type = repository.findByOfficialId(item.getId());
            if(type == null) {
                repository.save(item.buildEntity());
            }
        });
    }

}
