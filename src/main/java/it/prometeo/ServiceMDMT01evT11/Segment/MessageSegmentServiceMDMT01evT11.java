package it.prometeo.ServiceMDMT01evT11.Segment;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.MDM_T01;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.PipeParser;
import it.prometeo.Configuration.HL7Config;
import it.prometeo.Entity.MessageEvent;
import it.prometeo.Entity.MessageSegment;
import it.prometeo.Repository.MessageEventRepository;
import it.prometeo.Repository.MessageSegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MessageSegmentServiceMDMT01evT11 {

    private static HL7Config hl7Config = null;

    @Autowired
    public MessageSegmentServiceMDMT01evT11(HL7Config hl7Config) {
        MessageSegmentServiceMDMT01evT11.hl7Config = hl7Config;
    }
    @Autowired
    private MessageEventRepository messageEventRepository;
    @Autowired
    private MessageSegmentRepository messageSegmentRepository;

    public void saveMSHMessageSegmentMDMT01evT11(MDM_T01 mdmT01, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());
        MessageSegment messageSegment = new MessageSegment();
        if(messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            messageSegment.setCode(hl7Config.getSegmentMsh());
            String serializedMessage = parser.encode(mdmT01);
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

    public void saveEVNMessageSegmentMDMT01evT11(MDM_T01 mdmT01, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());
        MessageSegment messageSegment = new MessageSegment();
        if(messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            messageSegment.setCode(hl7Config.getSegmentEvn());
            String serializedMessage = parser.encode(mdmT01);
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

    public void savePIDMessageSegmentMDMT01evT11(MDM_T01 mdmT01, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());
        MessageSegment messageSegment = new MessageSegment();
        if(messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            messageSegment.setCode(hl7Config.getSegmentPid());
            String serializedMessage = parser.encode(mdmT01);
            String serializedSegment = null;
            for (String segment : serializedMessage.split("\r")) {
                if (segment.startsWith(hl7Config.getSegmentPid())) {
                    serializedSegment = segment;
                }
            }
            if (serializedSegment != null) {
                messageSegment.setBody(serializedSegment);
                messageSegment.setMessageEventId(messageEventOptional.get());
                messageSegmentRepository.save(messageSegment);
            } else {
                throw new IllegalArgumentException("PID segment not found in the message");
            }
        } else {
            throw new IllegalArgumentException("MessageEvent with ID " +messageEvent.getId()+ " not found");
        }
    }

    public void savePV1MessageSegmentMDMT01evT11(MDM_T01 mdmT01, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());
        MessageSegment messageSegment = new MessageSegment();
        if(messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            messageSegment.setCode(hl7Config.getSegmentPv1());
            String serializedMessage = parser.encode(mdmT01);
            String serializedSegment = null;
            for (String segment : serializedMessage.split("\r")) {
                if (segment.startsWith(hl7Config.getSegmentPv1())) {
                    serializedSegment = segment;
                }
            }
            if (serializedSegment != null) {
                messageSegment.setBody(serializedSegment);
                messageSegment.setMessageEventId(messageEventOptional.get());
                messageSegmentRepository.save(messageSegment);
            } else {
                throw new IllegalArgumentException("PV1 segment not found in the message");
            }
        } else {
            throw new IllegalArgumentException("MessageEvent with ID " +messageEvent.getId()+ " not found");
        }
    }

    public void saveTXAMessageSegmentMDMT01evT11(MDM_T01 mdmT01, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());

        if (messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            String serializedMessage = parser.encode(mdmT01);
            String[] segments = serializedMessage.split("\r");

            for (String segment : segments) {
                if (segment.startsWith(hl7Config.getSegmentTxa())) {
                    MessageSegment messageSegment = new MessageSegment();
                    messageSegment.setCode(hl7Config.getSegmentTxa());
                    messageSegment.setBody(segment);
                    messageSegment.setMessageEventId(messageEventOptional.get());
                    messageSegmentRepository.save(messageSegment);
                }
            }
        } else {
            throw new IllegalArgumentException("MessageEvent with ID " + messageEvent.getId() + " not found");
        }
    }
}
