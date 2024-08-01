package it.prometeo.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
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

    @Value("${hl7.sendingApplicationTransfusion}")
    private String sendingApplicationTransfusion;

    @Value("${hl7.sendingFacilityTransfusion}")
    private String sendingFacilityTransfusion;

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

    @Value("${hl7.socketClientPortPS}")
    private String socketClientPortPS;

    @Value("${hl7.socketClientPortTransfusion}")
    private String socketClientPortTransfusion;

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

    @Value("${hl7.messageStructureQBPQ11}")
    private String messageStructureQBPQ11;

    @Value("${hl7.segmentEvn}")
    private String segmentEvn;

    @Value("${hl7.segmentQpd}")
    private String segmentQpd;

    @Value("${hl7.segmentRcp}")
    private String segmentRcp;

    @Value("${hl7.processingID}")
    private String processingID;

    @Value("${hl7.versionID}")
    private String versionID;

    @Value("${hl7.queryChanges}")
    private String queryChanges;

    @Value("${hl7.columnAccessNumber}")
    private String columnAccessNumber;

    @Value("${hl7.columnTubeNumber}")
    private String columnTubeNumber;

    @Value("${hl7.columnStato}")
    private String columnStato;

    @Value("${hl7.columnFlagInoltrato}")
    private String columnFlagInoltrato;

    @Value("${hl7.columnLogUserID}")
    private String columnLogUserID;

    @Value("${hl7.columnHostOrderNumber}")
    private String columnHostOrderNumber;

    @Value("${hl7.columnReparto}")
    private String columnReparto;

    @Value("${hl7.columnDateCHK}")
    private String columnDateCHK;

    @Value("${hl7.messageCodeMdm}")
    private String messageCodeMdm;

    @Value("${hl7.triggerEventT02}")
    private String triggerEventT02;

    @Value("${hl7.triggerEventT10}")
    private String triggerEventT10;

    @Value("${hl7.triggerEventT11}")
    private String triggerEventT11;

    @Value("${hl7.messageStructureMDMT02evT02}")
    private String messageStructureMDMT02evT02;

    @Value("${hl7.messageStructureMDMT02evT10}")
    private String messageStructureMDMT02evT10;

    @Value("${hl7.messageStructureMDMT01evT11}")
    private String messageStructureMDMT01evT11;

    @Value("${hl7.orderStatusMDMT02evT02}")
    private String orderStatusMDMT02evT02;

    @Value("${hl7.setIDTXA}")
    private String setIDTXA;

    @Value("${hl7.documentTypeTXA}")
    private String documentTypeTXA;

    @Value("${hl7.documentContentPresentation}")
    private String documentContentPresentation;

    @Value("${hl7.documentCompletionStatus}")
    private String documentCompletionStatus;

    @Value("${hl7.documentAvailabilityStatusPartial}")
    private String documentAvailabilityStatusPartial;

    @Value("${hl7.documentAvailabilityStatusComplete}")
    private String documentAvailabilityStatusComplete;

    @Value("${hl7.authenticationPersonTimeStampMF}")
    private String authenticationPersonTimeStampMF;

    @Value("${hl7.authenticationPersonTimeStampN}")
    private String authenticationPersonTimeStampN;

    @Value("${hl7.queryReports}")
    private String queryReports;

    @Value("${hl7.columnID}")
    private String columnID;

    @Value("${hl7.columnRefID}")
    private String columnRefID;

    @Value("${hl7.columnDataAcc}")
    private String columnDataAcc;

    @Value("${hl7.columnDataRef}")
    private String columnDataRef;

    @Value("${hl7.columnMed}")
    private String columnMed;

    @Value("${hl7.columnRep}")
    private String columnRep;

    @Value("${hl7.columnName}")
    private String columnName;

    @Value("${hl7.columnFirstName}")
    private String columnFirstName;

    @Value("${hl7.columnSite}")
    private String columnSite;

    @Value("${hl7.columnLabo}")
    private String columnLabo;

    @Value("${hl7.columnDipa}")
    private String columnDipa;

    @Value("${hl7.columnVal}")
    private String columnVal;

    @Value("${hl7.columnLockID}")
    private String columnLockID;

    @Value("${hl7.columnLockDate}")
    private String columnLockDate;

    @Value("${hl7.columnMetaInfo}")
    private String columnMetaInfo;

    @Value("${hl7.columnMetaSign}")
    private String columnMetaSign;

    @Value("${hl7.columnStatus}")
    private String columnStatus;

    @Value("${hl7.columnDocument}")
    private String columnDocument;

    @Value("${hl7.columnLoadDate}")
    private String columnLoadDate;

    @Value("${hl7.columnSignDate}")
    private String columnSignDate;

    @Value("${hl7.columnCategory}")
    private String columnCategory;

    @Value("${hl7.columnFileName}")
    private String columnFileName;

    @Value("${hl7.valueTypeOBX}")
    private String valueTypeOBX;

    @Value("${hl7.observationIdentifierCE1}")
    private String observationIdentifierCE1;

    @Value("${hl7.observationIdentifierCE4}")
    private String observationIdentifierCE4;

    @Value("${hl7.observationResultStatusUnique}")
    private String observationResultStatusUnique;

    @Value("${hl7.observationResultStatusSplitted}")
    private String observationResultStatusSplitted;

    @Value("${hl7.responsibleObserveHD1}")
    private String responsibleObserveHD1;

    @Value("${hl7.responsibleObserveHD2}")
    private String responsibleObserveHD2;

    @Value("${hl7.orc1New}")
    private String orc1New;

    @Value("${hl7.orc5Scheduled}")
    private String orc5Scheduled;

    @Value("${hl7.orcReplacement}")
    private String orcReplacement;

    @Value("${hl7.orcCancel}")
    private String orcCancel;


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

    public String getSendingApplicationTransfusion() {
        return sendingApplicationTransfusion;
    }

    public String getSendingFacilityTransfusion() {
        return sendingFacilityTransfusion;
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

    public String getSocketClientPortPS() {
        return socketClientPortPS;
    }

    public String getSocketClientPortTransfusion() {
        return socketClientPortTransfusion;
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

    public String getMessageStructureQBPQ11() {
        return messageStructureQBPQ11;
    }

    public String getSegmentEvn() {
        return segmentEvn;
    }

    public String getSegmentQpd() {
        return segmentQpd;
    }

    public String getSegmentRcp() {
        return segmentRcp;
    }

    public String getProcessingID() {
        return processingID;
    }
    public String getVersionID() {
        return versionID;
    }
    public String getQueryChanges() {
        return queryChanges;
    }

    public String getColumnAccessNumber() {
        return columnAccessNumber;
    }

    public String getColumnTubeNumber() {
        return columnTubeNumber;
    }

    public String getColumnStato() {
        return columnStato;
    }

    public String getColumnFlagInoltrato() {
        return columnFlagInoltrato;
    }

    public String getColumnLogUserID() {
        return columnLogUserID;
    }

    public String getColumnHostOrderNumber() {
        return columnHostOrderNumber;
    }

    public String getColumnReparto() {
        return columnReparto;
    }

    public String getColumnDateCHK() {
        return columnDateCHK;
    }

    public String getMessageCodeMdm() {
        return messageCodeMdm;
    }

    public String getTriggerEventT02() {
        return triggerEventT02;
    }

    public String getMessageStructureMDMT02evT02() {
        return messageStructureMDMT02evT02;
    }

    public String getOrderStatusMDMT02evT02() {
        return orderStatusMDMT02evT02;
    }

    public String getSetIDTXA() {
        return setIDTXA;
    }

    public String getDocumentTypeTXA() {
        return documentTypeTXA;
    }

    public String getDocumentContentPresentation() {
        return documentContentPresentation;
    }

    public String getDocumentCompletionStatus() {
        return documentCompletionStatus;
    }

    public String getDocumentAvailabilityStatusPartial() {
        return documentAvailabilityStatusPartial;
    }

    public String getDocumentAvailabilityStatusComplete() {
        return documentAvailabilityStatusComplete;
    }

    public String getAuthenticationPersonTimeStampMF() {
        return authenticationPersonTimeStampMF;
    }

    public String getAuthenticationPersonTimeStampN() {
        return authenticationPersonTimeStampN;
    }

    public String getQueryReports() {
        return queryReports;
    }

    public String getColumnID() {
        return columnID;
    }

    public String getColumnRefID() {
        return columnRefID;
    }

    public String getColumnDataAcc() {
        return columnDataAcc;
    }

    public String getColumnDataRef() {
        return columnDataRef;
    }

    public String getColumnMed() {
        return columnMed;
    }

    public String getColumnRep() {
        return columnRep;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnFirstName() {
        return columnFirstName;
    }

    public String getColumnSite() {
        return columnSite;
    }

    public String getColumnLabo() {
        return columnLabo;
    }

    public String getColumnDipa() {
        return columnDipa;
    }

    public String getColumnVal() {
        return columnVal;
    }

    public String getColumnLockID() {
        return columnLockID;
    }

    public String getColumnLockDate() {
        return columnLockDate;
    }

    public String getColumnMetaInfo() {
        return columnMetaInfo;
    }

    public String getColumnMetaSign() {
        return columnMetaSign;
    }

    public String getColumnStatus() {
        return columnStatus;
    }

    public String getColumnDocument() {
        return columnDocument;
    }

    public String getColumnLoadDate() {
        return columnLoadDate;
    }

    public String getColumnSignDate() {
        return columnSignDate;
    }

    public String getColumnCategory() {
        return columnCategory;
    }

    public String getColumnFileName() {
        return columnFileName;
    }

    public String getValueTypeOBX() {
        return valueTypeOBX;
    }

    public String getObservationIdentifierCE1() {
        return observationIdentifierCE1;
    }

    public String getObservationIdentifierCE4() {
        return observationIdentifierCE4;
    }

    public String getObservationResultStatusUnique() {
        return observationResultStatusUnique;
    }

    public String getObservationResultStatusSplitted() {
        return observationResultStatusSplitted;
    }

    public String getResponsibleObserveHD1() {
        return responsibleObserveHD1;
    }

    public String getResponsibleObserveHD2() {
        return responsibleObserveHD2;
    }

    public String getOrc1New() {
        return orc1New;
    }

    public String getOrc5Scheduled() {
        return orc5Scheduled;
    }

    public String getTriggerEventT10() {
        return triggerEventT10;
    }

    public String getMessageStructureMDMT02evT10() {
        return messageStructureMDMT02evT10;
    }

    public String getOrcReplacement() {
        return orcReplacement;
    }

    public String getTriggerEventT11() {
        return triggerEventT11;
    }

    public String getMessageStructureMDMT01evT11() {
        return messageStructureMDMT01evT11;
    }

    public String getOrcCancel() {
        return orcCancel;
    }
}

