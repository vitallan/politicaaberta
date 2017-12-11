package com.allanvital.politicaaberta.service.pojo;

import com.allanvital.politicaaberta.model.Expense;

import java.util.List;

public class ThreeMonthExpenses {

    private List<Expense> expensesFromLastMonth;
    private List<Expense> expensesFromTwoMonthsAgo;
    private List<Expense> expensesFromThreeMonthsAgo;

    public List<Expense> getLastMonth() {
        return expensesFromLastMonth;
    }

    public void setExpensesFromLastMonth(List<Expense> expensesFromLastMonth) {
        this.expensesFromLastMonth = expensesFromLastMonth;
    }

    public List<Expense> getTwoMonthsAgo() {
        return expensesFromTwoMonthsAgo;
    }

    public void setExpensesFromTwoMonthsAgo(List<Expense> expensesFromTwoMonthsAgo) {
        this.expensesFromTwoMonthsAgo = expensesFromTwoMonthsAgo;
    }

    public List<Expense> getThreeMonthsAgo() {
        return expensesFromThreeMonthsAgo;
    }

    public void setExpensesFromThreeMonthsAgo(List<Expense> expensesFromThreeMonthsAgo) {
        this.expensesFromThreeMonthsAgo = expensesFromThreeMonthsAgo;
    }
}
