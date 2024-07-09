//
// Questo file è stato generato dall'Eclipse Implementation of JAXB, v3.0.0 
// Vedere https://eclipse-ee4j.github.io/jaxb-ri 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2024.07.04 alle 07:02:47 PM CEST 
//


package com.mirth.connect.connectors.ws;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    private final static QName _AcceptMessage_QNAME = new QName("http://ws.connectors.connect.mirth.com/", "acceptMessage");
    private final static QName _AcceptMessageResponse_QNAME = new QName("http://ws.connectors.connect.mirth.com/", "acceptMessageResponse");

    public ObjectFactory() {
    }

    public AcceptMessage createAcceptMessage() {
        return new AcceptMessage();
    }

    public AcceptMessageResponse createAcceptMessageResponse() {
        return new AcceptMessageResponse();
    }

    @XmlElementDecl(namespace = "http://ws.connectors.connect.mirth.com/", name = "acceptMessage")
    public JAXBElement<AcceptMessage> createAcceptMessage(AcceptMessage value) {
        return new JAXBElement<AcceptMessage>(_AcceptMessage_QNAME, AcceptMessage.class, null, value);
    }

    @XmlElementDecl(namespace = "http://ws.connectors.connect.mirth.com/", name = "acceptMessageResponse")
    public JAXBElement<AcceptMessageResponse> createAcceptMessageResponse(AcceptMessageResponse value) {
        return new JAXBElement<AcceptMessageResponse>(_AcceptMessageResponse_QNAME, AcceptMessageResponse.class, null, value);
    }

}
