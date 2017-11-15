package com.allanvital.politicaaberta;

import com.allanvital.politicaaberta.batch.reader.DeputadoXmlEntryReader;
import com.allanvital.politicaaberta.batch.reader.DespesaXmlEntryReader;
import com.allanvital.politicaaberta.model.DeputadoXmlEntry;
import com.allanvital.politicaaberta.model.Deputy;
import com.allanvital.politicaaberta.model.DespesaXmlEntry;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;

import javax.xml.bind.JAXBException;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class XmlParsingTests {

    private DeputadoXmlEntryReader deputadoReader;
    private DespesaXmlEntryReader despesaReader;

    @Before
    public void setup() throws JAXBException {
        this.deputadoReader = new DeputadoXmlEntryReader("src/test/resources/deputies.xml");
        this.despesaReader = new DespesaXmlEntryReader("src/test/resources/expenses.xml");
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
    public void expenseXmlLoadTest() throws Exception {
        DespesaXmlEntry xmlEntry = this.despesaReader.read();
        assertEquals("ZENAIDE MAIA", xmlEntry.getTxNomeParlamentar());
        assertEquals(178949, xmlEntry.getIdeCadastro());
        assertEquals(126, xmlEntry.getNuCarteiraParlamentar());
        assertEquals(2015, xmlEntry.getNuLegislatura());
        assertEquals("RN", xmlEntry.getSgUF());
        assertEquals("PR", xmlEntry.getSgPartido());
        assertEquals("55", xmlEntry.getCodLegislatura());
        assertEquals(999, xmlEntry.getNumSubCota());
        assertEquals("Emissão Bilhete Aéreo", xmlEntry.getTxtDescricao());
        assertEquals(0, xmlEntry.getNumEspecificacaoSubCota());
        assertEquals("", xmlEntry.getTxtDescricaoEspecificacao());
        assertEquals("Cia Aérea - TAM", xmlEntry.getTxtFornecedor());
        assertEquals("02012862000160", xmlEntry.getTxtCNPJCPF());
        assertEquals("Bilhete: 957-4554.550321", xmlEntry.getTxtNumero());
        assertEquals(0, xmlEntry.getIndTipoDocumento());
        assertEquals("2016-12-06 00:00:00", xmlEntry.getDatEmissao());
        assertEquals(new BigDecimal(200), xmlEntry.getVlrDocumento());
        assertEquals(new BigDecimal(0), xmlEntry.getVlrGlosa());
        assertEquals(new BigDecimal(200), xmlEntry.getVlrLiquido());
        assertEquals(12, xmlEntry.getNumMes());
        assertEquals(2016, xmlEntry.getNumAno());
        assertEquals(0, xmlEntry.getNumParcela());
        assertEquals("ZENAIDE MAIA", xmlEntry.getTxtPassageiro());
        assertEquals("BSB/BSB", xmlEntry.getTxtTrecho());
        assertEquals(0, xmlEntry.getNumLote());
        assertEquals(0, xmlEntry.getNumRessarcimento());
        assertEquals(new BigDecimal(0), xmlEntry.getVlrRestituicao());
        assertEquals(2930, xmlEntry.getNuDeputadoId());
        assertEquals(0, xmlEntry.getIdeDocumento());
    }

    @Test
    public void testDeputyBuild() throws Exception {
        System.out.println(StringUtils.uncapitalize("ASADASD"));
        DeputadoXmlEntry xmlEntry = deputadoReader.read();
        Long xmlId = 1L;
        xmlEntry.setId(xmlId);
        Deputy deputy = xmlEntry.buildDeputy();
        assertEquals(deputy.getName(), "Abel Mesquita Jr.");
        assertEquals(deputy.getUf(), "RR");
        assertEquals(deputy.getDeputyXmlEntryId(), xmlId);
    }

}