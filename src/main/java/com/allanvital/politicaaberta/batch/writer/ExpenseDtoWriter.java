package com.allanvital.politicaaberta.batch.writer;

import com.allanvital.politicaaberta.batch.repository.dto.ExpenseDto;
import com.allanvital.politicaaberta.model.Deputy;
import com.allanvital.politicaaberta.model.Expense;
import com.allanvital.politicaaberta.repository.DeputyRepository;
import com.allanvital.politicaaberta.repository.ExpenseRepository;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@StepScope
@Component
public class ExpenseDtoWriter implements ItemWriter<Expense> {

    @Override
    public void write(List<? extends Expense> items) throws Exception {
        items.forEach(System.out::println);
    }

}
