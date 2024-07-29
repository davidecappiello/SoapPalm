package it.prometeo.Entity;

import jakarta.persistence.*;
import it.prometeo.Entity.MessageEvent;

@Entity
@Inheritance
@Table(name = "PRO_MESSAGE_SEGMENT")
public class MessageSegment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code", nullable = false)
    private String code;

    @Lob
    @Column(name = "body", nullable = false, columnDefinition = "CLOB")
    private String body;

    /*@Column(name = "body", nullable = false, columnDefinition = "TEXT")
    private String body;*/

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

    public MessageEvent getMessageEventId() {
        return messageEventId;
    }

    public void setMessageEventId(MessageEvent messageEventId) {
        this.messageEventId = messageEventId;
    }
}