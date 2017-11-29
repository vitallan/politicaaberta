package com.allanvital.politicaaberta.batch.writer;

import com.allanvital.politicaaberta.batch.repository.dto.CommitteeDto;
import com.allanvital.politicaaberta.model.Committee;
import com.allanvital.politicaaberta.model.Deputy;
import com.allanvital.politicaaberta.model.Expense;
import com.allanvital.politicaaberta.repository.CommitteeRepository;
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
public class CommitteeDtoWriter implements ItemWriter<CommitteeDto> {

    private final CommitteeRepository repository;
    private final DeputyRepository deputyRepository;
    private final String deputyOfficialId;

    public CommitteeDtoWriter(CommitteeRepository repository, DeputyRepository deputyRepository, @Value("#{jobParameters['deputyOfficialId']}") String deputyOfficialId) {
        this.repository = repository;
        this.deputyRepository = deputyRepository;
        this.deputyOfficialId = deputyOfficialId;
    }

    @Override
    public void write(List<? extends CommitteeDto> items) throws Exception {
        items.forEach(item -> {
            Committee committee = repository.findByOfficialId(item.getId());
            if (committee == null) {
                Deputy deputy = deputyRepository.findByOfficialId(new Long(this.deputyOfficialId));
                committee = item.buildEntity();
                committee.setDeputy(deputy);
                repository.save(committee);
            }
        });
    }

}
