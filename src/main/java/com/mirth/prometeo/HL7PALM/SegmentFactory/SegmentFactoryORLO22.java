package com.mirth.prometeo.HL7PALM.SegmentFactory;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v25.datatype.CE;
import ca.uhn.hl7v2.model.v25.group.ORM_O01_ORDER;
import ca.uhn.hl7v2.model.v25.message.ACK;
import ca.uhn.hl7v2.model.v25.message.ORM_O01;
import ca.uhn.hl7v2.model.v25.segment.*;

public class SegmentFactoryORLO22 {

    public static void createMSHSegmentIntegrateORLO22(MSH mshSegmentIntegrate, ORM_O01 ormMessage, ACK ackMessage) throws HL7Exception {

        MSH mshSource = ormMessage.getMSH();

        mshSegmentIntegrate.getFieldSeparator().setValue("|");
        mshSegmentIntegrate.getEncodingCharacters().setValue("^~\\&");
        mshSegmentIntegrate.getSendingApplication().getNamespaceID().setValue(mshSource.getSendingApplication().getNamespaceID().getValue());
        mshSegmentIntegrate.getSendingFacility().getNamespaceID().setValue(mshSource.getSendingFacility().getNamespaceID().getValue());
        mshSegmentIntegrate.getReceivingApplication().getNamespaceID().setValue(mshSource.getReceivingApplication().getNamespaceID().getValue());
        mshSegmentIntegrate.getReceivingFacility().getNamespaceID().setValue(mshSource.getReceivingFacility().getNamespaceID().getValue());
        mshSegmentIntegrate.getDateTimeOfMessage().getTime().setValue(mshSource.getDateTimeOfMessage().getTime().getValue());
        mshSegmentIntegrate.getMessageType().getMessageCode().setValue("ORL");
        mshSegmentIntegrate.getMessageType().getTriggerEvent().setValue("O22");
        mshSegmentIntegrate.getMessageType().getMessageStructure().setValue("ORL_O22");
        mshSegmentIntegrate.getMessageControlID().setValue(mshSource.getMessageControlID().getValue());
        mshSegmentIntegrate.getProcessingID().getProcessingID().setValue(mshSource.getProcessingID().getProcessingID().getValue());
        mshSegmentIntegrate.getVersionID().getVersionID().setValue(mshSource.getVersionID().getVersionID().getValue());
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

    public static void createORCSegmentIntegrateORLO22(ORC orcSegmentIntegrate, ORC orcSource, int index) throws HL7Exception {

        orcSegmentIntegrate.getOrderControl().setValue(orcSource.getOrderControl().getValue());
        orcSegmentIntegrate.getOrderStatus().setValue(orcSource.getOrderStatus().getValue());
        orcSegmentIntegrate.getPlacerOrderNumber().getEntityIdentifier().setValue(orcSource.getPlacerOrderNumber().getEntityIdentifier().getValue());
        orcSegmentIntegrate.getPlacerOrderNumber().getNamespaceID().setValue(orcSource.getPlacerOrderNumber().getNamespaceID().getValue());
        orcSegmentIntegrate.getPlacerOrderNumber().getUniversalID().setValue(orcSource.getPlacerOrderNumber().getUniversalID().getValue());
        orcSegmentIntegrate.getPlacerOrderNumber().getUniversalIDType().setValue(orcSource.getPlacerOrderNumber().getUniversalIDType().getValue());
        orcSegmentIntegrate.getFillerOrderNumber().getEntityIdentifier().setValue(orcSource.getFillerOrderNumber().getEntityIdentifier().getValue());
        orcSegmentIntegrate.getFillerOrderNumber().getNamespaceID().setValue(orcSource.getFillerOrderNumber().getNamespaceID().getValue());
        orcSegmentIntegrate.getPlacerGroupNumber().getEntityIdentifier().setValue(orcSource.getPlacerGroupNumber().getEntityIdentifier().getValue());
        orcSegmentIntegrate.getQuantityTiming(0).getStartDateTime().getTime().setValue(orcSource.getQuantityTiming(0).getStartDateTime().getTime().getValue());
        orcSegmentIntegrate.getDateTimeOfTransaction().getTime().setValue(orcSource.getDateTimeOfTransaction().getTime().getValue());
        orcSegmentIntegrate.getOrderingProvider(0).getIDNumber().setValue(orcSource.getOrderingProvider(0).getIDNumber().getValue());
        orcSegmentIntegrate.getOrderingProvider(0).getFamilyName().getSurname().setValue(orcSource.getOrderingProvider(0).getFamilyName().getSurname().getValue());
        orcSegmentIntegrate.getOrderingProvider(0).getGivenName().setValue(orcSource.getOrderingProvider(0).getGivenName().getValue());
        orcSegmentIntegrate.getOrderingFacilityName(0).getOrganizationName().setValue(orcSource.getOrderingFacilityName(0).getOrganizationName().getValue());
        orcSegmentIntegrate.getOrderingFacilityName(0).getOrganizationIdentifier().setValue(orcSource.getOrderingFacilityName(0).getOrganizationIdentifier().getValue());
        orcSegmentIntegrate.getConfidentialityCode().getIdentifier().setValue(orcSource.getConfidentialityCode().getIdentifier().getValue());
        orcSegmentIntegrate.getConfidentialityCode().getText().setValue(orcSource.getConfidentialityCode().getText().getValue());
    }

    public static void createOBRSegmentIntegrateORLO22(OBR obrSegmentIntegrate, ORM_O01 ormMessage, int index) throws HL7Exception {
            ORM_O01_ORDER observationRequest = ormMessage.getORDER(index);
            OBR obrSource = observationRequest.getORDER_DETAIL().getOBR();
            ORC orcSource = ormMessage.getORDER().getORC();

        obrSegmentIntegrate.getSetIDOBR().setValue(obrSource.getSetIDOBR().getValue());
        obrSegmentIntegrate.getPlacerOrderNumber().getEntityIdentifier().setValue(obrSource.getPlacerOrderNumber().getEntityIdentifier().getValue());
        obrSegmentIntegrate.getFillerOrderNumber().getEntityIdentifier().setValue(obrSource.getFillerOrderNumber().getEntityIdentifier().getValue());
        obrSegmentIntegrate.getUniversalServiceIdentifier().getIdentifier().setValue(obrSource.getUniversalServiceIdentifier().getIdentifier().getValue());
        obrSegmentIntegrate.getUniversalServiceIdentifier().getText().setValue(obrSource.getUniversalServiceIdentifier().getText().getValue());
        obrSegmentIntegrate.getUniversalServiceIdentifier().getNameOfCodingSystem().setValue(obrSource.getUniversalServiceIdentifier().getNameOfCodingSystem().getValue());
        obrSegmentIntegrate.getObservationDateTime().getTime().setValue(obrSource.getObservationDateTime().getTime().getValue());
        //Controllare il campo obr.10 perchè discordanti.. per ora non inserito nel messaggio di test
        obrSegmentIntegrate.getRelevantClinicalInformation().setValue(obrSource.getRelevantClinicalInformation().getValue());
        obrSegmentIntegrate.getOrderingProvider(0).getIDNumber().setValue(obrSource.getOrderingProvider(0).getIDNumber().getValue());
        obrSegmentIntegrate.getOrderingProvider(0).getFamilyName().getSurname().setValue(obrSource.getOrderingProvider(0).getFamilyName().getSurname().getValue());
        obrSegmentIntegrate.getOrderingProvider(0).getGivenName().setValue(obrSource.getOrderingProvider(0).getGivenName().getValue());
        obrSegmentIntegrate.getPlacerField2().setValue(obrSource.getPlacerField2().getValue());
        obrSegmentIntegrate.getResultsRptStatusChngDateTime().getTime().setValue(obrSource.getResultsRptStatusChngDateTime().getTime().getValue());
        obrSegmentIntegrate.getResultCopiesTo(0).getIDNumber().setValue(orcSource.getOrderingProvider(0).getIDNumber().getValue());
        obrSegmentIntegrate.getResultCopiesTo(0).getIDNumber().setValue(orcSource.getOrderingProvider(0).getFamilyName().getSurname().getValue());
        obrSegmentIntegrate.getResultCopiesTo(0).getGivenName().setValue(orcSource.getOrderingProvider(0).getGivenName().getValue());

        obrSegmentIntegrate.getDiagnosticServSectID().setValue(obrSource.getDiagnosticServSectID().getValue());

        for (int i = 0; i < obrSource.getPlacerSupplementalServiceInformationReps(); i++) {
            CE sourceSupplementalInfo = obrSource.getPlacerSupplementalServiceInformation(i);
            CE targetSupplementalInfo = obrSegmentIntegrate.getPlacerSupplementalServiceInformation(i);

            targetSupplementalInfo.getIdentifier().setValue(sourceSupplementalInfo.getIdentifier().getValue());
            targetSupplementalInfo.getText().setValue(sourceSupplementalInfo.getText().getValue());
            targetSupplementalInfo.getNameOfCodingSystem().setValue(sourceSupplementalInfo.getNameOfCodingSystem().getValue());
        }
    }

}
