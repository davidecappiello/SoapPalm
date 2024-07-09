package com.mirth.prometeo.ServiceORLO22;

import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v25.message.ORL_O22;
import com.mirth.prometeo.Entity.EventTimeline;
import com.mirth.prometeo.Entity.MessageEvent;
import com.mirth.prometeo.Repository.MessageEventRepository;
import com.mirth.prometeo.Repository.MessageEventTimelineRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class  MessageEventTimelineORLO22 {

    @Autowired
    private MessageEventRepository messageEventRepository;
    @Autowired
    private MessageEventTimelineRepository messageEventTimelineRepository;

    @Transactional
    public EventTimeline updateEventTimeLineORLO22(ORL_O22 orlO22, MessageEvent messageEvent) throws DataTypeException {
            List<EventTimeline> eventTimelines = messageEventTimelineRepository.findAll();
            Optional<EventTimeline> eventTimelineOpt = eventTimelines.stream().max(Comparator.comparingInt(EventTimeline::getId));
        if(!eventTimelines.isEmpty()) {
            EventTimeline eventTimeline = eventTimelineOpt.get();

            return messageEventTimelineRepository.save(eventTimeline);
        }
        return null;
    }
}
