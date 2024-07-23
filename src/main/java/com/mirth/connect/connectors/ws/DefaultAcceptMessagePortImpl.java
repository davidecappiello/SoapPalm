
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.mirth.connect.connectors.ws;

import java.util.logging.Logger;
import jakarta.jws.WebService;

@WebService(
        serviceName = "DefaultAcceptMessageService",
        portName = "DefaultAcceptMessagePort",
        targetNamespace = "http://ws.connectors.connect.mirth.com/",
        wsdlLocation = "file:C:/Users/davide.cappiello/Desktop/SoapPalm/src/main/resources/Mirth/Mirth.wsdl",
        endpointInterface = "com.mirth.connect.connectors.ws.DefaultAcceptMessage")

public class DefaultAcceptMessagePortImpl implements DefaultAcceptMessage {

    private static final Logger LOG = Logger.getLogger(DefaultAcceptMessagePortImpl.class.getName());

    public String acceptMessage(String arg0) {
        LOG.info("Executing operation acceptMessage");
        System.out.println(arg0);
        try {
            String _return = "_return-388329968";
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
