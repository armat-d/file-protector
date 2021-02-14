package org.company;

import org.apache.commons.io.FileUtils;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.company.security.FileStreamer;
import org.company.security.Streamer;
import org.company.security.utils.SecurityUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FileStreamerTest {
    private final Streamer streamer = new FileStreamer("src/test/resources/",
            "src/test/resources/");
    private final SecurityUtils securityUtils = new SecurityUtils();
    
    @Test
    public void shouldEncryptFileContent()
            throws URISyntaxException, CertificateException, CMSException, IOException, NoSuchProviderException {
        File initialFile = new File("src/test/resources/inputFile.txt");
        File encryptedFile = this.encryptFile(initialFile);
    
        String initialFileContent = FileUtils.readFileToString(initialFile, StandardCharsets.UTF_8);
        String encryptedFileContent = FileUtils.readFileToString(encryptedFile, StandardCharsets.UTF_8);
    
        assertNotEquals(initialFileContent, encryptedFileContent);
        
        FileUtils.forceDelete(encryptedFile);
    }
    
    @Test
    public void shouldDecryptFileContent()
            throws URISyntaxException, CertificateException, CMSException, IOException,
            NoSuchProviderException, KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        File initialFile = new File("src/test/resources/inputFile.txt");
        File encryptedFile = this.encryptFile(initialFile);
    
        KeyStore keystore = securityUtils.getKeystore("src/test/resources/keys.keystore", "password");
        PrivateKey privateKey = (PrivateKey) keystore.getKey("client", "password".toCharArray());
    
        File decryptedFile = streamer.decryptAndGet(encryptedFile, privateKey);
    
        String initialFileContent = FileUtils.readFileToString(initialFile, StandardCharsets.UTF_8);
        String decryptedFileContent = FileUtils.readFileToString(decryptedFile, StandardCharsets.UTF_8);
        
        assertEquals(initialFileContent, decryptedFileContent);
    
        FileUtils.forceDelete(encryptedFile);
        FileUtils.forceDelete(decryptedFile);
    }
    
    private File encryptFile(File initialFile)
            throws CertificateException, IOException, NoSuchProviderException, CMSException, URISyntaxException {
        Security.addProvider(new BouncyCastleProvider());
        X509Certificate certificate = securityUtils.getCertificate("src/test/resources/certificate.pem");
        return streamer.encryptAndSave(initialFile, certificate);
    }
}
