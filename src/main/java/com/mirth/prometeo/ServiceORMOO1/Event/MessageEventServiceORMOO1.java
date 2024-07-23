package com.mirth.prometeo.ServiceORMOO1.Event;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.message.ORM_O01;
import com.mirth.prometeo.Entity.MessageEvent;
import com.mirth.prometeo.HL7Config;
import com.mirth.prometeo.Repository.MessageEventRepository;
import com.mirth.prometeo.ServiceORLO22.Event.MessageEventServiceORLO22;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageEventServiceORMOO1 {

    private static HL7Config hl7Config = null;

    @Autowired
    public MessageEventServiceORMOO1(HL7Config hl7Config) {
        MessageEventServiceORMOO1.hl7Config = hl7Config;
    }
    @Autowired
    MessageEventRepository messageEventRepository;

    @Transactional
    public MessageEvent saveORMOO1Message(ORM_O01 orm, OML_O21 omlO21) throws DataTypeException {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setCode(hl7Config.getMessageStructureORMO01());
        messageEvent.setSource(hl7Config.getReceivingApplication());
        messageEvent.setPlacerGroupNumber(orm.getORDER().getORC().getPlacerGroupNumber().getEntityIdentifier().getValue());
        return messageEventRepository.save(messageEvent);
    }
}