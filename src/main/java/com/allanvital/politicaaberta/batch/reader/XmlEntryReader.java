package com.allanvital.politicaaberta.batch.reader;

import com.allanvital.politicaaberta.model.DeputadoXmlEntry;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public abstract class XmlEntryReader<T> implements ItemReader<T> {

    private String intermediaryXml;
    private String fragmentRootElement;
    private Class<T> type;
    private boolean isFileOpened = false;
    private XMLStreamReader xss;
    private InputStream is;
    private Unmarshaller unmarshaller;

    public XmlEntryReader(String intermediaryXml, String fragmentRootElement, Class<T> type) throws JAXBException {
        this.intermediaryXml = intermediaryXml;
        this.fragmentRootElement = fragmentRootElement;
        this.type = type;
        JAXBContext jaxbContext = JAXBContext.newInstance(type);
        this.unmarshaller = jaxbContext.createUnmarshaller();
    }

    @Override
    public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (!isFileOpened) {
            this.is = new FileInputStream(new File(this.intermediaryXml));
            this.xss = XMLInputFactory.newFactory().createXMLStreamReader(is);
            isFileOpened = true;
        }
        while (xss.hasNext()) {
            if (xss.next() == XMLStreamConstants.START_ELEMENT && xss.getLocalName().equals(fragmentRootElement)) {
                JAXBElement<T> unmarshalledObj = unmarshaller.unmarshal(xss, type);
                return unmarshalledObj.getValue();
            }
        }
        this.xss.close();
        this.is.close();
        isFileOpened = false;
        return null;
    }

}
