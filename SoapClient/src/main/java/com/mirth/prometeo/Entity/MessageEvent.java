package com.mirth.prometeo.Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "message_event")
public class MessageEvent extends BaseObject {

    private static final long serialVersionUID = 1L;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "source", nullable = false)
    private String source;

    @OneToMany(mappedBy = "messageEventId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MessageSegment> messageSegments = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "event_timeline_id", referencedColumnName = "id")
    private EventTimeline eventTimeline;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<MessageSegment> getMessageSegments() {
        return messageSegments;
    }

    public void setMessageSegments(List<MessageSegment> messageSegments) {
        this.messageSegments.clear();
        if (messageSegments != null) {
            for (MessageSegment messageSegment : messageSegments) {
                addMessageSegment(messageSegment);
            }
        }
    }

    public void addMessageSegment(MessageSegment messageSegment) {
        messageSegments.add(messageSegment);
        messageSegment.setMessageEventId(this);
    }

    public void removeMessageSegment(MessageSegment messageSegment) {
        messageSegments.remove(messageSegment);
        messageSegment.setMessageEventId(null);
    }

    public EventTimeline getEventTimeline() {
        return eventTimeline;
    }

    public void setEventTimeline(EventTimeline eventTimeline) {
        this.eventTimeline = eventTimeline;
    }
}
