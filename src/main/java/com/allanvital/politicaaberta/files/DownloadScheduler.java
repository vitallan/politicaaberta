package com.allanvital.politicaaberta.files;

import com.allanvital.politicaaberta.batch.reader.FileDownloadReader;
import com.allanvital.politicaaberta.batch.writer.FileUnzipWriter;
import org.springframework.stereotype.Component;

import com.allanvital.politicaaberta.model.DeputadoXmlEntry;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

@Component
public class DownloadScheduler {

    /*private FileDownloadReader downloader;

    public DownloadScheduler(FileDownloadReader downloader) {
        this.downloader = downloader;
    }

    public void downloadOlderFiles() {
        for(int year = 2009; year < this.currentYear(); year++) {
            downloader.downloadExpenses(year);
        }
    }

    public void downloadCurrentYearFile() {
        downloader.downloadExpenses(this.currentYear());
    }

    private int currentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static void main(String[] args) throws JAXBException, IOException, XMLStreamException {
        FileDownloadReader downloader = new FileDownloadReader();
        FileUnzipWriter unzipper = new FileUnzipWriter();
        XmlProcessor processor = new XmlProcessor();
        File downloaded = downloader.downloadExpenses(2017);
        File unzipedFile = unzipper.unzipFile(downloaded);
        
        File downloadedDeputies = downloader.downloadDeputies();
        File unzipedDeputies = unzipper.unzipFile(downloadedDeputies);
        
        //processor.readXml(unzipedFile, "DESPESA", DespesaXmlEntry.class);
        processor.readXml(unzipedDeputies, "Deputado", DeputadoXmlEntry.class);
    }*/

}
