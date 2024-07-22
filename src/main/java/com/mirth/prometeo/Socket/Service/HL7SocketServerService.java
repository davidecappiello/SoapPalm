package com.mirth.prometeo.Socket.Service;

import ca.uhn.hl7v2.model.v25.message.ACK;
import ca.uhn.hl7v2.model.v25.segment.MSH;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.XMLParser;
import com.mirth.prometeo.HL7Palm.Message.ACKResponse;
import com.mirth.prometeo.HL7Palm.Message.ORUR01;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.message.ORU_R01;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.PipeParser;
import com.mirth.prometeo.Entity.MessageEvent;
import com.mirth.prometeo.ServiceOMLO21.Event.MessageEventServiceOMLO21;
import com.mirth.prometeo.ServiceOMLO21.Segment.MessageSegmentServiceOMLO21;
import com.mirth.prometeo.ServiceORUR01.Event.MessageEventServiceORUR01;
import com.mirth.prometeo.ServiceORUR01.Segment.MessageSegmentServiceORUR01;
import com.mirth.prometeo.SpringbootSoapClient.SoapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Service
public class HL7SocketServerService {
    private final Parser pipeParser = new PipeParser();
    private final XMLParser xmlParser = new DefaultXMLParser();

    @Autowired
    private MessageEventServiceORUR01 messageEventServiceORUR01;
    @Autowired
    private MessageSegmentServiceORUR01 messageSegmentServiceORUR01;
    @Autowired
    private MessageEventServiceOMLO21 messageEventServiceOMLO21;
    @Autowired
    private MessageSegmentServiceOMLO21 messageSegmentServiceOMLO21;
    @Autowired
    private SoapClient soapClient;

    public void startServer(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server in ascolto sulla porta " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Connessione accettata da " + clientSocket.getInetAddress());

                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

                    String hl7Message = readMessage(in);
                    System.out.println("Ricevuto: " + hl7Message);
                    System.out.println("Salvo sul db locale il messaggio ORU");
                    saveORUR01Database(hl7Message);
                    System.out.println("Utilizzo i dati contenuti nell'ORU per generare un OML_O21");
                    ORUR01 object = new ORUR01();
                    ORU_R01 parsedORU = null;
                    try {
                        parsedORU = (ORU_R01) pipeParser.parse(hl7Message);
                        OML_O21 omlO21 = object.generateOMLO21FromORUR01TD(parsedORU);
                        System.out.println("Salvo sul db locale l'OML_O21 generato, prima di inviarlo al PS");
                        saveOMLO21Database(omlO21);
                        PipeParser pipeParser = new PipeParser();
                        String omlFinal = String.valueOf(pipeParser.parse(String.valueOf(omlO21)));
                        String omlXML = object.convertPIPEToXML(omlO21);
                        String responseMessage = processHL7Message(omlFinal);
                        soapClient.sendAcceptMessage(omlXML);
                        writeMessage(out, responseMessage);
                    } catch (Exception e) {
                        ACKResponse ackResponse = new ACKResponse();
                        System.out.println("Genero la risposta ACK inviata da TD");
                        ACK ackMessage = ackResponse.generateACKResponseORU(hl7Message);
                        System.out.println(xmlParser.encode(ackMessage));
                        //hl7SocketClientService.sendHL7Message(pipeParser.encode(ackMessage));
                        System.err.println("Errore di comunicazione: " + e.getMessage());
                    }
                } catch (Exception e) {
                    System.err.println("Errore di comunicazione: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Errore di avvio del server: " + e.getMessage());
        }
    }

    private String processHL7Message(String hl7Message) {
        return "ACK^" + hl7Message;
    }

    private String readMessage(BufferedReader in) throws Exception {
        StringBuilder responseBuilder = new StringBuilder();
        int character;
        boolean endOfMessage = false;

        do {
            character = in.read();
            if (character != -1) {
                responseBuilder.append((char) character);
                if (responseBuilder.toString().endsWith("\u001c\r")) {
                    endOfMessage = true;
                }
            }
        } while (!endOfMessage && character != -1);

        if (character == -1) {
            System.out.println("End of stream reached.");
        }

        String response = responseBuilder.toString();
        if (response.startsWith("\u000b") && response.endsWith("\u001c\r")) {
            response = response.substring(1, response.length() - 2);
        }
        return response;
    }

    private void writeMessage(PrintWriter out, String hl7Message) {
        String messageWithDelimiters = '\u000b' + hl7Message + '\u001c' + '\r';
        out.print(messageWithDelimiters);
        out.flush();
    }

    public void saveORUR01Database(String hl7Message) {
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

    public void saveOMLO21Database(OML_O21 omlO21) {
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
