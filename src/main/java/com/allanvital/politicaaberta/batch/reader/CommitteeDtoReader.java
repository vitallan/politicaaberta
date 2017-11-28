package com.allanvital.politicaaberta.batch.reader;

import com.allanvital.politicaaberta.batch.repository.DeputiesChamberRepository;
import com.allanvital.politicaaberta.batch.repository.dto.CommitteeDto;
import com.allanvital.politicaaberta.batch.repository.dto.ExpenseDto;
import org.apache.log4j.Logger;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@StepScope
@Component
public class CommitteeDtoReader extends AbstractDtoReader<CommitteeDto> {

    private final static Logger log = Logger.getLogger(CommitteeDtoReader.class.getName());
    private DeputiesChamberRepository repository;
    private String deputyOfficialId;

    public CommitteeDtoReader(DeputiesChamberRepository repository, @Value("#{jobParameters['deputyOfficialId']}") String deputyOfficialId) {
        super(log);
        this.repository = repository;
        this.deputyOfficialId = deputyOfficialId;
    }

    @Override
    protected List<CommitteeDto> findDtos(int page) {
        return repository.findCommitteesFromDeputy(new Long(deputyOfficialId), page).getData();
    }

}
