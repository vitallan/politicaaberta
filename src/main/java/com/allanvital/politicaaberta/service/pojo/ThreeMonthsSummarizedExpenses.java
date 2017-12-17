package com.allanvital.politicaaberta.service.pojo;

import com.allanvital.politicaaberta.model.ExpenseByMonthAndYear;

public class ThreeMonthsSummarizedExpenses {

    private ExpenseByMonthAndYear lastMonthSummarized;
    private ExpenseByMonthAndYear twoMonthsAgoSummarized;
    private ExpenseByMonthAndYear threeMonthsAgoSummarized;

    public ExpenseByMonthAndYear getLastMonth() {
        return lastMonthSummarized;
    }

    public void setLastMonthSummarized(ExpenseByMonthAndYear lastMonthSummarized) {
        this.lastMonthSummarized = lastMonthSummarized;
    }

    public ExpenseByMonthAndYear getTwoMonthsAgo() {
        return twoMonthsAgoSummarized;
    }

    public void setTwoMonthsAgoSummarized(ExpenseByMonthAndYear twoMonthsAgoSummarized) {
        this.twoMonthsAgoSummarized = twoMonthsAgoSummarized;
    }

    public ExpenseByMonthAndYear getThreeMonthsAgo() {
        return threeMonthsAgoSummarized;
    }

    public void setThreeMonthsAgoSummarized(ExpenseByMonthAndYear threeMonthsAgoSummarized) {
        this.threeMonthsAgoSummarized = threeMonthsAgoSummarized;
    }
}
