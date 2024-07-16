package com.mirth.prometeo.ServiceOMLO21.Event;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import com.mirth.prometeo.Entity.MessageEvent;
import com.mirth.prometeo.Repository.MessageEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageEventServiceOMLO21 {

    @Autowired
    private MessageEventRepository messageEventRepository;

    @Transactional
    public MessageEvent saveOMLO21Message(OML_O21 omlO21) throws DataTypeException {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setCode("OML_O21");
        messageEvent.setSource("Pronto Soccorso");
        return messageEventRepository.save(messageEvent);
    }
}