package com.allanvital.politicaaberta.batch.writer;

import com.allanvital.politicaaberta.model.DespesaXmlEntry;
import com.allanvital.politicaaberta.model.Expense;
import com.allanvital.politicaaberta.repository.DeputyRepository;
import com.allanvital.politicaaberta.repository.ExpenseRepository;
import org.apache.log4j.Logger;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@JobScope
public class ExpenseWriter implements ItemWriter<DespesaXmlEntry> {

    private final static Logger log = Logger.getLogger(ExpenseWriter.class.getName());

    private ExpenseRepository repository;
    private DeputyRepository deputyRepository;

    public ExpenseWriter(ExpenseRepository repository, DeputyRepository deputyRepository) {
        this.repository = repository;
        this.deputyRepository = deputyRepository;
    }

    @Override
    public void write(List<? extends DespesaXmlEntry> items) throws Exception {
        items.forEach(item -> {
            log.info("Checando existencia da despesaXmlEntry " + item.getId() + " na tabela de expenses");
            Expense persistedExpense = repository.findByExpenseXmlEntryId(item.getId());
            if (persistedExpense == null) {
                Expense expense = item.buildExpense();
                log.info("DespesaXmlEntry " + item.getId() + " nao existe. Persistindo para ideCadastro=" + item.getIdeCadastro());
                expense.setDeputy(deputyRepository.findByIdeCadastro(item.getIdeCadastro()));
                repository.save(expense);
            }
        });
    }

}
