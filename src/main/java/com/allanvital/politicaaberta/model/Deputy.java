package com.allanvital.politicaaberta.model;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Deputy {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private Long deputyXmlEntryId;

    @Column(nullable = false)
    private String uf;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id", nullable = false)
    private Party party;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "deputy")
    private List<Expense> expenses;

    public Long getDeputyXmlEntryId() {
        return deputyXmlEntryId;
    }

    public void setDeputyXmlEntryId(Long deputyXmlEntryId) {
        this.deputyXmlEntryId = deputyXmlEntryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Deputy deputy = (Deputy) o;

        if (id != null ? !id.equals(deputy.id) : deputy.id != null) return false;
        if (name != null ? !name.equals(deputy.name) : deputy.name != null) return false;
        if (deputyXmlEntryId != null ? !deputyXmlEntryId.equals(deputy.deputyXmlEntryId) : deputy.deputyXmlEntryId != null) return false;
        if (uf != null ? !uf.equals(deputy.uf) : deputy.uf != null) return false;
        return party != null ? party.equals(deputy.party) : deputy.party == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (deputyXmlEntryId != null ? deputyXmlEntryId.hashCode() : 0);
        result = 31 * result + (uf != null ? uf.hashCode() : 0);
        result = 31 * result + (party != null ? party.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Deputy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", xmlId=" + deputyXmlEntryId +
                ", uf='" + uf + '\'' +
                ", party=" + party +
                '}';
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }
}
