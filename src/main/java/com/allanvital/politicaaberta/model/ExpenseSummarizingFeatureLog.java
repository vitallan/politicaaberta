package com.allanvital.politicaaberta.model;

import javax.persistence.*;

import static com.allanvital.politicaaberta.model.ExpenseSummarizingFeatureLog.EXPENSE_COLUMN_NAME;
import static com.allanvital.politicaaberta.model.ExpenseSummarizingFeatureLog.FEATURE_COLUMN_NAME;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {EXPENSE_COLUMN_NAME, FEATURE_COLUMN_NAME})
})
public class ExpenseSummarizingFeatureLog {

    protected static final String EXPENSE_COLUMN_NAME = "expenseXmlEntryId";
    protected static final String FEATURE_COLUMN_NAME = "feature";

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name=EXPENSE_COLUMN_NAME)
    private Long expenseXmlEntryId;

    @Column(name=FEATURE_COLUMN_NAME)
    private int feature;

    public ExpenseSummarizingFeatureLog () {

    }

    public ExpenseSummarizingFeatureLog(Long expenseXmlEntryId, SummarizingFeature feature) {
        this.expenseXmlEntryId = expenseXmlEntryId;
        this.feature = feature.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExpenseXmlEntryId() {
        return expenseXmlEntryId;
    }

    public void setExpenseXmlEntryId(Long expenseXmlEntryId) {
        this.expenseXmlEntryId = expenseXmlEntryId;
    }

    public SummarizingFeature getFeature() {
        return SummarizingFeature.parse(this.feature);
    }

    public void setFeature(SummarizingFeature feature) {
        this.feature = feature.getId();
    }
}
