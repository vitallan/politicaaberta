package com.allanvital.politicaaberta.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Proposition {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Long officialId;
    private String uri;
    private Integer number;
    private Integer year;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proposition_type_id", nullable = false)
    private PropositionType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deputy_id", nullable = false)
    private Deputy deputy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOfficialId() {
        return officialId;
    }

    public void setOfficialId(Long officialId) {
        this.officialId = officialId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PropositionType getType() {
        return type;
    }

    public void setType(PropositionType type) {
        this.type = type;
    }

    public Deputy getDeputy() {
        return deputy;
    }

    public void setDeputy(Deputy deputy) {
        this.deputy = deputy;
    }
}
