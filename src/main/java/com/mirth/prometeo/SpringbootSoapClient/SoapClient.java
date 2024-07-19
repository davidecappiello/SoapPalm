package com.mirth.prometeo.SpringbootSoapClient;

import com.mirth.connect.connectors.ws.*;
import org.springframework.stereotype.Service;

@Service
public class SoapClient {

    public void sendAcceptMessage(String omlFinal)

    {
        DefaultAcceptMessageService defaultAcceptMessageService = new DefaultAcceptMessageService();
        DefaultAcceptMessage defaultAcceptMessage = defaultAcceptMessageService.getDefaultAcceptMessagePort();

       String omlToSend = wrapInCdata(omlFinal);
       defaultAcceptMessage.acceptMessage(omlToSend);
    }

    private String wrapInCdata(String omlFinal) {
        return "<![CDATA[\n" + omlFinal + "]]>";
    }
}
