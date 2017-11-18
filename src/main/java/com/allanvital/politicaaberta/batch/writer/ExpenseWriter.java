package com.allanvital.politicaaberta.batch.writer;

import com.allanvital.politicaaberta.model.DeputadoXmlEntry;
import com.allanvital.politicaaberta.model.DespesaXmlEntry;
import com.allanvital.politicaaberta.model.Expense;
import com.allanvital.politicaaberta.repository.DeputadoXmlEntryRepository;
import com.allanvital.politicaaberta.repository.DeputyRepository;
import com.allanvital.politicaaberta.repository.ExpenseRepository;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@JobScope
public class ExpenseWriter implements ItemWriter<DespesaXmlEntry> {

    private ExpenseRepository repository;
    private DeputyRepository deputyRepository;
    private DeputadoXmlEntryRepository deputadoXmlEntryRepository;

    public ExpenseWriter(ExpenseRepository repository, DeputyRepository deputyRepository, DeputadoXmlEntryRepository deputadoXmlEntryRepository) {
        this.repository = repository;
        this.deputyRepository = deputyRepository;
        this.deputadoXmlEntryRepository = deputadoXmlEntryRepository;
    }

    @Override
    public void write(List<? extends DespesaXmlEntry> items) throws Exception {
        items.forEach(item -> {
            Expense persistedExpense = repository.findByDespesaXmlEntryId(item.getId());
            if (persistedExpense == null) {
                Expense expense = item.buildExpense();
                DeputadoXmlEntry deputadoXmlEntry = deputadoXmlEntryRepository.findByIdeCadastro(item.getIdeCadastro());
                expense.setDeputy(deputyRepository.findByDeputyXmlEntryId(deputadoXmlEntry.getId()));
                repository.save(expense);
            }
        });
    }

}
