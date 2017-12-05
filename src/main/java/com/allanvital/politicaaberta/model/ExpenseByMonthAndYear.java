package com.allanvital.politicaaberta.model;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class ExpenseByMonthAndYear implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "deputy_id", nullable = false)
    private Deputy deputy;

    private BigDecimal value;
    private int month;
    private int year;

    public ExpenseByMonthAndYear() {}

    public ExpenseByMonthAndYear(Expense item) {
        this.deputy = item.getDeputy();
        this.month = item.getMonth();
        this.year = item.getYear();
        this.value = item.getValue();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Deputy getDeputy() {
        return deputy;
    }

    public void setDeputy(Deputy deputy) {
        this.deputy = deputy;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void addValue(Expense item) {
        this.value = this.value.add(item.getValue());
    }

    public String getDeputyName() {
        return this.getDeputy().getName();
    }
}
