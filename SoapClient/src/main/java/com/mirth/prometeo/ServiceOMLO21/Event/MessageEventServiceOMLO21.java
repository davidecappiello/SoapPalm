package com.mirth.prometeo.ServiceOMLO21.Event;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import com.mirth.prometeo.Entity.EventTimeline;
import com.mirth.prometeo.Entity.MessageEvent;
import com.mirth.prometeo.Repository.MessageEventRepository;
import com.mirth.prometeo.Repository.MessageEventTimelineRepository;
import com.mirth.prometeo.ServiceOMLO21.EventTimeline.MessageEventTimelineOMLO21;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageEventServiceOMLO21 {

    @Autowired
    private MessageEventRepository messageEventRepository;
    @Autowired
    private MessageEventTimelineRepository messageEventTimelineRepository;
    @Autowired
    private MessageEventTimelineOMLO21 messageEventTimelineOMLO21;

    @Transactional
    public MessageEvent saveOMLO21Message(String oml, OML_O21 omlO21) throws DataTypeException {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setCode("OML_O21");
        messageEvent.setSource("Pronto Soccorso");
        messageEventRepository.save(messageEvent);
        EventTimeline eventTimeline = messageEventTimelineOMLO21.saveEventTimeLineOMLO21(omlO21, messageEvent);
        messageEvent.setEventTimeline(eventTimeline);
        return messageEventRepository.save(messageEvent);
    }
}
