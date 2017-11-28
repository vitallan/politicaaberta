package com.allanvital.politicaaberta.batch.writer;

import com.allanvital.politicaaberta.batch.repository.dto.CommitteeDto;
import com.allanvital.politicaaberta.model.Expense;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@StepScope
@Component
public class CommitteeDtoWriter implements ItemWriter<CommitteeDto> {

    @Override
    public void write(List<? extends CommitteeDto> items) throws Exception {
        items.forEach(System.out::println);
    }

}
