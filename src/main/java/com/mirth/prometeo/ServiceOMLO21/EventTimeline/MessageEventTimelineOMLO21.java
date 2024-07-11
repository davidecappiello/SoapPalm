package com.mirth.prometeo.ServiceOMLO21.EventTimeline;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import com.mirth.prometeo.Entity.EventTimeline;
import com.mirth.prometeo.Entity.MessageEvent;
import com.mirth.prometeo.Repository.MessageEventTimelineRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageEventTimelineOMLO21 {

    @Autowired
    private MessageEventTimelineRepository messageEventTimelineRepository;

    //chiedere ad Antonio la roba sulla data del loro messaggio tq 4

    @Transactional
    public EventTimeline saveEventTimeLineOMLO21(OML_O21 omlO21, MessageEvent messageEvent) throws DataTypeException {
        EventTimeline eventTimeline = new EventTimeline();
        eventTimeline.setIssueDate(omlO21.getORDER().getORC().getQuantityTiming(0).getStartDateTime().getTime().getValueAsDate());
        eventTimeline.setStatus("SENT TO TD");
        eventTimeline.setMessageEvent(messageEvent);
        return messageEventTimelineRepository.save(eventTimeline);
    }
}
