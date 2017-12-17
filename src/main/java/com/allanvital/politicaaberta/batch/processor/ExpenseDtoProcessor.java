package com.allanvital.politicaaberta.batch.processor;

import com.allanvital.politicaaberta.batch.repository.dto.ExpenseDto;
import com.allanvital.politicaaberta.model.Deputy;
import com.allanvital.politicaaberta.model.Expense;
import com.allanvital.politicaaberta.model.ServiceProvider;
import com.allanvital.politicaaberta.repository.DeputyRepository;
import com.allanvital.politicaaberta.repository.ExpenseRepository;
import com.allanvital.politicaaberta.repository.ServiceProviderRepository;
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
    private ServiceProviderRepository serviceProviderRepository;

    public ExpenseDtoProcessor(@Value("#{jobParameters['deputyOfficialId']}") String deputyOfficialId, DeputyRepository deputyRepository, ExpenseRepository expenseRepository, ServiceProviderRepository serviceProviderRepository) {
        this.deputyOfficialId = deputyOfficialId;
        this.deputyRepository = deputyRepository;
        this.expenseRepository = expenseRepository;
        this.serviceProviderRepository = serviceProviderRepository;
    }

    @Override
    public Expense process(ExpenseDto item) throws Exception {
        log.debug("Processando " + item);
        Deputy deputy = deputyRepository.findByOfficialId(new Long(this.deputyOfficialId));
        ServiceProvider provider = item.buildServiceProvider();
        ServiceProvider persistedProvider = serviceProviderRepository.findByCpfCnpjAndName(provider.getCpfCnpj(), provider.getName());
        if (persistedProvider == null) {//TODO: separate this
            persistedProvider = serviceProviderRepository.save(provider);
        }
        Expense expense = item.buildExpense();
        expense = expenseRepository.findByDeputyAndValueAndYearAndMonthAndDescriptionAndDocumentNumber(deputy, expense.getValue(), expense.getYear(), expense.getMonth(), expense.getDescription(), expense.getDocumentNumber());
        if (expense == null) {
            log.debug("Expense ainda nao persistido. Salvando... " + item);
            expense = item.buildExpense();
            expense.setServiceProvider(persistedProvider);
            expense.setDeputy(deputy);
            expenseRepository.save(expense);
        }
        return expense;
    }

}
