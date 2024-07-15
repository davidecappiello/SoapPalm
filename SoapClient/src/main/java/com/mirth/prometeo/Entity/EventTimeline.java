package com.mirth.prometeo.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance
@Table(name = "event_timeline")
public class EventTimeline extends BaseObject {

    @Column(name = "issue_date", nullable = false)
    private Date issueDate;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "status_change_date", nullable = true)
    private LocalDateTime statusChangeDate;

    @OneToOne(mappedBy = "eventTimeline")
    private MessageEvent messageEvent;

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getStatusChangeDate() {
        return statusChangeDate;
    }

    public void setStatusChangeDate(LocalDateTime statusChangeDate) {
        this.statusChangeDate = statusChangeDate;
    }

    public MessageEvent getMessageEvent() {
        return messageEvent;
    }

    public void setMessageEvent(MessageEvent messageEvent) {
        this.messageEvent = messageEvent;
    }
}
