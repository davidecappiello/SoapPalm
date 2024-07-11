//
// Questo file è stato generato dall'Eclipse Implementation of JAXB, v3.0.0 
// Vedere https://eclipse-ee4j.github.io/jaxb-ri 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2024.07.11 alle 10:44:47 AM CEST 
//


package com.mirth.connect.connectors.ws;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.mirth.connect.connectors.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AcceptMessage_QNAME = new QName("http://ws.connectors.connect.mirth.com/", "acceptMessage");
    private final static QName _AcceptMessageResponse_QNAME = new QName("http://ws.connectors.connect.mirth.com/", "acceptMessageResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.mirth.connect.connectors.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AcceptMessage }
     * 
     */
    public AcceptMessage createAcceptMessage() {
        return new AcceptMessage();
    }

    /**
     * Create an instance of {@link AcceptMessageResponse }
     * 
     */
    public AcceptMessageResponse createAcceptMessageResponse() {
        return new AcceptMessageResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcceptMessage }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AcceptMessage }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.connectors.connect.mirth.com/", name = "acceptMessage")
    public JAXBElement<AcceptMessage> createAcceptMessage(AcceptMessage value) {
        return new JAXBElement<AcceptMessage>(_AcceptMessage_QNAME, AcceptMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcceptMessageResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AcceptMessageResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://ws.connectors.connect.mirth.com/", name = "acceptMessageResponse")
    public JAXBElement<AcceptMessageResponse> createAcceptMessageResponse(AcceptMessageResponse value) {
        return new JAXBElement<AcceptMessageResponse>(_AcceptMessageResponse_QNAME, AcceptMessageResponse.class, null, value);
    }

}
