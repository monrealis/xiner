package eu.vytenis.xiner.impl.xml;

import eu.vytenis.xiner.api.Keys;
import eu.vytenis.xiner.api.SignatureXmlBuilder;
import eu.vytenis.xiner.impl.keys.Pkcs12Keys;
import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMValidateContext;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class JaxbSignatureXmlBuilderTest {
    private SignatureXmlBuilder builder = new JaxbSignatureXmlBuilder();
    private Keys keys = new Pkcs12Keys();

    @Test
    public void shouldContainSignature() {
        assertThat(builder.createSignatureDocumentString(), containsString("Signature"));
    }

    @Test
    public void shouldProduceSignatureThatCanBeUnmarshalled() throws MarshalException {
        Document d = builder.createSignatureDocument();
        DOMValidateContext c = new DOMValidateContext(keys.getKey().getPublic(), d.getDocumentElement());
        XMLSignatureFactory.getInstance().unmarshalXMLSignature(c);

    }
}
