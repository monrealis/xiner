package eu.vytenis.xiner.impl.keys;

import eu.vytenis.xiner.api.Key;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

import static java.security.KeyStore.PrivateKeyEntry;

public class PrivateKeyEntryKey implements Key {
    private PrivateKeyEntry entry;

    public PrivateKeyEntryKey(PrivateKeyEntry entry) {
        this.entry = entry;
    }

    @Override
    public PrivateKey getPrivate() {
        return entry.getPrivateKey();
    }

    @Override
    public PublicKey getPublic() {
        return getCertificate().getPublicKey();
    }

    @Override
    public X509Certificate getCertificate() {
        return (X509Certificate) entry.getCertificate();
    }
}
