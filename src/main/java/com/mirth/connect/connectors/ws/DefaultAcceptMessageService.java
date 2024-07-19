package com.mirth.connect.connectors.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


@WebServiceClient(name = "DefaultAcceptMessageService", targetNamespace = "http://ws.connectors.connect.mirth.com/", wsdlLocation = "file:/C:/Prometeo/SoapClientWSDL/Mirth.wsdl")
public class DefaultAcceptMessageService
    extends Service
{

    private final static URL DEFAULTACCEPTMESSAGESERVICE_WSDL_LOCATION;
    private final static WebServiceException DEFAULTACCEPTMESSAGESERVICE_EXCEPTION;
    private final static QName DEFAULTACCEPTMESSAGESERVICE_QNAME = new QName("http://ws.connectors.connect.mirth.com/", "DefaultAcceptMessageService");
    private final static String URL_WSDL = "http://weplus206:8080/?WSDL";

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL(URL_WSDL);
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        DEFAULTACCEPTMESSAGESERVICE_WSDL_LOCATION = url;
        DEFAULTACCEPTMESSAGESERVICE_EXCEPTION = e;
    }

    public DefaultAcceptMessageService() {
        super(__getWsdlLocation(), DEFAULTACCEPTMESSAGESERVICE_QNAME);
    }

    public DefaultAcceptMessageService(WebServiceFeature... features) {
        super(__getWsdlLocation(), DEFAULTACCEPTMESSAGESERVICE_QNAME, features);
    }

    public DefaultAcceptMessageService(URL wsdlLocation) {
        super(wsdlLocation, DEFAULTACCEPTMESSAGESERVICE_QNAME);
    }

    public DefaultAcceptMessageService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, DEFAULTACCEPTMESSAGESERVICE_QNAME, features);
    }

    public DefaultAcceptMessageService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DefaultAcceptMessageService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    @WebEndpoint(name = "DefaultAcceptMessagePort")
    public DefaultAcceptMessage getDefaultAcceptMessagePort() {
        return super.getPort(new QName("http://ws.connectors.connect.mirth.com/", "DefaultAcceptMessagePort"), DefaultAcceptMessage.class);
    }

    @WebEndpoint(name = "DefaultAcceptMessagePort")
    public DefaultAcceptMessage getDefaultAcceptMessagePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.connectors.connect.mirth.com/", "DefaultAcceptMessagePort"), DefaultAcceptMessage.class, features);
    }

    private static URL __getWsdlLocation() {
        if (DEFAULTACCEPTMESSAGESERVICE_EXCEPTION!= null) {
            throw DEFAULTACCEPTMESSAGESERVICE_EXCEPTION;
        }
        return DEFAULTACCEPTMESSAGESERVICE_WSDL_LOCATION;
    }

}
