package com.allanvital.politicaaberta.batch.writer;

import com.allanvital.politicaaberta.model.DeputadoXmlEntry;
import com.allanvital.politicaaberta.model.Deputy;
import com.allanvital.politicaaberta.model.DespesaXmlEntry;
import com.allanvital.politicaaberta.model.Expense;
import com.allanvital.politicaaberta.repository.DeputadoXmlEntryRepository;
import com.allanvital.politicaaberta.repository.DeputyRepository;
import com.allanvital.politicaaberta.repository.ExpenseRepository;
import com.allanvital.politicaaberta.service.DeputyService;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@JobScope
public class ExpenseWriter implements ItemWriter<DespesaXmlEntry> {

    private ExpenseRepository repository;
    private DeputyService deputyService;

    public ExpenseWriter(ExpenseRepository repository, DeputyService deputyService) {
        this.repository = repository;
        this.deputyService = deputyService;
    }

    @Override
    public void write(List<? extends DespesaXmlEntry> items) throws Exception {
        items.forEach(item -> {
            Expense persistedExpense = repository.findByExpenseXmlEntryId(item.getId());
            if (persistedExpense == null) {
                Expense expense = item.buildExpense();
                expense.setDeputy(deputyService.getDeputyByXmlEntryIdeCadastro(item.getIdeCadastro()));
                repository.save(expense);
            }
        });
    }

}
