package com.allanvital.politicaaberta.batch.writer;

import com.allanvital.politicaaberta.batch.repository.dto.PropositionDto;
import com.allanvital.politicaaberta.model.Deputy;
import com.allanvital.politicaaberta.model.Proposition;
import com.allanvital.politicaaberta.model.PropositionType;
import com.allanvital.politicaaberta.repository.DeputyRepository;
import com.allanvital.politicaaberta.repository.PropositionRepository;
import com.allanvital.politicaaberta.repository.PropositionTypeRepository;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@StepScope
@Component
public class PropositionDtoWriter implements ItemWriter<PropositionDto> {

    private final PropositionRepository repository;
    private DeputyRepository deputyRepository;
    private final PropositionTypeRepository typeRepository;
    private String deputyOfficialId;

    public PropositionDtoWriter(PropositionRepository repository, DeputyRepository deputyRepository, PropositionTypeRepository typeRepository, @Value("#{jobParameters['deputyOfficialId']}") String deputyOfficialId) {
        this.repository = repository;
        this.deputyRepository = deputyRepository;
        this.typeRepository = typeRepository;
        this.deputyOfficialId = deputyOfficialId;
    }
    
    @Override
    public void write(List<? extends PropositionDto> items) throws Exception {
        items.forEach(item -> {
            Proposition persistedProposition = repository.findByOfficialId(item.getId());
            if (persistedProposition == null) {
                PropositionType type = typeRepository.findByOfficialId(item.getTypeId());
                Deputy deputy = deputyRepository.findByOfficialId(new Long(this.deputyOfficialId));
                Proposition proposition = item.buildEntity();
                proposition.setDeputy(deputy);
                proposition.setType(type);
                repository.save(proposition);
            }
        });
    }

}
