package com.allanvital.politicaaberta.repository;

import com.allanvital.politicaaberta.model.Committee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitteeRepository extends CrudRepository<Committee, Long> {

    Committee findByOfficialId(Long officialId);

}
