package com.allanvital.politicaaberta.api.service;

import com.allanvital.politicaaberta.api.model.Deputy;
import com.allanvital.politicaaberta.api.model.Party;
import com.allanvital.politicaaberta.api.repository.DeputyRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeputyService {

	private static final Logger log = Logger.getLogger(DeputyService.class);

	private DeputyRepository repository;
	private PartyService partyService;
	
	@Autowired
	public DeputyService(DeputyRepository repository, PartyService partyService) {
		this.repository = repository;
		this.partyService = partyService;
	}

	public Deputy saveDeputyAndParty(Deputy deputy, Party party) {
		if (deputy.getSiteId() == 0 || party.getDescription() == null) {
			return null;
		}
		log.info("Salvando deputy de siteId " + deputy.getSiteId());
		Deputy persitedDeputy = repository.findBySiteId(deputy.getSiteId());
		if (persitedDeputy == null) {
			deputy.setParty(partyService.idempotentSave(party));
			persitedDeputy = repository.save(deputy);		
		}
		return persitedDeputy;
	}

	public Deputy findBySiteId(int siteId) {
		return repository.findBySiteId(siteId);
	}

	public void saveDeputy(Deputy deputy) {
		this.saveDeputyAndParty(deputy, deputy.getParty());
	}

}
