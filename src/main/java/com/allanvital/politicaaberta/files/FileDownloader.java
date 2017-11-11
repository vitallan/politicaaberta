package com.allanvital.politicaaberta.files;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

@Component
public class FileDownloader {

    public File downloadDeputies() {
        return this.download("deputies.zip", "http://www.camara.leg.br/internet/deputado/DeputadosXML_52a55.zip");
    }
    
    public File downloadExpenses(int year) {
        return this.download("file" + year + ".zip", "http://www.camara.leg.br/cotas/Ano-" + year + ".xml.zip");
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

}
