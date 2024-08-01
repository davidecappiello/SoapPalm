package it.prometeo.HL7Palm.Decoding;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v25.message.OUL_R22;
import ca.uhn.hl7v2.parser.PipeParser;
import org.springframework.stereotype.Service;

import java.io.IOException;


public class OULDecoding {
    private final OUL_R22 newOUL= new OUL_R22();

    public OUL_R22 decodeOUL (String hl7) throws HL7Exception, IOException {

        PipeParser parser = new PipeParser();

        /*newOUL.initQuickstart("OUL", "R22", "P");

        MSH newMSHSegment = newOUL.getMSH();
        PID newPidSegment = newOUL.getPATIENT().getPID();
        PV1 newPv1Segment = newOUL.getVISIT().getPV1();
        SPM newSPM = newOUL.getSPECIMEN().getSPM();
        ORC newORCSegment = newOUL.getSPECIMEN().getORDER().getORC();
        TQ1 newTq1Segment = newOUL.getSPECIMEN().getORDER().getTIMING_QTY().getTQ1();
        OBR newObrSegment = newOUL.getSPECIMEN().getORDER().getOBR();
        OBX newObxSegment = newOUL.getSPECIMEN().getOBX();

        MSH mshSegment = oul.getMSH();
        newMSHSegment.getFieldSeparator().setValue(mshSegment.getFieldSeparator().getValue());
        newMSHSegment.getEncodingCharacters().setValue(mshSegment.getEncodingCharacters().getValue());
        newMSHSegment.getSendingApplication().getNamespaceID().setValue(mshSegment.getSendingApplication().getNamespaceID().getValue());
        newMSHSegment.getSendingFacility().getNamespaceID().setValue(mshSegment.getSendingFacility().getNamespaceID().getValue());
        newMSHSegment.getMsh5_ReceivingApplication().getNamespaceID().setValue(mshSegment.getMsh5_ReceivingApplication().getNamespaceID().getValue());
        newMSHSegment.getDateTimeOfMessage().getTime().setValue(mshSegment.getDateTimeOfMessage().getTime().getValue());
        newMSHSegment.getMessageType().getMessageCode().setValue(mshSegment.getMessageType().getMessageCode().getValue());
        newMSHSegment.getMessageType().getTriggerEvent().setValue(mshSegment.getMessageType().getTriggerEvent().getValue());
        newMSHSegment.getMessageType().getMessageStructure().setValue(mshSegment.getMessageType().getMessageStructure().getValue());
        newMSHSegment.getMessageControlID().setValue(mshSegment.getMessageControlID().getValue());
        newMSHSegment.getProcessingID().getProcessingID().setValue(mshSegment.getProcessingID().getProcessingID().getValue());
        newMSHSegment.getVersionID().getVersionID().setValue(mshSegment.getVersionID().getVersionID().getValue());

        PID pidSegment = oul.getPATIENT().getPID();
        newPidSegment.getPatientIdentifierList(0).getIDNumber().setValue(pidSegment.getPatientIdentifierList(0).getIDNumber().getValue());
        newPidSegment.getPatientIdentifierList(0).getAssigningAuthority().getNamespaceID().setValue(pidSegment.getPatientIdentifierList(0).getAssigningAuthority().getNamespaceID().getValue());
        newPidSegment.getPatientIdentifierList(0).getIdentifierTypeCode().setValue(pidSegment.getPatientIdentifierList(0).getIdentifierTypeCode().getValue());
        newPidSegment.getPatientName(0).getFamilyName().getSurname().setValue(pidSegment.getPatientName(0).getFamilyName().getSurname().getValue());
        newPidSegment.getPatientName(0).getGivenName().setValue(pidSegment.getPatientName(0).getGivenName().getValue());
        newPidSegment.getDateTimeOfBirth().getTime().setValue(pidSegment.getDateTimeOfBirth().getTime().getValue());
        newPidSegment.getAdministrativeSex().setValue(pidSegment.getAdministrativeSex().getValue());
        newPidSegment.getPatientAddress(0).getStreetAddress().getStreetName().setValue(pidSegment.getPatientAddress(0).getStreetAddress().getStreetName().getValue());
        newPidSegment.getPatientAddress(0).getCity().setValue(pidSegment.getPatientAddress(0).getCity().getValue());
        newPidSegment.getPatientAddress(0).getStateOrProvince().setValue(pidSegment.getPatientAddress(0).getStateOrProvince().getValue());
        newPidSegment.getPatientAddress(0).getZipOrPostalCode().setValue(pidSegment.getPatientAddress(0).getZipOrPostalCode().getValue());
        newPidSegment.getPatientAddress(0).getAddressType().setValue(pidSegment.getPatientAddress(0).getAddressType().getValue());
        newPidSegment.getPatientAddress(0).getCountyParishCode().setValue(pidSegment.getPatientAddress(0).getCountyParishCode().getValue());
        newPidSegment.getPhoneNumberBusiness(0).getTelecommunicationEquipmentType().setValue(pidSegment.getPhoneNumberBusiness(0).getTelecommunicationEquipmentType().getValue());
        newPidSegment.getPhoneNumberBusiness(0).getUnformattedTelephoneNumber().setValue(pidSegment.getPhoneNumberBusiness(0).getUnformattedTelephoneNumber().getValue());
        newPidSegment.getPhoneNumberBusiness(0).getTelecommunicationUseCode().setValue(pidSegment.getPhoneNumberBusiness(0).getTelecommunicationUseCode().getValue());
        newPidSegment.getPhoneNumberBusiness(0).getEmailAddress().setValue(pidSegment.getPhoneNumberBusiness(0).getEmailAddress().getValue());
        newPidSegment.getCitizenship(0).getIdentifier().setValue(pidSegment.getCitizenship(0).getIdentifier().getValue());
        newPidSegment.getCitizenship(0).getText().setValue(pidSegment.getCitizenship(0).getText().getValue());
        newPidSegment.getCitizenship(0).getNameOfCodingSystem().setValue(pidSegment.getCitizenship(0).getNameOfCodingSystem().getValue());

        PV1 pv1Segment = oul.getVISIT().getPV1();
        newPv1Segment.getPatientClass().setValue(pv1Segment.getPatientClass().getValue());
        newPv1Segment.getAssignedPatientLocation().getPointOfCare().setValue(pv1Segment.getAssignedPatientLocation().getPointOfCare().getValue());
        newPv1Segment.getAssignedPatientLocation().getFloor().setValue(pv1Segment.getAssignedPatientLocation().getFloor().getValue());
        newPv1Segment.getAssignedPatientLocation().getLocationDescription().setValue(pv1Segment.getAssignedPatientLocation().getLocationDescription().getValue());
        newPv1Segment.getVisitNumber().getIDNumber().setValue(pv1Segment.getVisitNumber().getIDNumber().getValue());
        newPv1Segment.getVisitNumber().getIdentifierTypeCode().setValue(pv1Segment.getVisitNumber().getIdentifierTypeCode().getValue());

        SPM spmSegment = oul.getSPECIMEN().getSPM();
        newSPM.getSetIDSPM().setValue(spmSegment.getSetIDSPM().getValue());
        newSPM.getSpecimenID().getPlacerAssignedIdentifier().getEntityIdentifier().setValue(spmSegment.getSpecimenID().getPlacerAssignedIdentifier().getEntityIdentifier().getValue());
        newSPM.getSpecimenID().getFillerAssignedIdentifier().getEntityIdentifier().setValue(spmSegment.getSpecimenID().getFillerAssignedIdentifier().getEntityIdentifier().getValue());

        newSPM.getSpecimenType().getIdentifier().setValue(spmSegment.getSpecimenType().getIdentifier().getValue());
        newSPM.getSpecimenType().getText().setValue(spmSegment.getSpecimenType().getText().getValue());
        newSPM.getSpecimenType().getNameOfCodingSystem().setValue(spmSegment.getSpecimenType().getNameOfCodingSystem().getValue());
        newSPM.getSpecimenCollectionDateTime().getRangeEndDateTime().getTime().setValue(spmSegment.getSpecimenCollectionDateTime().getRangeEndDateTime().getTime().getValue());



        ORC orcSegment = oul.getSPECIMEN().getORDER().getORC();
        newORCSegment.getOrderControl().setValue(orcSegment.getOrderControl().getValue());
        newORCSegment.getPlacerOrderNumber().getEntityIdentifier().setValue(orcSegment.getPlacerOrderNumber().getEntityIdentifier().getValue());
        newORCSegment.getPlacerOrderNumber().getNamespaceID().setValue(orcSegment.getPlacerOrderNumber().getNamespaceID().getValue());
        newORCSegment.getPlacerOrderNumber().getUniversalID().setValue(orcSegment.getPlacerOrderNumber().getUniversalID().getValue());
        newORCSegment.getPlacerOrderNumber().getUniversalIDType().setValue(orcSegment.getPlacerOrderNumber().getUniversalIDType().getValue());
        newORCSegment.getFillerOrderNumber().getEntityIdentifier().setValue(orcSegment.getFillerOrderNumber().getEntityIdentifier().getValue());
        newORCSegment.getPlacerOrderNumber().getNamespaceID().setValue(orcSegment.getPlacerOrderNumber().getNamespaceID().getValue());
        newORCSegment.getPlacerGroupNumber().getEntityIdentifier().setValue(orcSegment.getPlacerGroupNumber().getEntityIdentifier().getValue());
        newORCSegment.getOrderStatus().setValue(orcSegment.getOrderStatus().getValue());
        newORCSegment.getQuantityTiming(0).getStartDateTime().getTime().setValue(orcSegment.getQuantityTiming(0).getStartDateTime().getTime().getValue());
        newORCSegment.getDateTimeOfTransaction().getTime().setValue(orcSegment.getDateTimeOfTransaction().getTime().getValue());
        newORCSegment.getOrderingProvider(0).getIDNumber().setValue(orcSegment.getOrderingProvider(0).getIDNumber().getValue());
        newORCSegment.getOrderingProvider(0).getFamilyName().getSurname().setValue(orcSegment.getOrderingProvider(0).getFamilyName().getSurname().getValue());
        newORCSegment.getOrderingProvider(0).getGivenName().setValue(orcSegment.getOrderingProvider(0).getGivenName().getValue());
        newORCSegment.getOrderingFacilityName(0).getOrganizationName().setValue(orcSegment.getOrderingFacilityName(0).getOrganizationName().getValue());
        newORCSegment.getOrderingFacilityName(0).getOrganizationIdentifier().setValue(orcSegment.getOrderingFacilityName(0).getOrganizationIdentifier().getValue());

        TQ1 tq1Segment = oul.getSPECIMEN().getORDER().getTIMING_QTY().getTQ1();
        newTq1Segment.getPriority(0).getIdentifier().setValue(tq1Segment.getPriority(0).getIdentifier().getValue());
        newTq1Segment.getSetIDTQ1().setValue(tq1Segment.getSetIDTQ1().getValue());
        newTq1Segment.getStartDateTime().getTime().setValue(tq1Segment.getStartDateTime().getTime().getValue());
        newTq1Segment.getPriority(0).getIdentifier().setValue(tq1Segment.getPriority(0).getIdentifier().getValue());


        OBR obrSegment = oul.getSPECIMEN().getORDER().getOBR();
        newObrSegment.getSetIDOBR().setValue(obrSegment.getSetIDOBR().getValue());
        newObrSegment.getPlacerOrderNumber().getEntityIdentifier().setValue(obrSegment.getPlacerOrderNumber().getEntityIdentifier().getValue());
        newObrSegment.getFillerOrderNumber().getEntityIdentifier().setValue(obrSegment.getFillerOrderNumber().getEntityIdentifier().getValue());
        newObrSegment.getUniversalServiceIdentifier().getIdentifier().setValue(obrSegment.getUniversalServiceIdentifier().getIdentifier().getValue());
        newObrSegment.getUniversalServiceIdentifier().getText().setValue(obrSegment.getUniversalServiceIdentifier().getText().getValue());
        newObrSegment.getUniversalServiceIdentifier().getNameOfCodingSystem().setValue(obrSegment.getUniversalServiceIdentifier().getNameOfCodingSystem().getValue());
        newObrSegment.getUniversalServiceIdentifier().getNameOfCodingSystem().setValue(obrSegment.getRelevantClinicalInformation().getValue());
        newObrSegment.getObservationDateTime().getTime().setValue(obrSegment.getObservationDateTime().getTime().getValue());
        newObrSegment.getRelevantClinicalInformation().setValue(obrSegment.getRelevantClinicalInformation().getValue());
        newObrSegment.getOrderingProvider(0).getIDNumber().setValue(obrSegment.getOrderingProvider(0).getIDNumber().getValue());
        newObrSegment.getOrderingProvider(0).getFamilyName().getSurname().setValue(obrSegment.getOrderingProvider(0).getFamilyName().getSurname().getValue());
        newObrSegment.getOrderingProvider(0).getGivenName().setValue(obrSegment.getOrderingProvider(0).getGivenName().getValue());
        newObrSegment.getPlacerField2().setValue(obrSegment.getPlacerField2().getValue());
        newObrSegment.getDiagnosticServSectID().setValue(obrSegment.getDiagnosticServSectID().getValue());
        newObrSegment.getResultStatus().setValue(obrSegment.getResultStatus().getValue());
        newObrSegment.getQuantityTiming(0).getStartDateTime().getTime().setValue(obrSegment.getQuantityTiming(0).getStartDateTime().getTime().getValue());
        newObrSegment.getCollectorSComment(0).getIdentifier().setValue(obrSegment.getCollectorSComment(0).getIdentifier().getValue());
        newObrSegment.getCollectorSComment(0).getText().setValue(obrSegment.getCollectorSComment(0).getText().getValue());
        newObrSegment.getCollectorSComment(0).getNameOfCodingSystem().setValue(obrSegment.getCollectorSComment(0).getNameOfCodingSystem().getValue());
        newObrSegment.getCollectorSComment(0).getAlternateIdentifier().setValue(obrSegment.getCollectorSComment(0).getAlternateIdentifier().getValue());
        newObrSegment.getCollectorSComment(0).getAlternateText().setValue(obrSegment.getCollectorSComment(0).getAlternateText().getValue());
        newObrSegment.getCollectorSComment(0).getNameOfCodingSystem().setValue(obrSegment.getCollectorSComment(0).getNameOfCodingSystem().getValue());
        newObrSegment.getFillerSupplementalServiceInformation(0).getIdentifier().setValue(obrSegment.getFillerSupplementalServiceInformation(0).getIdentifier().getValue());


        OBX obxSegment = oul.getSPECIMEN().getOBX();
        newObxSegment.getSetIDOBX().setValue(obxSegment.getSetIDOBX().getValue());
        newObxSegment.getValueType().setValue(obxSegment.getValueType().getValue());
        newObxSegment.getObservationIdentifier().getIdentifier().setValue(obxSegment.getObservationIdentifier().getIdentifier().getValue());
        newObxSegment.getObservationIdentifier().getText().setValue(obxSegment.getObservationIdentifier().getText().getValue());
        newObxSegment.getObservationIdentifier().getNameOfCodingSystem().setValue(obxSegment.getObservationIdentifier().getNameOfCodingSystem().getValue());
        newObxSegment.getObservationIdentifier().getAlternateIdentifier().setValue(obxSegment.getObservationIdentifier().getAlternateIdentifier().getValue());
        newObxSegment.getObservationIdentifier().getAlternateText().setValue(obxSegment.getObservationIdentifier().getAlternateText().getValue());
        newObxSegment.getObservationIdentifier().getNameOfAlternateCodingSystem().setValue(obxSegment.getObservationIdentifier().getNameOfAlternateCodingSystem().getValue());
        newObxSegment.getObservationValue(0).setData(obxSegment.getObservationValue(0).getData());
        newObxSegment.getUnits().getIdentifier().setValue(obxSegment.getUnits().getIdentifier().getValue());
        newObxSegment.getReferencesRange().setValue(obxSegment.getReferencesRange().getValue());
        newObxSegment.getAbnormalFlags(0).setValue(obxSegment.getAbnormalFlags(0).getValue());
        newObxSegment.getObservationResultStatus().setValue(obxSegment.getObservationResultStatus().getValue());
        newObxSegment.getDateTimeOfTheObservation().getTime().setValue(obxSegment.getDateTimeOfTheObservation().getTime().getValue());
        newObxSegment.getResponsibleObserver(0).getIDNumber().setValue(obxSegment.getResponsibleObserver(0).getIDNumber().getValue());
        newObxSegment.getResponsibleObserver(0).getFamilyName().getSurname().setValue(obxSegment.getResponsibleObserver(0).getFamilyName().getSurname().getValue());
        newObxSegment.getResponsibleObserver(0).getGivenName().setValue(obxSegment.getResponsibleObserver(0).getGivenName().getValue());
        newObxSegment.getObservationMethod(0).getIdentifier().setValue(obxSegment.getObservationMethod(0).getIdentifier().getValue());
        newObxSegment.getObservationMethod(0).getText().setValue(obxSegment.getObservationMethod(0).getText().getValue());
        newObxSegment.getObservationMethod(0).getNameOfCodingSystem().setValue(obxSegment.getObservationMethod(0).getNameOfCodingSystem().getValue());
        newObxSegment.getEquipmentInstanceIdentifier(0).getEntityIdentifier().setValue(obxSegment.getEquipmentInstanceIdentifier(0).getEntityIdentifier().getValue());


        Parser parser = new PipeParser();
        String pipeEncoded = parser.encode(newOUL);

        System.out.println("parser: " + pipeEncoded);

        XMLParser xml = new DefaultXMLParser();
        String xmlEncoded = xml.encode(newOUL);

        System.out.println("xml: " + xmlEncoded);*/

        return (OUL_R22) parser.parse(hl7);
    }

}
