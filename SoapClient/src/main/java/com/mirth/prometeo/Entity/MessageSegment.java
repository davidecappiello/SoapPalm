package com.mirth.prometeo.Entity;

import jakarta.persistence.*;
import com.mirth.prometeo.Entity.MessageEvent;

@Entity
@Inheritance
@Table(name = "message_segment")
public class MessageSegment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "placer_group_number", nullable = true)
    private String placerGroupNumber;

    @ManyToOne
    @JoinColumn(name = "message_event_id", nullable = false)
    private MessageEvent messageEventId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPlacerGroupNumber() {
        return placerGroupNumber;
    }

    public void setPlacerGroupNumber(String placerGroupNumber) {
        this.placerGroupNumber = placerGroupNumber;
    }

    public MessageEvent getMessageEventId() {
        return messageEventId;
    }

    public void setMessageEventId(MessageEvent messageEventId) {
        this.messageEventId = messageEventId;
    }
}
