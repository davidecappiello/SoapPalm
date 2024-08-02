package it.prometeo.ServiceMDMT02evT02.Segment;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.MDM_T02;
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
public class MessageSegmentServiceMDMT02evT02 {

    private static HL7Config hl7Config = null;

    @Autowired
    public MessageSegmentServiceMDMT02evT02(HL7Config hl7Config) {
        MessageSegmentServiceMDMT02evT02.hl7Config = hl7Config;
    }
    @Autowired
    private MessageEventRepository messageEventRepository;
    @Autowired
    private MessageSegmentRepository messageSegmentRepository;

    public void saveMSHMessageSegmentMDMT02evT02(MDM_T02 mdmT02, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());
        MessageSegment messageSegment = new MessageSegment();
        if(messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            messageSegment.setCode(hl7Config.getSegmentMsh());
            String serializedMessage = parser.encode(mdmT02);
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

    public void saveEVNMessageSegmentMDMT02evT02(MDM_T02 mdmT02, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());
        MessageSegment messageSegment = new MessageSegment();
        if(messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            messageSegment.setCode(hl7Config.getSegmentEvn());
            String serializedMessage = parser.encode(mdmT02);
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

    public void savePIDMessageSegmentMDMT02evT02(MDM_T02 mdmT02, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());
        MessageSegment messageSegment = new MessageSegment();
        if(messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            messageSegment.setCode(hl7Config.getSegmentPid());
            String serializedMessage = parser.encode(mdmT02);
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

    public void savePV1MessageSegmentMDMT02evT02(MDM_T02 mdmT02, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());
        MessageSegment messageSegment = new MessageSegment();
        if(messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            messageSegment.setCode(hl7Config.getSegmentPv1());
            String serializedMessage = parser.encode(mdmT02);
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

    public void saveORDERBLOCKMessageMDMT02evT02(MDM_T02 mdmT02, MessageEvent messageEvent) throws HL7Exception {
        Optional<MessageEvent> messageEventOptional = messageEventRepository.findById(messageEvent.getId());

        if (messageEventOptional.isPresent()) {
            Parser parser = new PipeParser();
            String serializedMessage = parser.encode(mdmT02);
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
                } else if (segment.startsWith(hl7Config.getSegmentTxa())) {
                    MessageSegment messageSegment = new MessageSegment();
                    messageSegment.setCode(hl7Config.getSegmentTxa());
                    messageSegment.setBody(segment);
                    messageSegment.setMessageEventId(messageEventOptional.get());
                    messageSegmentRepository.save(messageSegment);
                }else if (segment.startsWith(hl7Config.getSegmentObx())) {
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
