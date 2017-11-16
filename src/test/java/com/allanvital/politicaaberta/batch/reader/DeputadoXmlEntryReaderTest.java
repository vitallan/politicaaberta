package com.allanvital.politicaaberta.batch.reader;

import com.allanvital.politicaaberta.model.DeputadoXmlEntry;
import com.allanvital.politicaaberta.model.Deputy;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

import javax.xml.bind.JAXBException;

import static org.junit.Assert.assertEquals;

public class DeputadoXmlEntryReaderTest {

    private DeputadoXmlEntryReader deputadoReader;

    @Before
    public void setup() throws JAXBException {
        this.deputadoReader = new DeputadoXmlEntryReader("src/test/resources/deputies.xml");
    }

    @Test
    public void deputyXmlLoadTest() throws Exception {
        DeputadoXmlEntry xmlEntry = deputadoReader.read();
        Integer ide = 178957;
        Integer numLegislatura = 55;
        Integer matricula = 1;
        Integer gabinete = 248;
        Integer anexo = 4;
        assertEquals(ide, xmlEntry.getIdeCadastro());
        assertEquals(numLegislatura,xmlEntry.getNumLegislatura());
        assertEquals("ABEL MESQUITA JR.", xmlEntry.getNomeParlamentar());
        assertEquals("M", xmlEntry.getSEXO());
        assertEquals("Empresário", xmlEntry.getProfissao());
        assertEquals("PDT", xmlEntry.getLegendaPartidoEleito());
        assertEquals("RR", xmlEntry.getUFEleito());
        assertEquals("Titular", xmlEntry.getCondicao());
        assertEquals("Em Exercício", xmlEntry.getSiTuacaoMandato());
        assertEquals(matricula, xmlEntry.getMatricula());
        assertEquals(gabinete, xmlEntry.getGabinete());
        assertEquals(anexo, xmlEntry.getAnexo());
        assertEquals("3215-5248", xmlEntry.getFone());
    }

    @Test
    public void testDeputyBuild() throws Exception {
        DeputadoXmlEntry xmlEntry = deputadoReader.read();
        Long xmlId = 1L;
        xmlEntry.setId(xmlId);
        Deputy deputy = xmlEntry.buildDeputy();
        assertEquals(deputy.getName(), "Abel Mesquita Jr.");
        assertEquals(deputy.getUf(), "RR");
        assertEquals(deputy.getDeputyXmlEntryId(), xmlId);
    }

}
