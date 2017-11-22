package com.allanvital.politicaaberta.batch.reader;

import com.allanvital.politicaaberta.batch.repository.DeputiesChamberRepository;
import com.allanvital.politicaaberta.batch.repository.dto.DeputyDto;
import org.apache.log4j.Logger;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@StepScope
public class DeputyDtoReader extends AbstractDtoReader<DeputyDto>{

    private final static Logger log = Logger.getLogger(DeputyDtoReader.class.getName());
    private DeputiesChamberRepository repository;

    public DeputyDtoReader(DeputiesChamberRepository repository) {
        super(log);
        this.repository = repository;
    }

    @Override
    protected List<DeputyDto> findDtos(int page) {
        return repository.findDeputies(page).getData();
    }
}
