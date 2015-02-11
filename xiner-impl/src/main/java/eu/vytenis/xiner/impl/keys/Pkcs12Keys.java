package eu.vytenis.xiner.impl.keys;

import eu.vytenis.xiner.api.Keys;
import eu.vytenis.xiner.api.Key;

import java.security.KeyStore;

import static java.security.KeyStore.*;

public class Pkcs12Keys implements Keys {
    public static final char[] EMPTY_CHARS = "".toCharArray();

    @Override
    public Key getKey() {
        try {
            return new PrivateKeyEntryKey(getEntry());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private PrivateKeyEntry getEntry() throws Exception {
        KeyStore ks = getKeyStore();
        String alias = ks.aliases().nextElement();
        return (PrivateKeyEntry) ks.getEntry(alias, new PasswordProtection(EMPTY_CHARS));
    }

    private KeyStore getKeyStore() throws Exception {
        KeyStore ks = getInstance("PKCS12");
        ks.load(getClass().getResourceAsStream("/server.p12"), EMPTY_CHARS);
        return ks;
    }
}
