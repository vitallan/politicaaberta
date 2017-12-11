package com.allanvital.politicaaberta.service;

import com.allanvital.politicaaberta.model.Deputy;
import com.allanvital.politicaaberta.repository.ExpenseByMonthRepository;
import com.allanvital.politicaaberta.service.pojo.ThreeMonthsSummarizedExpenses;
import com.allanvital.politicaaberta.utils.DateShortcuts;
import org.springframework.stereotype.Service;

@Service
public class ExpenseByMonthAndYearService {

    private ExpenseByMonthRepository repository;

    public ExpenseByMonthAndYearService(ExpenseByMonthRepository repository) {
        this.repository = repository;
    }

    public ThreeMonthsSummarizedExpenses getLastThreeMonthsSummarizedExpenses(Deputy deputy) {
        ThreeMonthsSummarizedExpenses expenses = new ThreeMonthsSummarizedExpenses();

        expenses.setThreeMonthsAgoSummarized(repository.findByDeputyAndMonthAndYear(deputy, DateShortcuts.threeMonthsAgo(), DateShortcuts.yearFromThreeMonthsAgo()));
        expenses.setTwoMonthsAgoSummarized(repository.findByDeputyAndMonthAndYear(deputy, DateShortcuts.twoMonthsAgo(), DateShortcuts.yearFromTwoMonthsAgo()));
        expenses.setLastMonthSummarized(repository.findByDeputyAndMonthAndYear(deputy, DateShortcuts.lastMonth(), DateShortcuts.yearFromLastMonth()));

        return expenses;
    }

}
