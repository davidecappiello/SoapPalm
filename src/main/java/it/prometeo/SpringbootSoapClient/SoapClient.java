package it.prometeo.SpringbootSoapClient;

import com.mirth.connect.connectors.ws.*;
import it.prometeo.Configuration.HL7Config;
import it.prometeo.HL7Palm.SegmentFactory.SegmentFactoryACKResponseORLO22;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SoapClient {

    private static HL7Config hl7Config = null;

    @Autowired
    public SoapClient(HL7Config hl7Config) {
        SoapClient.hl7Config = hl7Config;
    }

    public void sendAcceptMessage(String omlFinal) throws MalformedURLException {

        DefaultAcceptMessageService defaultAcceptMessageService = new DefaultAcceptMessageService(new URL(hl7Config.getWsdlURLPS()));
        //DefaultAcceptMessageService defaultAcceptMessageService = new DefaultAcceptMessageService(new URL("http://weplus206:8080/?WSDL"));
        //DefaultAcceptMessageService defaultAcceptMessageService = new DefaultAcceptMessageService(new URL("http://weplus162:8092/?WSDL"));
        DefaultAcceptMessage defaultAcceptMessage = defaultAcceptMessageService.getDefaultAcceptMessagePort();
        String omlCorrected = removeXMLDeclaration(omlFinal);
        defaultAcceptMessage.acceptMessage(omlCorrected);
    }

    public void sendAcceptMessagetransfusion(String omlFinal) throws MalformedURLException {

        //DefaultAcceptMessageService defaultAcceptMessageService = new DefaultAcceptMessageService(new URL("http://desktop-7hnt1ju:8190/?WSDL"));
        //DefaultAcceptMessageService defaultAcceptMessageService = new DefaultAcceptMessageService(new URL("http://weplus206:8080/?WSDL"));
        //DefaultAcceptMessageService defaultAcceptMessageService = new DefaultAcceptMessageService(new URL("http://weplus162:8190/?WSDL"));
        DefaultAcceptMessageService defaultAcceptMessageService = new DefaultAcceptMessageService(new URL(hl7Config.getWsdlURLTransfusion()));
        DefaultAcceptMessage defaultAcceptMessage = defaultAcceptMessageService.getDefaultAcceptMessagePort();
        String omlCorrected = removeXMLDeclaration(omlFinal);
        defaultAcceptMessage.acceptMessage(omlCorrected);
    }

    public static String removeXMLDeclaration(String xml) {
        String patternStr = "<\\?xml version=\"1\\.0\" encoding=\"UTF-8\"\\?>";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(xml);

        return matcher.replaceAll("");
    }
}
