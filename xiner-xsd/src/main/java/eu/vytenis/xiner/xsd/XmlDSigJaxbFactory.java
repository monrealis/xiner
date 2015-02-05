package eu.vytenis.xiner.xsd;

import org.w3._2000._09.xmldsig_.ObjectFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XmlDSigJaxbFactory {
    protected static XmlDSigJaxbFactory instance = new XmlDSigJaxbFactory();

    public static XmlDSigJaxbFactory get() {
        return instance;
    }

    public Marshaller createMarshaller() {
        try {
            return createContext().createMarshaller();
        } catch (JAXBException e) {
            throw createRuntimeException(e);
        }
    }

    public Unmarshaller createUnmarshaller() {
        try {
            return createContext().createUnmarshaller();
        } catch (JAXBException e) {
            throw createRuntimeException(e);
        }
    }

    public JAXBContext createContext() {
        try {
            return JAXBContext.newInstance(ObjectFactory.class);
        } catch (JAXBException e) {
            throw createRuntimeException(e);
        }
    }

    private RuntimeException createRuntimeException(JAXBException e) {
        return new RuntimeException(e);
    }
}
