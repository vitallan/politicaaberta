package com.allanvital.politicaaberta.repository;

import com.allanvital.politicaaberta.model.Deputy;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DeputyRepository extends PagingAndSortingRepository<Deputy, Long> {

    Deputy findByOfficialId(Long ideCadastro);

}
