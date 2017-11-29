package com.allanvital.politicaaberta.model;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class PropositionType {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Long officialId;
    private String initials;
    private String name;
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
    private List<Proposition> propositions;

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

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Proposition> getPropositions() {
        return propositions;
    }

    public void setPropositions(List<Proposition> propositions) {
        this.propositions = propositions;
    }
}
