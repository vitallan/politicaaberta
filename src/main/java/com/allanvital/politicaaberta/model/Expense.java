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

    private int year;
    private int month;
    private String documentNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deputy_id", nullable = false)
    private Deputy deputy;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

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

    public String getDeputyName() {
        return this.getDeputy().getName();
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
}
