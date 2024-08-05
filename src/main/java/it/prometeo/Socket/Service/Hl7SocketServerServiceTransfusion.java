package it.prometeo.Socket.Service;

import ca.uhn.hl7v2.model.v25.message.ACK;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.message.ORU_R01;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.parser.XMLParser;
import it.prometeo.HL7Palm.Message.ACKResponse;
import it.prometeo.HL7Palm.Message.ORUR01;
import it.prometeo.ServiceOMLO21.Event.MessageEventServiceOMLO21;
import it.prometeo.ServiceOMLO21.Segment.MessageSegmentServiceOMLO21;
import it.prometeo.ServiceORUR01.Event.MessageEventServiceORUR01;
import it.prometeo.ServiceORUR01.Segment.MessageSegmentServiceORUR01;
import it.prometeo.SpringbootSoapClient.SoapClient;
import it.prometeo.Util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

@Service
public class Hl7SocketServerServiceTransfusion {

    private final Parser pipeParser = new PipeParser();
    private final XMLParser xmlParser = new DefaultXMLParser();
    private static final Util util = new Util();
    private static ORUR01 oruObject = new ORUR01();
    private static ORU_R01 parsedORU = null;

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

    public void startServer(int socketServerPort) {
        try (ServerSocket serverSocket = new ServerSocket(socketServerPort)) {

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    util.insertLogRow("Connessione accettata da " + clientSocket.getInetAddress());

                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

                    String hl7Message = readMessage(in);
                    util.insertLogRow("Ricevuto: " + hl7Message);
                    util.insertLogRow("Salvo sul db locale il messaggio ORU");
                    util.saveORUR01Database(hl7Message, messageEventServiceORUR01, messageSegmentServiceORUR01);
                    util.insertLogRow("Utilizzo i dati contenuti nell'ORU per generare un OML_O21");
                    try {
                        parsedORU = (ORU_R01) pipeParser.parse(hl7Message);
                        ACK ackPositiveResponse = (ACK) parsedORU.generateACK();
                        try {
                            writeMessage(out, String.valueOf(ackPositiveResponse));
                        } catch (Error e) {
                            e.printStackTrace();
                        }
                        OML_O21 omlO21 = oruObject.generateOMLO21FromORUR01TDForTransfusion(parsedORU);
                        util.insertLogRow("Salvo sul db locale l'OML_O21 generato, prima di inviarlo al PS");
                        util.saveOMLO21Database2(omlO21, messageEventServiceOMLO21, messageSegmentServiceOMLO21);
                        String omlFinal = String.valueOf(pipeParser.parse(String.valueOf(omlO21)));
                        String omlXML = oruObject.convertPIPEToXML(omlO21);
                        String responseMessage = processHL7Message(omlFinal);
                        soapClient.sendAcceptMessagetransfusion(omlXML);
                        writeMessage(out, responseMessage);
                    } catch (Exception e) {
                        ACKResponse ackResponse = new ACKResponse();
                        util.insertLogRow("Genero la risposta ACK inviata da TD");
                        ACK ackMessage = ackResponse.generateACKResponseORUError(hl7Message);
                        util.insertLogRow(xmlParser.encode(ackMessage));
                        System.err.println("Errore di comunicazione: " + e.getMessage());
                        writeMessage(out, pipeParser.encode(ackMessage));
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
}
