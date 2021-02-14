package org.company.security;

import org.bouncycastle.cms.CMSException;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

public interface Encryptor {
  OutputStream encrypt(File outputFile, X509Certificate encryptionCertificate)
          throws CertificateEncodingException, IOException, CMSException;

}
