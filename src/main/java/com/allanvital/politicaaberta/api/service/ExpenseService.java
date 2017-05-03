package com.allanvital.politicaaberta.api.service;

import com.allanvital.politicaaberta.api.model.Deputy;
import com.allanvital.politicaaberta.api.model.Expense;
import com.allanvital.politicaaberta.api.repository.ExpenseRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

	private static final Logger log = Logger.getLogger(ExpenseService.class);

	private ExpenseRepository repository;
	private DeputyService deputyService;

	@Autowired
	public ExpenseService(ExpenseRepository repository, DeputyService deputyService) {
		this.repository = repository;
		this.deputyService = deputyService;
	}

	public void idempotentSaveExpense(Expense expense) {
		if (expense.getDescription() == null) {
			return;
		}
		log.info("Salvando expense do deputySiteId " + expense.getDeputy().getSiteId() + " com valor " + expense.getValue());
		Deputy deputy = deputyService.findBySiteId(expense.getDeputy().getSiteId());
		if (deputy != null) {
		    Expense persistedExpense = repository.findByDeputyIdAndMonthAndYearAndValue(deputy.getId(), expense.getMonthAndYear(), expense.getValue());
			if (persistedExpense == null) {
			    expense.setDeputy(deputy);
	            repository.save(expense);
			}
		}
	}

}
