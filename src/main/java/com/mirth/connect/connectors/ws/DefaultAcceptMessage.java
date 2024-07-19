
package com.mirth.connect.connectors.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "DefaultAcceptMessage", targetNamespace = "http://ws.connectors.connect.mirth.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface DefaultAcceptMessage {

    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "acceptMessage", targetNamespace = "http://ws.connectors.connect.mirth.com/", className = "com.mirth.connect.connectors.ws.AcceptMessage")
    @ResponseWrapper(localName = "acceptMessageResponse", targetNamespace = "http://ws.connectors.connect.mirth.com/", className = "com.mirth.connect.connectors.ws.AcceptMessageResponse")
    @Action(input = "http://ws.connectors.connect.mirth.com/DefaultAcceptMessage/acceptMessageRequest", output = "http://ws.connectors.connect.mirth.com/DefaultAcceptMessage/acceptMessageResponse")
    public String acceptMessage(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
