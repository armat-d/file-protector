package org.company.security;

import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSTypedStream;

import java.io.IOException;
import java.io.InputStream;
import java.security.PrivateKey;

public interface Decryptor {
    CMSTypedStream decode(InputStream encodedInputStream, PrivateKey decryptionKey)
            throws CMSException, IOException;
}
