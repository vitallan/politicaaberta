package com.allanvital.politicaaberta.service;

import com.allanvital.politicaaberta.model.DeputadoXmlEntry;
import com.allanvital.politicaaberta.model.Deputy;
import com.allanvital.politicaaberta.repository.DeputadoXmlEntryRepository;
import com.allanvital.politicaaberta.repository.DeputyRepository;
import org.springframework.stereotype.Service;

@Service
public class DeputyService {

    private DeputyRepository repository;
    private DeputadoXmlEntryRepository xmlEntryRepository;

    public DeputyService(DeputyRepository repository, DeputadoXmlEntryRepository xmlEntryRepository) {
        this.repository = repository;
        this.xmlEntryRepository = xmlEntryRepository;
    }

    public Deputy getDeputyByXmlEntryIdeCadastro(int ideCadastro) {
        DeputadoXmlEntry xmlEntry = xmlEntryRepository.findByIdeCadastro(ideCadastro);
        Deputy deputy = repository.findByDeputyXmlEntryId(xmlEntry.getId());
        return deputy;
    }

}
