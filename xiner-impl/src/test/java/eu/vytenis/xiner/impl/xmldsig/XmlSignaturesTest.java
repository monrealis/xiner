package eu.vytenis.xiner.impl.xmldsig;

import eu.vytenis.xiner.api.XmlSignature;
import org.apache.jcp.xml.dsig.internal.dom.DOMXMLSignatureFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class XmlSignaturesTest {
    XmlSignature jdkSignature = new JdkXmlSignature();
    XmlSignature santuarioSignature = new SantuarioXmlSignature();

    @Test
    public void shouldReturnJdkSignatureFactory() {
        assertEquals("org.jcp.xml.dsig.internal.dom.DOMXMLSignatureFactory", jdkSignature.getFactory().getClass().getName());
    }

    @Test
    public void shouldReturnsSantuarioSignatureFactory() {
        assertEquals(DOMXMLSignatureFactory.class.getName(), santuarioSignature.getFactory().getClass().getName());
    }
}
