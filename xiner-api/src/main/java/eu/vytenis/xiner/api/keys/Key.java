package eu.vytenis.xiner.api.keys;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

public interface Key {
    PrivateKey getPrivate();
    PublicKey getPublic();
    X509Certificate getCertificate();
}
