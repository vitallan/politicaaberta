package com.allanvital.politicaaberta.repository;

import com.allanvital.politicaaberta.model.Deputy;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DeputyRepository extends PagingAndSortingRepository<Deputy, Long> {

    Deputy findByOfficialId(Long ideCadastro);

    Deputy findByNormalizedName(String normalizedName);

    List<Deputy> findTop5ByNormalizedNameContaining(String normalizedName);

}
