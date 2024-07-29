package it.prometeo.ServiceQBPQ11.Segment;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.QBP_Q11;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.PipeParser;
import it.prometeo.Entity.MessageEvent;
import it.prometeo.Entity.MessageSegment;
import it.prometeo.Configuration.HL7Config;
import it.prometeo.Repository.MessageEventRepository;
import it.prometeo.Repository.MessageSegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageSegmentServiceQBPQ11 {

    private static HL7Config hl7Config = null;

    @Autowired
    public MessageSegmentServiceQBPQ11(HL7Config hl7Config) {
        MessageSegmentServiceQBPQ11.hl7Config = hl7Config;
    }
    @Autowired
    private MessageEventRepository messageEventRepository;
    @Autowired
    private MessageSegmentRepository messageSegmentRepository;

    public void saveMSHMessageSegmentQBPQ11(QBP_Q11 qbpQ11, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());
        MessageSegment messageSegment = new MessageSegment();
        if(messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            messageSegment.setCode(hl7Config.getSegmentMsh());
            String serializedMessage = parser.encode(qbpQ11);
            String serializedSegment = serializedMessage.split("\r")[0];
            if (serializedSegment != null) {
                messageSegment.setBody(serializedSegment);
                messageSegment.setMessageEventId(messageEventOptional.get());
                messageSegmentRepository.save(messageSegment);
            } else {
                throw new IllegalArgumentException("MSH segment not found in the message");
            }
        } else {
            throw new IllegalArgumentException("MessageEvent with ID " +messageEvent.getId()+ " not found");
        }
    }

    public void saveEVNMessageSegmentQBPQ11(QBP_Q11 qbpQ11, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());
        MessageSegment messageSegment = new MessageSegment();
        if(messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            messageSegment.setCode(hl7Config.getSegmentEvn());
            String serializedMessage = parser.encode(qbpQ11);
            String serializedSegment = null;
            for (String segment : serializedMessage.split("\r")) {
                if (segment.startsWith(hl7Config.getSegmentEvn())) {
                    serializedSegment = segment;
                }
            }
            if (serializedSegment != null) {
                messageSegment.setBody(serializedSegment);
                messageSegment.setMessageEventId(messageEventOptional.get());
                messageSegmentRepository.save(messageSegment);
            } else {
                throw new IllegalArgumentException("EVN segment not found in the message");
            }
        } else {
            throw new IllegalArgumentException("MessageEvent with ID " +messageEvent.getId()+ " not found");
        }
    }

    public void saveQPDMessageSegmentQBPQ11(QBP_Q11 qbpQ11, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());
        MessageSegment messageSegment = new MessageSegment();
        if(messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            messageSegment.setCode(hl7Config.getSegmentQpd());
            String serializedMessage = parser.encode(qbpQ11);
            String serializedSegment = null;
            for (String segment : serializedMessage.split("\r")) {
                if (segment.startsWith(hl7Config.getSegmentQpd())) {
                    serializedSegment = segment;
                }
            }
            if (serializedSegment != null) {
                messageSegment.setBody(serializedSegment);
                messageSegment.setMessageEventId(messageEventOptional.get());
                messageSegmentRepository.save(messageSegment);
            } else {
                throw new IllegalArgumentException("QPD segment not found in the message");
            }
        } else {
            throw new IllegalArgumentException("MessageEvent with ID " +messageEvent.getId()+ " not found");
        }
    }

    public void saveRCPMessageSegmentQBPQ11(QBP_Q11 qbpQ11, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());
        MessageSegment messageSegment = new MessageSegment();
        if(messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            messageSegment.setCode(hl7Config.getSegmentRcp());
            String serializedMessage = parser.encode(qbpQ11);
            String serializedSegment = null;
            for (String segment : serializedMessage.split("\r")) {
                if (segment.startsWith(hl7Config.getSegmentRcp())) {
                    serializedSegment = segment;
                }
            }
            if (serializedSegment != null) {
                messageSegment.setBody(serializedSegment);
                messageSegment.setMessageEventId(messageEventOptional.get());
                messageSegmentRepository.save(messageSegment);
            } else {
                throw new IllegalArgumentException("RCP segment not found in the message");
            }
        } else {
            throw new IllegalArgumentException("MessageEvent with ID " +messageEvent.getId()+ " not found");
        }
    }
}
