package com.mirth.prometeo.Entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "PRO_MESSAGE_EVENT")
public class MessageEvent extends BaseObject {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "source", nullable = false)
    private String source;

    @OneToMany(mappedBy = "messageEventId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MessageSegment> messageSegments = new ArrayList<>();

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

}