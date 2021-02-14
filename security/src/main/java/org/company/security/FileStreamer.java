package org.company.security;

import org.apache.commons.io.IOUtils;
import org.bouncycastle.cms.CMSException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.PrivateKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.stream.Collectors;

public class FileStreamer implements Streamer {
    private static final String ENCRYPTED_FILES_PATH = "/Users/ajabassov/Downloads/fileprotector/client/";
    private static final String DECRYPTED_FILES_PATH = "/Users/ajabassov/Downloads/fileprotector/client/";
    private static final String ENCRYPTED_FILE_EXTENSION = ".enc";
    private static final String DECRYPTED_FILE_EXTENSION = ".dec";
    
    private final Encryptor encoder = new FileEncryptor();
    private final Decryptor decoder = new FileDecryptor();
    
    @Override
    public File encryptAndSave(File inputFile, X509Certificate encryptionCertificate)
            throws IOException, CMSException, CertificateEncodingException {
        File encryptedFile = new File(ENCRYPTED_FILES_PATH + inputFile.getName() + ENCRYPTED_FILE_EXTENSION);
        try(
                InputStream inputStream = new FileInputStream(inputFile);
                OutputStream encryptedOutputStream = encoder.encrypt(encryptedFile, encryptionCertificate)
        ) {
            IOUtils.copyLarge(inputStream, encryptedOutputStream);
        }
        return encryptedFile;
    }
    
    @Override
    public File decryptAndGet(File encryptedFile, PrivateKey decryptionKey) throws CMSException, IOException {
        try (
                InputStream inputStream = decoder.decode(new FileInputStream(encryptedFile), decryptionKey)
                        .getContentStream()
        ) {
            String fileNameWithRemovedExtension = Arrays.stream(encryptedFile.getName().split("\\."))
                    .filter(part -> !part.equals("enc"))
                    .collect(Collectors.joining("."));
    
            String decryptedFile = DECRYPTED_FILES_PATH + fileNameWithRemovedExtension + DECRYPTED_FILE_EXTENSION;
            Files.copy(inputStream, Paths.get(decryptedFile), StandardCopyOption.REPLACE_EXISTING);
            
            return new File(decryptedFile);
        }
    }
}
