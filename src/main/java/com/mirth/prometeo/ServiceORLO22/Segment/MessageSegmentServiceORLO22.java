package com.mirth.prometeo.ServiceORLO22.Segment;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.ORL_O22;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.PipeParser;
import com.mirth.prometeo.Entity.MessageEvent;
import com.mirth.prometeo.Entity.MessageSegment;
import com.mirth.prometeo.HL7Config;
import com.mirth.prometeo.Repository.MessageEventRepository;
import com.mirth.prometeo.Repository.MessageSegmentRepository;
import com.mirth.prometeo.ServiceORLO22.Event.MessageEventServiceORLO22;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MessageSegmentServiceORLO22 {

    private static HL7Config hl7Config = null;

    @Autowired
    public MessageSegmentServiceORLO22(HL7Config hl7Config) {
        MessageSegmentServiceORLO22.hl7Config = hl7Config;
    }
    @Autowired
    private MessageEventRepository messageEventRepository;
    @Autowired
    private MessageSegmentRepository messageSegmentRepository;

    public void saveMSHMessageSegmentORLO22(ORL_O22 orlO22, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());
        if(messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            MessageSegment messageSegment = new MessageSegment();
            messageSegment.setCode(hl7Config.getSegmentMsh());
            String serializedMessage = parser.encode(orlO22);
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

    public void saveMSAMessageSegmentORLO22(ORL_O22 orlO22, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());
        if(messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            MessageSegment messageSegment = new MessageSegment();
            messageSegment.setCode(hl7Config.getSegmentMsa());
            String serializedMessage = parser.encode(orlO22);
            String serializedSegment = null;
            for (String segment : serializedMessage.split("\r")) {
                if (segment.startsWith(hl7Config.getSegmentMsa())) {
                    serializedSegment = segment;
                    break;
                }
            }
            if (serializedSegment != null) {
                messageSegment.setBody(serializedSegment);
                messageSegment.setMessageEventId(messageEventOptional.get());
                messageSegmentRepository.save(messageSegment);
            } else {
                throw new IllegalArgumentException("MSA segment not found in the message");
            }
        } else {
            throw new IllegalArgumentException("MessageEvent with ID " +messageEvent.getId()+ " not found");
        }
    }

    public void saveORDERBLOCKMessageORLO22(ORL_O22 orlO22, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());

        if (messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            String serializedMessage = parser.encode(orlO22);
            String[] segments = serializedMessage.split("\r");

            for (String segment : segments) {
                if (segment.startsWith(hl7Config.getSegmentOrc())) {
                    MessageSegment messageSegment = new MessageSegment();
                    messageSegment.setCode(hl7Config.getSegmentOrc());
                    messageSegment.setBody(segment);
                    messageSegment.setMessageEventId(messageEventOptional.get());
                    messageSegmentRepository.save(messageSegment);
                } else if (segment.startsWith(hl7Config.getSegmentObr())) {
                    MessageSegment messageSegment = new MessageSegment();
                    messageSegment.setCode(hl7Config.getSegmentObr());
                    messageSegment.setBody(segment);
                    messageSegment.setMessageEventId(messageEventOptional.get());
                    messageSegmentRepository.save(messageSegment);
                }
            }
        } else {
            throw new IllegalArgumentException("MessageEvent with ID " + messageEvent.getId() + " not found");
        }
    }

    public void saveSPMMessageSegmentORLO22(ORL_O22 orlO22, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());
        if(messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            MessageSegment messageSegment = new MessageSegment();
            messageSegment.setCode(hl7Config.getSegmentSpm());
            String serializedMessage = parser.encode(orlO22);
            String serializedSegment = null;
            for (String segment : serializedMessage.split("\r")) {
                if (segment.startsWith(hl7Config.getSegmentSpm())) {
                    serializedSegment = segment;
                    break;
                }
            }
            if (serializedSegment != null) {
                messageSegment.setBody(serializedSegment);
                messageSegment.setMessageEventId(messageEventOptional.get());
                messageSegmentRepository.save(messageSegment);
            } else {
                throw new IllegalArgumentException("SPM segment not found in the message");
            }
        } else {
            throw new IllegalArgumentException("MessageEvent with ID " +messageEvent.getId()+ " not found");
        }
    }

}