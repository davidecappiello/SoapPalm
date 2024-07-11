package com.mirth.prometeo.HL7Palm.SegmentFactory;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.group.OML_O21_OBSERVATION;
import ca.uhn.hl7v2.model.v25.group.OML_O21_ORDER;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.segment.*;

public class SegmentFactoryORMOO1 {

    public static void createMSHSegmentIntegrateORMOO1(MSH mshSegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {
        mshSegmentIntegrate.getFieldSeparator().setValue("|");
        mshSegmentIntegrate.getEncodingCharacters().setValue("^~\\&");
        mshSegmentIntegrate.getSendingApplication().getNamespaceID().setValue(omlMessage.getMSH().getSendingApplication().getNamespaceID().getValue());
        mshSegmentIntegrate.getSendingFacility().getNamespaceID().setValue(omlMessage.getMSH().getSendingFacility().getNamespaceID().getValue());
        mshSegmentIntegrate.getReceivingApplication().getNamespaceID().setValue(omlMessage.getMSH().getReceivingApplication().getNamespaceID().getValue());
        mshSegmentIntegrate.getDateTimeOfMessage().getTime().setValue(omlMessage.getMSH().getDateTimeOfMessage().getTime().getValue());
        mshSegmentIntegrate.getMessageType().getMessageCode().setValue("ORM");
        mshSegmentIntegrate.getMessageType().getTriggerEvent().setValue("O01");
        mshSegmentIntegrate.getMessageType().getMessageStructure().setValue("ORM_O01");
        mshSegmentIntegrate.getMessageControlID().setValue(omlMessage.getMSH().getMessageControlID().getValue());
        mshSegmentIntegrate.getProcessingID().getProcessingID().setValue(omlMessage.getMSH().getProcessingID().getProcessingID().getValue());
        mshSegmentIntegrate.getVersionID().getVersionID().setValue(omlMessage.getMSH().getVersionID().getVersionID().getValue());
    }

    public static void createPIDSegmentIntegrateORMOO1(PID pidSegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {
        pidSegmentIntegrate.getPatientIdentifierList(0).getIDNumber().setValue(omlMessage.getPATIENT().getPID().getPatientIdentifierList(0).getIDNumber().getValue());
        pidSegmentIntegrate.getPatientIdentifierList(0).getAssigningAuthority().getNamespaceID().setValue(omlMessage.getPATIENT().getPID().getPatientIdentifierList(0).getAssigningAuthority().getNamespaceID().getValue());
        pidSegmentIntegrate.getPatientIdentifierList(0).getIdentifierTypeCode().setValue(omlMessage.getPATIENT().getPID().getPatientIdentifierList(0).getIdentifierTypeCode().getValue());
        pidSegmentIntegrate.getPatientName(0).getFamilyName().getSurname().setValue(omlMessage.getPATIENT().getPID().getPatientName(0).getFamilyName().getSurname().getValue());
        pidSegmentIntegrate.getPatientName(0).getGivenName().setValue(omlMessage.getPATIENT().getPID().getPatientName(0).getGivenName().getValue());
        pidSegmentIntegrate.getDateTimeOfBirth().getTime().setValue(omlMessage.getPATIENT().getPID().getDateTimeOfBirth().getTime().getValue());
        pidSegmentIntegrate.getAdministrativeSex().setValue(omlMessage.getPATIENT().getPID().getAdministrativeSex().getValue());
        pidSegmentIntegrate.getPatientAddress(0).getStreetAddress().getStreetName().setValue(omlMessage.getPATIENT().getPID().getPatientAddress(0).getStreetAddress().getStreetName().getValue());
        pidSegmentIntegrate.getPatientAddress(0).getCity().setValue(omlMessage.getPATIENT().getPID().getPatientAddress(0).getCity().getValue());
        pidSegmentIntegrate.getPatientAddress(0).getStateOrProvince().setValue(omlMessage.getPATIENT().getPID().getPatientAddress(0).getStateOrProvince().getValue());
        pidSegmentIntegrate.getPatientAddress(0).getZipOrPostalCode().setValue(omlMessage.getPATIENT().getPID().getPatientAddress(0).getZipOrPostalCode().getValue());
        pidSegmentIntegrate.getPatientAddress(0).getAddressType().setValue(omlMessage.getPATIENT().getPID().getPatientAddress(0).getAddressType().getValue());
        pidSegmentIntegrate.getPatientAddress(0).getCountyParishCode().setValue(omlMessage.getPATIENT().getPID().getPatientAddress(0).getCountyParishCode().getValue());
        pidSegmentIntegrate.getPhoneNumberBusiness(0).getTelecommunicationEquipmentType().setValue(omlMessage.getPATIENT().getPID().getPhoneNumberBusiness(0).getTelecommunicationEquipmentType().getValue());
        pidSegmentIntegrate.getPhoneNumberBusiness(0).getUnformattedTelephoneNumber().setValue(omlMessage.getPATIENT().getPID().getPhoneNumberBusiness(0).getUnformattedTelephoneNumber().getValue());
        pidSegmentIntegrate.getPhoneNumberBusiness(0).getTelecommunicationUseCode().setValue(omlMessage.getPATIENT().getPID().getPhoneNumberBusiness(0).getTelecommunicationUseCode().getValue());
        pidSegmentIntegrate.getPhoneNumberBusiness(0).getEmailAddress().setValue(omlMessage.getPATIENT().getPID().getPhoneNumberBusiness(0).getEmailAddress().getValue());
        pidSegmentIntegrate.getCitizenship(0).getIdentifier().setValue(omlMessage.getPATIENT().getPID().getCitizenship(0).getIdentifier().getValue());
        pidSegmentIntegrate.getCitizenship(0).getText().setValue(omlMessage.getPATIENT().getPID().getCitizenship(0).getText().getValue());
        pidSegmentIntegrate.getCitizenship(0).getNameOfCodingSystem().setValue(omlMessage.getPATIENT().getPID().getCitizenship(0).getNameOfCodingSystem().getValue());
    }

    public static void createPV1SegmentIntegrateORMOO1(PV1 pv1SegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {
        pv1SegmentIntegrate.getPatientClass().setValue(omlMessage.getPATIENT().getPATIENT_VISIT().getPV1().getPatientClass().getValue());
        pv1SegmentIntegrate.getAssignedPatientLocation().getPointOfCare().setValue(omlMessage.getPATIENT().getPATIENT_VISIT().getPV1().getAssignedPatientLocation().getPointOfCare().getValue());
        pv1SegmentIntegrate.getAssignedPatientLocation().getFloor().setValue(omlMessage.getPATIENT().getPATIENT_VISIT().getPV1().getAssignedPatientLocation().getFloor().getValue());
        pv1SegmentIntegrate.getAssignedPatientLocation().getLocationDescription().setValue(omlMessage.getPATIENT().getPATIENT_VISIT().getPV1().getAssignedPatientLocation().getLocationDescription().getValue());
        pv1SegmentIntegrate.getVisitNumber().getIDNumber().setValue(omlMessage.getPATIENT().getPATIENT_VISIT().getPV1().getVisitNumber().getIDNumber().getValue());
        pv1SegmentIntegrate.getVisitNumber().getIdentifierTypeCode().setValue(omlMessage.getPATIENT().getPATIENT_VISIT().getPV1().getVisitNumber().getIdentifierTypeCode().getValue());
    }

    public static void createORCSegmentIntegrateORMOO1(ORC orcSegmentIntegrate, OML_O21 omlMessage, int index) throws HL7Exception {
        OML_O21_ORDER order = omlMessage.getORDER(index);
        ORC sourceORC = order.getORC();

        orcSegmentIntegrate.getOrderControl().setValue(sourceORC.getOrderControl().getValue());
        orcSegmentIntegrate.getPlacerOrderNumber().getEntityIdentifier().setValue(sourceORC.getPlacerOrderNumber().getEntityIdentifier().getValue());
        orcSegmentIntegrate.getPlacerOrderNumber().getNamespaceID().setValue(sourceORC.getPlacerOrderNumber().getNamespaceID().getValue());
        orcSegmentIntegrate.getPlacerOrderNumber().getUniversalID().setValue(sourceORC.getPlacerOrderNumber().getUniversalID().getValue());
        orcSegmentIntegrate.getPlacerOrderNumber().getUniversalIDType().setValue(sourceORC.getPlacerOrderNumber().getUniversalIDType().getValue());
        orcSegmentIntegrate.getFillerOrderNumber().getEntityIdentifier().setValue(sourceORC.getFillerOrderNumber().getEntityIdentifier().getValue());
        orcSegmentIntegrate.getFillerOrderNumber().getNamespaceID().setValue(sourceORC.getFillerOrderNumber().getNamespaceID().getValue());
        orcSegmentIntegrate.getFillerOrderNumber().getUniversalID().setValue(sourceORC.getFillerOrderNumber().getUniversalID().getValue());
        orcSegmentIntegrate.getFillerOrderNumber().getUniversalIDType().setValue(sourceORC.getFillerOrderNumber().getUniversalIDType().getValue());
        orcSegmentIntegrate.getPlacerGroupNumber().getEntityIdentifier().setValue(sourceORC.getPlacerGroupNumber().getEntityIdentifier().getValue());
        orcSegmentIntegrate.getOrderStatus().setValue(sourceORC.getOrderStatus().getValue());
        orcSegmentIntegrate.getQuantityTiming(0).getStartDateTime().getTime().setValue(sourceORC.getQuantityTiming(0).getStartDateTime().getTime().getValue());
        orcSegmentIntegrate.getDateTimeOfTransaction().getTime().setValue(sourceORC.getDateTimeOfTransaction().getTime().getValue());
        orcSegmentIntegrate.getOrderingProvider(0).getIDNumber().setValue(sourceORC.getOrderingProvider(0).getIDNumber().getValue());
        orcSegmentIntegrate.getOrderingProvider(0).getFamilyName().getSurname().setValue(sourceORC.getOrderingProvider(0).getFamilyName().getSurname().getValue());
        orcSegmentIntegrate.getOrderingProvider(0).getGivenName().setValue(sourceORC.getOrderingProvider(0).getGivenName().getValue());
        orcSegmentIntegrate.getOrderingFacilityName(0).getOrganizationName().setValue(sourceORC.getOrderingFacilityName(0).getOrganizationName().getValue());
        orcSegmentIntegrate.getOrderingFacilityName(0).getOrganizationIdentifier().setValue(sourceORC.getOrderingFacilityName(0).getOrganizationIdentifier().getValue());
        orcSegmentIntegrate.getConfidentialityCode().getIdentifier().setValue(sourceORC.getConfidentialityCode().getIdentifier().getValue());
        orcSegmentIntegrate.getConfidentialityCode().getText().setValue(sourceORC.getConfidentialityCode().getText().getValue());
    }

    public static void createTQ1SegmentIntegrateORMOO1(TQ1 tq1SegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {
        tq1SegmentIntegrate.getPriority(0).getIdentifier().setValue(omlMessage.getORDER().getTIMING().getTQ1().getPriority(0).getIdentifier().getValue());
    }

    public static void createOBRSegmentIntegrateORMOO1(OBR obrSegmentIntegrate, OML_O21 omlMessage, int index) throws HL7Exception {
        if (index < omlMessage.getORDERReps()) {
            OML_O21_ORDER observationRequest = omlMessage.getORDER(index);
            OBR sourceOBR = observationRequest.getOBSERVATION_REQUEST().getOBR();

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
        } else {
            throw new IllegalArgumentException("Index out of bounds for observation requests in OML message");
        }
    }

    public static void createOBXSegmentIntegrateORMOO1(OBX obxSegmentIntegrate, OML_O21 omlMessage, int orderIndex, int obxIndex) throws HL7Exception {
        OML_O21_ORDER order = omlMessage.getORDER(orderIndex);
        if (obxIndex < order.getOBSERVATION_REQUEST().getOBSERVATIONReps()) {
            OML_O21_OBSERVATION observation = order.getOBSERVATION_REQUEST().getOBSERVATION(obxIndex);
            OBX sourceOBX = observation.getOBX();

            obxSegmentIntegrate.getSetIDOBX().parse(sourceOBX.getSetIDOBX().getValue());
            obxSegmentIntegrate.getValueType().setValue(sourceOBX.getValueType().getValue());
            obxSegmentIntegrate.getObservationIdentifier().getIdentifier().setValue(sourceOBX.getObservationIdentifier().getIdentifier().getValue());
            obxSegmentIntegrate.getObservationIdentifier().getText().setValue(sourceOBX.getObservationIdentifier().getText().getValue());
            obxSegmentIntegrate.getObservationValue(0).parse(String.valueOf(sourceOBX.getObservationValue(0).getData()));
            obxSegmentIntegrate.getObservationResultStatus().setValue(sourceOBX.getObservationResultStatus().getValue());
        } else {
            throw new IllegalArgumentException("Index out of bounds for OBX segments in order");
        }
    }
}
