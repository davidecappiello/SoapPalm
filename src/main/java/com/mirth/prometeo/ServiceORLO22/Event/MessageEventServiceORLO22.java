package com.mirth.prometeo.ServiceORLO22.Event;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.message.ORL_O22;
import com.mirth.prometeo.Entity.MessageEvent;
import com.mirth.prometeo.Repository.MessageEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageEventServiceORLO22 {

    @Autowired
    private MessageEventRepository messageEventRepository;

    @Transactional
    public MessageEvent saveORLO22Message(ORL_O22 orlo22, OML_O21 omlO21) throws DataTypeException {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setCode("ORL_O22");
        messageEvent.setSource("Middleware");
        return messageEventRepository.save(messageEvent);
    }
}
