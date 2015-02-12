package eu.vytenis.xiner.impl.jaxb;

import org.w3._2000._09.xmldsig_.ObjectFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XmlDSigJaxbFactoryImpl implements XmlDSigJaxbFactory {
    @Override
    public Marshaller createMarshaller() {
        try {
            return createContext().createMarshaller();
        } catch (JAXBException e) {
            throw createRuntimeException(e);
        }
    }

    @Override
    public Unmarshaller createUnmarshaller() {
        try {
            return createContext().createUnmarshaller();
        } catch (JAXBException e) {
            throw createRuntimeException(e);
        }
    }

    @Override
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
