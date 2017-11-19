package com.allanvital.politicaaberta.repository;

import com.allanvital.politicaaberta.model.Deputy;
import com.allanvital.politicaaberta.model.ExpenseByMonthAndYear;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseByMonthRepository extends CrudRepository<ExpenseByMonthAndYear, Long> {

    ExpenseByMonthAndYear findByDeputyAndMonthAndYear(Deputy deputy, int month, int year);

}
