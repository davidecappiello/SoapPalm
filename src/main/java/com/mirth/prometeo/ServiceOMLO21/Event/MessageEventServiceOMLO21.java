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
    private static MessageEventRepository messageEventRepository;

    @Transactional
    public static MessageEvent saveOMLO21Message(String oml, OML_O21 omlO21) throws DataTypeException {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setCode("OML_O21");
        messageEvent.setSource("Pronto Soccorso");
        messageEvent.setPlacerGroupNumber(omlO21.getORDER().getORC().getPlacerGroupNumber().getEntityIdentifier().getValue());
        return messageEventRepository.save(messageEvent);
    }
}
