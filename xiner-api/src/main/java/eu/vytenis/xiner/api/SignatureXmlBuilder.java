package eu.vytenis.xiner.api;

import org.w3c.dom.Document;

public interface SignatureXmlBuilder {
    String createSignatureDocumentString();

    Document createSignatureDocument();
}
