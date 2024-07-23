package com.mirth.connect.connectors.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;

@WebService(targetNamespace = "http://ws.connectors.connect.mirth.com/", name = "DefaultAcceptMessage")
@XmlSeeAlso({ObjectFactory.class})
public interface DefaultAcceptMessage {

    @WebMethod
    @Action(input = "http://ws.connectors.connect.mirth.com/DefaultAcceptMessage/acceptMessageRequest", output = "http://ws.connectors.connect.mirth.com/DefaultAcceptMessage/acceptMessageResponse")
    @RequestWrapper(localName = "acceptMessage", targetNamespace = "http://ws.connectors.connect.mirth.com/", className = "com.mirth.connect.connectors.ws.AcceptMessage")
    @ResponseWrapper(localName = "acceptMessageResponse", targetNamespace = "http://ws.connectors.connect.mirth.com/", className = "com.mirth.connect.connectors.ws.AcceptMessageResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String acceptMessage(

            @WebParam(name = "arg0", targetNamespace = "")
            java.lang.String arg0
    );
}
