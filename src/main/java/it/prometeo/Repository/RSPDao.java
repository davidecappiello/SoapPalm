package it.prometeo.Repository;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.message.QBP_Q11;
import ca.uhn.hl7v2.model.v25.segment.ORC;
import ca.uhn.hl7v2.parser.PipeParser;
import it.prometeo.Entity.MessageEvent;
import it.prometeo.Entity.MessageSegment;
import it.prometeo.Util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RSPDao {

    private static final Logger logger = LoggerFactory.getLogger(Util.class);

    @Autowired
    private MessageEventDao eventDao;

    @Autowired
    private MessageSegmentDao messageSegmentDao;

    private PipeParser pipeParser = new PipeParser();

    public ORC findORCForRSP(QBP_Q11 qbpDecoded) throws HL7Exception {
        String placer = null;
        if (qbpDecoded.getQPD().getUserParametersInsuccessivefields().toString().startsWith("Varies[") &&
                qbpDecoded.getQPD().getUserParametersInsuccessivefields().toString().endsWith("]")) {
            String content = qbpDecoded.getQPD().getUserParametersInsuccessivefields().toString().substring(7, qbpDecoded.getQPD().getUserParametersInsuccessivefields().toString().length() - 1);
            String[] parts = content.split("\\^");
            if (parts.length == 2) {
                placer = parts[1];
            }
        }
        MessageEvent event = eventDao.findMessageEventPlacerFromOML(placer);

        if(event != null) {
            List<MessageSegment> segmentList = messageSegmentDao.findMessageSegment(event);

            StringBuilder messageBody = new StringBuilder();
            for (int j = 0; j < segmentList.size(); j++) {
                messageBody.append(segmentList.get(j).getBody() + "\r");
                System.out.println(segmentList.get(j).getBody());
            }

            String body = messageBody.toString();

            OML_O21 oml = (OML_O21) pipeParser.parse(body);

            ORC orcFormOML = oml.getORDER().getORC();

            return orcFormOML;
        } else {
            logger.error("Messaggio non trovato");
            throw new RuntimeException("Messaggio non trovato");
        }
    }

    public String findOMLForRSP(QBP_Q11 qbpDecoded) throws HL7Exception {
        String placer = null;
        if (qbpDecoded.getQPD().getUserParametersInsuccessivefields().toString().startsWith("Varies[") &&
                qbpDecoded.getQPD().getUserParametersInsuccessivefields().toString().endsWith("]")) {
            String content = qbpDecoded.getQPD().getUserParametersInsuccessivefields().toString().substring(7, qbpDecoded.getQPD().getUserParametersInsuccessivefields().toString().length() - 1);
            String[] parts = content.split("\\^");
            if (parts.length == 2) {
                placer = parts[1];
            }
        }
        MessageEvent event = eventDao.findMessageEventPlacerFromORL(placer);

        List<MessageSegment> segmentList = messageSegmentDao.findMessageSegment(event);
        StringBuilder messageBody = new StringBuilder();
        for (int j = 0; j < segmentList.size(); j++) {
            messageBody.append(segmentList.get(j).getBody() + "\r");
            System.out.println(segmentList.get(j).getBody());
        }

        String body = messageBody.toString();

        return body;

    }

    public String findORLforZET(QBP_Q11 qbpDecoded) throws HL7Exception {
        String placer = null;
        if (qbpDecoded.getQPD().getUserParametersInsuccessivefields().toString().startsWith("Varies[") &&
                qbpDecoded.getQPD().getUserParametersInsuccessivefields().toString().endsWith("]")) {
            String content = qbpDecoded.getQPD().getUserParametersInsuccessivefields().toString().substring(7, qbpDecoded.getQPD().getUserParametersInsuccessivefields().toString().length() - 1);
            String[] parts = content.split("\\^");
            if (parts.length == 2) {
                placer = parts[1];
            }
        }

        MessageEvent event = eventDao.findMessageEventPlacerFromORL(placer);

        List<MessageSegment> segmentList = messageSegmentDao.findMessageSegment(event);

        StringBuilder messageBody = new StringBuilder();
        for (int j = 0; j < segmentList.size(); j++) {
            messageBody.append(segmentList.get(j).getBody() + "\r");
            System.out.println(segmentList.get(j).getBody());
        }

        String body = messageBody.toString();
        return body;
    }

}
