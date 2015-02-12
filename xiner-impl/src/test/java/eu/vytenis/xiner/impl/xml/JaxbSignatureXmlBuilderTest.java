package eu.vytenis.xiner.impl.xml;

import eu.vytenis.xiner.api.Keys;
import eu.vytenis.xiner.api.SignatureXmlBuilder;
import eu.vytenis.xiner.api.XmlSignature;
import eu.vytenis.xiner.impl.keys.Pkcs12Keys;
import eu.vytenis.xiner.impl.xmldsig.JdkXmlSignature;
import eu.vytenis.xiner.impl.xmldsig.SantuarioXmlSignature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.w3c.dom.Document;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class JaxbSignatureXmlBuilderTest {
    private SignatureXmlBuilder builder = new JaxbSignatureXmlBuilder();
    private Keys keys = new Pkcs12Keys();
    public XmlSignature signature;

    public JaxbSignatureXmlBuilderTest(XmlSignature signature) {
        this.signature = signature;
    }

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

    @Parameters
    public static List<Object[]> getParams() {
        XmlSignature[] signatures = {new JdkXmlSignature(), new SantuarioXmlSignature()};
        return stream(signatures).map(o -> new Object[]{o}).collect(toList());
    }
}
