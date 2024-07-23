
package com.mirth.prometeo.ServiceORMOO1.Segment;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.message.ORM_O01;
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
public class MessageSegmentServiceORMOO1 {

    private static HL7Config hl7Config = null;

    @Autowired
    public MessageSegmentServiceORMOO1(HL7Config hl7Config) {
        MessageSegmentServiceORMOO1.hl7Config = hl7Config;
    }
    @Autowired
    private MessageEventRepository messageEventRepository;
    @Autowired
    private MessageSegmentRepository messageSegmentRepository;

    public void saveMSHMessageSegmentORMOO1(ORM_O01 ormO01, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());
        if(messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            MessageSegment messageSegment = new MessageSegment();
            messageSegment.setCode(hl7Config.getSegmentMsh());
            String serializedMessage = parser.encode(ormO01);
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

    public void savePIDMessageSegmentORMOO1(ORM_O01 ormO01, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());
        if(messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            MessageSegment messageSegment = new MessageSegment();
            messageSegment.setCode(hl7Config.getSegmentPid());
            String serializedMessage = parser.encode(ormO01);
            String serializedSegment = null;
            for (String segment : serializedMessage.split("\r")) {
                if (segment.startsWith(hl7Config.getSegmentPid())) {
                    serializedSegment = segment;
                    break;
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

    public void savePV1MessageSegmentORMOO1(ORM_O01 ormO01, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());
        if(messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            MessageSegment messageSegment = new MessageSegment();
            messageSegment.setCode(hl7Config.getSegmentPv1());
            String serializedMessage = parser.encode(ormO01);
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

    public void saveORDERBLOCKMessageORMOO1(ORM_O01 ormO01, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());

        if (messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            String serializedMessage = parser.encode(ormO01);
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
