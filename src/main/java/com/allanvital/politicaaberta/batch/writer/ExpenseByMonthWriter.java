package com.allanvital.politicaaberta.batch.writer;

import com.allanvital.politicaaberta.batch.repository.dto.ExpenseDto;
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
public class ExpenseByMonthWriter implements ItemWriter<ExpenseDto> {

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
    public void write(List<? extends ExpenseDto> items) throws Exception {
        items.forEach(item -> {
            /*if(summarizingRepository.findByExpenseXmlEntryIdAndFeature(item.getOfficialId(), this.writerFeature) != null) {
                return;
            }
            Deputy deputy = deputyRepository.findByOfficialId(null);
            ExpenseByMonthAndYear expenseByMonthAndYear = repository.findByDeputyAndMonthAndYear(deputy, item.getNumMes(), item.getNumAno());
            if (expenseByMonthAndYear == null) {
                expenseByMonthAndYear = new ExpenseByMonthAndYear(item, deputy);
            } else {
                expenseByMonthAndYear.addValue(item);
            }
            summarizingRepository.save(new ExpenseSummarizingFeatureLog(item.getOfficialId(), this.writerFeature));
            repository.save(expenseByMonthAndYear); */
        });
    }

}
