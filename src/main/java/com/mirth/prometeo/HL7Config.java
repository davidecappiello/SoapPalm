package com.mirth.prometeo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HL7Config {

    @Value("${hl7.separator}")
    private String separator;

    @Value("${hl7.encodingCharacters}")
    private String encodingCharacters;

    @Value("${hl7.messageCodeOrl}")
    private String messageCodeOrl;

    @Value("${hl7.triggerEventO22}")
    private String triggerEventO22;

    @Value("${hl7.messageStructureORLO22}")
    private String messageStructureORLO22;

    @Value("${hl7.applicationError}")
    private String applicationError;

    @Value("${hl7.applicationRejected}")
    private String applicationRejected;

    @Value("${hl7.applicationAccepted}")
    private String applicationAccepted;

    @Value("${hl7.messageCodeAck}")
    private String messageCodeAck;

    @Value("${hl7.triggerEventO01}")
    private String triggerEventO01;

    @Value("${hl7.messageStructureACKO01}")
    private String messageStructureACKO01;

    @Value("${hl7.sendingApplication}")
    private String sendingApplication;

    @Value("${hl7.sendingFacility}")
    private String sendingFacility;

    @Value("${hl7.receivingApplication}")
    private String receivingApplication;

    @Value("${hl7.receivingFacility}")
    private String receivingFacility;

    @Value("${hl7.messageCodeOml}")
    private String messageCodeOml;

    @Value("${hl7.triggerEventO21}")
    private String triggerEventO21;

    @Value("${hl7.messageStructureOMLO21}")
    private String messageStructureOMLO21;

    @Value("${hl7.checkIn}")
    private String checkIn;

    @Value("${hl7.messageCodeOrm}")
    private String messageCodeOrm;

    @Value("${hl7.messageStructureORMO01}")
    private String messageStructureORMO01;

    @Value("${hl7.sourceDatabaseEventOMLO21PS}")
    private String sourceDatabaseEventOMLO21PS;

    @Value("${hl7.sourceDatabaseEventOMLO21PSCheckStatus}")
    private String sourceDatabaseEventOMLO21PSCheckStatus;

    @Value("${hl7.segmentMsh}")
    private String segmentMsh;

    @Value("${hl7.segmentPid}")
    private String segmentPid;

    @Value("${hl7.segmentPd1}")
    private String segmentPd1;

    @Value("${hl7.segmentPv1}")
    private String segmentPv1;

    @Value("${hl7.segmentTq1}")
    private String segmentTq1;

    @Value("${hl7.segmentOrc}")
    private String segmentOrc;

    @Value("${hl7.segmentObr}")
    private String segmentObr;

    @Value("${hl7.segmentObx}")
    private String segmentObx;

    @Value("${hl7.segmentMsa}")
    private String segmentMsa;

    @Value("${hl7.segmentSpm}")
    private String segmentSpm;

    @Value("${hl7.messageStructureORUR01}")
    private String messageStructureORUR01;

    @Value("${hl7.socketClientHost}")
    private String socketClientHost;

    @Value("${hl7.socketClientPort}")
    private String socketClientPort;

    @Value("${hl7.socketClientTimeout}")
    private int socketClientTimeout;

    @Value("${hl7.socketServerPort}")
    private int socketServerPort;

    @Value("${hl7.statusPositiveDatabase}")
    private String statusPositiveDatabase;

    @Value("${hl7.statusRejectedDatabase}")
    private String statusRejectedDatabase;

    @Value("${hl7.statusErrorDatabase}")
    private String statusErrorDatabase;

    public String getSeparator() {
        return separator;
    }

    public String getEncodingCharacters() {
        return encodingCharacters;
    }

    public String getMessageCodeOrl() {
        return messageCodeOrl;
    }

    public String getTriggerEventO22() {
        return triggerEventO22;
    }

    public String getMessageStructureORLO22() {
        return messageStructureORLO22;
    }

    public String getApplicationError() {
        return applicationError;
    }

    public String getApplicationRejected() {
        return applicationRejected;
    }

    public String getApplicationAccepted() {
        return applicationAccepted;
    }

    public String getMessageCodeAck() {
        return messageCodeAck;
    }

    public String getTriggerEventO01() {
        return triggerEventO01;
    }

    public String getMessageStructureACKO01() {
        return messageStructureACKO01;
    }

    public String getSendingFacility() {
        return sendingFacility;
    }

    public String getReceivingApplication() {
        return receivingApplication;
    }

    public String getSendingApplication() {
        return sendingApplication;
    }

    public String getReceivingFacility() {
        return receivingFacility;
    }

    public String getMessageCodeOml() {
        return messageCodeOml;
    }

    public String getTriggerEventO21() {
        return triggerEventO21;
    }

    public String getMessageStructureOMLO21() {
        return messageStructureOMLO21;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getMessageCodeOrm() {
        return messageCodeOrm;
    }

    public String getMessageStructureORMO01() {
        return messageStructureORMO01;
    }

    public String getSourceDatabaseEventOMLO21PS() {
        return sourceDatabaseEventOMLO21PS;
    }

    public String getSourceDatabaseEventOMLO21PSCheckStatus() {
        return sourceDatabaseEventOMLO21PSCheckStatus;
    }

    public String getSegmentMsh() {
        return segmentMsh;
    }

    public String getSegmentPid() {
        return segmentPid;
    }

    public String getSegmentPd1() {
        return segmentPd1;
    }

    public String getSegmentPv1() {
        return segmentPv1;
    }

    public String getSegmentTq1() {
        return segmentTq1;
    }

    public String getSegmentOrc() {
        return segmentOrc;
    }

    public String getSegmentObr() {
        return segmentObr;
    }

    public String getSegmentObx() {
        return segmentObx;
    }

    public String getSegmentMsa() {
        return segmentMsa;
    }

    public String getSegmentSpm() {
        return segmentSpm;
    }

    public String getMessageStructureORUR01() {
        return messageStructureORUR01;
    }

    public String getSocketClientHost() {
        return socketClientHost;
    }

    public String getSocketClientPort() {
        return socketClientPort;
    }

    public int getSocketClientTimeout() {
        return socketClientTimeout;
    }

    public int getSocketServerPort() {
        return socketServerPort;
    }

    public String getStatusPositiveDatabase() {
        return statusPositiveDatabase;
    }

    public String getStatusRejectedDatabase() {
        return statusRejectedDatabase;
    }

    public String getStatusErrorDatabase() {
        return statusErrorDatabase;
    }
}
