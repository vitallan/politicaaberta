package com.allanvital.politicaaberta.api.repository;

import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.allanvital.politicaaberta.api.model.Expense;

public interface ExpenseRepository extends CrudRepository<Expense, Long>{

    @Query("select e from Expense e where e.deputyId = :deputyId and e.monthAndYear = :monthAndYear and e.value = :value")
	Expense findByDeputyIdAndMonthAndYearAndValue(@Param("deputyId") Long deputyId, 
	    @Param("monthAndYear") Calendar monthAndYear, @Param("value") BigDecimal value);

}
