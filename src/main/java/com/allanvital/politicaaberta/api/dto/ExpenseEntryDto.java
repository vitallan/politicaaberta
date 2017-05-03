package com.allanvital.politicaaberta.api.dto;

import java.math.BigDecimal;
import java.util.Calendar;

import com.allanvital.politicaaberta.api.model.Deputy;
import com.allanvital.politicaaberta.api.model.Expense;

public class ExpenseEntryDto {
	
	private int deputySiteId;
	private String description;
	private BigDecimal value;
	private String urlDetails;
	private Calendar expenseMonthAndYear;

	public Expense buildExpense() {
		Deputy deputy = new Deputy();
		deputy.setSiteId(this.deputySiteId);
		Expense expense = new Expense();
		expense.setDeputy(deputy);
		expense.setDescription(this.description);
		expense.setMonthAndYear(this.expenseMonthAndYear);
		expense.setValue(this.value);
		return expense;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getUrlDetails() {
		return urlDetails;
	}

	public void setUrlDetails(String urlDetails) {
		this.urlDetails = urlDetails;
	}
	
	public Calendar getExpenseMonthAndYear() {
		return expenseMonthAndYear;
	}

	public void setExpenseMonthAndYear(Calendar expenseMonthAndYear) {
		this.expenseMonthAndYear = expenseMonthAndYear;
	}

	public int getDeputySiteId() {
		return deputySiteId;
	}

	public void setDeputySiteId(int deputySiteId) {
		this.deputySiteId = deputySiteId;
	}
}
