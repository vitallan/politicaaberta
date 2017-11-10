package com.allanvital.politicaaberta.files;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Component
public class FileUnzipper {

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

}
