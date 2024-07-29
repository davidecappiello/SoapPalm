package it.prometeo.HL7Palm.SegmentFactory;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.datatype.CE;
import ca.uhn.hl7v2.model.v25.group.OML_O21_ORDER;
import ca.uhn.hl7v2.model.v25.group.ORL_O22_ORDER;
import ca.uhn.hl7v2.model.v25.group.ORM_O01_ORDER;
import ca.uhn.hl7v2.model.v25.message.ACK;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.message.ORL_O22;
import ca.uhn.hl7v2.model.v25.message.ORM_O01;
import ca.uhn.hl7v2.model.v25.segment.*;
import it.prometeo.Configuration.HL7Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SegmentFactoryORLO22 {

    private static HL7Config hl7Config = null;

    @Autowired
    public SegmentFactoryORLO22(HL7Config hl7Config) {
        SegmentFactoryORLO22.hl7Config = hl7Config;
    }

    public static void createMSHSegmentIntegrateORLO22FromOML(MSH mshSegmentIntegrate, OML_O21 oml, ACK ackMessage) throws HL7Exception {

        MSH mshSource = oml.getMSH();

        mshSegmentIntegrate.getFieldSeparator().setValue(hl7Config.getSeparator());
        mshSegmentIntegrate.getEncodingCharacters().setValue(hl7Config.getEncodingCharacters());
        if(mshSource.getReceivingApplication().getNamespaceID().getValue() != null)
            mshSegmentIntegrate.getSendingApplication().getNamespaceID().setValue(mshSource.getReceivingApplication().getNamespaceID().getValue());
        if(mshSource.getReceivingFacility().getNamespaceID().getValue() != null)
            mshSegmentIntegrate.getSendingFacility().getNamespaceID().setValue(mshSource.getReceivingFacility().getNamespaceID().getValue());
        if(mshSource.getSendingApplication().getNamespaceID().getValue() != null)
            mshSegmentIntegrate.getReceivingApplication().getNamespaceID().setValue(mshSource.getSendingApplication().getNamespaceID().getValue());
        if(mshSource.getSendingFacility().getNamespaceID().getValue() != null)
            mshSegmentIntegrate.getReceivingFacility().getNamespaceID().setValue(mshSource.getSendingFacility().getNamespaceID().getValue());
        if(mshSource.getDateTimeOfMessage().getTime().getValue() != null)
            mshSegmentIntegrate.getDateTimeOfMessage().getTime().setValue(mshSource.getDateTimeOfMessage().getTime().getValue());
        if(mshSource.getMessageType().getMessageCode().getValue() != null)
            mshSegmentIntegrate.getMessageType().getMessageCode().setValue(hl7Config.getMessageCodeOrl());
        if(mshSource.getMessageType().getTriggerEvent().getValue() != null)
            mshSegmentIntegrate.getMessageType().getTriggerEvent().setValue(hl7Config.getTriggerEventO22());
        if(mshSource.getMessageType().getMessageStructure().getValue() != null)
            mshSegmentIntegrate.getMessageType().getMessageStructure().setValue(hl7Config.getMessageStructureORLO22());
        if(mshSource.getMessageControlID().getValue() != null)
            mshSegmentIntegrate.getMessageControlID().setValue(mshSource.getMessageControlID().getValue());
        if(mshSource.getProcessingID().getProcessingID().getValue() != null)
            mshSegmentIntegrate.getProcessingID().getProcessingID().setValue(mshSource.getProcessingID().getProcessingID().getValue());
        if(mshSource.getVersionID().getVersionID().getValue() != null)
            mshSegmentIntegrate.getVersionID().getVersionID().setValue(mshSource.getVersionID().getVersionID().getValue());
    }

    public static void createMSHSegmentIntegrateORLO22FromORM(MSH mshSegmentIntegrate, ORM_O01 ormMessage, ACK ackMessage) throws HL7Exception {

        MSH mshSource = ormMessage.getMSH();

        mshSegmentIntegrate.getFieldSeparator().setValue(hl7Config.getSeparator());
        mshSegmentIntegrate.getEncodingCharacters().setValue(hl7Config.getEncodingCharacters());
        if(mshSource.getReceivingApplication().getNamespaceID().getValue() != null)
            mshSegmentIntegrate.getSendingApplication().getNamespaceID().setValue(mshSource.getReceivingApplication().getNamespaceID().getValue());
        if(mshSource.getReceivingFacility().getNamespaceID().getValue() != null)
            mshSegmentIntegrate.getSendingFacility().getNamespaceID().setValue(mshSource.getReceivingFacility().getNamespaceID().getValue());
        if(mshSource.getSendingApplication().getNamespaceID().getValue() != null)
            mshSegmentIntegrate.getReceivingApplication().getNamespaceID().setValue(mshSource.getSendingApplication().getNamespaceID().getValue());
        if(mshSource.getSendingFacility().getNamespaceID().getValue() != null)
            mshSegmentIntegrate.getReceivingFacility().getNamespaceID().setValue(mshSource.getSendingFacility().getNamespaceID().getValue());
        if(mshSource.getDateTimeOfMessage().getTime().getValue() != null)
            mshSegmentIntegrate.getDateTimeOfMessage().getTime().setValue(mshSource.getDateTimeOfMessage().getTime().getValue());
        if(mshSource.getMessageType().getMessageCode().getValue() != null)
            mshSegmentIntegrate.getMessageType().getMessageCode().setValue(hl7Config.getMessageCodeOrl());
        if(mshSource.getMessageType().getTriggerEvent().getValue() != null)
            mshSegmentIntegrate.getMessageType().getTriggerEvent().setValue(hl7Config.getTriggerEventO22());
        if(mshSource.getMessageType().getMessageStructure().getValue() != null)
            mshSegmentIntegrate.getMessageType().getMessageStructure().setValue(hl7Config.getMessageStructureORLO22());
        if(mshSource.getMessageControlID().getValue() != null)
            mshSegmentIntegrate.getMessageControlID().setValue(mshSource.getMessageControlID().getValue());
        if(mshSource.getProcessingID().getProcessingID().getValue() != null)
            mshSegmentIntegrate.getProcessingID().getProcessingID().setValue(mshSource.getProcessingID().getProcessingID().getValue());
        if(mshSource.getVersionID().getVersionID().getValue() != null)
            mshSegmentIntegrate.getVersionID().getVersionID().setValue(mshSource.getVersionID().getVersionID().getValue());
    }

    public static void createMSASegmentIntegrateORLO22(MSA msaSegmentIntegrate, Message genericMessage) throws HL7Exception {

        MSA oldMSA = (MSA) genericMessage.get("MSA");
        MSH oldMSH = (MSH) genericMessage.get("MSH");

        if(oldMSA.getAcknowledgmentCode().getValue() != null)
            msaSegmentIntegrate.getAcknowledgmentCode().setValue(oldMSA.getAcknowledgmentCode().getValue());
        if(oldMSH.getMessageControlID().getValue() != null)
            msaSegmentIntegrate.getMessageControlID().setValue(oldMSH.getMessageControlID().getValue());
        if(msaSegmentIntegrate.getAcknowledgmentCode().getValue().equals(hl7Config.getApplicationError()) || (msaSegmentIntegrate.getAcknowledgmentCode().getValue().equals(hl7Config.getApplicationRejected()))) {
            ERR oldERR = (ERR) genericMessage.get("ERR");
            msaSegmentIntegrate.getTextMessage().setValue(oldERR.getHL7ErrorCode().getText().getValue());
        }
    }

    public static void createORCSegmentIntegrateORLO22(ORC orcSegmentIntegrate, ORC orcSource, int index) throws HL7Exception {

        if(orcSource.getOrderControl().getValue() != null)
            orcSegmentIntegrate.getOrderControl().setValue(orcSource.getOrderControl().getValue());
        if(orcSource.getOrderStatus().getValue() != null)
            orcSegmentIntegrate.getOrderStatus().setValue(orcSource.getOrderStatus().getValue());
        if(orcSource.getPlacerOrderNumber().getEntityIdentifier().getValue() != null)
            orcSegmentIntegrate.getPlacerOrderNumber().getEntityIdentifier().setValue(orcSource.getPlacerOrderNumber().getEntityIdentifier().getValue());
        if(orcSource.getPlacerOrderNumber().getNamespaceID().getValue() != null)
            orcSegmentIntegrate.getPlacerOrderNumber().getNamespaceID().setValue(orcSource.getPlacerOrderNumber().getNamespaceID().getValue());
        if(orcSource.getPlacerOrderNumber().getUniversalID().getValue() != null)
            orcSegmentIntegrate.getPlacerOrderNumber().getUniversalID().setValue(orcSource.getPlacerOrderNumber().getUniversalID().getValue());
        if(orcSource.getPlacerOrderNumber().getUniversalIDType().getValue() != null)
            orcSegmentIntegrate.getPlacerOrderNumber().getUniversalIDType().setValue(orcSource.getPlacerOrderNumber().getUniversalIDType().getValue());
        if(orcSource.getFillerOrderNumber().getEntityIdentifier().getValue() != null)
            orcSegmentIntegrate.getFillerOrderNumber().getEntityIdentifier().setValue(orcSource.getFillerOrderNumber().getEntityIdentifier().getValue());
        if(orcSource.getFillerOrderNumber().getNamespaceID().getValue() != null)
            orcSegmentIntegrate.getFillerOrderNumber().getNamespaceID().setValue(orcSource.getFillerOrderNumber().getNamespaceID().getValue());
        if(orcSource.getPlacerGroupNumber().getEntityIdentifier().getValue() != null)
            orcSegmentIntegrate.getPlacerGroupNumber().getEntityIdentifier().setValue(orcSource.getPlacerGroupNumber().getEntityIdentifier().getValue());
        if(orcSource.getQuantityTiming(0).getStartDateTime().getTime().getValue() != null)
            orcSegmentIntegrate.getQuantityTiming(0).getStartDateTime().getTime().setValue(orcSource.getQuantityTiming(0).getStartDateTime().getTime().getValue());
        if(orcSource.getDateTimeOfTransaction().getTime().getValue() != null)
            orcSegmentIntegrate.getDateTimeOfTransaction().getTime().setValue(orcSource.getDateTimeOfTransaction().getTime().getValue());
        if(orcSource.getOrderingProvider(0).getIDNumber().getValue() != null)
            orcSegmentIntegrate.getOrderingProvider(0).getIDNumber().setValue(orcSource.getOrderingProvider(0).getIDNumber().getValue());
        if(orcSource.getOrderingProvider(0).getFamilyName().getSurname().getValue() != null)
            orcSegmentIntegrate.getOrderingProvider(0).getFamilyName().getSurname().setValue(orcSource.getOrderingProvider(0).getFamilyName().getSurname().getValue());
        if(orcSource.getOrderingProvider(0).getGivenName().getValue() != null)
            orcSegmentIntegrate.getOrderingProvider(0).getGivenName().setValue(orcSource.getOrderingProvider(0).getGivenName().getValue());
        if(orcSource.getOrderingFacilityName(0).getOrganizationName().getValue() != null)
            orcSegmentIntegrate.getOrderingFacilityName(0).getOrganizationName().setValue(orcSource.getOrderingFacilityName(0).getOrganizationName().getValue());
        if(orcSource.getOrderingFacilityName(0).getOrganizationIdentifier().getValue() != null)
            orcSegmentIntegrate.getOrderingFacilityName(0).getOrganizationIdentifier().setValue(orcSource.getOrderingFacilityName(0).getOrganizationIdentifier().getValue());
        if(orcSource.getConfidentialityCode().getIdentifier().getValue() != null)
            orcSegmentIntegrate.getConfidentialityCode().getIdentifier().setValue(orcSource.getConfidentialityCode().getIdentifier().getValue());
        if(orcSource.getConfidentialityCode().getText().getValue() != null)
            orcSegmentIntegrate.getConfidentialityCode().getText().setValue(orcSource.getConfidentialityCode().getText().getValue());
    }

    public static void createOBRSegmentIntegrateORLO22FromOML(OBR obrSegmentIntegrate, OML_O21 oml_o21, int index) throws HL7Exception {
        OML_O21_ORDER observationRequest = oml_o21.getORDER(index);
        OBR obrSource = observationRequest.getOBSERVATION_REQUEST().getOBR();
        ORC orcSource = oml_o21.getORDER().getORC();

        if(obrSource.getSetIDOBR().getValue() != null)
            obrSegmentIntegrate.getSetIDOBR().setValue(obrSource.getSetIDOBR().getValue());
        if(obrSource.getPlacerOrderNumber().getEntityIdentifier().getValue() != null)
            obrSegmentIntegrate.getPlacerOrderNumber().getEntityIdentifier().setValue(obrSource.getPlacerOrderNumber().getEntityIdentifier().getValue());
        if(obrSource.getFillerOrderNumber().getEntityIdentifier().getValue() != null)
            obrSegmentIntegrate.getFillerOrderNumber().getEntityIdentifier().setValue(obrSource.getFillerOrderNumber().getEntityIdentifier().getValue());
        if(obrSource.getUniversalServiceIdentifier().getIdentifier().getValue() != null)
            obrSegmentIntegrate.getUniversalServiceIdentifier().getIdentifier().setValue(obrSource.getUniversalServiceIdentifier().getIdentifier().getValue());
        if(obrSource.getUniversalServiceIdentifier().getText().getValue() != null)
            obrSegmentIntegrate.getUniversalServiceIdentifier().getText().setValue(obrSource.getUniversalServiceIdentifier().getText().getValue());
        if(obrSource.getUniversalServiceIdentifier().getNameOfCodingSystem().getValue() != null)
            obrSegmentIntegrate.getUniversalServiceIdentifier().getNameOfCodingSystem().setValue(obrSource.getUniversalServiceIdentifier().getNameOfCodingSystem().getValue());
        if(obrSource.getObservationDateTime().getTime().getValue() != null)
            obrSegmentIntegrate.getObservationDateTime().getTime().setValue(obrSource.getObservationDateTime().getTime().getValue());
        //Controllare il campo obr.10 perchè discordanti.. per ora non inserito nel messaggio di test
        if(obrSource.getRelevantClinicalInformation().getValue() != null)
            obrSegmentIntegrate.getRelevantClinicalInformation().setValue(obrSource.getRelevantClinicalInformation().getValue());
        if(orcSource.getOrderingProvider(0).getIDNumber().getValue() != null)
            obrSegmentIntegrate.getOrderingProvider(0).getIDNumber().setValue(orcSource.getOrderingProvider(0).getIDNumber().getValue());
        if(orcSource.getOrderingProvider(0).getFamilyName().getSurname().getValue() != null)
            obrSegmentIntegrate.getOrderingProvider(0).getFamilyName().getSurname().setValue(orcSource.getOrderingProvider(0).getFamilyName().getSurname().getValue());
        if(orcSource.getOrderingProvider(0).getGivenName().getValue() != null)
            obrSegmentIntegrate.getOrderingProvider(0).getGivenName().setValue(orcSource.getOrderingProvider(0).getGivenName().getValue());
        if(obrSource.getPlacerField2().getValue() != null)
            obrSegmentIntegrate.getPlacerField2().setValue(obrSource.getPlacerField2().getValue());
        if(obrSource.getResultsRptStatusChngDateTime().getTime().getValue() != null)
            obrSegmentIntegrate.getResultsRptStatusChngDateTime().getTime().setValue(obrSource.getResultsRptStatusChngDateTime().getTime().getValue());
        if(orcSource.getOrderingProvider(0).getIDNumber().getValue() != null)
            obrSegmentIntegrate.getResultCopiesTo(0).getIDNumber().setValue(orcSource.getOrderingProvider(0).getIDNumber().getValue());
        if(orcSource.getOrderingProvider(0).getFamilyName().getSurname().getValue() != null)
            obrSegmentIntegrate.getResultCopiesTo(0).getFamilyName().getSurname().setValue(orcSource.getOrderingProvider(0).getFamilyName().getSurname().getValue());
        if(orcSource.getOrderingProvider(0).getGivenName().getValue() != null)
            obrSegmentIntegrate.getResultCopiesTo(0).getGivenName().setValue(orcSource.getOrderingProvider(0).getGivenName().getValue());
        if(obrSource.getDiagnosticServSectID().getValue() != null)
            obrSegmentIntegrate.getDiagnosticServSectID().setValue(obrSource.getDiagnosticServSectID().getValue());

        for (int i = 0; i < obrSource.getPlacerSupplementalServiceInformationReps(); i++) {
            CE sourceSupplementalInfo = obrSource.getPlacerSupplementalServiceInformation(i);
            CE targetSupplementalInfo = obrSegmentIntegrate.getPlacerSupplementalServiceInformation(i);

            if(sourceSupplementalInfo.getIdentifier() != null)
                targetSupplementalInfo.getIdentifier().setValue(sourceSupplementalInfo.getIdentifier().getValue());
            if(sourceSupplementalInfo.getText().getValue() != null)
                targetSupplementalInfo.getText().setValue(sourceSupplementalInfo.getText().getValue());
            if(sourceSupplementalInfo.getNameOfCodingSystem().getValue() != null)
                targetSupplementalInfo.getNameOfCodingSystem().setValue(sourceSupplementalInfo.getNameOfCodingSystem().getValue());
        }
    }
    public static void createOBRSegmentIntegrateORLO22FromORM(OBR obrSegmentIntegrate, ORM_O01 ormMessage, int index) throws HL7Exception {
        ORM_O01_ORDER observationRequest = ormMessage.getORDER(index);
        OBR obrSource = observationRequest.getORDER_DETAIL().getOBR();
        ORC orcSource = ormMessage.getORDER().getORC();

        if(obrSource.getSetIDOBR().getValue() != null)
            obrSegmentIntegrate.getSetIDOBR().setValue(obrSource.getSetIDOBR().getValue());
        if(obrSource.getPlacerOrderNumber().getEntityIdentifier().getValue() != null)
            obrSegmentIntegrate.getPlacerOrderNumber().getEntityIdentifier().setValue(obrSource.getPlacerOrderNumber().getEntityIdentifier().getValue());
        if(obrSource.getFillerOrderNumber().getEntityIdentifier().getValue() != null)
            obrSegmentIntegrate.getFillerOrderNumber().getEntityIdentifier().setValue(obrSource.getFillerOrderNumber().getEntityIdentifier().getValue());
        if(obrSource.getUniversalServiceIdentifier().getIdentifier().getValue() != null)
            obrSegmentIntegrate.getUniversalServiceIdentifier().getIdentifier().setValue(obrSource.getUniversalServiceIdentifier().getIdentifier().getValue());
        if(obrSource.getUniversalServiceIdentifier().getText().getValue() != null)
            obrSegmentIntegrate.getUniversalServiceIdentifier().getText().setValue(obrSource.getUniversalServiceIdentifier().getText().getValue());
        if(obrSource.getUniversalServiceIdentifier().getNameOfCodingSystem().getValue() != null)
            obrSegmentIntegrate.getUniversalServiceIdentifier().getNameOfCodingSystem().setValue(obrSource.getUniversalServiceIdentifier().getNameOfCodingSystem().getValue());
        if(obrSource.getObservationDateTime().getTime().getValue() != null)
            obrSegmentIntegrate.getObservationDateTime().getTime().setValue(obrSource.getObservationDateTime().getTime().getValue());
        //Controllare il campo obr.10 perchè discordanti.. per ora non inserito nel messaggio di test
        if(obrSource.getRelevantClinicalInformation().getValue() != null)
            obrSegmentIntegrate.getRelevantClinicalInformation().setValue(obrSource.getRelevantClinicalInformation().getValue());
        if(orcSource.getOrderingProvider(0).getIDNumber().getValue() != null)
            obrSegmentIntegrate.getOrderingProvider(0).getIDNumber().setValue(orcSource.getOrderingProvider(0).getIDNumber().getValue());
        if(orcSource.getOrderingProvider(0).getFamilyName().getSurname().getValue() != null)
            obrSegmentIntegrate.getOrderingProvider(0).getFamilyName().getSurname().setValue(orcSource.getOrderingProvider(0).getFamilyName().getSurname().getValue());
        if(orcSource.getOrderingProvider(0).getGivenName().getValue() != null)
            obrSegmentIntegrate.getOrderingProvider(0).getGivenName().setValue(orcSource.getOrderingProvider(0).getGivenName().getValue());
        if(obrSource.getPlacerField2().getValue() != null)
            obrSegmentIntegrate.getPlacerField2().setValue(obrSource.getPlacerField2().getValue());
        if(obrSource.getResultsRptStatusChngDateTime().getTime().getValue() != null)
            obrSegmentIntegrate.getResultsRptStatusChngDateTime().getTime().setValue(obrSource.getResultsRptStatusChngDateTime().getTime().getValue());
        if(orcSource.getOrderingProvider(0).getIDNumber().getValue() != null)
            obrSegmentIntegrate.getResultCopiesTo(0).getIDNumber().setValue(orcSource.getOrderingProvider(0).getIDNumber().getValue());
        if(orcSource.getOrderingProvider(0).getFamilyName().getSurname().getValue() != null)
            obrSegmentIntegrate.getResultCopiesTo(0).getFamilyName().getSurname().setValue(orcSource.getOrderingProvider(0).getFamilyName().getSurname().getValue());
        if(orcSource.getOrderingProvider(0).getGivenName().getValue() != null)
            obrSegmentIntegrate.getResultCopiesTo(0).getGivenName().setValue(orcSource.getOrderingProvider(0).getGivenName().getValue());
        if(obrSource.getDiagnosticServSectID().getValue() != null)
            obrSegmentIntegrate.getDiagnosticServSectID().setValue(obrSource.getDiagnosticServSectID().getValue());

        for (int i = 0; i < obrSource.getPlacerSupplementalServiceInformationReps(); i++) {
            CE sourceSupplementalInfo = obrSource.getPlacerSupplementalServiceInformation(i);
            CE targetSupplementalInfo = obrSegmentIntegrate.getPlacerSupplementalServiceInformation(i);

            if(sourceSupplementalInfo.getIdentifier() != null)
                targetSupplementalInfo.getIdentifier().setValue(sourceSupplementalInfo.getIdentifier().getValue());
            if(sourceSupplementalInfo.getText().getValue() != null)
                targetSupplementalInfo.getText().setValue(sourceSupplementalInfo.getText().getValue());
            if(sourceSupplementalInfo.getNameOfCodingSystem().getValue() != null)
                targetSupplementalInfo.getNameOfCodingSystem().setValue(sourceSupplementalInfo.getNameOfCodingSystem().getValue());
        }
    }

    public static void createMSHSegmentIntegrateORLO22FromORLPS(MSH mshSegmentIntegrate, ORL_O22 orlFromTD) throws HL7Exception {

        MSH mshSource = orlFromTD.getMSH();

        mshSegmentIntegrate.getFieldSeparator().setValue(hl7Config.getSeparator());
        mshSegmentIntegrate.getEncodingCharacters().setValue(hl7Config.getEncodingCharacters());
        if(mshSource.getSendingApplication().getNamespaceID().getValue() != null)
            mshSegmentIntegrate.getReceivingApplication().getNamespaceID().setValue(hl7Config.getReceivingApplication());
        if(mshSource.getSendingFacility().getNamespaceID().getValue() != null)
            mshSegmentIntegrate.getReceivingFacility().getNamespaceID().setValue(hl7Config.getReceivingFacility());
        if(mshSource.getReceivingApplication().getNamespaceID().getValue() != null)
            mshSegmentIntegrate.getSendingApplication().getNamespaceID().setValue(hl7Config.getSendingApplication());
        if(mshSource.getReceivingFacility().getNamespaceID().getValue() != null)
            mshSegmentIntegrate.getSendingFacility().getNamespaceID().setValue(hl7Config.getSendingFacility());
        if(mshSource.getDateTimeOfMessage().getTime().getValue() != null)
            mshSegmentIntegrate.getDateTimeOfMessage().getTime().setValue(mshSource.getDateTimeOfMessage().getTime().getValue());
        if(mshSource.getMessageType().getMessageCode().getValue() != null)
            mshSegmentIntegrate.getMessageType().getMessageCode().setValue(hl7Config.getMessageCodeOml());
        if(mshSource.getMessageType().getTriggerEvent().getValue() != null)
            mshSegmentIntegrate.getMessageType().getTriggerEvent().setValue(hl7Config.getTriggerEventO22());
        if(mshSource.getMessageType().getMessageStructure().getValue() != null)
            mshSegmentIntegrate.getMessageType().getMessageStructure().setValue(hl7Config.getMessageStructureORLO22());
        if(mshSource.getMessageControlID().getValue() != null)
            mshSegmentIntegrate.getMessageControlID().setValue(mshSource.getMessageControlID().getValue());
        if(mshSource.getProcessingID().getProcessingID().getValue() != null)
            mshSegmentIntegrate.getProcessingID().getProcessingID().setValue(mshSource.getProcessingID().getProcessingID().getValue());
        if(mshSource.getVersionID().getVersionID().getValue() != null)
            mshSegmentIntegrate.getVersionID().getVersionID().setValue(mshSource.getVersionID().getVersionID().getValue());
    }

    public static void createMSASegmentIntegrateORLO22FromORL(MSA msaSegmentIntegrate, ORL_O22 orlFromTD) throws HL7Exception {

        MSA oldMSA = orlFromTD.getMSA();
        MSH oldMSH = orlFromTD.getMSH();

        if(oldMSA.getAcknowledgmentCode().getValue() != null)
            msaSegmentIntegrate.getAcknowledgmentCode().setValue(oldMSA.getAcknowledgmentCode().getValue());
        if(oldMSH.getMessageControlID().getValue() != null)
            msaSegmentIntegrate.getMessageControlID().setValue(oldMSH.getMessageControlID().getValue());
        if(msaSegmentIntegrate.getAcknowledgmentCode().getValue().equals(hl7Config.getApplicationError()) || (msaSegmentIntegrate.getAcknowledgmentCode().getValue().equals(hl7Config.getApplicationRejected()))) {
            ERR oldERR = orlFromTD.getERR();
            msaSegmentIntegrate.getTextMessage().setValue(oldERR.getHL7ErrorCode().getText().getValue());
        }
    }

    public static void createOBRSegmentIntegrateORLO22FromORL(OBR obrSegmentIntegrate, ORL_O22 orlFromTD, int index) throws HL7Exception {
        ORL_O22_ORDER observationRequest = orlFromTD.getRESPONSE().getPATIENT().getORDER(index);
        OBR obrSource = observationRequest.getOBSERVATION_REQUEST().getOBR();
        ORC orcSource = orlFromTD.getRESPONSE().getPATIENT().getORDER().getORC();

        if(obrSource.getSetIDOBR().getValue() != null)
            obrSegmentIntegrate.getSetIDOBR().setValue(obrSource.getSetIDOBR().getValue());
        if(obrSource.getPlacerOrderNumber().getEntityIdentifier().getValue() != null)
            obrSegmentIntegrate.getPlacerOrderNumber().getEntityIdentifier().setValue(obrSource.getPlacerOrderNumber().getEntityIdentifier().getValue());
        if(obrSource.getFillerOrderNumber().getEntityIdentifier().getValue() != null)
            obrSegmentIntegrate.getFillerOrderNumber().getEntityIdentifier().setValue(obrSource.getFillerOrderNumber().getEntityIdentifier().getValue());
        if(obrSource.getUniversalServiceIdentifier().getIdentifier().getValue() != null)
            obrSegmentIntegrate.getUniversalServiceIdentifier().getIdentifier().setValue(obrSource.getUniversalServiceIdentifier().getIdentifier().getValue());
        if(obrSource.getUniversalServiceIdentifier().getText().getValue() != null)
            obrSegmentIntegrate.getUniversalServiceIdentifier().getText().setValue(obrSource.getUniversalServiceIdentifier().getText().getValue());
        if(obrSource.getUniversalServiceIdentifier().getNameOfCodingSystem().getValue() != null)
            obrSegmentIntegrate.getUniversalServiceIdentifier().getNameOfCodingSystem().setValue(obrSource.getUniversalServiceIdentifier().getNameOfCodingSystem().getValue());
        if(obrSource.getObservationDateTime().getTime().getValue() != null)
            obrSegmentIntegrate.getObservationDateTime().getTime().setValue(obrSource.getObservationDateTime().getTime().getValue());
        //Controllare il campo obr.10 perchè discordanti.. per ora non inserito nel messaggio di test
        if(obrSource.getRelevantClinicalInformation().getValue() != null)
            obrSegmentIntegrate.getRelevantClinicalInformation().setValue(obrSource.getRelevantClinicalInformation().getValue());
        if(orcSource.getOrderingProvider(0).getIDNumber().getValue() != null)
            obrSegmentIntegrate.getOrderingProvider(0).getIDNumber().setValue(orcSource.getOrderingProvider(0).getIDNumber().getValue());
        if(orcSource.getOrderingProvider(0).getFamilyName().getSurname().getValue() != null)
            obrSegmentIntegrate.getOrderingProvider(0).getFamilyName().getSurname().setValue(orcSource.getOrderingProvider(0).getFamilyName().getSurname().getValue());
        if(orcSource.getOrderingProvider(0).getGivenName().getValue() != null)
            obrSegmentIntegrate.getOrderingProvider(0).getGivenName().setValue(orcSource.getOrderingProvider(0).getGivenName().getValue());
        if(obrSource.getPlacerField2().getValue() != null)
            obrSegmentIntegrate.getPlacerField2().setValue(obrSource.getPlacerField2().getValue());
        if(obrSource.getResultsRptStatusChngDateTime().getTime().getValue() != null)
            obrSegmentIntegrate.getResultsRptStatusChngDateTime().getTime().setValue(obrSource.getResultsRptStatusChngDateTime().getTime().getValue());
        if(orcSource.getOrderingProvider(0).getIDNumber().getValue() != null)
            obrSegmentIntegrate.getResultCopiesTo(0).getIDNumber().setValue(orcSource.getOrderingProvider(0).getIDNumber().getValue());
        if(orcSource.getOrderingProvider(0).getFamilyName().getSurname().getValue() != null)
            obrSegmentIntegrate.getResultCopiesTo(0).getFamilyName().getSurname().setValue(orcSource.getOrderingProvider(0).getFamilyName().getSurname().getValue());
        if(orcSource.getOrderingProvider(0).getGivenName().getValue() != null)
            obrSegmentIntegrate.getResultCopiesTo(0).getGivenName().setValue(orcSource.getOrderingProvider(0).getGivenName().getValue());
        if(obrSource.getDiagnosticServSectID().getValue() != null)
            obrSegmentIntegrate.getDiagnosticServSectID().setValue(obrSource.getDiagnosticServSectID().getValue());

        for (int i = 0; i < obrSource.getPlacerSupplementalServiceInformationReps(); i++) {
            CE sourceSupplementalInfo = obrSource.getPlacerSupplementalServiceInformation(i);
            CE targetSupplementalInfo = obrSegmentIntegrate.getPlacerSupplementalServiceInformation(i);

            if(sourceSupplementalInfo.getIdentifier() != null)
                targetSupplementalInfo.getIdentifier().setValue(sourceSupplementalInfo.getIdentifier().getValue());
            if(sourceSupplementalInfo.getText().getValue() != null)
                targetSupplementalInfo.getText().setValue(sourceSupplementalInfo.getText().getValue());
            if(sourceSupplementalInfo.getNameOfCodingSystem().getValue() != null)
                targetSupplementalInfo.getNameOfCodingSystem().setValue(sourceSupplementalInfo.getNameOfCodingSystem().getValue());
        }
    }

    public static void createMSHSegmentIntegrateORLO22FromORLTransfusion(MSH mshSegmentIntegrate, ORL_O22 orlFromTD) throws HL7Exception {

        MSH mshSource = orlFromTD.getMSH();

        mshSegmentIntegrate.getFieldSeparator().setValue(hl7Config.getSeparator());
        mshSegmentIntegrate.getEncodingCharacters().setValue(hl7Config.getEncodingCharacters());
        if(mshSource.getSendingApplication().getNamespaceID().getValue() != null)
            mshSegmentIntegrate.getReceivingApplication().getNamespaceID().setValue(hl7Config.getSendingApplication());
        if(mshSource.getSendingFacility().getNamespaceID().getValue() != null)
            mshSegmentIntegrate.getReceivingFacility().getNamespaceID().setValue(hl7Config.getSendingFacility());
        if(mshSource.getReceivingApplication().getNamespaceID().getValue() != null)
            mshSegmentIntegrate.getSendingApplication().getNamespaceID().setValue(hl7Config.getSendingApplicationTransfusion());
        if(mshSource.getReceivingFacility().getNamespaceID().getValue() != null)
            mshSegmentIntegrate.getSendingFacility().getNamespaceID().setValue(hl7Config.getSendingFacilityTransfusion());
        if(mshSource.getDateTimeOfMessage().getTime().getValue() != null)
            mshSegmentIntegrate.getDateTimeOfMessage().getTime().setValue(mshSource.getDateTimeOfMessage().getTime().getValue());
        if(mshSource.getMessageType().getMessageCode().getValue() != null)
            mshSegmentIntegrate.getMessageType().getMessageCode().setValue(hl7Config.getMessageCodeOml());
        if(mshSource.getMessageType().getTriggerEvent().getValue() != null)
            mshSegmentIntegrate.getMessageType().getTriggerEvent().setValue(hl7Config.getTriggerEventO22());
        if(mshSource.getMessageType().getMessageStructure().getValue() != null)
            mshSegmentIntegrate.getMessageType().getMessageStructure().setValue(hl7Config.getMessageStructureORLO22());
        if(mshSource.getMessageControlID().getValue() != null)
            mshSegmentIntegrate.getMessageControlID().setValue(mshSource.getMessageControlID().getValue());
        if(mshSource.getProcessingID().getProcessingID().getValue() != null)
            mshSegmentIntegrate.getProcessingID().getProcessingID().setValue(mshSource.getProcessingID().getProcessingID().getValue());
        if(mshSource.getVersionID().getVersionID().getValue() != null)
            mshSegmentIntegrate.getVersionID().getVersionID().setValue(mshSource.getVersionID().getVersionID().getValue());
    }

}
