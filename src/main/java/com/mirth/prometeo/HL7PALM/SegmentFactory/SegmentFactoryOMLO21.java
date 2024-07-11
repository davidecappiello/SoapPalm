package com.mirth.prometeo.HL7PALM.SegmentFactory;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.datatype.CE;
import ca.uhn.hl7v2.model.v25.datatype.CX;
import ca.uhn.hl7v2.model.v25.datatype.XAD;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.segment.*;

public class SegmentFactoryOMLO21 {

    public static void createMSHSegmentIntegrateOMLO21(MSH mshSegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {

        MSH mshSource = omlMessage.getMSH();

        mshSegmentIntegrate.getFieldSeparator().setValue("|");
        mshSegmentIntegrate.getEncodingCharacters().setValue("^~\\&");
        mshSegmentIntegrate.getSendingApplication().getNamespaceID().setValue(mshSource.getSendingApplication().getNamespaceID().getValue());
        mshSegmentIntegrate.getSendingFacility().getNamespaceID().setValue(mshSource.getSendingFacility().getNamespaceID().getValue());
        mshSegmentIntegrate.getReceivingApplication().getNamespaceID().setValue(mshSource.getReceivingApplication().getNamespaceID().getValue());
        mshSegmentIntegrate.getReceivingFacility().getNamespaceID().setValue(mshSource.getReceivingFacility().getNamespaceID().getValue());
        mshSegmentIntegrate.getDateTimeOfMessage().getTime().setValue(mshSource.getDateTimeOfMessage().getTime().getValue());
        mshSegmentIntegrate.getMessageType().getMessageCode().setValue(mshSource.getMessageType().getMessageCode().getValue());
        mshSegmentIntegrate.getMessageType().getTriggerEvent().setValue(mshSource.getMessageType().getTriggerEvent().getValue());
        mshSegmentIntegrate.getMessageType().getMessageStructure().setValue(mshSource.getMessageType().getMessageStructure().getValue());
        mshSegmentIntegrate.getMessageControlID().setValue(mshSource.getMessageControlID().getValue());
        mshSegmentIntegrate.getProcessingID().getProcessingID().setValue(mshSource.getProcessingID().getProcessingID().getValue());
        mshSegmentIntegrate.getVersionID().getVersionID().setValue(mshSource.getVersionID().getVersionID().getValue());
    }

    public static void createPIDSegmentIntegrateOMLO21(PID pidSegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {

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

    public static void createPD1SegmentIntegrateOMLO21(PD1 pd1SegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {

        PD1 pd1Source = omlMessage.getPATIENT().getPD1();

        pd1SegmentIntegrate.getProtectionIndicator().setValue(pd1Source.getProtectionIndicator().getValue());
    }

    public static void createPV1SegmentIntegrateOMLO21(PV1 pv1SegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {

        PV1 pv1Source = omlMessage.getPATIENT().getPATIENT_VISIT().getPV1();

        pv1SegmentIntegrate.getPatientClass().setValue(pv1Source.getPatientClass().getValue());
        pv1SegmentIntegrate.getAssignedPatientLocation().getPointOfCare().setValue(pv1Source.getAssignedPatientLocation().getPointOfCare().getValue());
        pv1SegmentIntegrate.getAssignedPatientLocation().getFloor().setValue(pv1Source.getAssignedPatientLocation().getFloor().getValue());
        pv1SegmentIntegrate.getAssignedPatientLocation().getLocationDescription().setValue(pv1Source.getAssignedPatientLocation().getLocationDescription().getValue());
        pv1SegmentIntegrate.getVisitNumber().getIDNumber().setValue(pv1Source.getVisitNumber().getIDNumber().getValue());
        pv1SegmentIntegrate.getVisitNumber().getIdentifierTypeCode().setValue(pv1Source.getVisitNumber().getIdentifierTypeCode().getValue());
    }

    public static void createORCSegmentIntegrateOMLO21(ORC orcSegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {

        ORC orcSource = omlMessage.getORDER().getORC();

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

    public static void createTQ1SegmentIntegrateOMLO21(TQ1 tq1SegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {

        TQ1 tq1Source = omlMessage.getORDER().getTIMING().getTQ1();

        tq1SegmentIntegrate.getPriority(0).getIdentifier().setValue(tq1Source.getPriority(0).getIdentifier().getValue());
    }

    public static void createOBRSegmentIntegrateOMLO21(OBR obrSegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {

        OBR obrSource = omlMessage.getORDER().getOBSERVATION_REQUEST().getOBR();
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
        obrSegmentIntegrate.getPlacerSupplementalServiceInformation(0).getIdentifier().setValue(obrSource.getPlacerSupplementalServiceInformation(0).getIdentifier().getValue());
        obrSegmentIntegrate.getPlacerSupplementalServiceInformation(0).getText().setValue(obrSource.getPlacerSupplementalServiceInformation(0).getText().getValue());
    }

    public static void createOBXSegmentIntegrateOMLO21(OBX obxSegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {

        OBX obxSource = omlMessage.getORDER().getOBSERVATION_REQUEST().getOBSERVATION().getOBX();

        obxSegmentIntegrate.getSetIDOBX().parse(obxSource.getSetIDOBX().getValue());
        obxSegmentIntegrate.getValueType().setValue(obxSource.getValueType().getValue());
        obxSegmentIntegrate.getObservationIdentifier().getIdentifier().setValue(obxSource.getObservationIdentifier().getIdentifier().getValue());
        obxSegmentIntegrate.getObservationIdentifier().getText().setValue(obxSource.getObservationIdentifier().getText().getValue());
        obxSegmentIntegrate.getObservationValue(0).parse(String.valueOf(obxSource.getObservationValue(0).getData()));
        obxSegmentIntegrate.getObservationResultStatus().setValue(obxSource.getObservationResultStatus().getValue());
    }

}
