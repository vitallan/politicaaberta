package com.allanvital.politicaaberta.batch.writer;

import com.allanvital.politicaaberta.model.*;
import com.allanvital.politicaaberta.repository.DeputyRepository;
import com.allanvital.politicaaberta.repository.ExpenseByMonthRepository;
import com.allanvital.politicaaberta.repository.ExpenseSummarizingFeatureLogRepository;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
@JobScope
public class ExpenseByMonthWriter implements ItemWriter<Expense> {

    private SummarizingFeature monthlyExpenditureFeature = SummarizingFeature.EXPENDITURE_BY_DEPUTY_BY_MONTH;

    private ExpenseSummarizingFeatureLogRepository summarizingRepository;
    private DeputyRepository deputyRepository;
    private ExpenseByMonthRepository repository;

    public ExpenseByMonthWriter(ExpenseSummarizingFeatureLogRepository summarizingRepository, DeputyRepository deputyRepository, ExpenseByMonthRepository repository) {
        this.summarizingRepository = summarizingRepository;
        this.deputyRepository = deputyRepository;
        this.repository = repository;
    }

    @Override
    public void write(List<? extends Expense> items) throws Exception {
        items.forEach(item -> {
            if(summarizingRepository.findByExpenseAndFeature(item, monthlyExpenditureFeature.getId()) != null) {
                return;
            }
            ExpenseByMonthAndYear monthlyExpense = repository.findByDeputyAndMonthAndYear(item.getDeputy(), item.getMonth(), item.getYear());
            if(monthlyExpense == null) {
                monthlyExpense = new ExpenseByMonthAndYear(item);
            } else {
                monthlyExpense.addValue(item);
            }
            repository.save(monthlyExpense);
        });
    }

}
