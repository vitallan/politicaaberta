package com.allanvital.politicaaberta.model;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long despesaXmlEntryId;
//TODO: add other fields

    private String cpfCpnj;

    private BigDecimal value;

    private String receiver;

    private String description;

    private Date when;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deputy_id", nullable = false)
    private Deputy deputy;

    public String getCpfCpnj() {
        return cpfCpnj;
    }

    public void setCpfCpnj(String cpfCpnj) {
        this.cpfCpnj = cpfCpnj;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
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

    public Long getDespesaXmlEntryId() {
        return despesaXmlEntryId;
    }

    public void setDespesaXmlEntryId(Long despesaXmlEntryId) {
        this.despesaXmlEntryId = despesaXmlEntryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
