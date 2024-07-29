package it.prometeo.Entity;

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

    @Column(name = "placer_group_number", nullable = true)
    private String placerGroupNumber;

    @Column(name = "filler_order_number", nullable = true)
    private String fillerOrderNumber;

    @Column(name = "status", nullable = true)
    private String status;

    @Column(name = "error_description", nullable = true)
    private String errorDescription;

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

    public String getPlacerGroupNumber() {
        return placerGroupNumber;
    }

    public void setPlacerGroupNumber(String placerGroupNumber) {
        this.placerGroupNumber = placerGroupNumber;
    }

    public String getFillerOrderNumber() {
        return fillerOrderNumber;
    }

    public void setFillerOrderNumber(String fillerOrderNumber) {
        this.fillerOrderNumber = fillerOrderNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
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