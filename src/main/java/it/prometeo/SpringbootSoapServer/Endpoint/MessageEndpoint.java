package it.prometeo.SpringbootSoapServer.Endpoint;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.*;
import it.prometeo.HL7Palm.Decoding.OMLDecoding;
import it.prometeo.HL7Palm.Message.*;
import com.mirth.connect.connectors.ws.AcceptMessage;
import com.mirth.connect.connectors.ws.AcceptMessageResponse;
import com.mirth.connect.connectors.ws.ObjectFactory;
import it.prometeo.Repository.MessageEventRepository;
import it.prometeo.ServiceOMLO21.Event.MessageEventServiceOMLO21;
import it.prometeo.ServiceOMLO21.Segment.MessageSegmentServiceOMLO21;
import it.prometeo.ServiceORLO22.Event.MessageEventServiceORLO22;
import it.prometeo.ServiceORLO22.Segment.MessageSegmentServiceORLO22;
import it.prometeo.ServiceORMOO1.Event.MessageEventServiceORMOO1;
import it.prometeo.ServiceORMOO1.Segment.MessageSegmentServiceORMOO1;
import it.prometeo.ServiceQBPQ11.Event.MessageEventServiceQBPQ11;
import it.prometeo.ServiceQBPQ11.Segment.MessageSegmentServiceQBPQ11;
import it.prometeo.Util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import jakarta.xml.bind.JAXBElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@Endpoint
public class MessageEndpoint {

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
    @Autowired
    private MessageEventServiceQBPQ11 messageEventServiceQBPQ11;
    @Autowired
    private MessageSegmentServiceQBPQ11 messageSegmentServiceQBPQ11;
    @Autowired
    private MessageEventRepository messageEventRepository;


    private final ObjectFactory factory;
    private static final Logger logger = LoggerFactory.getLogger(MessageEndpoint.class);
    @Autowired
    private Util util;
    private static ORMOO1 ormO01 = new ORMOO1();
    private static OMLO21 oml_o21 = new OMLO21();
    private static String hl7Response = null;
    private static OML_O21 omlCreated = null;
    private static String param;
    static StringWriter sw = new StringWriter();
    static PrintWriter pw = new PrintWriter(sw);
    private static final String NAMESPACE_URI = "http://ws.connectors.connect.mirth.com/";
    private static ACKResponse ackObject = new ACKResponse();
    public MessageEndpoint() {
        factory = new ObjectFactory();
    }

    @PayloadRoot(namespace = NAMESPACE_URI,
            localPart = "acceptMessage")
    @ResponsePayload
    public JAXBElement<AcceptMessageResponse> acceptMessage(@RequestPayload JAXBElement<AcceptMessage> acceptMessage) throws Exception {
        Exception exceptionCaught = null;
        AcceptMessageResponse response = new AcceptMessageResponse();
        String finalMessage = acceptMessage.getValue().getArg0();
        util.insertLogRow("Ricevo il messaggio OML_O21 dal PS");
        util.insertLogRow(finalMessage);

        util.insertLogRow("Controllo i tag esterni al messaggio per capire se sono uguali al MessageTypeStructure del messaggio HL7");
        String updatedMessage = util.checkAndUpdateMessageType(finalMessage);
        String msg3Value = util.extractMsg3Value(finalMessage);
        String msh3Value = util.extractMSH3_HD1Value(finalMessage);

        util.insertLogRow(msh3Value);

        if (acceptMessage.getValue() != null && acceptMessage.getValue().getArg0() != null) {
            try {
                String date = util.modifyPid7Format(finalMessage);
                if (msg3Value.equals("OML_O21") && msh3Value.equals("NGH")) {
                    util.insertLogRow("Salvo l'OML_O21 inviato dal PS sul database locale");
                    omlCreated = OMLDecoding.decodeOML_XML(updatedMessage);
                    util.handleOMLPS(updatedMessage, omlCreated, oml_o21, hl7Response, response, param, messageEventServiceOMLO21, messageSegmentServiceOMLO21, messageEventServiceORLO22, messageSegmentServiceORLO22, messageEventRepository, msh3Value);
                } else if (msg3Value.equals("ORM_O01") && msh3Value.equals("NGH")) {
                        util.handleORMPS(updatedMessage, ormO01, hl7Response, response, date, param, messageEventServiceOMLO21, messageSegmentServiceOMLO21, messageEventServiceORMOO1, messageSegmentServiceORMOO1, messageEventServiceORLO22, messageSegmentServiceORLO22, msh3Value);
                    } else if (msg3Value.equals("OML_O21") && msh3Value.equals("ONIX")) {
                        util.handleOMLTransfusion(updatedMessage, omlCreated, oml_o21, hl7Response, response, param, messageEventServiceOMLO21, messageSegmentServiceOMLO21, messageEventServiceORLO22, messageSegmentServiceORLO22, messageEventRepository, msh3Value);
                    } else if (msg3Value.equals("ORM_O01") && msh3Value.equals("ONIX")) {

                }
                else if (msg3Value.equals("QBP_Q11")) {
                    util.handleQBP(updatedMessage, response, messageEventServiceQBPQ11, messageSegmentServiceQBPQ11);
                }
            } catch (Exception e) {
                exceptionCaught = e;
                if(e instanceof HL7Exception) {
                    util.insertLogRow("Errore nella decodifica del messaggio");
                    util.insertLogRow(e.getMessage());
                }
                if(e instanceof IOException) {
                    util.insertLogRow("Errore nella comunicazione di rete via socket o di connessione al database");
                    util.insertLogRow(e.getMessage());
                }
                if(e instanceof ParserConfigurationException) {
                    util.insertLogRow("Errore nella configurazione del parser XML");
                    util.insertLogRow(e.getMessage());
                }
                if(e instanceof SAXException) {
                    util.insertLogRow("Errore nelle operazioni di parsing XML");
                    util.insertLogRow(e.getMessage());
                }
            } finally {
                if(exceptionCaught != null) {
                        ACK ackMessage = ackObject.generateACKErrorOMLO21FromPSDecodingError(updatedMessage, exceptionCaught);
                        String ackResponseFinal = ackObject.transformACKIntoStringXML(ackMessage);
                        response.setReturn(ackResponseFinal);
                } else {
                    util.insertLogRow("Flusso completato correttamente");
                }
            }

        } else {
            response.setReturn("Received acceptMessage is null or its value is null.");
        }
        return factory.createAcceptMessageResponse(response);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        if (args.length > 0) {
//            String console = args[0];
//            param = console;
//        } else {
//            util.insertLogRow("Nessun parametro fornito");
//        }
//    }

}
