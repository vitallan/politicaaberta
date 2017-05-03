package com.allanvital.politicaaberta.api.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class Party implements Serializable {

	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId=true)
	@Id
    @GeneratedValue(strategy = IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique=true)
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "party")
	private Set<Deputy> deputies;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Deputy> getDeputies() {
		return deputies;
	}
	public void setDeputies(Set<Deputy> deputies) {
		this.deputies = deputies;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Party) {
			Party other = (Party) obj;
			return new EqualsBuilder()
					.append(other.id, id)
					.append(other.description, description)
					.build();
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(id)
				.append(description)
				.hashCode();
	}
	
}
