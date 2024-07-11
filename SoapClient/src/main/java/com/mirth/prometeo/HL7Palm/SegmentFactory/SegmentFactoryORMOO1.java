package com.mirth.prometeo.HL7PALM.SegmentFactory;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.datatype.CE;
import ca.uhn.hl7v2.model.v25.datatype.CX;
import ca.uhn.hl7v2.model.v25.datatype.XAD;
import ca.uhn.hl7v2.model.v25.group.OML_O21_OBSERVATION;
import ca.uhn.hl7v2.model.v25.group.OML_O21_ORDER;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.segment.*;

public class SegmentFactoryORMOO1 {

    public static void createMSHSegmentIntegrateORMOO1(MSH mshSegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {

        MSH mshSource = omlMessage.getMSH();

        mshSegmentIntegrate.getFieldSeparator().setValue("|");
        mshSegmentIntegrate.getEncodingCharacters().setValue("^~\\&");
        mshSegmentIntegrate.getSendingApplication().getNamespaceID().setValue(mshSource.getSendingApplication().getNamespaceID().getValue());
        mshSegmentIntegrate.getSendingFacility().getNamespaceID().setValue(mshSource.getSendingFacility().getNamespaceID().getValue());
        mshSegmentIntegrate.getReceivingApplication().getNamespaceID().setValue(mshSource.getReceivingApplication().getNamespaceID().getValue());
        mshSegmentIntegrate.getReceivingFacility().getNamespaceID().setValue(mshSource.getReceivingFacility().getNamespaceID().getValue());
        mshSegmentIntegrate.getDateTimeOfMessage().getTime().setValue(mshSource.getDateTimeOfMessage().getTime().getValue());
        mshSegmentIntegrate.getMessageType().getMessageCode().setValue("ORM");
        mshSegmentIntegrate.getMessageType().getTriggerEvent().setValue("O01");
        mshSegmentIntegrate.getMessageType().getMessageStructure().setValue("ORM_O01");
        mshSegmentIntegrate.getMessageControlID().setValue(mshSource.getMessageControlID().getValue());
        mshSegmentIntegrate.getProcessingID().getProcessingID().setValue(mshSource.getProcessingID().getProcessingID().getValue());
        mshSegmentIntegrate.getVersionID().getVersionID().setValue(mshSource.getVersionID().getVersionID().getValue());
    }

    public static void createPIDSegmentIntegrateORMOO1(PID pidSegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {

        PID pidSource = omlMessage.getPATIENT().getPID();

        for (int i = 0; i < pidSource.getPatientIdentifierListReps(); i++) {
            CX sourceIdentifier = pidSource.getPatientIdentifierList(i);
            CX targetIdentifier = pidSegmentIntegrate.getPatientIdentifierList(i);

            targetIdentifier.getIDNumber().setValue(sourceIdentifier.getIDNumber().getValue());
            targetIdentifier.getAssigningAuthority().getNamespaceID().setValue(sourceIdentifier.getAssigningAuthority().getNamespaceID().getValue());
            targetIdentifier.getIdentifierTypeCode().setValue(sourceIdentifier.getIdentifierTypeCode().getValue());
        }

        pidSegmentIntegrate.getPatientName(0).getFamilyName().getSurname().setValue(pidSource.getPatientName(0).getFamilyName().getSurname().getValue());
        pidSegmentIntegrate.getPatientName(0).getGivenName().setValue(pidSource.getPatientName(0).getGivenName().getValue());
        pidSegmentIntegrate.getDateTimeOfBirth().getTime().setValue(pidSource.getDateTimeOfBirth().getTime().getValue());
        pidSegmentIntegrate.getAdministrativeSex().setValue(pidSource.getAdministrativeSex().getValue());

        for (int i = 0; i < pidSource.getPatientAddressReps(); i++) {
            XAD sourceAddress = pidSource.getPatientAddress(i);
            XAD targetAddress = pidSegmentIntegrate.getPatientAddress(i);

            targetAddress.getStreetAddress().getStreetName().setValue(sourceAddress.getStreetAddress().getStreetName().getValue());
            targetAddress.getCity().setValue(sourceAddress.getCity().getValue());
            targetAddress.getStateOrProvince().setValue(sourceAddress.getStateOrProvince().getValue());
            targetAddress.getZipOrPostalCode().setValue(sourceAddress.getZipOrPostalCode().getValue());
            targetAddress.getAddressType().setValue(sourceAddress.getAddressType().getValue());
            targetAddress.getCountyParishCode().setValue(sourceAddress.getCountyParishCode().getValue());
        }

        for (int i = 0; i < pidSource.getPhoneNumberHomeReps(); i++) {
            if(pidSource.getPhoneNumberHome(i).getTelecommunicationUseCode().getValue() != null) {
                pidSegmentIntegrate.getPhoneNumberHome(i).getTelecommunicationUseCode().setValue(pidSource.getPhoneNumberHome(i).getTelecommunicationUseCode().getValue());
                pidSegmentIntegrate.getPhoneNumberHome(i).getTelecommunicationEquipmentType().setValue(pidSource.getPhoneNumberHome(i).getTelecommunicationEquipmentType().getValue());
                pidSegmentIntegrate.getPhoneNumberHome(i).getEmailAddress().setValue(pidSource.getPhoneNumberHome(i).getEmailAddress().getValue());
            } else {
                pidSegmentIntegrate.getPhoneNumberHome(i).getTelecommunicationEquipmentType().setValue(pidSource.getPhoneNumberHome(i).getTelecommunicationEquipmentType().getValue());
                pidSegmentIntegrate.getPhoneNumberHome(i).getUnformattedTelephoneNumber().setValue(pidSource.getPhoneNumberHome(i).getUnformattedTelephoneNumber().getValue());
            }
        }

        for (int i = 0; i < pidSource.getCitizenshipReps(); i++) {
            CE sourceCitizenship = pidSource.getCitizenship(i);
            CE targetCitizenship = pidSegmentIntegrate.getCitizenship(i);

            targetCitizenship.getIdentifier().setValue(sourceCitizenship.getIdentifier().getValue());
            targetCitizenship.getText().setValue(sourceCitizenship.getText().getValue());
            targetCitizenship.getNameOfCodingSystem().setValue(sourceCitizenship.getNameOfCodingSystem().getValue());
        }

    }

    public static void createPV1SegmentIntegrateORMOO1(PV1 pv1SegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {

        PV1 pv1Source = omlMessage.getPATIENT().getPATIENT_VISIT().getPV1();

        pv1SegmentIntegrate.getPatientClass().setValue(pv1Source.getPatientClass().getValue());
        pv1SegmentIntegrate.getAssignedPatientLocation().getPointOfCare().setValue(pv1Source.getAssignedPatientLocation().getPointOfCare().getValue());
        pv1SegmentIntegrate.getAssignedPatientLocation().getFloor().setValue(pv1Source.getAssignedPatientLocation().getFloor().getValue());
        pv1SegmentIntegrate.getAssignedPatientLocation().getLocationDescription().setValue(pv1Source.getAssignedPatientLocation().getLocationDescription().getValue());
        pv1SegmentIntegrate.getVisitNumber().getIDNumber().setValue(pv1Source.getVisitNumber().getIDNumber().getValue());
        pv1SegmentIntegrate.getVisitNumber().getIdentifierTypeCode().setValue(pv1Source.getVisitNumber().getIdentifierTypeCode().getValue());
    }

    public static void createORCSegmentIntegrateORMOO1(ORC orcSegmentIntegrate, OML_O21 omlMessage, int index) throws HL7Exception {

        OML_O21_ORDER order = omlMessage.getORDER(index);
        ORC orcSource = order.getORC();

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

    public static void createTQ1SegmentIntegrateORMOO1(TQ1 tq1SegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {
        tq1SegmentIntegrate.getPriority(0).getIdentifier().setValue(omlMessage.getORDER().getTIMING().getTQ1().getPriority(0).getIdentifier().getValue());
    }

    public static void createOBRSegmentIntegrateORMOO1(OBR obrSegmentIntegrate, OML_O21 omlMessage, int index) throws HL7Exception {
        if (index < omlMessage.getORDERReps()) {
            OML_O21_ORDER observationRequest = omlMessage.getORDER(index);
            OBR obrSource = observationRequest.getOBSERVATION_REQUEST().getOBR();
            ORC orcSource = omlMessage.getORDER().getORC();

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
