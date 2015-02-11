package eu.vytenis.xiner.impl.xmldsig;

import eu.vytenis.xiner.api.XmlSignature;
import org.apache.jcp.xml.dsig.internal.dom.XMLDSigRI;
import org.apache.xml.security.Init;

import javax.xml.crypto.dsig.XMLSignatureFactory;

public class SantuarioXmlSignature implements XmlSignature {
    static {
        Init.init();
    }
    @Override
    public XMLSignatureFactory getFactory() {
        return XMLSignatureFactory.getInstance("DOM", new XMLDSigRI());
    }
}
