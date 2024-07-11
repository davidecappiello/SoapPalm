package com.mirth.prometeo.ServiceORLO22.Event;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v25.message.ORL_O22;
import com.mirth.prometeo.Entity.EventTimeline;
import com.mirth.prometeo.Entity.MessageEvent;
import com.mirth.prometeo.Repository.MessageEventRepository;
import com.mirth.prometeo.Repository.MessageEventTimelineRepository;
import com.mirth.prometeo.ServiceORLO22.MessageEventTimelineORLO22;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class MessageEventServiceORLO22 {

    @Autowired
    private MessageEventRepository messageEventRepository;
    @Autowired
    private MessageEventTimelineORLO22 messageEventTimelineORLO22;
    @Autowired
    private MessageEventTimelineRepository messageEventTimelineRepository;

    @Transactional
    public MessageEvent saveORLO22Message(ORL_O22 orlo22) throws DataTypeException {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setCode("ORL_O22");
        messageEvent.setSource("Middleware");
        messageEventRepository.save(messageEvent);
        EventTimeline eventTimeline = saveEventTimeLineORLO22(orlo22, messageEvent);
        messageEvent.setEventTimeline(eventTimeline);
        return messageEventRepository.save(messageEvent);
    }

    @Transactional
    public EventTimeline saveEventTimeLineORLO22(ORL_O22 orlO22, MessageEvent messageEvent) throws DataTypeException {
        EventTimeline eventTimeline = new EventTimeline();
        eventTimeline.setIssueDate(orlO22.getRESPONSE().getPATIENT().getORDER().getORC().getQuantityTiming(0).getStartDateTime().getTime().getValueAsDate());
        if (orlO22.getMSA().getAcknowledgmentCode().getValue().equals("AA")) {
            eventTimeline.setStatus("ACCEPTED");
        } else if (orlO22.getMSA().getAcknowledgmentCode().getValue().equals("AE")) {
            eventTimeline.setStatus("ERROR");
        } else if (orlO22.getMSA().getAcknowledgmentCode().getValue().equals("AR")) {
            eventTimeline.setStatus("REJECTED");
        }
        eventTimeline.setStatusChangeDate(LocalDateTime.now());
        eventTimeline.setMessageEvent(messageEvent);
        return messageEventTimelineRepository.save(eventTimeline);
    }
}
