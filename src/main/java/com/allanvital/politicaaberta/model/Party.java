package com.allanvital.politicaaberta.model;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Party {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, unique=true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "party")
    private Set<Deputy> deputies;

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

    public Set<Deputy> getDeputies() {
        return deputies;
    }

    public void setDeputies(Set<Deputy> deputies) {
        this.deputies = deputies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Party party = (Party) o;

        if (id != null ? !id.equals(party.id) : party.id != null) return false;
        return name != null ? name.equals(party.name) : party.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Party{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
