package com.allanvital.politicaaberta.api.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.allanvital.politicaaberta.api.model.Party;

public interface PartyRepository extends CrudRepository<Party, Long>{

	Party findByDescription(String description);
	
	@Query("select p from Party p inner join fetch p.deputies where p.id = :id")
	Party findOne(@Param("id") Long id);
	
}
