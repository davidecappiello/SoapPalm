package com.mirth.prometeo.HL7Palm.SegmentFactory;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.datatype.*;
import ca.uhn.hl7v2.model.v25.group.OML_O21_OBSERVATION;
import ca.uhn.hl7v2.model.v25.group.OML_O21_ORDER;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.segment.*;
import com.mirth.prometeo.HL7Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SegmentFactoryORMOO1 {

    private static HL7Config hl7Config = null;

    @Autowired
    public SegmentFactoryORMOO1(HL7Config hl7Config) {
        SegmentFactoryORMOO1.hl7Config = hl7Config;
    }

    public static void createMSHSegmentIntegrateORMOO1(MSH mshSegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {

        MSH mshSource = omlMessage.getMSH();

        mshSegmentIntegrate.getFieldSeparator().setValue(hl7Config.getSeparator());
        mshSegmentIntegrate.getEncodingCharacters().setValue(hl7Config.getEncodingCharacters());
        if(mshSource.getSendingApplication().getNamespaceID().getValue() != null)
            mshSegmentIntegrate.getSendingApplication().getNamespaceID().setValue(mshSource.getSendingApplication().getNamespaceID().getValue());
        if(mshSource.getSendingFacility().getNamespaceID().getValue() != null)
            mshSegmentIntegrate.getSendingFacility().getNamespaceID().setValue(mshSource.getSendingFacility().getNamespaceID().getValue());
        if(mshSource.getReceivingApplication().getNamespaceID().getValue() != null)
            mshSegmentIntegrate.getReceivingApplication().getNamespaceID().setValue(mshSource.getReceivingApplication().getNamespaceID().getValue());
        if(mshSource.getReceivingFacility().getNamespaceID().getValue() != null)
            mshSegmentIntegrate.getReceivingFacility().getNamespaceID().setValue(mshSource.getReceivingFacility().getNamespaceID().getValue());
        if(mshSource.getDateTimeOfMessage().getTime().getValue() != null)
            mshSegmentIntegrate.getDateTimeOfMessage().getTime().setValue(mshSource.getDateTimeOfMessage().getTime().getValue());
        if(mshSource.getMessageType().getMessageCode().getValue() != null)
            mshSegmentIntegrate.getMessageType().getMessageCode().setValue(hl7Config.getMessageCodeOrm());
        if(mshSource.getMessageType().getTriggerEvent().getValue() != null)
            mshSegmentIntegrate.getMessageType().getTriggerEvent().setValue(hl7Config.getTriggerEventO01());
        if(mshSource.getMessageType().getMessageStructure().getValue() != null)
            mshSegmentIntegrate.getMessageType().getMessageStructure().setValue(hl7Config.getMessageStructureORMO01());
        if(mshSource.getMessageControlID().getValue() != null)
            mshSegmentIntegrate.getMessageControlID().setValue(mshSource.getMessageControlID().getValue());
        if(mshSource.getProcessingID().getProcessingID().getValue() != null)
            mshSegmentIntegrate.getProcessingID().getProcessingID().setValue(mshSource.getProcessingID().getProcessingID().getValue());
        if(mshSource.getVersionID().getVersionID().getValue() != null)
            mshSegmentIntegrate.getVersionID().getVersionID().setValue(mshSource.getVersionID().getVersionID().getValue());
    }

    public static void createPIDSegmentIntegrateORMOO1(PID pidSegmentIntegrate, OML_O21 omlMessage, String date) throws HL7Exception {

        PID pidSource = omlMessage.getPATIENT().getPID();
        ST targetIdentifierIDNumber = pidSegmentIntegrate.getPatientIdentifierList(0).getIDNumber();
        HD targetIdentifierAssigningAuthority = pidSegmentIntegrate.getPatientIdentifierList(0).getAssigningAuthority();
        ID targetIdentifierIdentifierTypeCode = pidSegmentIntegrate.getPatientIdentifierList(0).getIdentifierTypeCode();

        for (int i = 0; i < pidSource.getPatientIdentifierListReps(); i++) {
            CX sourceIdentifier = pidSource.getPatientIdentifierList(i);

            if(targetIdentifierIDNumber.getValue() == null && targetIdentifierAssigningAuthority.getNamespaceID().getValue() == null && targetIdentifierIdentifierTypeCode.getValue() == null) {
                targetIdentifierIDNumber.setValue(sourceIdentifier.getIDNumber().getValue());
                targetIdentifierAssigningAuthority.getNamespaceID().setValue(sourceIdentifier.getAssigningAuthority().getNamespaceID().getValue());
                targetIdentifierIdentifierTypeCode.setValue(sourceIdentifier.getIdentifierTypeCode().getValue());
            }
        }

        if(pidSource.getPatientName(0).getFamilyName().getSurname().getValue() != null)
            pidSegmentIntegrate.getPatientName(0).getFamilyName().getSurname().setValue(pidSource.getPatientName(0).getFamilyName().getSurname().getValue());
        if(pidSource.getPatientName(0).getGivenName().getValue() != null)
            pidSegmentIntegrate.getPatientName(0).getGivenName().setValue(pidSource.getPatientName(0).getGivenName().getValue());
        if(pidSource.getDateTimeOfBirth().getTime().getValue() != null)
            pidSegmentIntegrate.getDateTimeOfBirth().getTime().setValue(date);
        if(pidSource.getAdministrativeSex().getValue() != null)
            pidSegmentIntegrate.getAdministrativeSex().setValue(pidSource.getAdministrativeSex().getValue());

        for (int i = 0; i < pidSource.getPatientAddressReps(); i++) {
            XAD sourceAddress = pidSource.getPatientAddress(i);
            XAD targetAddress = pidSegmentIntegrate.getPatientAddress(i);

            if(sourceAddress.getStreetAddress().getStreetName().getValue() != null)
                targetAddress.getStreetAddress().getStreetName().setValue(sourceAddress.getStreetAddress().getStreetName().getValue());
            if(sourceAddress.getCity().getValue() != null)
                targetAddress.getCity().setValue(sourceAddress.getCity().getValue());
            if(sourceAddress.getStateOrProvince().getValue() != null)
                targetAddress.getStateOrProvince().setValue(sourceAddress.getStateOrProvince().getValue());
            if(sourceAddress.getZipOrPostalCode().getValue() != null)
                targetAddress.getZipOrPostalCode().setValue(sourceAddress.getZipOrPostalCode().getValue());
            if(sourceAddress.getAddressType().getValue() != null)
                targetAddress.getAddressType().setValue(sourceAddress.getAddressType().getValue());
            if(sourceAddress.getCountyParishCode().getValue() != null)
                targetAddress.getCountyParishCode().setValue(sourceAddress.getCountyParishCode().getValue());
        }

        for (int i = 0; i < pidSource.getPhoneNumberHomeReps(); i++) {
            ST emailAddress = pidSource.getPhoneNumberHome(i).getEmailAddress();
            if(pidSource.getPhoneNumberHome(i).getUnformattedTelephoneNumber().getValue() != null) {
                pidSegmentIntegrate.getPhoneNumberHome(i).getTelecommunicationUseCode().setValue(pidSource.getPhoneNumberHome(i).getTelecommunicationUseCode().getValue());
                if(pidSource.getPhoneNumberHome(i).getUnformattedTelephoneNumber().getValue() != null)
                    pidSegmentIntegrate.getPhoneNumberHome(i).getUnformattedTelephoneNumber().setValue(pidSource.getPhoneNumberHome(i).getUnformattedTelephoneNumber().getValue());
            } else if(!emailAddress.getValue().isEmpty()) {
                pidSegmentIntegrate.getPhoneNumberHome(i).getTelecommunicationUseCode().setValue(pidSource.getPhoneNumberHome(i).getTelecommunicationUseCode().getValue());
                pidSegmentIntegrate.getPhoneNumberHome(i).getTelecommunicationEquipmentType().setValue(pidSource.getPhoneNumberHome(i).getTelecommunicationEquipmentType().getValue());
                pidSegmentIntegrate.getPhoneNumberHome(i).getEmailAddress().setValue(pidSource.getPhoneNumberHome(i).getEmailAddress().getValue());
            }
        }

        for (int i = 0; i < pidSource.getCitizenshipReps(); i++) {
            CE sourceCitizenship = pidSource.getCitizenship(i);
            CE targetCitizenship = pidSegmentIntegrate.getCitizenship(i);

            if(sourceCitizenship.getIdentifier().getValue() != null)
                targetCitizenship.getIdentifier().setValue(sourceCitizenship.getIdentifier().getValue());
            if(sourceCitizenship.getText().getValue() != null)
                targetCitizenship.getText().setValue(sourceCitizenship.getText().getValue());
            if(sourceCitizenship.getNameOfCodingSystem().getValue() != null)
                targetCitizenship.getNameOfCodingSystem().setValue(sourceCitizenship.getNameOfCodingSystem().getValue());
        }
    }

    public static void createPV1SegmentIntegrateORMOO1(PV1 pv1SegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {

        PV1 pv1Source = omlMessage.getPATIENT().getPATIENT_VISIT().getPV1();

        if(pv1Source.getPatientClass().getValue() != null)
            pv1SegmentIntegrate.getPatientClass().setValue(pv1Source.getPatientClass().getValue());
        if(pv1Source.getAssignedPatientLocation().getPointOfCare().getValue() != null)
            pv1SegmentIntegrate.getAssignedPatientLocation().getPointOfCare().setValue(pv1Source.getAssignedPatientLocation().getPointOfCare().getValue());
        if(pv1Source.getAssignedPatientLocation().getFloor().getValue() != null)
            pv1SegmentIntegrate.getAssignedPatientLocation().getFloor().setValue(pv1Source.getAssignedPatientLocation().getFloor().getValue());
        if(pv1Source.getAssignedPatientLocation().getLocationDescription().getValue() != null)
            pv1SegmentIntegrate.getAssignedPatientLocation().getLocationDescription().setValue(pv1Source.getAssignedPatientLocation().getLocationDescription().getValue());
        if(pv1Source.getVisitNumber().getIDNumber().getValue() != null)
            pv1SegmentIntegrate.getVisitNumber().getIDNumber().setValue(pv1Source.getVisitNumber().getIDNumber().getValue());
        if(pv1Source.getVisitNumber().getIdentifierTypeCode().getValue() != null)
            pv1SegmentIntegrate.getVisitNumber().getIdentifierTypeCode().setValue(pv1Source.getVisitNumber().getIdentifierTypeCode().getValue());
    }

    public static void createORCSegmentIntegrateORMOO1(ORC orcSegmentIntegrate, OML_O21 omlMessage, int index) throws HL7Exception {

        OML_O21_ORDER order = omlMessage.getORDER(index);
        ORC orcSource = order.getORC();

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

    public static void createOBRSegmentIntegrateORMOO1(OBR obrSegmentIntegrate, OML_O21 omlMessage, int index) throws HL7Exception {
        if (index < omlMessage.getORDERReps()) {
            OML_O21_ORDER observationRequest = omlMessage.getORDER(index);
            OBR obrSource = observationRequest.getOBSERVATION_REQUEST().getOBR();
            ORC orcSource = omlMessage.getORDER().getORC();

            if (obrSource.getSetIDOBR().getValue() != null)
                obrSegmentIntegrate.getSetIDOBR().setValue(obrSource.getSetIDOBR().getValue());
            if (obrSource.getPlacerOrderNumber().getEntityIdentifier().getValue() != null)
                obrSegmentIntegrate.getPlacerOrderNumber().getEntityIdentifier().setValue(obrSource.getPlacerOrderNumber().getEntityIdentifier().getValue());
            if (obrSource.getFillerOrderNumber().getEntityIdentifier().getValue() != null)
                obrSegmentIntegrate.getFillerOrderNumber().getEntityIdentifier().setValue(obrSource.getFillerOrderNumber().getEntityIdentifier().getValue());
            if (obrSource.getUniversalServiceIdentifier().getIdentifier().getValue() != null)
                obrSegmentIntegrate.getUniversalServiceIdentifier().getIdentifier().setValue(obrSource.getUniversalServiceIdentifier().getIdentifier().getValue());
            if (obrSource.getUniversalServiceIdentifier().getText().getValue() != null)
                obrSegmentIntegrate.getUniversalServiceIdentifier().getText().setValue(obrSource.getUniversalServiceIdentifier().getText().getValue());
            if (obrSource.getUniversalServiceIdentifier().getNameOfCodingSystem().getValue() != null)
                obrSegmentIntegrate.getUniversalServiceIdentifier().getNameOfCodingSystem().setValue(obrSource.getUniversalServiceIdentifier().getNameOfCodingSystem().getValue());
            if (obrSource.getObservationDateTime().getTime().getValue() != null)
                obrSegmentIntegrate.getObservationDateTime().getTime().setValue(obrSource.getObservationDateTime().getTime().getValue());
            if (obrSource.getRelevantClinicalInformation().getValue() != null)
                obrSegmentIntegrate.getRelevantClinicalInformation().setValue(obrSource.getRelevantClinicalInformation().getValue());
            if (obrSource.getOrderingProvider(0).getIDNumber().getValue() != null)
                obrSegmentIntegrate.getOrderingProvider(0).getIDNumber().setValue(obrSource.getOrderingProvider(0).getIDNumber().getValue());
            if (obrSource.getOrderingProvider(0).getFamilyName().getSurname().getValue() != null)
                obrSegmentIntegrate.getOrderingProvider(0).getFamilyName().getSurname().setValue(obrSource.getOrderingProvider(0).getFamilyName().getSurname().getValue());
            if (obrSource.getOrderingProvider(0).getGivenName().getValue() != null)
                obrSegmentIntegrate.getOrderingProvider(0).getGivenName().setValue(obrSource.getOrderingProvider(0).getGivenName().getValue());
            if (obrSource.getPlacerField2().getValue() != null)
                obrSegmentIntegrate.getPlacerField2().setValue(obrSource.getPlacerField2().getValue());
            if (obrSource.getResultsRptStatusChngDateTime().getTime().getValue() != null)
                obrSegmentIntegrate.getResultsRptStatusChngDateTime().getTime().setValue(obrSource.getResultsRptStatusChngDateTime().getTime().getValue());
            if (obrSource.getResultCopiesTo(0).getIDNumber().getValue() != null)
                obrSegmentIntegrate.getResultCopiesTo(0).getIDNumber().setValue(orcSource.getOrderingProvider(0).getIDNumber().getValue());
            if (obrSource.getResultCopiesTo(0).getIDNumber().getValue() != null)
                obrSegmentIntegrate.getResultCopiesTo(0).getIDNumber().setValue(orcSource.getOrderingProvider(0).getFamilyName().getSurname().getValue());
            if (obrSource.getResultCopiesTo(0).getGivenName().getValue() != null)
                obrSegmentIntegrate.getResultCopiesTo(0).getGivenName().setValue(orcSource.getOrderingProvider(0).getGivenName().getValue());
            if (obrSource.getDiagnosticServSectID().getValue() != null)
                obrSegmentIntegrate.getDiagnosticServSectID().setValue(obrSource.getDiagnosticServSectID().getValue());
            if (obrSource.getPlacerSupplementalServiceInformation(0).getIdentifier().getValue() != null)
                obrSegmentIntegrate.getPlacerSupplementalServiceInformation(0).getIdentifier().setValue(obrSource.getPlacerSupplementalServiceInformation(0).getIdentifier().getValue());
            if (obrSource.getPlacerSupplementalServiceInformation(0).getText().getValue() != null)
                obrSegmentIntegrate.getPlacerSupplementalServiceInformation(0).getText().setValue(obrSource.getPlacerSupplementalServiceInformation(0).getText().getValue());
        } else {
            throw new IllegalArgumentException("Index out of bounds for observation requests in OML message");
        }
    }

    public static void createOBXSegmentIntegrateORMOO1(OBX obxSegmentIntegrate, OML_O21 omlMessage, int orderIndex, int obxIndex) throws HL7Exception {
        OML_O21_ORDER order = omlMessage.getORDER(orderIndex);
        if (obxIndex < order.getOBSERVATION_REQUEST().getOBSERVATIONReps()) {
            OML_O21_OBSERVATION observation = order.getOBSERVATION_REQUEST().getOBSERVATION(obxIndex);
            OBX obxSource = observation.getOBX();

            if(obxSource.getSetIDOBX().getValue() != null)
                obxSegmentIntegrate.getSetIDOBX().parse(obxSource.getSetIDOBX().getValue());
            if(obxSource.getValueType().getValue() != null)
                obxSegmentIntegrate.getValueType().setValue(obxSource.getValueType().getValue());
            if(obxSource.getObservationIdentifier().getIdentifier().getValue() != null)
                obxSegmentIntegrate.getObservationIdentifier().getIdentifier().setValue(obxSource.getObservationIdentifier().getIdentifier().getValue());
            if(obxSource.getObservationIdentifier().getText().getValue() != null)
                obxSegmentIntegrate.getObservationIdentifier().getText().setValue(obxSource.getObservationIdentifier().getText().getValue());
            if(obxSource.getObservationValue(0).getData() != null)
                obxSegmentIntegrate.getObservationValue(0).parse(String.valueOf(obxSource.getObservationValue(0).getData()));
            if(obxSource.getObservationResultStatus().getValue() != null)
                obxSegmentIntegrate.getObservationResultStatus().setValue(obxSource.getObservationResultStatus().getValue());
        } else {
            throw new IllegalArgumentException("Index out of bounds for OBX segments in order");
        }
    }
}
