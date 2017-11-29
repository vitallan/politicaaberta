package com.allanvital.politicaaberta.batch.writer;

import com.allanvital.politicaaberta.batch.repository.dto.PropositionTypeDto;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@StepScope
@Component
public class PropositionTypeDtoWriter implements ItemWriter<PropositionTypeDto> {

    @Override
    public void write(List<? extends PropositionTypeDto> items) throws Exception {
        items.forEach(System.out::println);
    }

}
