package com.allanvital.politicaaberta.service.pojo;

import com.allanvital.politicaaberta.model.Deputy;
import com.allanvital.politicaaberta.model.Expense;

import java.math.BigDecimal;
import java.util.List;

public class DeputyDetail {

    private Deputy deputy;
    private ThreeMonthExpenses expenses; //TODO: check if parametrized (getFromMonth(x)) is better than closed object (lastMonth, twoMonthsAgo, etc)
    private ThreeMonthsSummarizedExpenses summarizedExpenses;

    public Deputy getDeputy() {
        return deputy;
    }

    public void setDeputy(Deputy deputy) {
        this.deputy = deputy;
    }

    public ThreeMonthExpenses getExpenses() {
        return expenses;
    }

    public void setExpenses(ThreeMonthExpenses expenses) {
        this.expenses = expenses;
    }

    public ThreeMonthsSummarizedExpenses getSummarizedExpenses() {
        return summarizedExpenses;
    }

    public void setSummarizedExpenses(ThreeMonthsSummarizedExpenses summarizedExpenses) {
        this.summarizedExpenses = summarizedExpenses;
    }

    public String getDeputyName() {
        return this.getDeputy().getName();
    }

    public String getPartyName() {
        return this.getDeputy().getParty().getName();
    }

    public BigDecimal getLastMonthSummarized() {
        return summarizedExpenses.getLastMonth().getValue();
    }

    public BigDecimal getTwoMonthsAgoSummarized() {
        if (summarizedExpenses.getTwoMonthsAgo() == null) {
            return new BigDecimal(0);
        }
        return summarizedExpenses.getTwoMonthsAgo().getValue();
    }

    public BigDecimal getThreeMonthsAgoSummarized() {
        if (summarizedExpenses.getThreeMonthsAgo() == null) {
            return new BigDecimal(0);
        }
        return summarizedExpenses.getThreeMonthsAgo().getValue();
    }

    public List<Expense> getLastMonth() {
        return expenses.getLastMonth();
    }

    public List<Expense>  getTwoMonthsAgo() {
        return expenses.getTwoMonthsAgo();
    }

    public List<Expense> getThreeMonthsAgo() {
        return expenses.getThreeMonthsAgo();
    }

}
