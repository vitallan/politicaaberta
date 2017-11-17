package com.allanvital.politicaaberta.batch.processor;

import com.allanvital.politicaaberta.model.DeputadoXmlEntry;
import com.allanvital.politicaaberta.repository.DeputadoXmlEntryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeputadoXmlEntryProcessorTest {

    @Mock
    private DeputadoXmlEntryRepository repository;

    private DeputadoXmlEntryProcessor processor;

    private Integer ideCadastro = 1;
    private Long persistedId = 2L;

    @Before
    public void setup() {
        this.processor = new DeputadoXmlEntryProcessor(repository);
    }

    @Test
    public void shouldSaveNewEntryWhenEntryIsNotPersisted() throws Exception {
        when(repository.findByIdeCadastro(ideCadastro)).thenReturn(null);
        when(repository.save(this.buildEntry(false))).thenReturn(this.buildEntry(true));
        processor.process(this.buildEntry(false));
        verify(repository, times(1)).findByIdeCadastro(ideCadastro);
        verify(repository, times(1)).save(this.buildEntry(false));
    }

    @Test
    public void shouldReturnPersistedEntryWhenEntryIsAlreadyPersisted() throws Exception {
        when(repository.findByIdeCadastro(ideCadastro)).thenReturn(this.buildEntry(true));
        processor.process(this.buildEntry(false));
        verify(repository, times(1)).findByIdeCadastro(ideCadastro);
        verify(repository, times(0)).save((DeputadoXmlEntry) any());
    }

    private DeputadoXmlEntry buildEntry(boolean persisted) {
        DeputadoXmlEntry entry = new DeputadoXmlEntry();
        entry.setIdeCadastro(ideCadastro);
        if(persisted) {
            entry.setId(persistedId);
        }
        return entry;
    }

}
