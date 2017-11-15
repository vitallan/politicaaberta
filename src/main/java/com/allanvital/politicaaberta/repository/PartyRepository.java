package com.allanvital.politicaaberta.repository;

import com.allanvital.politicaaberta.model.Party;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRepository extends CrudRepository<Party, Long> {

    Party findByName(String name);

}
