package com.allanvital.politicaaberta.api.dto;

import com.allanvital.politicaaberta.api.model.Deputy;
import com.allanvital.politicaaberta.api.model.Party;

public class DeputyEntryDto {
	
	private int siteId;
	private String name;
	private String party;
	private String uf;
	private int secondarySiteId;

	public Deputy buildDeputy() {
		Deputy deputy = new Deputy();
		deputy.setSiteId(this.siteId);
		deputy.setName(this.name);
		deputy.setUf(this.uf);
		deputy.setSecondarySiteId(this.secondarySiteId);
		Party party = new Party();
		party.setDescription(this.party);
		deputy.setParty(party);
		return deputy;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getParty() {
		return party;
	}
	
	public void setParty(String party) {
		this.party = party;
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
}
