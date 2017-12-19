package com.allanvital.politicaaberta.batch.writer;

import com.allanvital.politicaaberta.model.Expense;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@StepScope
@Component
public class ExpenseWriter implements ItemWriter<Expense> {

    @Override
    public void write(List<? extends Expense> items) throws Exception {

    }

}
