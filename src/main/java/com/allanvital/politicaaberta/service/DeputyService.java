package com.allanvital.politicaaberta.service;

import com.allanvital.politicaaberta.model.Deputy;
import com.allanvital.politicaaberta.repository.DeputyRepository;
import com.allanvital.politicaaberta.service.pojo.DeputyDetail;
import org.springframework.stereotype.Service;

@Service
public class DeputyService {

    private DeputyRepository repository;
    private ExpenseByMonthAndYearService expenseByMonthAndYearService;
    private ExpenseService expenseService;

    public DeputyService(DeputyRepository repository, ExpenseByMonthAndYearService expenseByMonthAndYearService, ExpenseService expenseService) {
        this.repository = repository;
        this.expenseByMonthAndYearService = expenseByMonthAndYearService;
        this.expenseService = expenseService;
    }

    public DeputyDetail getDeputyDetail(String deputyNormalizedName) {
        DeputyDetail detail = new DeputyDetail();

        Deputy deputy = repository.findByNormalizedName(deputyNormalizedName);

        detail.setDeputy(deputy);
        detail.setSummarizedExpenses(expenseByMonthAndYearService.getLastThreeMonthsSummarizedExpenses(deputy));
        detail.setExpenses(expenseService.getLastThreeMonthsExpenses(deputy));

        return detail;
    }

}
