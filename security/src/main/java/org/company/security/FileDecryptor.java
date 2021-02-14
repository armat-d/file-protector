package org.company.security;

import org.bouncycastle.cms.*;
import org.bouncycastle.cms.jcajce.JceKeyTransEnvelopedRecipient;
import org.bouncycastle.cms.jcajce.JceKeyTransRecipient;

import java.io.IOException;
import java.io.InputStream;
import java.security.PrivateKey;
import java.util.Collection;

class FileDecryptor implements Decryptor {
    @Override
    public CMSTypedStream decode(InputStream encodedInputStream, PrivateKey decryptionKey)
            throws CMSException, IOException {
        if (encodedInputStream != null && decryptionKey != null) {
            CMSEnvelopedData envelopedData = new CMSEnvelopedData(encodedInputStream);
            
            Collection<RecipientInformation> recipients
                    = envelopedData.getRecipientInfos().getRecipients();
            KeyTransRecipientInformation recipientInfo
                    = (KeyTransRecipientInformation) recipients.iterator().next();
            JceKeyTransRecipient recipient
                    = new JceKeyTransEnvelopedRecipient(decryptionKey);
            
            return recipientInfo.getContentStream(recipient);
        }
        return null;
    }
}
