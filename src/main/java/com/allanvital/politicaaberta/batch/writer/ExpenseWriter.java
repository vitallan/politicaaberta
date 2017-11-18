package com.allanvital.politicaaberta.batch.writer;

import com.allanvital.politicaaberta.model.DespesaXmlEntry;
import com.allanvital.politicaaberta.model.Expense;
import com.allanvital.politicaaberta.repository.ExpenseRepository;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@JobScope
public class ExpenseWriter implements ItemWriter<DespesaXmlEntry> {

    private ExpenseRepository repository;

    public ExpenseWriter(ExpenseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void write(List<? extends DespesaXmlEntry> items) throws Exception {
        items.forEach(item -> {
            Expense persistedExpense = repository.findByDespesaXmlEntryId(item.getId());
            if (persistedExpense == null) {
                repository.save(item.buildExpense());
            }
        });
    }

}
