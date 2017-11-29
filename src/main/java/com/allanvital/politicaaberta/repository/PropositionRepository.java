package com.allanvital.politicaaberta.repository;

import com.allanvital.politicaaberta.model.Proposition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropositionRepository extends CrudRepository<Proposition, Long> {

    Proposition findByOfficialId(Long officialId);

}
