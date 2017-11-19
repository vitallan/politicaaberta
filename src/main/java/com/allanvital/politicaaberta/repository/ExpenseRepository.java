package com.allanvital.politicaaberta.repository;

import com.allanvital.politicaaberta.model.Expense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long> {

    Expense findByExpenseXmlEntryId(Long expenseXmlEntryId);

}
