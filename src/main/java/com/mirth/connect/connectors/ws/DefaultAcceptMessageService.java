package com.mirth.connect.connectors.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceFeature;
import jakarta.xml.ws.Service;

@WebServiceClient(name = "DefaultAcceptMessageService",
        wsdlLocation = "file:C:/Prometeo/SoapPalm/src/main/resources/Mirth/Mirth.wsdl",
        //wsdlLocation = "file:C:/Users/davide.cappiello/Desktop/SoapPalm/src/main/resources/Mirth/Mirth.wsdl",
        targetNamespace = "http://ws.connectors.connect.mirth.com/")
public class DefaultAcceptMessageService extends Service {

    public static final URL WSDL_LOCATION;
    public static final QName SERVICE = new QName("http://ws.connectors.connect.mirth.com/", "DefaultAcceptMessageService");
    public static final QName DefaultAcceptMessagePort = new QName("http://ws.connectors.connect.mirth.com/", "DefaultAcceptMessagePort");
    static {
        URL url = null;
        try {
            url = new URL("file:C:/Prometeo/SoapPalm/src/main/resources/Mirth/Mirth.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(DefaultAcceptMessageService.class.getName())
                    .log(java.util.logging.Level.INFO,
                            "Can not initialize the default wsdl from {0}", "file:/C:/Development/Eclipse/TestSoapCXF/src/main/webapp/wsdl/Mirth.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public DefaultAcceptMessageService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public DefaultAcceptMessageService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DefaultAcceptMessageService() {
        super(WSDL_LOCATION, SERVICE);
    }

    public DefaultAcceptMessageService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public DefaultAcceptMessageService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public DefaultAcceptMessageService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    @WebEndpoint(name = "DefaultAcceptMessagePort")
    public DefaultAcceptMessage getDefaultAcceptMessagePort() {
        return super.getPort(DefaultAcceptMessagePort, DefaultAcceptMessage.class);
    }

    @WebEndpoint(name = "DefaultAcceptMessagePort")
    public DefaultAcceptMessage getDefaultAcceptMessagePort(WebServiceFeature... features) {
        return super.getPort(DefaultAcceptMessagePort, DefaultAcceptMessage.class, features);
    }

}
