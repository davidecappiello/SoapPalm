package com.mirth.prometeo.ServiceOMLO21.Segment;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.PipeParser;
import com.mirth.prometeo.HL7Config;
import com.mirth.prometeo.Repository.MessageEventRepository;
import com.mirth.prometeo.Repository.MessageSegmentRepository;
import com.mirth.prometeo.ServiceOMLO21.Event.MessageEventServiceOMLO21;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.mirth.prometeo.Entity.MessageEvent;
import com.mirth.prometeo.Entity.MessageSegment;

@Service
public class MessageSegmentServiceOMLO21 {

    private static HL7Config hl7Config = null;

    @Autowired
    public MessageSegmentServiceOMLO21(HL7Config hl7Config) {
        MessageSegmentServiceOMLO21.hl7Config = hl7Config;
    }
    @Autowired
    private MessageEventRepository messageEventRepository;
    @Autowired
    private MessageSegmentRepository messageSegmentRepository;

    public void saveMSHMessageSegmentOMLO21(OML_O21 omlO21, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());
        MessageSegment messageSegment = new MessageSegment();
        if(messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            messageSegment.setCode(hl7Config.getSegmentMsh());
            String serializedMessage = parser.encode(omlO21);
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

    public void savePIDMessageSegmentOMLO21(OML_O21 omlO21, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());
        MessageSegment messageSegment = new MessageSegment();
        if(messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            messageSegment.setCode(hl7Config.getSegmentPid());
            String serializedMessage = parser.encode(omlO21);
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

    public void savePD1MessageSegmentOMLO21(OML_O21 omlO21, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());
        MessageSegment messageSegment = new MessageSegment();
        if(messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            messageSegment.setCode(hl7Config.getSegmentPd1());
            String serializedMessage = parser.encode(omlO21);
            String serializedSegment = null;
            for (String segment : serializedMessage.split("\r")) {
                if (segment.startsWith(hl7Config.getSegmentPd1())) {
                    serializedSegment = segment;
                    break;
                }
            }
            if (serializedSegment != null) {
                messageSegment.setBody(serializedSegment);
                messageSegment.setMessageEventId(messageEventOptional.get());
                messageSegmentRepository.save(messageSegment);
            } else {
                throw new IllegalArgumentException("PD1 segment not found in the message");
            }
        } else {
            throw new IllegalArgumentException("MessageEvent with ID " +messageEvent.getId()+ " not found");
        }
    }

    public void savePV1MessageSegmentOMLO21(OML_O21 omlO21, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());
        MessageSegment messageSegment = new MessageSegment();
        if(messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            messageSegment.setCode(hl7Config.getSegmentPv1());
            String serializedMessage = parser.encode(omlO21);
            String serializedSegment = null;
            for (String segment : serializedMessage.split("\r")) {
                if (segment.startsWith(hl7Config.getSegmentPv1())) {
                    serializedSegment = segment;
                    break;
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

    public void saveTQ1MessageSegmentOMLO21(OML_O21 omlO21, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());
        MessageSegment messageSegment = new MessageSegment();
        if(messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            messageSegment.setCode(hl7Config.getSegmentTq1());
            String serializedMessage = parser.encode(omlO21);
            String serializedSegment = null;
            for (String segment : serializedMessage.split("\r")) {
                if (segment.startsWith(hl7Config.getSegmentTq1())) {
                    serializedSegment = segment;
                    break;
                }
            }
            if (serializedSegment != null) {
                messageSegment.setBody(serializedSegment);
                messageSegment.setMessageEventId(messageEventOptional.get());
                messageSegmentRepository.save(messageSegment);
            } else {
                throw new IllegalArgumentException("TQ1 segment not found in the message");
            }
        } else {
            throw new IllegalArgumentException("MessageEvent with ID " +messageEvent.getId()+ " not found");
        }
    }

    public void saveORDERBLOCKMessageOMLO21(OML_O21 omlO21, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());

        if (messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            String serializedMessage = parser.encode(omlO21);
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
                } else if (segment.startsWith(hl7Config.getSegmentObx())) {
                    MessageSegment messageSegment = new MessageSegment();
                    messageSegment.setCode(hl7Config.getSegmentObx());
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