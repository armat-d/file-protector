package org.company.controller;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class FileController {
    private final static String FILES_DIRECTORY = "/Users/ajabassov/Downloads/fileprotector/server/";
    
    public void upload(String filename, InputStream inputStream) throws IOException {
        File targetFile = new File(FILES_DIRECTORY + filename);
        // inputStream is closed in copyInputStreamToFile
        FileUtils.copyInputStreamToFile(inputStream, targetFile);
    }
    
    public InputStream download(String filename) throws IOException {
        File inputFile = new File(FILES_DIRECTORY + filename);
        return FileUtils.openInputStream(inputFile);
    }
}
