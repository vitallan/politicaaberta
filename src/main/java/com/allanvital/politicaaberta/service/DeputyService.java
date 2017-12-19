package com.allanvital.politicaaberta.service;

import com.allanvital.politicaaberta.model.Deputy;
import com.allanvital.politicaaberta.repository.DeputyRepository;
import com.allanvital.politicaaberta.service.pojo.DeputyDetail;
import com.allanvital.politicaaberta.utils.NormalizerShortcuts;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeputyService {

    private DeputyRepository repository;
    private ExpenseByMonthAndYearService expenseByMonthAndYearService;
    private ExpenseService expenseService;

    public List<Deputy> searchDeputies(String query) { //TODO: when there is a ElasticSearch, this will be removed
        return repository.findTop5ByNormalizedNameContaining(NormalizerShortcuts.normalize(query));
    }

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
