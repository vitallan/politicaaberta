package com.allanvital.politicaaberta.repository;

import com.allanvital.politicaaberta.model.ExpenseSummarizingFeatureLog;
import com.allanvital.politicaaberta.model.SummarizingFeature;
import org.springframework.data.repository.CrudRepository;

public interface ExpenseSummarizingFeatureLogRepository extends CrudRepository<ExpenseSummarizingFeatureLog, Long> {

    ExpenseSummarizingFeatureLog findByExpenseXmlEntryIdAndFeature(Long expenseXmlEntryId, SummarizingFeature feature);

}
