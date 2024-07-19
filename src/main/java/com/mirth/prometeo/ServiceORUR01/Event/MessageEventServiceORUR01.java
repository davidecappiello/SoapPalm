package com.mirth.prometeo.ServiceORUR01.Event;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v25.message.ORU_R01;
import com.mirth.prometeo.Entity.MessageEvent;
import com.mirth.prometeo.Repository.MessageEventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageEventServiceORUR01 {

    @Autowired
    MessageEventRepository messageEventRepository;

    @Transactional
    public MessageEvent saveORUR01Message(ORU_R01 oruR01) throws DataTypeException {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setCode("ORU_R01");
        messageEvent.setSource("LIS");
        if(oruR01.getPATIENT_RESULT().getORDER_OBSERVATION().getORC().getPlacerGroupNumber().getEntityIdentifier().getValue() != null)
            messageEvent.setPlacerGroupNumber(oruR01.getPATIENT_RESULT().getORDER_OBSERVATION().getORC().getPlacerGroupNumber().getEntityIdentifier().getValue());
        return messageEventRepository.save(messageEvent);
    }
}
