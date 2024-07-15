package com.mirth.prometeo.SpringbootSoapServer.Endpoint;


import Prometeo.HL7Palm.Decoding.OMLDecoding;
import Prometeo.HL7Palm.Decoding.ORLDecoding;
import Prometeo.HL7Palm.Decoding.QBPDecoding;
import Prometeo.HL7Palm.Message.ACKResponse;
import Prometeo.HL7Palm.Message.Custom.RSP_K11;
import Prometeo.HL7Palm.Message.ORLO22;
import Prometeo.HL7Palm.Message.ORMOO1;
import Prometeo.HL7Palm.Message.RSPK11;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.message.*;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.parser.XMLParser;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Endpoint
public class MessageEndpoint {

    private final PipeParser pipeParser = new PipeParser();

    private final XMLParser xmlParser = new DefaultXMLParser();
    private static final String NAMESPACE_URI = "http://ws.connectors.connect.mirth.com/";

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

    private final ObjectFactory factory;

    private static final Logger logger = LoggerFactory.getLogger(MessageEndpoint.class);

    static StringWriter sw = new StringWriter();
    static PrintWriter pw = new PrintWriter(sw);

    public MessageEndpoint() {
        factory = new ObjectFactory();
    }

    @PayloadRoot(namespace = NAMESPACE_URI,
            localPart = "acceptMessage")
    @ResponsePayload
    public JAXBElement<AcceptMessageResponse> acceptMessage(@RequestPayload JAXBElement<AcceptMessage> acceptMessage) {
        AcceptMessageResponse response = new AcceptMessageResponse();
        String finalMessage = acceptMessage.getValue().getArg0();
        System.out.println("Ricevo il messaggio HL7 dal pronto soccorso");
        System.out.println(finalMessage);
        ORMOO1 ormO01 = new ORMOO1();

        try {
            System.out.println("Controllo i tag esterni al messaggio per capire se sono uguali al MessageTypeStructure del messaggio HL7");
            String updatedMessage = checkAndUpdateMessageType(finalMessage);
            String msg3Value = extractMsg3Value(finalMessage);


            if (acceptMessage.getValue() != null && acceptMessage.getValue().getArg0() != null) {



                try {
                    if (msg3Value.equals("OML_O21")) {
                        System.out.println("Salvo l'OML_O21 inviato dal PS e l'ORM_O01 generato da noi sul database locale");
                        ORM_O01 ormCreated = saveOMLO21AndORMOO1OnDatabase(updatedMessage, ormO01);
                        String finalMessagePIPE = ormO01.convertXMLToPipeFormat(ormCreated);


                        System.out.println("Invio via socket il messaggio a TD");
                        String hl7Response = HL7SocketClientService.sendHL7Message(finalMessagePIPE);
                        Message genericMessage = pipeParser.parse(hl7Response);
                        System.out.println(updatedMessage);
                        OML_O21 omlMessage = (OML_O21) xmlParser.parse(updatedMessage);
                        ACKResponse object = new ACKResponse();
                        System.out.println("Genero la risposta ACK inviata da TD");
                        ACK ackMessage = object.generateACKResponseORLO22(genericMessage, omlMessage);
                        ORLO22 object2 = new ORLO22();
                        String finalResponseToPSPIPE = String.valueOf(object2.generateORL_O22(ackMessage, genericMessage, ormCreated));
                        String finalResponseToPSXML = String.valueOf(object2.stringMessageToXML(finalResponseToPSPIPE));
                        System.out.println("Salvo l'ORL_O22 generato sul database locale");
                        saveORLO22OnDatabase(finalResponseToPSPIPE, omlMessage);
                        System.out.println("Setto il return");
                        response.set_Return(finalResponseToPSXML);
                        System.out.println(finalResponseToPSXML);
                    } else if(msg3Value.equals("QBP_Q11")){



                        System.out.println("Creazione del RSP_K11 di prova per lo ZET");
//                        QBPQ11 qbpQ11 = new QBPQ11();
                        QBP_Q11 qbp = QBPDecoding.decodeQBP_XML(updatedMessage);
//                        QBP_Q11 qbp = qbpQ11.crateQBP();
                        //String qbpXMl = qbpQ11.XmlEncodind(qbp);
//                        QBPDecoding qbpInput = new QBPDecoding();
//                        QBP_Q11 qbpQ11 = qbpInput.decodeQBP_XML(updatedMessage);

                        System.out.println(qbp);

                        String msg2QBP = extractMsg2Value(updatedMessage);
                        RSPK11 rspk11 = new RSPK11();
                        RSP_K11 rsp = rspk11.createEncodedMessage(msg2QBP, qbp);
                        String rspXMl = rspk11.XmlEncodind(rsp);
                        System.out.println(rspXMl);

                    }
                } catch (Exception e) {
                    e.printStackTrace(pw);
                    logger.error(sw.toString());
                    System.out.println("sw: "+sw.toString());

                    response.set_Return("Message processed but failed to send via socket: " + e.getMessage());
                }

            } else {
                response.set_Return("Received acceptMessage is null or its value is null.");
            }


            System.out.println("Creo un oggetto acceptMessageResponse e rispondo al PS");
            return factory.createAcceptMessageResponse(response);

        } catch (Exception e) {
            e.printStackTrace(pw);
            logger.error(sw.toString());
            System.out.println("sw: "+sw.toString());
            throw new RuntimeException(e);
        }

    }

    public static String checkAndUpdateMessageType(String finalMessage) {
        try {
            String message = extractCdataContent(finalMessage);
            String msg3Value = extractMsg3Value(finalMessage);
            return updateMessageTypeTag(finalMessage, msg3Value);
        } catch (Exception e) {
            e.printStackTrace(pw);
            logger.error(sw.toString());
            System.out.println("sw: "+sw.toString());
            throw new RuntimeException(e);
        }

    }

    private static String extractCdataContent(String cdataMessage) throws Exception {
        int start = cdataMessage.indexOf("<![CDATA[") + "<![CDATA[".length();
        int end = cdataMessage.indexOf(">");

        if (start < 0 || end < 0) {
            Exception e = new Exception("Invalid CDATA section");
            e.printStackTrace(pw);
            logger.error(sw.toString());
            System.out.println("sw: "+sw.toString());
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
            Exception e = new Exception("MSG.3 value not found in MSH");
            e.printStackTrace(pw);
            logger.error(sw.toString());
            System.out.println("sw: "+sw.toString());
            throw new Exception("MSG.3 value not found in MSH");
        }
    }

    private static String extractMsg2Value(String message) throws Exception {
        Pattern pattern = Pattern.compile("<MSG.2>(.*)</MSG.2>");
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            System.out.println(matcher.group(1));
            return matcher.group(1);
        } else {
            Exception e = new Exception("MSG.2 value not found in MSH");
            e.printStackTrace(pw);
            logger.error(sw.toString());
            System.out.println("sw: "+sw.toString());
            throw new Exception("MSG.2 value not found in MSH");
        }
    }

    private static String updateMessageTypeTag(String message, String msg3Value) {
        Pattern pattern = Pattern.compile("<(\\w+:)?(\\w+)(\\s+xmlns=\"[^\"]+\")?>");
        Matcher matcher = pattern.matcher(message);
        try {
            if (matcher.find()) {
                String tagName = matcher.group(2);
                String uri = matcher.group(3);
                if (!tagName.equals(msg3Value)) {
                    String newTag = (matcher.group(1) != null ? matcher.group(1) : "") + msg3Value;

                    if(uri != null && uri.equals(" xmlns=\"urn:hl7-org:v2xml\"")) {
                        String updatedMessage = matcher.replaceFirst("<" + newTag + (matcher.group(3) != null ? matcher.group(3) : "") + ">");
                        updatedMessage = updatedMessage.replaceAll("</" + tagName + ">", "</" + newTag + ">");
                        return updatedMessage;
                    } else if (uri != null && !uri.equals(" xmlns=\"urn:hl7-org:v2xml\"")) {
                        uri = " xmlns=\"urn:hl7-org:v2xml\"";
                    } else if (uri == null) {
                        String updatedMessage = matcher.replaceFirst("<" + newTag + (matcher.group(3) != null ? matcher.group(3) : "") + " xmlns='urn:hl7-org:v2xml'" + ">");
                        updatedMessage = updatedMessage.replaceAll("</" + tagName + ">", "</" + newTag + ">");
                        return updatedMessage;
                    }
                } else {
                    String newTag = (matcher.group(1) != null ? matcher.group(1) : "") + msg3Value;

                    if (uri != null && !uri.equals(" xmlns=\"urn:hl7-org:v2xml\"")) {
                        uri = " xmlns=\"urn:hl7-org:v2xml\"";
                    } else if (uri == null) {
                        String updatedMessage = matcher.replaceFirst("<" + newTag + (matcher.group(3) != null ? matcher.group(3) : "") + " xmlns='urn:hl7-org:v2xml'" + ">");
                        updatedMessage = updatedMessage.replaceAll("</" + tagName + ">", "</" + newTag + ">");
                        return updatedMessage;
                    }
                }
            }

            return message;
        } catch (Exception e){
            e.printStackTrace(pw);
            logger.error(sw.toString());
            System.out.println("sw: "+sw.toString());
            throw new RuntimeException(e);
        }

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
