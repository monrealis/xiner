package eu.vytenis.xiner.impl.xmldsig;

import eu.vytenis.xiner.api.XmlSignature;

import javax.xml.crypto.dsig.XMLSignatureFactory;
import java.security.NoSuchProviderException;

public class JdkXmlSignature implements XmlSignature {
    @Override
    public XMLSignatureFactory getFactory() {
        try {
            XMLSignatureFactory.getInstance().getProvider().getName();
            return XMLSignatureFactory.getInstance("DOM", "XMLDSig");
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        }
    }
}
