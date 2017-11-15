package com.allanvital.politicaaberta;

import com.allanvital.politicaaberta.model.DeputadoXmlEntry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.StepScopeTestExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners( { StepScopeTestExecutionListener.class })
public class PoliticaabertaApplicationTests {

    @Autowired
    private StaxEventItemReader<DeputadoXmlEntry> deputadoXmlEntryStaxEventItemReader;

    public StepExecution getStepExecution() {
        Map<String, JobParameter> parameterMap = new HashMap<>();
        parameterMap.put("outputZippedFilename", new JobParameter("deputies.zip"));
        parameterMap.put("downloadUrl", new JobParameter("http://www.camara.leg.br/internet/deputado/DeputadosXML_52a55.zip"));
        parameterMap.put("intermediaryXml", new JobParameter("deputies.xml"));
        parameterMap.put("startingAt", new JobParameter(new Date()));
        JobParameters parameters = new JobParameters(parameterMap);
        StepExecution execution = MetaDataInstanceFactory.createStepExecution(parameters);
        System.out.println("asddad --------- " +execution.getJobParameters());
        execution.getExecutionContext().putString("fileName", "deputies.xml");
        return execution;
    }

	@Test
	public void contextLoads() {
        StepScopeTestExecutionListener a;
	}

	@Test
	public void deputyXmlLoadTest() throws Exception {
        System.out.println(deputadoXmlEntryStaxEventItemReader.read());
    }

}
