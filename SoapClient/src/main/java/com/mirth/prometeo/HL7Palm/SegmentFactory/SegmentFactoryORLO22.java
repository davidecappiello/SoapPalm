package com.mirth.prometeo.HL7Palm.SegmentFactory;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.group.ORM_O01_ORDER;
import ca.uhn.hl7v2.model.v25.message.ACK;
import ca.uhn.hl7v2.model.v25.message.ORM_O01;
import ca.uhn.hl7v2.model.v25.segment.*;

public class SegmentFactoryORLO22 {

    public static void createMSHSegmentIntegrateORLO22(MSH mshSegmentIntegrate, ORM_O01 ormMessage, ACK ackMessage) throws HL7Exception {

        mshSegmentIntegrate.getFieldSeparator().setValue("|");
        mshSegmentIntegrate.getEncodingCharacters().setValue("^~\\&");
        mshSegmentIntegrate.getSendingApplication().getNamespaceID().setValue(ormMessage.getMSH().getSendingApplication().getNamespaceID().getValue());
        mshSegmentIntegrate.getSendingFacility().getNamespaceID().setValue(ormMessage.getMSH().getSendingFacility().getNamespaceID().getValue());
        mshSegmentIntegrate.getReceivingApplication().getNamespaceID().setValue(ormMessage.getMSH().getReceivingApplication().getNamespaceID().getValue());
        mshSegmentIntegrate.getDateTimeOfMessage().getTime().setValue(ormMessage.getMSH().getDateTimeOfMessage().getTime().getValue());
        mshSegmentIntegrate.getMessageType().getMessageCode().setValue("ORL");
        mshSegmentIntegrate.getMessageType().getTriggerEvent().setValue("O22");
        mshSegmentIntegrate.getMessageType().getMessageStructure().setValue("ORL_O22");
        mshSegmentIntegrate.getMessageControlID().setValue(ormMessage.getMSH().getMessageControlID().getValue());
        mshSegmentIntegrate.getProcessingID().getProcessingID().setValue(ormMessage.getMSH().getProcessingID().getProcessingID().getValue());
        mshSegmentIntegrate.getVersionID().getVersionID().setValue(ormMessage.getMSH().getVersionID().getVersionID().getValue());
    }

    public static void createMSASegmentIntegrateORLO22(MSA msaSegmentIntegrate, Message genericMessage) throws HL7Exception {

        MSA oldMSA = (MSA) genericMessage.get("MSA");

        msaSegmentIntegrate.getAcknowledgmentCode().setValue(oldMSA.getAcknowledgmentCode().getValue());
        msaSegmentIntegrate.getMessageControlID().setValue(oldMSA.getMessageControlID().getValue());
        if(msaSegmentIntegrate.getAcknowledgmentCode().getValue().equals("AE") || (msaSegmentIntegrate.getAcknowledgmentCode().getValue().equals("AR"))) {
            ERR oldERR = (ERR) genericMessage.get("ERR");
            msaSegmentIntegrate.getTextMessage().setValue(oldERR.getHL7ErrorCode().getText().getValue());
        }
    }

    public static void createORCSegmentIntegrateORLO22(ORC orcSegmentIntegrate, ORC orcOld, int index) throws HL7Exception {
        
        orcSegmentIntegrate.getOrderControl().setValue(orcOld.getOrderControl().getValue());
        orcSegmentIntegrate.getPlacerOrderNumber().getEntityIdentifier().setValue(orcOld.getPlacerOrderNumber().getEntityIdentifier().getValue());
        orcSegmentIntegrate.getPlacerOrderNumber().getNamespaceID().setValue(orcOld.getPlacerOrderNumber().getNamespaceID().getValue());
        orcSegmentIntegrate.getPlacerOrderNumber().getUniversalID().setValue(orcOld.getPlacerOrderNumber().getUniversalID().getValue());
        orcSegmentIntegrate.getPlacerOrderNumber().getUniversalIDType().setValue(orcOld.getPlacerOrderNumber().getUniversalIDType().getValue());
        orcSegmentIntegrate.getFillerOrderNumber().getEntityIdentifier().setValue(orcOld.getFillerOrderNumber().getEntityIdentifier().getValue());
        orcSegmentIntegrate.getPlacerOrderNumber().getNamespaceID().setValue(orcOld.getPlacerOrderNumber().getNamespaceID().getValue());
        orcSegmentIntegrate.getPlacerGroupNumber().getEntityIdentifier().setValue(orcOld.getPlacerGroupNumber().getEntityIdentifier().getValue());
        orcSegmentIntegrate.getOrderStatus().setValue(orcOld.getOrderStatus().getValue());
        orcSegmentIntegrate.getQuantityTiming(0).getStartDateTime().getTime().setValue(orcOld.getQuantityTiming(0).getStartDateTime().getTime().getValue());
        orcSegmentIntegrate.getDateTimeOfTransaction().getTime().setValue(orcOld.getDateTimeOfTransaction().getTime().getValue());
        orcSegmentIntegrate.getOrderingProvider(0).getIDNumber().setValue(orcOld.getOrderingProvider(0).getIDNumber().getValue());
        orcSegmentIntegrate.getOrderingProvider(0).getFamilyName().getSurname().setValue(orcOld.getOrderingProvider(0).getFamilyName().getSurname().getValue());
        orcSegmentIntegrate.getOrderingProvider(0).getGivenName().setValue(orcOld.getOrderingProvider(0).getGivenName().getValue());
        orcSegmentIntegrate.getOrderingFacilityName(0).getOrganizationName().setValue(orcOld.getOrderingFacilityName(0).getOrganizationName().getValue());
        orcSegmentIntegrate.getOrderingFacilityName(0).getOrganizationIdentifier().setValue(orcOld.getOrderingFacilityName(0).getOrganizationIdentifier().getValue());
        orcSegmentIntegrate.getConfidentialityCode().getIdentifier().setValue(orcOld.getConfidentialityCode().getIdentifier().getValue());
        orcSegmentIntegrate.getConfidentialityCode().getText().setValue(orcOld.getConfidentialityCode().getText().getValue());
    }

    public static void createOBRSegmentIntegrateORLO22(OBR obrSegmentIntegrate, ORM_O01 ormMessage, int index) throws HL7Exception {
            ORM_O01_ORDER observationRequest = ormMessage.getORDER(index);
            OBR sourceOBR = observationRequest.getORDER_DETAIL().getOBR();

            obrSegmentIntegrate.getPlacerOrderNumber().getEntityIdentifier().setValue(sourceOBR.getPlacerOrderNumber().getEntityIdentifier().getValue());
            obrSegmentIntegrate.getFillerOrderNumber().getEntityIdentifier().setValue(sourceOBR.getFillerOrderNumber().getEntityIdentifier().getValue());
            obrSegmentIntegrate.getUniversalServiceIdentifier().getIdentifier().setValue(sourceOBR.getUniversalServiceIdentifier().getIdentifier().getValue());
            obrSegmentIntegrate.getUniversalServiceIdentifier().getText().setValue(sourceOBR.getUniversalServiceIdentifier().getText().getValue());
            obrSegmentIntegrate.getUniversalServiceIdentifier().getNameOfCodingSystem().setValue(sourceOBR.getUniversalServiceIdentifier().getNameOfCodingSystem().getValue());
            obrSegmentIntegrate.getRelevantClinicalInformation().setValue(sourceOBR.getRelevantClinicalInformation().getValue());
            obrSegmentIntegrate.getOrderingProvider(0).getIDNumber().setValue(sourceOBR.getOrderingProvider(0).getIDNumber().getValue());
            obrSegmentIntegrate.getOrderingProvider(0).getFamilyName().getSurname().setValue(sourceOBR.getOrderingProvider(0).getFamilyName().getSurname().getValue());
            obrSegmentIntegrate.getOrderingProvider(0).getGivenName().setValue(sourceOBR.getOrderingProvider(0).getGivenName().getValue());
            obrSegmentIntegrate.getPlacerField2().setValue(sourceOBR.getPlacerField2().getValue());
            obrSegmentIntegrate.getResultsRptStatusChngDateTime().getTime().setValue(sourceOBR.getResultsRptStatusChngDateTime().getTime().getValue());
            obrSegmentIntegrate.getDiagnosticServSectID().setValue(sourceOBR.getDiagnosticServSectID().getValue());
            obrSegmentIntegrate.getPlacerSupplementalServiceInformation(0).getIdentifier().setValue(sourceOBR.getPlacerSupplementalServiceInformation(0).getIdentifier().getValue());
            obrSegmentIntegrate.getPlacerSupplementalServiceInformation(0).getText().setValue(sourceOBR.getPlacerSupplementalServiceInformation(0).getText().getValue());
    }

}
