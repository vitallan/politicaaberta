package com.allanvital.politicaaberta.repository;

import com.allanvital.politicaaberta.model.Deputy;
import com.allanvital.politicaaberta.model.Expense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long> {

    Expense findByDeputyAndValueAndYearAndMonthAndDescriptionAndDocumentNumber(Deputy deputy, BigDecimal value, int year, int month, String description, String documentNumber);

    List<Expense> findTop10ByMonthAndYearOrderByValueDesc(int month, int year);

    List<Expense> findByDeputyAndMonthAndYear(Deputy deputy, int month, int year);

}
