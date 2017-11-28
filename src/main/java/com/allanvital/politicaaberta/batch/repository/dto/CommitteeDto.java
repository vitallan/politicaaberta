package com.allanvital.politicaaberta.batch.repository.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class CommitteeDto {

    @JsonProperty("idOrgao")
    private Long id;

    @JsonProperty("dataFim")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;

    @JsonProperty("dataInicio")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;

    @JsonProperty("nomeOrgao")
    private String name;

    @JsonProperty("nomePapel")
    private String role;

    @JsonProperty("siglaOrgao")
    private String initials;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    @Override
    public String toString() {
        return "CommitteeDto{" +
                "id=" + id +
                ", endDate=" + endDate +
                ", startDate=" + startDate +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", initials='" + initials + '\'' +
                '}';
    }
}
