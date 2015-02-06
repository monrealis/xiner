package eu.vytenis.xiner.impl.xml;

import eu.vytenis.xiner.api.SignatureXmlBuilder;
import eu.vytenis.xiner.xsd.XmlDSigJaxbFactory;
import org.w3._2000._09.xmldsig_.*;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBException;
import javax.xml.transform.Result;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;


public class JaxbSignatureXmlBuilder implements SignatureXmlBuilder {
    private ObjectFactory factory = new ObjectFactory();

    @Override
    public String createSignatureDocumentString() {
        StringWriter w = new StringWriter();
        create(new StreamResult(w));
        return w.toString();
    }

    private void create(Result result) {
        SignatureType signature = createSignature();
        marshall(signature, result);
    }

    private SignatureType createSignature() {
        return new SignatureBuilder().invoke();
    }

    private void marshall(SignatureType signature, Result result) {
        try {
            XmlDSigJaxbFactory.get().createMarshaller().marshal(factory.createSignature(signature), result);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Document createSignatureDocument() {
        DOMResult r = new DOMResult();
        create(r);
        return (Document) r.getNode();
    }
}
