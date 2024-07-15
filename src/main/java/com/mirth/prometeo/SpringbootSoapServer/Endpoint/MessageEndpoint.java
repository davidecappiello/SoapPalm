package com.mirth.prometeo.SpringbootSoapServer.Endpoint;

import Prometeo.HL7Palm.Decoding.OMLDecoding;
import Prometeo.HL7Palm.Decoding.ORLDecoding;
import Prometeo.HL7Palm.Message.ACKResponse;
import Prometeo.HL7Palm.Message.ORLO22;
import Prometeo.HL7Palm.Message.ORMOO1;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.message.ACK;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.message.ORL_O22;
import ca.uhn.hl7v2.model.v25.message.ORM_O01;
import ca.uhn.hl7v2.parser.*;
import com.mirth.prometeo.Entity.MessageEvent;
import com.mirth.prometeo.ServiceOMLO21.Event.MessageEventServiceOMLO21;
import com.mirth.prometeo.ServiceOMLO21.Segment.MessageSegmentServiceOMLO21;
import com.mirth.prometeo.ServiceORLO22.Event.MessageEventServiceORLO22;
import com.mirth.prometeo.ServiceORLO22.Segment.MessageSegmentServiceORLO22;
import com.mirth.prometeo.ServiceORMOO1.Event.MessageEventServiceORMOO1;
import com.mirth.prometeo.ServiceORMOO1.Segment.MessageSegmentServiceORMOO1;
import com.mirth.prometeo.Socket.Service.HL7SocketClientService;
import com.mirth.connect.connectors.ws.AcceptMessage;
import com.mirth.connect.connectors.ws.AcceptMessageResponse;
import com.mirth.connect.connectors.ws.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import jakarta.xml.bind.JAXBElement;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Endpoint
public class MessageEndpoint {

    private final PipeParser pipeParser = new PipeParser();
    private final XMLParser xmlParser = new DefaultXMLParser(new CanonicalModelClassFactory("2.5"));
    private static final String NAMESPACE_URI = "http://ws.connectors.connect.mirth.com/";
    private final ObjectFactory factory;
    public MessageEndpoint() {
        factory = new ObjectFactory();
    }

    @Autowired
    private MessageEventServiceOMLO21 messageEventServiceOMLO21;
    @Autowired
    private MessageEventServiceORMOO1 messageEventServiceORMOO1;
    @Autowired
    private MessageEventServiceORLO22 messageEventServiceORLO22;
    @Autowired
    private MessageSegmentServiceOMLO21 messageSegmentServiceOMLO21;
    @Autowired
    private MessageSegmentServiceORMOO1 messageSegmentServiceORMOO1;
    @Autowired
    private MessageSegmentServiceORLO22 messageSegmentServiceORLO22;

    @PayloadRoot(namespace = NAMESPACE_URI,
            localPart = "acceptMessage")
    @ResponsePayload
    public JAXBElement<AcceptMessageResponse> acceptMessage(@RequestPayload JAXBElement<AcceptMessage> acceptMessage) throws Exception {

        AcceptMessageResponse response = new AcceptMessageResponse();
        String finalMessage = acceptMessage.getValue().getArg0();

        String updatedMessage = checkAndUpdateMessageType(finalMessage);

        if (acceptMessage.getValue() != null && acceptMessage.getValue().getArg0() != null) {

            ORMOO1 ormO01 = new ORMOO1();

            ORM_O01 ormCreated = saveOMLO21AndORMOO1OnDatabase(updatedMessage, ormO01);
            String finalMessagePIPE = ormO01.convertXMLToPipeFormat(ormCreated);

            try {
                String hl7Response = HL7SocketClientService.sendHL7Message(finalMessagePIPE);
                Message genericMessage = pipeParser.parse(hl7Response);
                OML_O21 omlMessage = (OML_O21) xmlParser.parse(updatedMessage);
                ACKResponse object = new ACKResponse();
                ACK ackMessage = object.generateACKResponseORLO22(genericMessage, omlMessage);
                ORLO22 object2 = new ORLO22();
                String finalResponseToPSPIPE = String.valueOf(object2.generateORL_O22(ackMessage, genericMessage, ormCreated));
                String finalResponseToPSXML = String.valueOf(object2.stringMessageToXML(finalResponseToPSPIPE));
                saveORLO22OnDatabase(finalResponseToPSPIPE, omlMessage);
                response.setReturn(finalResponseToPSXML);
            } catch (Exception e) {
                e.printStackTrace();
                response.setReturn("Message processed but failed to send via socket: " + e.getMessage());
            }

        } else {
            response.setReturn("Received acceptMessage is null or its value is null.");
        }

        return factory.createAcceptMessageResponse(response);
    }

    public static String checkAndUpdateMessageType(String finalMessage) throws Exception {

        String message = extractCdataContent(finalMessage);
        String msg3Value = extractMsg3Value(finalMessage);
        return updateMessageTypeTag(finalMessage, msg3Value);
    }

    private static String extractCdataContent(String cdataMessage) throws Exception {
        int start = cdataMessage.indexOf("<![CDATA[") + "<![CDATA[".length();
        int end = cdataMessage.indexOf("x");

        if (start < 0 || end < 0) {
            throw new Exception("Invalid CDATA section");
        }

        System.out.println(cdataMessage.substring(start, end));

        return cdataMessage.substring(start, end);
    }

    private static String extractMsg3Value(String message) throws Exception {
        Pattern pattern = Pattern.compile("<MSG.3>(.*)</MSG.3>");
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            System.out.println(matcher.group(1));
            return matcher.group(1);
        } else {
            throw new Exception("MSG.3 value not found in MSH");
        }
    }

    private static String updateMessageTypeTag(String message, String msg3Value) {
        Pattern pattern = Pattern.compile("<(\\w+:)?(\\w+)(\\s+xmlns=\"[^\"]+\")?>");
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            String tagName = matcher.group(2);
            if (!tagName.equals(msg3Value)) {
                String newTag = (matcher.group(1) != null ? matcher.group(1) : "") + msg3Value;
                String updatedMessage = matcher.replaceFirst("<" + newTag + (matcher.group(3) != null ? matcher.group(3) : "") + ">");
                updatedMessage = updatedMessage.replaceAll("</" + tagName + ">", "</" + newTag + ">");

                return updatedMessage;
            }
        }

        return message;
    }

    public ORM_O01 saveOMLO21AndORMOO1OnDatabase(String finalMessage, ORMOO1 ormoo1) {
        ORM_O01 ormCreated = null;
        try {
            OML_O21 omlO21 = OMLDecoding.decodeOML_XML(finalMessage);
            MessageEvent messageEvent = messageEventServiceOMLO21.saveOMLO21Message(finalMessage, omlO21);
            messageSegmentServiceOMLO21.saveMSHMessageSegmentOMLO21(omlO21, messageEvent);
            messageSegmentServiceOMLO21.savePIDMessageSegmentOMLO21(omlO21, messageEvent);
            messageSegmentServiceOMLO21.savePD1MessageSegmentOMLO21(omlO21, messageEvent);
            messageSegmentServiceOMLO21.savePV1MessageSegmentOMLO21(omlO21, messageEvent);
            messageSegmentServiceOMLO21.saveORDERBLOCKMessageOMLO21(omlO21, messageEvent);
            messageSegmentServiceOMLO21.saveTQ1MessageSegmentOMLO21(omlO21, messageEvent);

            ormCreated = ormoo1.generateORM_OO1(omlO21);
            MessageEvent messageEvent2 = messageEventServiceORMOO1.saveORMOO1Message(ormCreated, omlO21);
            messageSegmentServiceORMOO1.saveMSHMessageSegmentORMOO1(ormCreated, messageEvent2);
            messageSegmentServiceORMOO1.savePIDMessageSegmentORMOO1(ormCreated, messageEvent2);
            messageSegmentServiceORMOO1.savePV1MessageSegmentORMOO1(ormCreated, messageEvent2);
            messageSegmentServiceORMOO1.saveORDERBLOCKMessageORMOO1(ormCreated, messageEvent2);

        } catch (HL7Exception | IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return ormCreated;
    }

    public void saveORLO22OnDatabase(String finalResponseToPS, OML_O21 omlO21) throws HL7Exception, IOException, ParserConfigurationException, SAXException {

        ORL_O22 orlO22 = ORLDecoding.decodeORL_XML(finalResponseToPS);

        MessageEvent messageEvent = messageEventServiceORLO22.saveORLO22Message(orlO22, omlO21);
        messageSegmentServiceORLO22.saveMSHMessageSegmentORLO22(orlO22, messageEvent);
        messageSegmentServiceORLO22.saveMSAMessageSegmentORLO22(orlO22, messageEvent);
        messageSegmentServiceORLO22.saveORDERBLOCKMessageORLO22(orlO22, messageEvent);
    }
}
