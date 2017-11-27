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

    protected static final String EXPENSE_COLUMN_NAME = "expense_id";
    protected static final String FEATURE_COLUMN_NAME = "feature";

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = EXPENSE_COLUMN_NAME, nullable = false)
    private Expense expense;

    @Column(name=FEATURE_COLUMN_NAME)
    private int feature;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SummarizingFeature getFeature() {
        return SummarizingFeature.parse(this.feature);
    }

    public void setFeature(SummarizingFeature feature) {
        this.feature = feature.getId();
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }
}
