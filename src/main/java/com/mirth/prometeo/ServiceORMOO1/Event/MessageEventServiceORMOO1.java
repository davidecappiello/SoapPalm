package com.mirth.prometeo.ServiceORMOO1.Event;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.message.ORM_O01;
import com.mirth.prometeo.Entity.MessageEvent;
import com.mirth.prometeo.Repository.MessageEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageEventServiceORMOO1 {

    @Autowired
    MessageEventRepository messageEventRepository;

    @Transactional
    public MessageEvent saveORMOO1Message(ORM_O01 orm, OML_O21 omlO21) throws DataTypeException {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setCode("ORM_OO1");
        messageEvent.setSource("Middleware");
        return messageEventRepository.save(messageEvent);
    }
}