package org.company;

public class Params {
    private String inputFilePath;
    private String certificatePath;
    private String keystorePath;
    private String keystorePassword;
    private String privateKeyPassword;
    private String certificateAlias;
    private String action;
    
    public Params(String inputFilePath, String certificatePath, String keystorePath,
                  String keystorePassword, String privateKeyPassword, String certificateAlias, String action) {
        this.inputFilePath = inputFilePath;
        this.certificatePath = certificatePath;
        this.keystorePath = keystorePath;
        this.keystorePassword = keystorePassword;
        this.privateKeyPassword = privateKeyPassword;
        this.certificateAlias = certificateAlias;
        this.action = action;
    }
    
    public String getInputFilePath() {
        return inputFilePath;
    }
    
    public String getCertificatePath() {
        return certificatePath;
    }
    
    public String getKeystorePath() {
        return keystorePath;
    }
    
    public String getKeystorePassword() {
        return keystorePassword;
    }
    
    public String getPrivateKeyPassword() {
        return privateKeyPassword;
    }
    
    public String getCertificateAlias() {
        return certificateAlias;
    }
    
    public String getAction() {
        return action;
    }
}
