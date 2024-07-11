package com.mirth.prometeo.HL7Palm.SegmentFactory;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.datatype.CE;
import ca.uhn.hl7v2.model.v25.datatype.CX;
import ca.uhn.hl7v2.model.v25.datatype.XAD;
import ca.uhn.hl7v2.model.v25.message.OML_O21;
import ca.uhn.hl7v2.model.v25.segment.*;

public class SegmentFactoryOMLO21 {

    public static void createMSHSegmentIntegrateOMLO21(MSH mshSegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {

        mshSegmentIntegrate.getFieldSeparator().setValue("|");
        mshSegmentIntegrate.getEncodingCharacters().setValue("^~\\&");
        mshSegmentIntegrate.getSendingApplication().getNamespaceID().setValue(omlMessage.getMSH().getSendingApplication().getNamespaceID().getValue());
        mshSegmentIntegrate.getSendingFacility().getNamespaceID().setValue(omlMessage.getMSH().getSendingFacility().getNamespaceID().getValue());
        mshSegmentIntegrate.getReceivingApplication().getNamespaceID().setValue(omlMessage.getMSH().getReceivingApplication().getNamespaceID().getValue());
        mshSegmentIntegrate.getReceivingFacility().getNamespaceID().setValue(omlMessage.getMSH().getReceivingFacility().getNamespaceID().getValue());
        mshSegmentIntegrate.getDateTimeOfMessage().getTime().setValue(omlMessage.getMSH().getDateTimeOfMessage().getTime().getValue());
        mshSegmentIntegrate.getMessageType().getMessageCode().setValue(omlMessage.getMSH().getMessageType().getMessageCode().getValue());
        mshSegmentIntegrate.getMessageType().getTriggerEvent().setValue(omlMessage.getMSH().getMessageType().getTriggerEvent().getValue());
        mshSegmentIntegrate.getMessageType().getMessageStructure().setValue(omlMessage.getMSH().getMessageType().getMessageStructure().getValue());
        mshSegmentIntegrate.getMessageControlID().setValue(omlMessage.getMSH().getMessageControlID().getValue());
        mshSegmentIntegrate.getProcessingID().getProcessingID().setValue(omlMessage.getMSH().getProcessingID().getProcessingID().getValue());
        mshSegmentIntegrate.getVersionID().getVersionID().setValue(omlMessage.getMSH().getVersionID().getVersionID().getValue());
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

        pd1SegmentIntegrate.getProtectionIndicator().setValue(omlMessage.getPATIENT().getPD1().getProtectionIndicator().getValue());
    }

    public static void createPV1SegmentIntegrateOMLO21(PV1 pv1SegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {

        pv1SegmentIntegrate.getPatientClass().setValue(omlMessage.getPATIENT().getPATIENT_VISIT().getPV1().getPatientClass().getValue());
        pv1SegmentIntegrate.getAssignedPatientLocation().getPointOfCare().setValue(omlMessage.getPATIENT().getPATIENT_VISIT().getPV1().getAssignedPatientLocation().getPointOfCare().getValue());
        pv1SegmentIntegrate.getAssignedPatientLocation().getFloor().setValue(omlMessage.getPATIENT().getPATIENT_VISIT().getPV1().getAssignedPatientLocation().getFloor().getValue());
        pv1SegmentIntegrate.getAssignedPatientLocation().getLocationDescription().setValue(omlMessage.getPATIENT().getPATIENT_VISIT().getPV1().getAssignedPatientLocation().getLocationDescription().getValue());
        pv1SegmentIntegrate.getVisitNumber().getIDNumber().setValue(omlMessage.getPATIENT().getPATIENT_VISIT().getPV1().getVisitNumber().getIDNumber().getValue());
        pv1SegmentIntegrate.getVisitNumber().getIdentifierTypeCode().setValue(omlMessage.getPATIENT().getPATIENT_VISIT().getPV1().getVisitNumber().getIdentifierTypeCode().getValue());
    }

    public static void createORCSegmentIntegrateOMLO21(ORC orcSegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {

        orcSegmentIntegrate.getOrderControl().setValue(omlMessage.getORDER().getORC().getOrderControl().getValue());
        orcSegmentIntegrate.getOrderStatus().setValue(omlMessage.getORDER().getORC().getOrderStatus().getValue());
        orcSegmentIntegrate.getPlacerOrderNumber().getEntityIdentifier().setValue(omlMessage.getORDER().getORC().getPlacerOrderNumber().getEntityIdentifier().getValue());
        orcSegmentIntegrate.getPlacerOrderNumber().getNamespaceID().setValue(omlMessage.getORDER().getORC().getPlacerOrderNumber().getNamespaceID().getValue());
        orcSegmentIntegrate.getPlacerOrderNumber().getUniversalID().setValue(omlMessage.getORDER().getORC().getPlacerOrderNumber().getUniversalID().getValue());
        orcSegmentIntegrate.getPlacerOrderNumber().getUniversalIDType().setValue(omlMessage.getORDER().getORC().getPlacerOrderNumber().getUniversalIDType().getValue());
        orcSegmentIntegrate.getFillerOrderNumber().getEntityIdentifier().setValue(omlMessage.getORDER().getORC().getFillerOrderNumber().getEntityIdentifier().getValue());
        orcSegmentIntegrate.getFillerOrderNumber().getNamespaceID().setValue(omlMessage.getORDER().getORC().getFillerOrderNumber().getNamespaceID().getValue());
        orcSegmentIntegrate.getPlacerGroupNumber().getEntityIdentifier().setValue(omlMessage.getORDER().getORC().getPlacerGroupNumber().getEntityIdentifier().getValue());
        orcSegmentIntegrate.getQuantityTiming(0).getStartDateTime().getTime().setValue(omlMessage.getORDER().getORC().getQuantityTiming(0).getStartDateTime().getTime().getValue());
        orcSegmentIntegrate.getDateTimeOfTransaction().getTime().setValue(omlMessage.getORDER().getORC().getDateTimeOfTransaction().getTime().getValue());
        orcSegmentIntegrate.getOrderingProvider(0).getIDNumber().setValue(omlMessage.getORDER().getORC().getOrderingProvider(0).getIDNumber().getValue());
        orcSegmentIntegrate.getOrderingProvider(0).getFamilyName().getSurname().setValue(omlMessage.getORDER().getORC().getOrderingProvider(0).getFamilyName().getSurname().getValue());
        orcSegmentIntegrate.getOrderingProvider(0).getGivenName().setValue(omlMessage.getORDER().getORC().getOrderingProvider(0).getGivenName().getValue());
        orcSegmentIntegrate.getOrderingFacilityName(0).getOrganizationName().setValue(omlMessage.getORDER().getORC().getOrderingFacilityName(0).getOrganizationName().getValue());
        orcSegmentIntegrate.getOrderingFacilityName(0).getOrganizationIdentifier().setValue(omlMessage.getORDER().getORC().getOrderingFacilityName(0).getOrganizationIdentifier().getValue());
        orcSegmentIntegrate.getConfidentialityCode().getIdentifier().setValue(omlMessage.getORDER().getORC().getConfidentialityCode().getIdentifier().getValue());
        orcSegmentIntegrate.getConfidentialityCode().getText().setValue(omlMessage.getORDER().getORC().getConfidentialityCode().getText().getValue());
    }

    public static void createTQ1SegmentIntegrateOMLO21(TQ1 tq1SegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {

        tq1SegmentIntegrate.getPriority(0).getIdentifier().setValue(omlMessage.getORDER().getTIMING().getTQ1().getPriority(0).getIdentifier().getValue());
    }

    public static void createOBRSegmentIntegrateOMLO21(OBR obrSegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {

        OBR obrSource = omlMessage.getORDER().getOBSERVATION_REQUEST().getOBR();

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
        //Controllare anche sti 2 che è tutto a merda mi sa
        obrSegmentIntegrate.getResultCopiesTo(0).getGivenName().setValue(omlMessage.getORDER().getORC().getOrderingFacilityName(0).getOrganizationName().getValue());
        obrSegmentIntegrate.getResultCopiesTo(0).getIDNumber().setValue(omlMessage.getORDER().getORC().getOrderingFacilityName(0).getOrganizationIdentifier().getValue());

        obrSegmentIntegrate.getDiagnosticServSectID().setValue(obrSource.getDiagnosticServSectID().getValue());
        for(int i = 0; i < obrSource.getFillerSupplementalServiceInformationReps(); i++) {
            obrSegmentIntegrate.getPlacerSupplementalServiceInformation(i).getIdentifier().setValue(omlMessage.getORDER().getOBSERVATION_REQUEST().getOBR().getPlacerSupplementalServiceInformation(0).getIdentifier().getValue());
            obrSegmentIntegrate.getPlacerSupplementalServiceInformation(i).getText().setValue(omlMessage.getORDER().getOBSERVATION_REQUEST().getOBR().getPlacerSupplementalServiceInformation(0).getText().getValue());
        }
    }

    public static void createOBXSegmentIntegrateOMLO21(OBX obxSegmentIntegrate, OML_O21 omlMessage) throws HL7Exception {

        obxSegmentIntegrate.getSetIDOBX().parse(omlMessage.getORDER().getOBSERVATION_REQUEST().getOBSERVATION().getOBX().getSetIDOBX().getValue());
        obxSegmentIntegrate.getValueType().setValue(omlMessage.getORDER().getOBSERVATION_REQUEST().getOBSERVATION().getOBX().getValueType().getValue());
        obxSegmentIntegrate.getObservationIdentifier().getIdentifier().setValue(omlMessage.getORDER().getOBSERVATION_REQUEST().getOBSERVATION().getOBX().getObservationIdentifier().getIdentifier().getValue());
        obxSegmentIntegrate.getObservationIdentifier().getText().setValue(omlMessage.getORDER().getOBSERVATION_REQUEST().getOBSERVATION().getOBX().getObservationIdentifier().getText().getValue());
        obxSegmentIntegrate.getObservationValue(0).parse(String.valueOf(omlMessage.getORDER().getOBSERVATION_REQUEST().getOBSERVATION().getOBX().getObservationValue(0).getData()));
        obxSegmentIntegrate.getObservationResultStatus().setValue(omlMessage.getORDER().getOBSERVATION_REQUEST().getOBSERVATION().getOBX().getObservationResultStatus().getValue());
    }

}
