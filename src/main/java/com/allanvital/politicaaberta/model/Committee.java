package com.allanvital.politicaaberta.model;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Committee {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Long officialId;
    private Date startDate;
    private Date endDate;

    @Column(length = 1023)
    private String name;
    private String role;
    private String initials;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deputy_id", nullable = false)
    private Deputy deputy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public Deputy getDeputy() {
        return deputy;
    }

    public void setDeputy(Deputy deputy) {
        this.deputy = deputy;
    }

    public Long getOfficialId() {
        return officialId;
    }

    public void setOfficialId(Long officialId) {
        this.officialId = officialId;
    }
}
