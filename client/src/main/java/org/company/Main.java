package org.company;

import org.bouncycastle.cms.CMSException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.company.security.FileStreamer;
import org.company.security.Streamer;
import org.company.security.utils.SecurityUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class Main {
    private static final String ENCRYPTED_FILES_PATH = "/Users/ajabassov/Downloads/fileprotector/client/";
    private static final String DECRYPTED_FILES_PATH = "/Users/ajabassov/Downloads/fileprotector/client/";
    
    public static void main(String[] args)
            throws CertificateException, NoSuchProviderException, KeyStoreException,
            IOException, UnrecoverableEntryException, NoSuchAlgorithmException, CMSException, URISyntaxException {
        if (args.length < 7) {
            throw new IllegalArgumentException("Wrong arguments size. Should be 7.");
        }
        Params params = new Params(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
    
        Security.addProvider(new BouncyCastleProvider());
    
        final HttpClient client = new HttpClient();
        final Streamer streamer = new FileStreamer(ENCRYPTED_FILES_PATH, DECRYPTED_FILES_PATH);
        final SecurityUtils securityUtils = new SecurityUtils();
        
        if (params.getAction().equals("upload")) {
            X509Certificate certificate = securityUtils.getCertificate(params.getCertificatePath());
            File encryptedFile = streamer.encryptAndSave(new File(params.getInputFilePath()), certificate);
            client.upload(encryptedFile);
        } else if (params.getAction().equals("download")) {
            KeyStore keystore = securityUtils.getKeystore(params.getKeystorePath(), params.getKeystorePassword());
            PrivateKey key = (PrivateKey) keystore.getKey(params.getCertificateAlias(),
                    params.getPrivateKeyPassword().toCharArray());
            File downloadedFile = client.download(new File(params.getInputFilePath()).getName() + ".enc");
    
            if (downloadedFile != null) {
                streamer.decryptAndGet(downloadedFile, key);
            }
        }
    }
}
