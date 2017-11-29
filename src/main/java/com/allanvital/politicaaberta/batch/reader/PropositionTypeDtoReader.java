package com.allanvital.politicaaberta.batch.reader;

import com.allanvital.politicaaberta.batch.repository.DeputiesChamberRepository;
import com.allanvital.politicaaberta.batch.repository.dto.PropositionTypeDto;
import org.apache.log4j.Logger;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

import java.util.List;

@StepScope
@Component
public class PropositionTypeDtoReader extends AbstractDtoReader<PropositionTypeDto> {

    private final static Logger log = Logger.getLogger(PropositionTypeDtoReader.class.getName());
    private DeputiesChamberRepository repository;

    public PropositionTypeDtoReader(DeputiesChamberRepository repository) {
        super(log);
        this.repository = repository;
    }

    @Override
    protected List<PropositionTypeDto> findDtos(int page) {
        if (page > 1) {
            return null;
        }
        return repository.findPropositionTypes().getData();
    }

}
