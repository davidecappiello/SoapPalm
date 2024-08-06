package it.prometeo.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HL7Config {

    @Value("${separator}")
    private String separator;

    @Value("${encodingCharacters}")
    private String encodingCharacters;

    @Value("${messageCodeOrl}")
    private String messageCodeOrl;

    @Value("${triggerEventO22}")
    private String triggerEventO22;

    @Value("${messageStructureORLO22}")
    private String messageStructureORLO22;

    @Value("${applicationError}")
    private String applicationError;

    @Value("${applicationRejected}")
    private String applicationRejected;

    @Value("${applicationAccepted}")
    private String applicationAccepted;

    @Value("${messageCodeAck}")
    private String messageCodeAck;

    @Value("${triggerEventO01}")
    private String triggerEventO01;

    @Value("${messageStructureACKO01}")
    private String messageStructureACKO01;

    @Value("${sendingApplication}")
    private String sendingApplication;

    @Value("${sendingFacility}")
    private String sendingFacility;

    @Value("${receivingApplication}")
    private String receivingApplication;

    @Value("${receivingFacility}")
    private String receivingFacility;

    @Value("${sendingApplicationTransfusion}")
    private String sendingApplicationTransfusion;

    @Value("${sendingFacilityTransfusion}")
    private String sendingFacilityTransfusion;

    @Value("${messageCodeOml}")
    private String messageCodeOml;

    @Value("${triggerEventO21}")
    private String triggerEventO21;

    @Value("${messageStructureOMLO21}")
    private String messageStructureOMLO21;

    @Value("${checkIn}")
    private String checkIn;

    @Value("${messageCodeOrm}")
    private String messageCodeOrm;

    @Value("${messageStructureORMO01}")
    private String messageStructureORMO01;

    @Value("${sourceDatabaseEventOMLO21PS}")
    private String sourceDatabaseEventOMLO21PS;

    @Value("${sourceDatabaseEventOMLO21PSCheckStatus}")
    private String sourceDatabaseEventOMLO21PSCheckStatus;

    @Value("${segmentMsh}")
    private String segmentMsh;

    @Value("${segmentPid}")
    private String segmentPid;

    @Value("${segmentPd1}")
    private String segmentPd1;

    @Value("${segmentPv1}")
    private String segmentPv1;

    @Value("${segmentTq1}")
    private String segmentTq1;

    @Value("${segmentOrc}")
    private String segmentOrc;

    @Value("${segmentObr}")
    private String segmentObr;

    @Value("${segmentObx}")
    private String segmentObx;

    @Value("${segmentMsa}")
    private String segmentMsa;

    @Value("${segmentSpm}")
    private String segmentSpm;

    @Value("${messageStructureORUR01}")
    private String messageStructureORUR01;

    @Value("${socketClientHost}")
    private String socketClientHost;

    @Value("${socketClientPortPS}")
    private String socketClientPortPS;

    @Value("${socketClientPortTransfusion}")
    private String socketClientPortTransfusion;

    @Value("${socketClientTimeout}")
    private int socketClientTimeout;

    @Value("${socketServerPort}")
    private int socketServerPort;

    @Value("${statusPositiveDatabase}")
    private String statusPositiveDatabase;

    @Value("${statusRejectedDatabase}")
    private String statusRejectedDatabase;

    @Value("${statusErrorDatabase}")
    private String statusErrorDatabase;

    @Value("${messageStructureQBPQ11}")
    private String messageStructureQBPQ11;

    @Value("${segmentEvn}")
    private String segmentEvn;

    @Value("${segmentQpd}")
    private String segmentQpd;

    @Value("${segmentRcp}")
    private String segmentRcp;

    @Value("${processingID}")
    private String processingID;

    @Value("${versionID}")
    private String versionID;

    @Value("${queryChangesCheckIn}")
    private String queryChangesCheckIn;

    @Value("${columnAccessNumber}")
    private String columnAccessNumber;

    @Value("${columnTubeNumber}")
    private String columnTubeNumber;

    @Value("${columnStato}")
    private String columnStato;

    @Value("${columnFlagInoltrato}")
    private String columnFlagInoltrato;

    @Value("${columnLogUserID}")
    private String columnLogUserID;

    @Value("${columnHostOrderNumber}")
    private String columnHostOrderNumber;

    @Value("${columnReparto}")
    private String columnReparto;

    @Value("${columnDateCHK}")
    private String columnDateCHK;

    @Value("${messageCodeMdm}")
    private String messageCodeMdm;

    @Value("${triggerEventT02}")
    private String triggerEventT02;

    @Value("${triggerEventT10}")
    private String triggerEventT10;

    @Value("${triggerEventT11}")
    private String triggerEventT11;

    @Value("${messageStructureMDMT02evT02}")
    private String messageStructureMDMT02evT02;

    @Value("${messageStructureMDMT02evT10}")
    private String messageStructureMDMT02evT10;

    @Value("${messageStructureMDMT01evT11}")
    private String messageStructureMDMT01evT11;

    @Value("${orderStatusMDMT02evT02}")
    private String orderStatusMDMT02evT02;

    @Value("${setIDTXA}")
    private String setIDTXA;

    @Value("${documentTypeTXA}")
    private String documentTypeTXA;

    @Value("${documentContentPresentation}")
    private String documentContentPresentation;

    @Value("${documentCompletionStatus}")
    private String documentCompletionStatus;

    @Value("${documentAvailabilityStatusPartial}")
    private String documentAvailabilityStatusPartial;

    @Value("${documentAvailabilityStatusComplete}")
    private String documentAvailabilityStatusComplete;

    @Value("${authenticationPersonTimeStampMF}")
    private String authenticationPersonTimeStampMF;

    @Value("${authenticationPersonTimeStampN}")
    private String authenticationPersonTimeStampN;

    @Value("${queryReports}")
    private String queryReports;

    @Value("${columnID}")
    private String columnID;

    @Value("${columnRefID}")
    private String columnRefID;

    @Value("${columnDataAcc}")
    private String columnDataAcc;

    @Value("${columnDataRef}")
    private String columnDataRef;

    @Value("${columnMed}")
    private String columnMed;

    @Value("${columnRep}")
    private String columnRep;

    @Value("${columnName}")
    private String columnName;

    @Value("${columnFirstName}")
    private String columnFirstName;

    @Value("${columnSite}")
    private String columnSite;

    @Value("${columnLabo}")
    private String columnLabo;

    @Value("${columnDipa}")
    private String columnDipa;

    @Value("${columnVal}")
    private String columnVal;

    @Value("${columnLockID}")
    private String columnLockID;

    @Value("${columnLockDate}")
    private String columnLockDate;

    @Value("${columnMetaInfo}")
    private String columnMetaInfo;

    @Value("${columnMetaSign}")
    private String columnMetaSign;

    @Value("${columnStatus}")
    private String columnStatus;

    @Value("${columnDocument}")
    private String columnDocument;

    @Value("${columnLoadDate}")
    private String columnLoadDate;

    @Value("${columnSignDate}")
    private String columnSignDate;

    @Value("${columnCategory}")
    private String columnCategory;

    @Value("${columnFileName}")
    private String columnFileName;

    @Value("${valueTypeOBX}")
    private String valueTypeOBX;

    @Value("${observationIdentifierCE1}")
    private String observationIdentifierCE1;

    @Value("${observationIdentifierCE4}")
    private String observationIdentifierCE4;

    @Value("${observationResultStatusUnique}")
    private String observationResultStatusUnique;

    @Value("${observationResultStatusSplitted}")
    private String observationResultStatusSplitted;

    @Value("${responsibleObserveHD1}")
    private String responsibleObserveHD1;

    @Value("${responsibleObserveHD2}")
    private String responsibleObserveHD2;

    @Value("${orc1New}")
    private String orc1New;

    @Value("${orc5Scheduled}")
    private String orc5Scheduled;

    @Value("${orcReplacement}")
    private String orcReplacement;

    @Value("${orcCancel}")
    private String orcCancel;

    @Value("${queryChangesDocument}")
    private String queryChangesDocument;

    @Value("${queryCheckCountDataDocument}")
    private String queryCheckCountDataDocument;

    @Value("${queryCheckCountDataCheckIn}")
    private String queryCheckCountDataCheckIn;

    @Value("${segmentTxa}")
    private String segmentTxa;

    @Value("${socketServerPortTransfusion}")
    private int socketServerPortTransfusion;

    @Value("${wsdlURLPS}")
    private String wsdlURLPS;

    @Value("${wsdlURLTransfusion}")
    private String wsdlURLTransfusion;


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
    public String getQueryChangesCheckIn() {
        return queryChangesCheckIn;
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

    public String getQueryChangesDocument() {
        return queryChangesDocument;
    }

    public String getQueryCheckCountDataDocument() {
        return queryCheckCountDataDocument;
    }

    public String getSegmentTxa() {
        return segmentTxa;
    }

    public int getSocketServerPortTransfusion() {
        return socketServerPortTransfusion;
    }

    public String getQueryCheckCountDataCheckIn() {
        return queryCheckCountDataCheckIn;
    }
    public String getWsdlURLPS() {
        return wsdlURLPS;
    }

    public String getWsdlURLTransfusion() {
        return wsdlURLTransfusion;
    }
}

