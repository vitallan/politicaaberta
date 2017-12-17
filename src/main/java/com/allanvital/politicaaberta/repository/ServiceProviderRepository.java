package com.allanvital.politicaaberta.repository;

import com.allanvital.politicaaberta.model.ServiceProvider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceProviderRepository extends CrudRepository<ServiceProvider, Long>{

    ServiceProvider findByCpfCnpjAndName(String cpfCnpj, String name);

}
