package com.allanvital.politicaaberta.repository;

import com.allanvital.politicaaberta.model.Deputy;
import com.allanvital.politicaaberta.model.Expense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long> {

    Expense findByDeputyAndValueAndExpenseDateAndDescription(Deputy deputy, BigDecimal value, Date expenseDate, String description);

}
