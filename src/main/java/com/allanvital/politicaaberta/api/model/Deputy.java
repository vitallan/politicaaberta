package com.allanvital.politicaaberta.api.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class Deputy implements Serializable{

	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private int siteId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String uf;

	@Column(nullable = false)
	private int secondarySiteId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "party_id", nullable = false)
	private Party party;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "deputy")
	private List<Expense> expenses;

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

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getSecondarySiteId() {
		return secondarySiteId;
	}

	public void setSecondarySiteId(int secondarySiteId) {
		this.secondarySiteId = secondarySiteId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Deputy) {
			Deputy other = (Deputy) obj;
			return new EqualsBuilder()
					.append(other.id, id)
					.append(other.secondarySiteId, secondarySiteId)
					.append(other.siteId, siteId)
					.append(other.uf, uf)
					.append(other.party, party)
					.isEquals();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(id)
				.append(secondarySiteId)
				.append(siteId)
				.append(uf)
				.append(party)
				.hashCode();
	}
	
}
