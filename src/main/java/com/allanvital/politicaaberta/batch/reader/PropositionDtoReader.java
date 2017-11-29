package com.allanvital.politicaaberta.batch.reader;

import com.allanvital.politicaaberta.batch.repository.DeputiesChamberRepository;
import com.allanvital.politicaaberta.batch.repository.dto.ExpenseDto;
import com.allanvital.politicaaberta.batch.repository.dto.PropositionDto;
import org.apache.log4j.Logger;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@StepScope
@Component
public class PropositionDtoReader extends AbstractDtoReader<PropositionDto> {

    private final static Logger log = Logger.getLogger(PropositionDtoReader.class.getName());
    private DeputiesChamberRepository repository;
    private String deputyOfficialId;

    public PropositionDtoReader(DeputiesChamberRepository repository, @Value("#{jobParameters['deputyOfficialId']}") String deputyOfficialId) {
        super(log);
        this.repository = repository;
        this.deputyOfficialId = deputyOfficialId;
    }

    @Override
    protected List<PropositionDto> findDtos(int page) {
        return repository.findPropositionsFromDeputy(new Long(deputyOfficialId), page).getData();
    }

}
