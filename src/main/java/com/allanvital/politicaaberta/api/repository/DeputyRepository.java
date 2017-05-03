package com.allanvital.politicaaberta.api.repository;

import com.allanvital.politicaaberta.api.model.Deputy;
import org.springframework.data.repository.CrudRepository;

public interface DeputyRepository extends CrudRepository<Deputy, Long>{

	Deputy findBySiteId(int siteId);

}
