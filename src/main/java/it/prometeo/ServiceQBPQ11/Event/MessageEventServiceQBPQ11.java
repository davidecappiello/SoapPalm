package it.prometeo.ServiceQBPQ11.Event;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v25.message.QBP_Q11;
import it.prometeo.Entity.MessageEvent;
import it.prometeo.Configuration.HL7Config;
import it.prometeo.Repository.MessageEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageEventServiceQBPQ11 {

    private static HL7Config hl7Config = null;

    @Autowired
    public MessageEventServiceQBPQ11(HL7Config hl7Config) {
        MessageEventServiceQBPQ11.hl7Config = hl7Config;
    }
    @Autowired
    private MessageEventRepository messageEventRepository;

    @Transactional
    public MessageEvent saveQBPQ11Message(QBP_Q11 qbpQ11) throws DataTypeException {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setCode(hl7Config.getMessageStructureQBPQ11());
        messageEvent.setSource(hl7Config.getSourceDatabaseEventOMLO21PS());
        return messageEventRepository.save(messageEvent);
    }
}
