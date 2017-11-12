package com.allanvital.politicaaberta.batch.writer;

import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@JobScope
@Component
public class FileUnzipWriter implements ItemWriter<File>{

    private String intermediaryXml;

    public FileUnzipWriter(@Value("#{jobParameters['intermediaryXml']}") String intermediaryXml) {
        this.intermediaryXml = intermediaryXml;
    }

    public File unzipFile(File zippedFile) {
        byte[] buffer = new byte[1024];
        try {
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zippedFile));
            ZipEntry ze = zis.getNextEntry();
            File insideZipFile = new File(ze.getName());
            FileOutputStream fos = new FileOutputStream(insideZipFile);
            int len;
            while((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            zis.closeEntry();
            zis.close();
            return insideZipFile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void write(List<? extends File> items) throws Exception {
        if (items.size() != 1) {
            throw new RuntimeException("Deveria haver apenas um arquivo baixado. Existem " + items.size());
        }
        File file = items.get(0);
        File unzippedFile = this.unzipFile(file);
        unzippedFile.renameTo(new File(intermediaryXml));
    }
}
