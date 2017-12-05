package com.allanvital.politicaaberta.model;

import javax.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String cpfCnpj;

    private BigDecimal value;

    private String receiver;

    private String description;

    private Date expenseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deputy_id", nullable = false)
    private Deputy deputy;

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public BigDecimal getValue() {
        return value.setScale(2, RoundingMode.CEILING);
    }

    public void setValue(BigDecimal value) {
        this.value = value.setScale(2, RoundingMode.CEILING);
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Deputy getDeputy() {
        return deputy;
    }

    public void setDeputy(Deputy deputy) {
        this.deputy = deputy;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return this.getLocalDate().getYear();
    }

    public int getMonth() {
        return this.getLocalDate().getMonthValue();
    }

    private LocalDate getLocalDate() {
        return this.getExpenseDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public String getDeputyName() {
        return this.getDeputy().getName();
    }

}
