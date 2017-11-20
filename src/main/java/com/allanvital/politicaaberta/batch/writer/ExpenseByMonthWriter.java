package com.allanvital.politicaaberta.batch.writer;

import com.allanvital.politicaaberta.model.*;
import com.allanvital.politicaaberta.repository.DeputyRepository;
import com.allanvital.politicaaberta.repository.ExpenseByMonthRepository;
import com.allanvital.politicaaberta.repository.ExpenseSummarizingFeatureLogRepository;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@JobScope
public class ExpenseByMonthWriter implements ItemWriter<DespesaXmlEntry> {

    private SummarizingFeature writerFeature = SummarizingFeature.EXPENDITURE_BY_DEPUTY_BY_MONTH;

    private ExpenseSummarizingFeatureLogRepository summarizingRepository;
    private DeputyRepository deputyRepository;
    private ExpenseByMonthRepository repository;

    public ExpenseByMonthWriter(ExpenseSummarizingFeatureLogRepository summarizingRepository, DeputyRepository deputyRepository, ExpenseByMonthRepository repository) {
        this.summarizingRepository = summarizingRepository;
        this.deputyRepository = deputyRepository;
        this.repository = repository;
    }

    @Override
    public void write(List<? extends DespesaXmlEntry> items) throws Exception {
        items.forEach(item -> {
            if(summarizingRepository.findByExpenseXmlEntryIdAndFeature(item.getId(), this.writerFeature) != null) {
                return;
            }
            Deputy deputy = deputyRepository.findByIdeCadastro(item.getIdeCadastro());
            ExpenseByMonthAndYear expenseByMonthAndYear = repository.findByDeputyAndMonthAndYear(deputy, item.getNumMes(), item.getNumAno());
            if (expenseByMonthAndYear == null) {
                expenseByMonthAndYear = new ExpenseByMonthAndYear(item, deputy);
            } else {
                expenseByMonthAndYear.addValue(item);
            }
            summarizingRepository.save(new ExpenseSummarizingFeatureLog(item.getId(), this.writerFeature));
            repository.save(expenseByMonthAndYear);
        });
    }

}
