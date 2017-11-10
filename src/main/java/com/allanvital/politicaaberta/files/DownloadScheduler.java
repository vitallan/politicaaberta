package com.allanvital.politicaaberta.files;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

@Component
public class DownloadScheduler {

    private FileDownloader downloader;

    public DownloadScheduler(FileDownloader downloader) {
        this.downloader = downloader;
    }

    public void downloadOlderFiles() {
        for(int year = 2009; year < this.currentYear(); year++) {
            downloader.download(year);
        }
    }

    public void downloadCurrentYearFile() {
        downloader.download(this.currentYear());
    }

    private int currentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static void main(String[] args) throws JAXBException, IOException, XMLStreamException {
        FileDownloader downloader = new FileDownloader();
        FileUnzipper unzipper = new FileUnzipper();
        XmlProcessor processor = new XmlProcessor();
        File downloaded = downloader.download(2017);
        File unzipedFile = unzipper.unzipFile(downloaded);
        processor.readXml(unzipedFile);
    }

}
