package com.allanvital.politicaaberta.files;

import com.allanvital.politicaaberta.model.DespesaXmlEntry;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class XmlProcessor {

    public <T> T readXml(File file, String localName, Class<T> type) throws IOException, JAXBException, XMLStreamException, FactoryConfigurationError {
        InputStream is = new FileInputStream(file);
        JAXBContext jaxbContext = JAXBContext.newInstance(type);
        XMLStreamReader xss = XMLInputFactory.newFactory().createXMLStreamReader(is);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        while (xss.hasNext()) {
            if (xss.next() == XMLStreamConstants.START_ELEMENT && xss.getLocalName().equals(localName)) {
                JAXBElement<?> unmarshalledObj = unmarshaller.unmarshal(xss, type);
                Object entry = unmarshalledObj.getValue();
                System.out.println(entry);
            }
        }
        return null;
    }

}
