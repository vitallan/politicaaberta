package com.allanvital.politicaaberta.repository;

import com.allanvital.politicaaberta.model.DeputadoXmlEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeputadoXmlEntryRepository extends CrudRepository<DeputadoXmlEntry, Long>{

    DeputadoXmlEntry findByIdeCadastro(Long ideCadastro);

}
