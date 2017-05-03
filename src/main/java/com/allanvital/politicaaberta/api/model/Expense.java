package com.allanvital.politicaaberta.api.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Expense implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private BigDecimal value;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deputy_id", nullable = false)
    private Deputy deputy;

    @Column(name = "deputy_id", insertable = false, updatable = false)
    private Long deputyId;

    private Calendar monthAndYear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Deputy getDeputy() {
        return deputy;
    }

    public void setDeputy(Deputy deputy) {
        this.deputy = deputy;
    }

    public Calendar getMonthAndYear() {
        return monthAndYear;
    }

    public void setMonthAndYear(Calendar monthAndYear) {
        this.monthAndYear = monthAndYear;
    }

    public Long getDeputyId() {
        return deputyId;
    }

    public void setDeputyId(Long deputyId) {
        this.deputyId = deputyId;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
