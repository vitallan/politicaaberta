package com.allanvital.politicaaberta.repository;

import com.allanvital.politicaaberta.model.PropositionType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropositionTypeRepository extends CrudRepository<PropositionType, Long> {

    PropositionType findByOfficialId(Long officialId);

}
