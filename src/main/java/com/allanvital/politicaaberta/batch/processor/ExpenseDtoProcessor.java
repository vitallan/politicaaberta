package com.allanvital.politicaaberta.batch.processor;

import com.allanvital.politicaaberta.batch.repository.dto.ExpenseDto;
import com.allanvital.politicaaberta.model.Deputy;
import com.allanvital.politicaaberta.model.Expense;
import com.allanvital.politicaaberta.repository.DeputyRepository;
import com.allanvital.politicaaberta.repository.ExpenseRepository;
import org.apache.log4j.Logger;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class ExpenseDtoProcessor implements ItemProcessor<ExpenseDto, Expense> {

    private final static Logger log = Logger.getLogger(ExpenseDtoProcessor.class.getName());

    private final String deputyOfficialId;
    private final DeputyRepository deputyRepository;
    private final ExpenseRepository expenseRepository;

    public ExpenseDtoProcessor(@Value("#{jobParameters['deputyOfficialId']}") String deputyOfficialId, DeputyRepository deputyRepository, ExpenseRepository expenseRepository) {
        this.deputyOfficialId = deputyOfficialId;
        this.deputyRepository = deputyRepository;
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Expense process(ExpenseDto item) throws Exception {
        log.info("Processando " + item);
        Deputy deputy = deputyRepository.findByOfficialId(new Long(this.deputyOfficialId));
        Expense expense = item.buildExpense();
        expense = expenseRepository.findByDeputyAndValueAndExpenseDateAndDescription(deputy, expense.getValue(), expense.getExpenseDate(), expense.getDescription());
        if (expense == null) {
            log.info("Expense ainda nao persistido. Salvando... " + item);
            expense = item.buildExpense();
            expense.setDeputy(deputy);
            expenseRepository.save(expense);
        }
        return expense;
    }

}
