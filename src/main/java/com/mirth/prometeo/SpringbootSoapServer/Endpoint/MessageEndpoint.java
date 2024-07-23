package com.mirth.prometeo.SpringbootSoapServer.Endpoint;

import ca.uhn.hl7v2.model.v25.message.*;
import com.mirth.prometeo.HL7Palm.Message.*;
import com.mirth.connect.connectors.ws.AcceptMessage;
import com.mirth.connect.connectors.ws.AcceptMessageResponse;
import com.mirth.connect.connectors.ws.ObjectFactory;
import com.mirth.prometeo.Repository.MessageEventRepository;
import com.mirth.prometeo.ServiceOMLO21.Event.MessageEventServiceOMLO21;
import com.mirth.prometeo.ServiceOMLO21.Segment.MessageSegmentServiceOMLO21;
import com.mirth.prometeo.ServiceORLO22.Event.MessageEventServiceORLO22;
import com.mirth.prometeo.ServiceORLO22.Segment.MessageSegmentServiceORLO22;
import com.mirth.prometeo.ServiceORMOO1.Event.MessageEventServiceORMOO1;
import com.mirth.prometeo.ServiceORMOO1.Segment.MessageSegmentServiceORMOO1;
import com.mirth.prometeo.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import jakarta.xml.bind.JAXBElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.PrintWriter;
import java.io.StringWriter;

@Endpoint
public class MessageEndpoint implements CommandLineRunner {

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
    private MessageEventRepository messageEventRepository;

    private final ObjectFactory factory;
    private static final Logger logger = LoggerFactory.getLogger(MessageEndpoint.class);
    private static final Util util = new Util();
    private static ORMOO1 ormO01 = new ORMOO1();
    private static OMLO21 oml_o21 = new OMLO21();
    private static String hl7Response = null;
    private static OML_O21 omlCreated = null;
    private static String param;
    static StringWriter sw = new StringWriter();
    static PrintWriter pw = new PrintWriter(sw);
    private static final String NAMESPACE_URI = "http://ws.connectors.connect.mirth.com/";

    public MessageEndpoint() {
        factory = new ObjectFactory();
    }

    @PayloadRoot(namespace = NAMESPACE_URI,
            localPart = "acceptMessage")
    @ResponsePayload
    public JAXBElement<AcceptMessageResponse> acceptMessage(@RequestPayload JAXBElement<AcceptMessage> acceptMessage) {
        AcceptMessageResponse response = new AcceptMessageResponse();
        String finalMessage = acceptMessage.getValue().getArg0();
        util.insertLogRow("Ricevo il messaggio OML_O21 dal PS");
        util.insertLogRow(finalMessage);

        try {
            util.insertLogRow("Controllo i tag esterni al messaggio per capire se sono uguali al MessageTypeStructure del messaggio HL7");
            String updatedMessage = util.checkAndUpdateMessageType(finalMessage);
            String msg3Value = util.extractMsg3Value(finalMessage);

            if (acceptMessage.getValue() != null && acceptMessage.getValue().getArg0() != null) {
                try {
                    if (msg3Value.equals("OML_O21")) {
                        util.insertLogRow("Salvo l'OML_O21 inviato dal PS e l'ORM_O01 generato da noi sul database locale");
                        String date = util.modifyPid7Format(finalMessage);
                        if (param.equals("orm")) {
                            util.handleORM(updatedMessage, ormO01, hl7Response, response, date, param, messageEventServiceOMLO21,  messageSegmentServiceOMLO21, messageEventServiceORMOO1, messageSegmentServiceORMOO1, messageEventServiceORLO22, messageSegmentServiceORLO22);
                        } else if (param.equals("oml")) {
                            util.handleOML(updatedMessage, omlCreated, oml_o21, hl7Response, response, param, messageEventServiceOMLO21, messageSegmentServiceOMLO21, messageEventServiceORLO22, messageSegmentServiceORLO22, messageEventRepository);
                        }
                    } else if(msg3Value.equals("QBP_Q11")){
                        util.handleQBP(updatedMessage, response);
                    }
                } catch (Exception e) {
                    e.printStackTrace(pw);
                    logger.error(sw.toString());
                    util.insertLogRow("sw: "+sw.toString());

                    response.setReturn(e.getMessage());
                }

            } else {
                response.setReturn("Received acceptMessage is null or its value is null.");
            }
            util.insertLogRow("Creo un oggetto acceptMessageResponse e rispondo al PS");
            return factory.createAcceptMessageResponse(response);

        } catch (Exception e) {
            e.printStackTrace(pw);
            logger.error(sw.toString());
            util.insertLogRow("sw: "+sw.toString());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length > 0) {
            String console = args[0];
            param = console;
        } else {
            util.insertLogRow("Nessun parametro fornito");
        }
    }

}
