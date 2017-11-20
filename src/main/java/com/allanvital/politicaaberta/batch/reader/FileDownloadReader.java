package com.allanvital.politicaaberta.batch.reader;

import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

@JobScope
@Component
public class FileDownloadReader implements ItemReader<File>{

    private String outputZippedFilename;//  deputies.zip  "file" + year + ".zip"
    private String downloadUrl;// "http://www.camara.leg.br/internet/deputado/DeputadosXML_52a55.zip" "http://www.camara.leg.br/cotas/Ano-" + year + ".xml.zip"
    private int executedCount = 0;

    public FileDownloadReader(@Value("#{jobParameters['outputZippedFilename']}") String outputZippedFilename,
                              @Value("#{jobParameters['downloadUrl']}") String downloadUrl) {

        this.outputZippedFilename = outputZippedFilename;
        this.downloadUrl = downloadUrl;
    }

    private File download(String newFile, String uri) {
        try {
            File file = new File(newFile);
            URL url = new URL(uri);
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream(file);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            return file;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public File read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (executedCount > 0) {
            return null;
        }
        executedCount++;
        return this.download(this.outputZippedFilename, this.downloadUrl);
    }
}
