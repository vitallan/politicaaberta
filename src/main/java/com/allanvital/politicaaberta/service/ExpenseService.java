package com.allanvital.politicaaberta.service;

import com.allanvital.politicaaberta.model.Deputy;
import com.allanvital.politicaaberta.repository.ExpenseRepository;
import com.allanvital.politicaaberta.service.pojo.ThreeMonthExpenses;
import com.allanvital.politicaaberta.utils.DateShortcuts;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

    private ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    public ThreeMonthExpenses getLastThreeMonthsExpenses(Deputy deputy) {
        ThreeMonthExpenses expenses = new ThreeMonthExpenses();

        expenses.setExpensesFromLastMonth(repository.findByDeputyAndMonthAndYearOrderByValueDesc(deputy, DateShortcuts.lastMonth(), DateShortcuts.yearFromLastMonth()));
        expenses.setExpensesFromTwoMonthsAgo(repository.findByDeputyAndMonthAndYearOrderByValueDesc(deputy, DateShortcuts.twoMonthsAgo(), DateShortcuts.yearFromTwoMonthsAgo()));
        expenses.setExpensesFromThreeMonthsAgo(repository.findByDeputyAndMonthAndYearOrderByValueDesc(deputy, DateShortcuts.threeMonthsAgo(), DateShortcuts.yearFromThreeMonthsAgo()));

        return expenses;
    }

}
