package com.allanvital.politicaaberta.batch.processor;

import com.allanvital.politicaaberta.model.DeputadoXmlEntry;
import com.allanvital.politicaaberta.repository.DeputadoXmlEntryRepository;
import com.allanvital.politicaaberta.repository.DeputyRepository;
import com.allanvital.politicaaberta.repository.PartyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeputyProcessorTest {

    private DeputyProcessor processor;

    @Mock
    private DeputadoXmlEntryRepository deputadoXmlEntryRepository;

    @Mock
    private DeputyRepository deputyRepository;

    @Mock
    private PartyRepository partyRepository;

    private String partyName = "PY";
    private Integer ideCadastro = 1;
    private String deputyName = "JOAO JOAQUIM";
    private String uf = "NA";
    private Long xmlEntryId = 1L;

    @Before
    public void setup() {
        this.processor = new DeputyProcessor(deputadoXmlEntryRepository, deputyRepository, partyRepository);
    }

    @Test
    public void shouldCreateNewPartyAndDeputy() {
        DeputadoXmlEntry entry = this.buildObjectToProcess();
        when(deputadoXmlEntryRepository.findByIdeCadastro(ideCadastro)).thenReturn(null);
        when(deputadoXmlEntryRepository.save(entry)).thenReturn(entry);
        when(deputyRepository.findByDeputyXmlEntryId(this.xmlEntryId)).thenReturn(null);

    }

    private DeputadoXmlEntry buildObjectToProcess() {
        DeputadoXmlEntry xmlEntry = new DeputadoXmlEntry();
        xmlEntry.setId(xmlEntryId);
        xmlEntry.setIdeCadastro(this.ideCadastro);
        xmlEntry.setLegendaPartidoEleito(this.partyName);
        xmlEntry.setNomeParlamentar(this.deputyName);
        xmlEntry.setUFEleito(this.uf);
        return xmlEntry;
    }

}
