package com.allanvital.politicaaberta.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long despesaXmlEntryId;
//TODO: add other fields
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
}
