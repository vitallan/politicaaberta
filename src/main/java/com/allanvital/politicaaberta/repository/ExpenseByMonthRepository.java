package com.allanvital.politicaaberta.repository;

import com.allanvital.politicaaberta.model.Deputy;
import com.allanvital.politicaaberta.model.ExpenseByMonthAndYear;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseByMonthRepository extends CrudRepository<ExpenseByMonthAndYear, Long> {

    ExpenseByMonthAndYear findByDeputyAndMonthAndYear(Deputy deputy, int month, int year);

    List<ExpenseByMonthAndYear> findTop10ByMonthAndYearOrderByValueDesc(int month, int year);

}
