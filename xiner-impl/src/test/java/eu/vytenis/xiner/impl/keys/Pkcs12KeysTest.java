package eu.vytenis.xiner.impl.keys;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class Pkcs12KeysTest {
    private Pkcs12Keys keys = new Pkcs12Keys();

    @Test
    public void getKey_returnsNotNulls() {
        assertNotNull(keys.getKey());
        assertNotNull(keys.getKey().getPrivate());
        assertNotNull(keys.getKey().getPublic());
        assertNotNull(keys.getKey().getCertificate());
    }
}
