package org.company.security.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class SecurityUtils {
    public X509Certificate getCertificate(String certificatePath)
            throws CertificateException, NoSuchProviderException, FileNotFoundException {
        File certificateFile = new File(certificatePath);
        if (certificateFile.exists()) {
            CertificateFactory certificateFactory = CertificateFactory
                    .getInstance("X.509", "BC");
            return (X509Certificate) certificateFactory
                    .generateCertificate(new FileInputStream(certificateFile));
        }
        return null;
    }
    
    public KeyStore getKeystore(String keystorePath, String keystorePassword)
            throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        KeyStore keystore = KeyStore.getInstance("PKCS12");
        keystore.load(new FileInputStream(keystorePath), keystorePassword.toCharArray());
        return keystore;
    }
}
