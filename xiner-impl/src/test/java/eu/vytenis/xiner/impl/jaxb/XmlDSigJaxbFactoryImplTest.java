package eu.vytenis.xiner.impl.jaxb;

import org.junit.Test;
import org.w3._2000._09.xmldsig_.ObjectFactory;
import org.w3._2000._09.xmldsig_.SignatureType;

import javax.xml.bind.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class XmlDSigJaxbFactoryImplTest {
    private ObjectFactory factory = new ObjectFactory();
    private XmlDSigJaxbFactory jaxbFactory = new XmlDSigJaxbFactoryImpl();
    private JAXBContext context = jaxbFactory.createContext();
    private Marshaller marshaller = jaxbFactory.createMarshaller();
    private Unmarshaller unmarshaller = jaxbFactory.createUnmarshaller();

    @Test
    public void shouldReturnsNotNullContext() {
        assertNotNull(context);
    }

    @Test
    public void shouldBeAbleToSerializeSignature() throws JAXBException {
        JAXBElement<SignatureType> s = createSignature();
        assertThat(serializeSignature(s), containsString("Signature"));
    }

    private JAXBElement<SignatureType> createSignature() {
        return factory.createSignature(new SignatureType());
    }

    private String serializeSignature(JAXBElement<SignatureType> s) throws JAXBException {
        StringWriter w = new StringWriter();
        marshaller.marshal(s, new StreamResult(w));
        return w.toString();
    }

    @Test
    public void shouldBeAbleToDeserializeSignature() throws JAXBException {
        Object s = deserializeSignature(serializeSignature(createSignature()));
        assertThat(s, instanceOf(JAXBElement.class));
        assertThat(((JAXBElement<?>) s).getValue(), instanceOf(SignatureType.class));
    }

    private Object deserializeSignature(String xml) throws JAXBException {
        return unmarshaller.unmarshal(new StreamSource(new StringReader(xml)));
    }
}
