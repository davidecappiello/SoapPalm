package com.mirth.prometeo.SpringbootSoapClient;

import com.mirth.connect.connectors.ws.*;
import org.springframework.stereotype.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SoapClient {

    public void sendAcceptMessage(String omlFinal) throws MalformedURLException {

//        DefaultAcceptMessageService defaultAcceptMessageService = new DefaultAcceptMessageService(new URL("http://weplus206:8080/?WSDL"));
       DefaultAcceptMessageService defaultAcceptMessageService = new DefaultAcceptMessageService(new URL("http://weplus162:8080/?WSDL"));
        DefaultAcceptMessage defaultAcceptMessage = defaultAcceptMessageService.getDefaultAcceptMessagePort();
        String omlCorrected = removeXMLDeclaration(omlFinal);
        String omlToSend = wrapInCdata(omlCorrected);
        defaultAcceptMessage.acceptMessage(omlToSend);
    }

    public static String removeXMLDeclaration(String xml) {
        String patternStr = "<\\?xml version=\"1\\.0\" encoding=\"UTF-8\"\\?>";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(xml);

        return matcher.replaceAll("");
    }

    private String wrapInCdata(String omlFinal) {
        return "<![CDATA[\n" + omlFinal + "]]>";
    }
}
