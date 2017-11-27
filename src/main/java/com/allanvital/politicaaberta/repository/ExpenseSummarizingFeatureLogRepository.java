package com.allanvital.politicaaberta.repository;

import com.allanvital.politicaaberta.model.Expense;
import com.allanvital.politicaaberta.model.ExpenseSummarizingFeatureLog;
import com.allanvital.politicaaberta.model.SummarizingFeature;
import org.springframework.data.repository.CrudRepository;

public interface ExpenseSummarizingFeatureLogRepository extends CrudRepository<ExpenseSummarizingFeatureLog, Long> {

    ExpenseSummarizingFeatureLog findByExpenseAndFeature(Expense expense, int feature);

}
