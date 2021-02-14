package org.company.security;

import org.bouncycastle.cms.*;
import org.bouncycastle.cms.jcajce.JceCMSContentEncryptorBuilder;
import org.bouncycastle.cms.jcajce.JceKeyTransRecipientInfoGenerator;
import org.bouncycastle.operator.OutputEncryptor;

import java.io.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

class FileEncryptor implements Encryptor {
    @Override
    public OutputStream encrypt(File outputFile, X509Certificate encryptionCertificate)
            throws CertificateEncodingException, IOException, CMSException {
        if (encryptionCertificate != null) {
            CMSEnvelopedDataStreamGenerator cmsEnvelopedDataStreamGenerator
                    = new CMSEnvelopedDataStreamGenerator();
        
            JceKeyTransRecipientInfoGenerator jceKey = new JceKeyTransRecipientInfoGenerator(encryptionCertificate);
            cmsEnvelopedDataStreamGenerator.addRecipientInfoGenerator(jceKey);

            OutputEncryptor outputEncryptor
                    = new JceCMSContentEncryptorBuilder(CMSAlgorithm.AES128_CBC)
                    .setProvider("BC").build();
            
            OutputStream encryptedOutputStream = new FileOutputStream(outputFile);
    
            return cmsEnvelopedDataStreamGenerator.open(encryptedOutputStream, outputEncryptor);
        }
        return null;
    }
}
