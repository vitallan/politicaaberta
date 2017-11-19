package com.allanvital.politicaaberta.batch.writer;

import com.allanvital.politicaaberta.model.*;
import com.allanvital.politicaaberta.repository.ExpenseByMonthRepository;
import com.allanvital.politicaaberta.repository.ExpenseSummarizingFeatureLogRepository;
import com.allanvital.politicaaberta.service.DeputyService;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@JobScope
public class ExpenseByMonthWriter implements ItemWriter<DespesaXmlEntry> {

    private SummarizingFeature writerFeature = SummarizingFeature.EXPENDITURE_BY_DEPUTY_BY_MONTH;

    private ExpenseSummarizingFeatureLogRepository summarizingRepository;
    private DeputyService deputyService;
    private ExpenseByMonthRepository repository;

    public ExpenseByMonthWriter(ExpenseSummarizingFeatureLogRepository summarizingRepository, DeputyService deputyService, ExpenseByMonthRepository repository) {
        this.summarizingRepository = summarizingRepository;
        this.deputyService = deputyService;
        this.repository = repository;
    }

    @Override
    public void write(List<? extends DespesaXmlEntry> items) throws Exception {
        items.forEach(item -> {
            if(summarizingRepository.findByExpenseXmlEntryIdAndFeature(item.getId(), this.writerFeature) != null) {
                return;
            }
            summarizingRepository.save(new ExpenseSummarizingFeatureLog(item.getId(), this.writerFeature));
            Deputy deputy = deputyService.getDeputyByXmlEntryIdeCadastro(item.getIdeCadastro());
            ExpenseByMonthAndYear monthAndYear = repository.findByDeputyAndMonthAndYear(deputy, item.getNumMes(), item.getNumAno());
            if (monthAndYear == null) {
                monthAndYear = new ExpenseByMonthAndYear(item, deputy);
            } else {
                monthAndYear.addValue(item);
            }
            repository.save(monthAndYear);
        });
    }

}
