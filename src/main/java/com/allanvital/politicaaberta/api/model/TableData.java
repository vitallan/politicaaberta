package com.allanvital.politicaaberta.api.model;

import java.math.BigDecimal;

public class TableData {

    private Long id;
    private String description;
    private BigDecimal quantity;

    public TableData(Long id, String description, BigDecimal quantity) {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
