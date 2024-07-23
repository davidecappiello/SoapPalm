package com.mirth.prometeo;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.message.*;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.parser.XMLParser;
import com.mirth.connect.connectors.ws.AcceptMessageResponse;
import com.mirth.prometeo.Entity.MessageEvent;
import com.mirth.prometeo.HL7Palm.Decoding.OMLDecoding;
import com.mirth.prometeo.HL7Palm.Decoding.ORLDecoding;
import com.mirth.prometeo.HL7Palm.Decoding.QBPDecoding;
import com.mirth.prometeo.HL7Palm.Message.*;
import com.mirth.prometeo.HL7Palm.Message.Custom.RSP_K11;
import com.mirth.prometeo.Repository.MessageEventRepository;
import com.mirth.prometeo.ServiceOMLO21.Event.MessageEventServiceOMLO21;
import com.mirth.prometeo.ServiceOMLO21.Segment.MessageSegmentServiceOMLO21;
import com.mirth.prometeo.ServiceORLO22.Event.MessageEventServiceORLO22;
import com.mirth.prometeo.ServiceORLO22.Segment.MessageSegmentServiceORLO22;
import com.mirth.prometeo.ServiceORMOO1.Event.MessageEventServiceORMOO1;
import com.mirth.prometeo.ServiceORMOO1.Segment.MessageSegmentServiceORMOO1;
import com.mirth.prometeo.ServiceORUR01.Event.MessageEventServiceORUR01;
import com.mirth.prometeo.ServiceORUR01.Segment.MessageSegmentServiceORUR01;
import com.mirth.prometeo.Socket.Service.HL7SocketClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    private static final Logger logger = LoggerFactory.getLogger(Util.class);
    static StringWriter sw = new StringWriter();
    static PrintWriter pw = new PrintWriter(sw);
    private final PipeParser pipeParser = new PipeParser();
    private final XMLParser xmlParser = new DefaultXMLParser();
    private static ACKResponse ackObject = new ACKResponse();
    private static ORLO22 orlObject = new ORLO22();
    private static ORM_O01 ormCreated = null;
    private static OMLO21 omlObject = new OMLO21();
    private static RSPK11 rspk11Object = new RSPK11();
    private static String finalResponseToPSPIPE = null;
    private static String finalResponseToPSXML = null;
    private static OML_O21 omlMessage = null;
    private static HL7Config hl7Config = null;

    public void insertLogRow(String log) {
        logger.info(log);
    }

    public String checkAndUpdateMessageType(String finalMessage) {
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

    private String extractCdataContent(String cdataMessage) throws Exception {
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

    public String extractMsg3Value(String message) throws Exception {
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

    private String updateMessageTypeTag(String message, String msg3Value) {
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

    public String modifyPid7Format(String xmlMessage) throws Exception {
        Pattern pattern = Pattern.compile(" {12}<PID.7>\n" +
                " {16}<TS.1>(.*)</TS.1>\n" +
                " {12}</PID.7>\n");
        Matcher matcher = pattern.matcher(xmlMessage);

        if (matcher.find()) {
            System.out.println(matcher.group(1));
            String dateOfBirth = matcher.group(1).substring(0, 8);
            System.out.println(dateOfBirth);
            return dateOfBirth;
        } else {
            Exception e = new Exception("TS.1 Null");
            e.printStackTrace(pw);
            logger.error(sw.toString());
            System.out.println("sw: "+sw.toString());
            throw new Exception("TS.1 Null");
        }
    }

    public void handleORM(String updatedMessage, ORMOO1 ormO01, String hl7Response, AcceptMessageResponse response,
                          String date, String param, MessageEventServiceOMLO21 messageEventServiceOMLO21,
                          MessageSegmentServiceOMLO21 messageSegmentServiceOMLO21, MessageEventServiceORMOO1 messageEventServiceORMOO1,
                          MessageSegmentServiceORMOO1 messageSegmentServiceORMOO1, MessageEventServiceORLO22 messageEventServiceORLO22,
                          MessageSegmentServiceORLO22 messageSegmentServiceORLO22) throws Exception {

        insertLogRow("Parametro ricevuto: " + param);
        ORM_O01 ormCreated = saveOMLO21AndORMOO1OnDatabase(updatedMessage, ormO01, date, messageEventServiceOMLO21,  messageEventServiceORMOO1, messageSegmentServiceOMLO21, messageSegmentServiceORMOO1);
        String finalMessagePIPE = ormO01.convertXMLToPipeFormat(ormCreated);
        insertLogRow("Invio via socket il messaggio a TD");
        insertLogRow(finalMessagePIPE);
        hl7Response = HL7SocketClientService.sendHL7Message(finalMessagePIPE);
        Message genericMessage = pipeParser.parse(hl7Response);
        insertLogRow(updatedMessage);
        OML_O21 omlMessage = (OML_O21) xmlParser.parse(updatedMessage);
        insertLogRow("Genero la risposta ACK inviata da TD");
        ACK ackMessage = ackObject.generateACKResponseORLO22(genericMessage, omlMessage);
        String finalResponseToPSPIPE = String.valueOf(orlObject.generateORL_O22FromORM(ackMessage, genericMessage, ormCreated));
        String finalResponseToPSXML = String.valueOf(orlObject.stringMessageToXML(finalResponseToPSPIPE));
        insertLogRow("Salvo l'ORL_O22 generato sul database locale");
        saveORLO22OnDatabase(finalResponseToPSPIPE, omlMessage, messageEventServiceORLO22, messageSegmentServiceORLO22);
        insertLogRow("Setto il return");
        response.setReturn(finalResponseToPSXML);
        insertLogRow(finalResponseToPSXML);
    }

    public void handleOML(String updatedMessage, OML_O21 omlCreated, OMLO21 oml_o21, String hl7Response, AcceptMessageResponse response, String date, String param,
                          MessageEventServiceOMLO21 messageEventServiceOMLO21, MessageSegmentServiceOMLO21 messageSegmentServiceOMLO21,
                          MessageEventServiceORLO22 messageEventServiceORLO22, MessageSegmentServiceORLO22 messageSegmentServiceORLO22,
                          MessageEventRepository messageEventRepository) throws Exception {

        insertLogRow("Parametro ricevuto: " + param);
        saveOMLO21Database(updatedMessage, messageEventServiceOMLO21, messageSegmentServiceOMLO21);
        omlCreated = OMLDecoding.decodeOML_XML(updatedMessage);
        OML_O21 omlForTD = omlObject.generateOML_021ForTD(omlCreated, date);
        String finalMessagePIPE = oml_o21.convertXMLToPipeFormat(omlForTD);
        insertLogRow("Invio via socket il messaggio a TD");
        hl7Response = HL7SocketClientService.sendHL7Message(finalMessagePIPE);
        insertLogRow(hl7Response);

        if (controllMSA(hl7Response) == true) {
            ORL_O22 orlFromTD = (ORL_O22) pipeParser.parse(hl7Response);
            saveORLO22OnDatabase(hl7Response, omlCreated, messageEventServiceORLO22, messageSegmentServiceORLO22);
            messageEventServiceOMLO21.updateLatestOMLO21RecordWithFillerOrderNumberAndStatus(orlFromTD);
            insertLogRow(updatedMessage);
            omlMessage = (OML_O21) xmlParser.parse(updatedMessage);
            insertLogRow("Genero la risposta ACK inviata da TD");
            finalResponseToPSPIPE = String.valueOf(orlObject.generateORL_O22FromORL(orlFromTD));
            finalResponseToPSXML = String.valueOf(orlObject.stringMessageToXML(finalResponseToPSPIPE));
        } else {
            Message genericMessage = pipeParser.parse(hl7Response);
            insertLogRow(updatedMessage);
            omlMessage = (OML_O21) xmlParser.parse(updatedMessage);
            insertLogRow("Genero la risposta ACK inviata da TD");
            ACK ackMessage = ackObject.generateACKResponseORLO22(genericMessage, omlMessage);
            finalResponseToPSPIPE = String.valueOf(orlObject.generateORL_O22FromOML(ackMessage, genericMessage, omlCreated));
            finalResponseToPSXML = String.valueOf(orlObject.stringMessageToXML(finalResponseToPSPIPE));
        }
        insertLogRow("Setto il return");
        response.setReturn(finalResponseToPSXML);
        insertLogRow(finalResponseToPSXML);
    }

    public void handleQBP(String updatedMessage, AcceptMessageResponse response) throws Exception {

        insertLogRow("Creazione del RSP_K11 di prova per lo ZET");
        QBP_Q11 qbp = QBPDecoding.decodeQBP_XML(updatedMessage);
        insertLogRow(String.valueOf(qbp));
        String msg2QBP = extractMsg2Value(updatedMessage);
        RSP_K11 rsp = rspk11Object.createEncodedMessage(msg2QBP, qbp);
        String rspXMl = rspk11Object.XmlEncodind(rsp);
        insertLogRow(rspXMl);
        response.setReturn(rspXMl);
    }

    public ORM_O01 saveOMLO21AndORMOO1OnDatabase(String finalMessage, ORMOO1 ormoo1, String date, MessageEventServiceOMLO21 messageEventServiceOMLO21,
                                                 MessageEventServiceORMOO1 messageEventServiceORMOO1, MessageSegmentServiceOMLO21 messageSegmentServiceOMLO21,
                                                 MessageSegmentServiceORMOO1 messageSegmentServiceORMOO1) {
        try {
            OML_O21 omlO21 = OMLDecoding.decodeOML_XML(finalMessage);
            MessageEvent messageEvent = messageEventServiceOMLO21.saveOMLO21Message(omlO21);
            messageSegmentServiceOMLO21.saveMSHMessageSegmentOMLO21(omlO21, messageEvent);
            messageSegmentServiceOMLO21.savePIDMessageSegmentOMLO21(omlO21, messageEvent);
            messageSegmentServiceOMLO21.savePV1MessageSegmentOMLO21(omlO21, messageEvent);
            messageSegmentServiceOMLO21.saveORDERBLOCKMessageOMLO21(omlO21, messageEvent);
            messageSegmentServiceOMLO21.saveTQ1MessageSegmentOMLO21(omlO21, messageEvent);

            ormCreated = ormoo1.generateORM_OO1(omlO21, date);
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

    public void saveOMLO21Database(String finalMessage, MessageEventServiceOMLO21 messageEventServiceOMLO21, MessageSegmentServiceOMLO21 messageSegmentServiceOMLO21) {
        try {
            OML_O21 omlO21 = OMLDecoding.decodeOML_XML(finalMessage);
            MessageEvent messageEvent = messageEventServiceOMLO21.saveOMLO21Message(omlO21);
            messageSegmentServiceOMLO21.saveMSHMessageSegmentOMLO21(omlO21, messageEvent);
            messageSegmentServiceOMLO21.savePIDMessageSegmentOMLO21(omlO21, messageEvent);
            messageSegmentServiceOMLO21.savePV1MessageSegmentOMLO21(omlO21, messageEvent);
            messageSegmentServiceOMLO21.saveORDERBLOCKMessageOMLO21(omlO21, messageEvent);
            messageSegmentServiceOMLO21.saveTQ1MessageSegmentOMLO21(omlO21, messageEvent);

        } catch (HL7Exception e) {
            e.printStackTrace();
        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveORLO22OnDatabase(String finalResponseToPS, OML_O21 omlO21, MessageEventServiceORLO22 messageEventServiceORLO22, MessageSegmentServiceORLO22 messageSegmentServiceORLO22) throws HL7Exception {

        ORL_O22 orlO22 = ORLDecoding.decodeORL_XML(finalResponseToPS);

        MessageEvent messageEvent = messageEventServiceORLO22.saveORLO22Message(orlO22, omlO21);
        messageSegmentServiceORLO22.saveMSHMessageSegmentORLO22(orlO22, messageEvent);
        messageSegmentServiceORLO22.saveMSAMessageSegmentORLO22(orlO22, messageEvent);
        messageSegmentServiceORLO22.saveORDERBLOCKMessageORLO22(orlO22, messageEvent);
        messageSegmentServiceORLO22.saveSPMMessageSegmentORLO22(orlO22, messageEvent);
    }

    public static boolean controllMSA(String hl7Message) throws HL7Exception {
        boolean flag = false;

        String[] segments = hl7Message.split("\r");
        String mshSegment = null;

        for (String segment : segments) {
            if (segment.startsWith("MSA")) {
                mshSegment = segment;
                break;
            }
        }

        if (mshSegment == null) {
            throw new HL7Exception("MSH segment not found in the HL7 message");
        }

        String[] fields = mshSegment.split("\\|");

        if (fields.length < 3) {
            throw new HL7Exception("Invalid MSH segment: missing required fields");
        }

        if(fields[1].equals("AA")){
            flag = true;
        } else if(fields[1].equals("AE") || fields[1].equals("AR")){
            flag = false;
        }

        return flag;
    }

    public static String extractMsg2Value(String message) throws Exception {
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

    public void saveORUR01Database(String hl7Message, MessageEventServiceORUR01 messageEventServiceORUR01, MessageSegmentServiceORUR01 messageSegmentServiceORUR01) {
        try {
            Parser parser = new PipeParser();
            ORU_R01 oruR01 = (ORU_R01) parser.parse(hl7Message);
            MessageEvent messageEvent = messageEventServiceORUR01.saveORUR01Message(oruR01);
            messageSegmentServiceORUR01.saveMSHMessageSegmentORUR01(oruR01, messageEvent);
            messageSegmentServiceORUR01.savePIDMessageSegmentORUR01(oruR01, messageEvent);
            messageSegmentServiceORUR01.savePV1MessageSegmentORUR01(oruR01, messageEvent);
            messageSegmentServiceORUR01.saveORDERBLOCKMessageORUR01(oruR01, messageEvent);
        } catch (HL7Exception e) {
            e.printStackTrace();
        }
    }

    public void saveOMLO21Database2(OML_O21 omlO21, MessageEventServiceOMLO21 messageEventServiceOMLO21, MessageSegmentServiceOMLO21 messageSegmentServiceOMLO21) {
        try {
            MessageEvent messageEvent = messageEventServiceOMLO21.saveOMLO21MessageCheckStatus(omlO21);
            messageSegmentServiceOMLO21.saveMSHMessageSegmentOMLO21(omlO21, messageEvent);
            messageSegmentServiceOMLO21.savePIDMessageSegmentOMLO21(omlO21, messageEvent);
            messageSegmentServiceOMLO21.savePV1MessageSegmentOMLO21(omlO21, messageEvent);
            messageSegmentServiceOMLO21.saveORDERBLOCKMessageOMLO21(omlO21, messageEvent);
        } catch (HL7Exception e) {
            e.printStackTrace();
        }
    }

}
