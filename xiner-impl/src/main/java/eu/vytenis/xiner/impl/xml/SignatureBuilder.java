package eu.vytenis.xiner.impl.xml;

import org.w3._2000._09.xmldsig_.*;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.Transform;

// http://www.oracle.com/technetwork/java/javamail/dig-signature-api-140772.html
public class SignatureBuilder {
    public static final byte[] EMPTY_BYTES = new byte[]{};
    public static final String EMPTY_STRING = "";
    private SignatureType signature = new SignatureType();
    private SignedInfoType signedInfo = new SignedInfoType();
    private SignatureValueType signatureValue = new SignatureValueType();
    private CanonicalizationMethodType cm = createCanonicalizationMethod();
    private SignatureMethodType smt = createSignatureMethod();

    private CanonicalizationMethodType createCanonicalizationMethod() {
        CanonicalizationMethodType r = new CanonicalizationMethodType();
        r.setAlgorithm(CanonicalizationMethod.INCLUSIVE);
        return r;
    }

    private SignatureMethodType createSignatureMethod() {
        SignatureMethodType smt = new SignatureMethodType();
        smt.setAlgorithm(SignatureMethod.RSA_SHA1);
        return smt;
    }

    public SignatureType invoke() {
        signature.setSignedInfo(signedInfo);
        signedInfo.setCanonicalizationMethod(cm);
        signedInfo.setSignatureMethod(smt);
        signedInfo.getReference().add(createEnvelopedReference());
        signature.setSignatureValue(signatureValue);
        return signature;
    }


    private ReferenceType createEnvelopedReference() {
        ReferenceType r = new ReferenceType();
        r.setURI(EMPTY_STRING);
        r.setDigestMethod(createDigestMethod());
        r.setDigestValue(EMPTY_BYTES);
        r.setTransforms(createEnvelopedTransforms());
        return r;
    }

    private DigestMethodType createDigestMethod() {
        DigestMethodType dm = new DigestMethodType();
        dm.setAlgorithm(DigestMethod.SHA1);
        return dm;
    }

    private TransformsType createEnvelopedTransforms() {
        TransformsType tt = new TransformsType();
        tt.getTransform().add(createEnvelopedTransform());
        return tt;
    }

    private TransformType createEnvelopedTransform() {
        TransformType t = new TransformType();
        t.setAlgorithm(Transform.ENVELOPED);
        return t;
    }


}
