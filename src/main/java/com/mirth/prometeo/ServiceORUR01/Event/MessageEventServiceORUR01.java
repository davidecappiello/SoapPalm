package com.mirth.prometeo.ServiceORUR01.Event;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v25.message.ORU_R01;
import com.mirth.prometeo.Entity.MessageEvent;
import com.mirth.prometeo.HL7Config;
import com.mirth.prometeo.Repository.MessageEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageEventServiceORUR01 {

    private static HL7Config hl7Config = null;

    @Autowired
    public MessageEventServiceORUR01(HL7Config hl7Config) {
        MessageEventServiceORUR01.hl7Config = hl7Config;
    }
    @Autowired
    MessageEventRepository messageEventRepository;

    @Transactional
    public MessageEvent saveORUR01Message(ORU_R01 oruR01) throws DataTypeException {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setCode(hl7Config.getMessageStructureORUR01());
        messageEvent.setSource(hl7Config.getSendingApplication());
        if(oruR01.getPATIENT_RESULT().getORDER_OBSERVATION().getORC().getPlacerGroupNumber().getEntityIdentifier().getValue() != null)
            messageEvent.setPlacerGroupNumber(oruR01.getPATIENT_RESULT().getORDER_OBSERVATION().getORC().getPlacerGroupNumber().getEntityIdentifier().getValue());
        return messageEventRepository.save(messageEvent);
    }
}
