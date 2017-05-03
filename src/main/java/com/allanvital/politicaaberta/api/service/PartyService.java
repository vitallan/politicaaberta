package com.allanvital.politicaaberta.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allanvital.politicaaberta.api.model.Party;
import com.allanvital.politicaaberta.api.repository.PartyRepository;

@Service
public class PartyService {

	private PartyRepository repository;

	@Autowired
	public PartyService(PartyRepository repository) {
		this.repository = repository;
	}

	public Party idempotentSave(Party party) {
		Party persistedParty = repository.findByDescription(party.getDescription());
		if (persistedParty == null) {
			persistedParty = repository.save(party);
		}
		return persistedParty;
	}

}
