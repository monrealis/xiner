package eu.vytenis.xiner.impl.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public interface XmlDSigJaxbFactory {
    Marshaller createMarshaller();

    Unmarshaller createUnmarshaller();

    JAXBContext createContext();
}
