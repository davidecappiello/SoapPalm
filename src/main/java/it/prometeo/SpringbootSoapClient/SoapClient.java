package it.prometeo.SpringbootSoapClient;

import com.mirth.connect.connectors.ws.*;
import org.springframework.stereotype.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SoapClient {

    public void sendAcceptMessage(String omlFinal) throws MalformedURLException {

        //DefaultAcceptMessageService defaultAcceptMessageService = new DefaultAcceptMessageService(new URL("http://desktop-7hnt1ju:8190/?WSDL"));
        //DefaultAcceptMessageService defaultAcceptMessageService = new DefaultAcceptMessageService(new URL("http://weplus206:8080/?WSDL"));
        DefaultAcceptMessageService defaultAcceptMessageService = new DefaultAcceptMessageService(new URL("http://weplus162:8190/?WSDL"));
        DefaultAcceptMessage defaultAcceptMessage = defaultAcceptMessageService.getDefaultAcceptMessagePort();
        String omlCorrected = removeXMLDeclaration(omlFinal);
        defaultAcceptMessage.acceptMessage(omlCorrected);
    }

    public void sendMdmMessage(String mdmFinal) throws MalformedURLException {

//        DefaultAcceptMessageService defaultAcceptMessageService = new DefaultAcceptMessageService(new URL("http://desktop-7hnt1ju:8290/?WSDL"));
        //DefaultAcceptMessageService defaultAcceptMessageService = new DefaultAcceptMessageService(new URL("http://weplus206:8080/?WSDL"));
        DefaultAcceptMessageService defaultAcceptMessageService = new DefaultAcceptMessageService(new URL("http://weplus162:8290/?WSDL"));
        DefaultAcceptMessage defaultAcceptMessage = defaultAcceptMessageService.getDefaultAcceptMessagePort();
        String mdmCorrected = removeXMLDeclaration(mdmFinal);
        defaultAcceptMessage.acceptMessage(mdmCorrected);
    }

    public static String removeXMLDeclaration(String xml) {
        String patternStr = "<\\?xml version=\"1\\.0\" encoding=\"UTF-8\"\\?>";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(xml);

        return matcher.replaceAll("");
    }
}
