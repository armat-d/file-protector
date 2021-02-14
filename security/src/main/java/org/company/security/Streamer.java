package org.company.security;

import org.bouncycastle.cms.CMSException;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.PrivateKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

public interface Streamer {
    File encryptAndSave(File file, X509Certificate encryptionCertificate)
            throws IOException, URISyntaxException, CMSException, CertificateEncodingException;
    File decryptAndGet(File encryptedFile, PrivateKey decryptionKey) throws CMSException, IOException;
}
